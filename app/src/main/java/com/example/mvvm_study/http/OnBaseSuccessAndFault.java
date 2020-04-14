package com.example.mvvm_study.http;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_study.Utils.JsonUtil;
import com.example.mvvm_study.http.entities.BaseResponse;
import com.example.mvvm_study.http.service.OnBaseListener;

import java.io.IOException;
import io.reactivex.observers.DisposableObserver;
import me.goldze.mvvmhabit.http.ExceptionHandle;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class OnBaseSuccessAndFault<T> extends DisposableObserver<T> {
    String                                                    msg;
    HttpException                                             httpException;
    Response                                                  response;
    ResponseBody                                              responseBody;
    ResponseThrowable                                         mResponseThrowable;
    int                                                       code;
    BaseResponse<T> baseResponse = new BaseResponse<>();
    OnBaseListener<T> mOnBaseListener;

    /**
     * @param mOnBaseListener 成功回调监听
     */
    public OnBaseSuccessAndFault(OnBaseListener<T> mOnBaseListener) {
        this.mOnBaseListener = mOnBaseListener;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }



    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        mResponseThrowable = ExceptionHandle.handleException(e);
        code = mResponseThrowable.code;
        if(code==1003){
            //Http Error
            httpException = (HttpException) e;
            //获取服务器返回message
            response = httpException.response();
            responseBody = response.errorBody();
            BaseResponse<T> response = null;
            try {
                msg = responseBody.string();
                response = JsonUtil.Str2JsonBean(msg,baseResponse.getClass());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            switch (httpException.code()){
                case 500:
                    if(responseBody!=null){
                        mOnBaseListener.onFault(response.data);
                    }
                    break;
                case 401:
                    //重新调用登录接口
                    mOnBaseListener.onFault(response.data);
                    break;
            }
        }else {
            mOnBaseListener.onFault(null);
        }
    }
}
