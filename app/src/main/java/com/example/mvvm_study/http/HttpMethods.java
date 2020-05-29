package com.example.mvvm_study.http;

import android.util.Log;

import com.example.mvvm_study.base.MyApp;
import com.example.mvvm_study.http.service.HttpApi;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.http.cookie.CookieJarImpl;
import me.goldze.mvvmhabit.http.cookie.store.PersistentCookieStore;
import okhttp3.Cookie;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpMethods {
    public               String      TAG                     = "HttpMethods";
    private static final int         DEFAULT_CONNECT_TIMEOUT = 30;
    private static final int         DEFAULT_WRITE_TIMEOUT   = 30;
    private static final int         DEFAULT_READ_TIMEOUT    = 30;
    private              Retrofit    retrofit;
    private              HttpApi     httpApi;
    private static       HttpMethods httpMethods;

    /**
     * 请求失败重连次数
     */
    private int                  RETRY_COUNT = 3;
    private OkHttpClient.Builder okHttpBuilder;
    CookieJarImpl mCookieJar;
    PersistentCookieStore mCookieStore;
    private String baseUrl = "http://47.97.100.152/";


    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        okHttpBuilder = new OkHttpClient.Builder();
        /**
         * 设置超时和重新连接
         */
        okHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        //错误重连
        okHttpBuilder.retryOnConnectionFailure(true);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG, "message " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        LoggingInterceptor mLoggingInterceptor = new LoggingInterceptor
//                .Builder()//构建者模式
//                .loggable(true) //是否开启日志打印
//                .setLevel(Level.BODY) //打印的等级
//                .log(Platform.INFO) // 打印类型
//                .request("Request") // request的Tag
//                .response("Response")// Response的Tag
//                .addHeader("version", BuildConfig.VERSION_NAME)//打印版本
//                .build();
        mCookieStore = new PersistentCookieStore(MyApp.getInstance());
        mCookieJar = new CookieJarImpl(mCookieStore);
        okHttpBuilder.addInterceptor(loggingInterceptor)
                .cookieJar(mCookieJar)//cookie管理
                .addInterceptor(new ReceivedCookiesInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())//json转换成JavaBean
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        httpApi = retrofit.create(HttpApi.class);
    }

    public class ReceivedCookiesInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//                HashSet<String> cookies = new HashSet<>();
//                for (String header : originalResponse.headers("Set-Cookie")) {
//                    cookies.add(header);
//                }
                //SPUtils.put(MyApp.getInstance(),"config",cookies);
               List<Cookie> cookieList = mCookieStore.getCookie(originalResponse.request().url());
            }
            return originalResponse;
        }
    }


    //获取单例
    public static HttpMethods getInstance() {
        if (httpMethods == null) {
            synchronized (HttpMethods.class) {
                if (httpMethods == null) {
                    httpMethods = new HttpMethods();
                }
            }
        }
        return httpMethods;
    }


    /**
     * 获取retrofit
     *
     * @return
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }


    /**
     * 获取httpService
     *
     * @return
     */
    public HttpApi getHttpApi() {
        return httpApi;
    }

    /**
     * 设置订阅 和 所在的线程环境
     */
    public <T> void toSubscribe(Observable<T> o, DisposableObserver<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_COUNT)//请求失败重连次数
                .subscribe(s);
    }
}
