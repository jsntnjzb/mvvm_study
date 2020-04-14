package com.example.mvvm_study.http.service;

public interface OnBaseListener<T> {
    void onSuccess(T result);

    void onFault(T result);
}
