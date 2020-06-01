package com.example.mvvm_study.http;

import com.example.mvvm_study.http.entities.BaseResponse;

import androidx.collection.ArrayMap;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/14 15:23
 * @描述
 */
public class HttpSubscribe {
    HttpMethods httpMethods;

    public HttpSubscribe() {
        httpMethods = HttpMethods.getInstance();
    }

    /**
     * 登陆
     */
    public void login(ArrayMap<String, String> params, DisposableObserver<BaseResponse<Object>> subscriber) {
        Observable<BaseResponse<Object>> observable = httpMethods.getHttpApi().login(params);
        httpMethods.toSubscribe(observable, subscriber);
    }
}
