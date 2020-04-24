package com.example.mvvm_study.http;

import android.content.Context;

import androidx.collection.ArrayMap;

import com.example.mvvm_study.http.entities.BaseResponse;
import com.example.mvvm_study.http.service.HttpInterface;
import com.example.mvvm_study.http.service.OnBaseListener;

import java.lang.ref.WeakReference;

import me.goldze.mvvmhabit.bus.RxBus;


public class HttpImpl implements HttpInterface {
    HttpSubscribe          httpSubscribe;
    WeakReference<Context> contextWeakReference;

    public HttpImpl(WeakReference<Context> contextWeakReference) {
        httpSubscribe = new HttpSubscribe();
        this.contextWeakReference = contextWeakReference;
//        mWarningDialog = new WarningDialog(contextWeakReference.get(), R.layout.warning_dialog,R.style.WarningDialog);
    }

    /**
     * 登录
     * @param deviceCode 设备号
     * @param password   密码
     */
    @Override
    public void Login(final String deviceCode, final String password) {
        final ArrayMap<String,String> map = new ArrayMap<>();
        map.put("deviceCode", deviceCode);
        map.put("password", password);
        httpSubscribe.login(map,new OnSuccessAndFault(new OnBaseListener<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse result) {
                //通知view
                RxBus.getDefault().post(result);
            }

            @Override
            public void onFault(BaseResponse response) {
                //通知view
                RxBus.getDefault().post(response);
            }
        }));
    }
}
