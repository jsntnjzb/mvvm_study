package com.example.mvvm_study.http;

import com.example.mvvm_study.BuildConfig;
import com.example.mvvm_study.base.MyApp;
import com.example.mvvm_study.http.service.HttpApi;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.http.cookie.CookieJarImpl;
import me.goldze.mvvmhabit.http.cookie.store.PersistentCookieStore;
import me.goldze.mvvmhabit.http.interceptor.logging.Level;
import me.goldze.mvvmhabit.http.interceptor.logging.LoggingInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
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
        LoggingInterceptor mLoggingInterceptor = new LoggingInterceptor
                .Builder()//构建者模式
                .loggable(true) //是否开启日志打印
                .setLevel(Level.BODY) //打印的等级
                .log(Platform.INFO) // 打印类型
                .request("Request") // request的Tag
                .response("Response")// Response的Tag
                .addHeader("version", BuildConfig.VERSION_NAME)//打印版本
                .build();


        okHttpBuilder.addInterceptor(mLoggingInterceptor)
                .cookieJar(new CookieJarImpl(new PersistentCookieStore(MyApp.getInstance())))//cookie管理
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())//json转换成JavaBean
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        httpApi = retrofit.create(HttpApi.class);
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
