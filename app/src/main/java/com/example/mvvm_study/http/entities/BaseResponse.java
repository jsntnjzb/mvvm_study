package com.example.mvvm_study.http.entities;

import java.io.Serializable;

/**
 * 网络请求结果 基类
 */
public class BaseResponse<T> implements Serializable {
    public String  message;
    public int     code;
    public boolean isConnected;
    public T       data;
}
