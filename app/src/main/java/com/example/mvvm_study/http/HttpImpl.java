package com.example.mvvm_study.http;

import android.content.Context;
import android.os.Build;
import android.os.Message;
import android.util.ArrayMap;

import androidx.annotation.RequiresApi;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.mvvm_study.Utils.NetUtils;
import com.example.mvvm_study.http.entities.BaseResponse;
import com.example.mvvm_study.http.service.HttpInterface;
import com.example.mvvm_study.http.service.OnBaseListener;
import com.example.mvvm_study.ui.LoginActivity;

import java.lang.ref.WeakReference;


public class HttpImpl implements HttpInterface {
    HttpSubscribe          httpSubscribe;
    WeakReference<Context> contextWeakReference;
    MutableLiveData<BaseResponse> mutableLiveData = new MutableLiveData<>();
    {
        mutableLiveData.setValue(null);
    }

    public HttpImpl(WeakReference<Context> contextWeakReference,MutableLiveData<BaseResponse> liveData) {
        httpSubscribe = new HttpSubscribe();
        this.contextWeakReference = contextWeakReference;
        this.mutableLiveData = liveData;
//        mWarningDialog = new WarningDialog(contextWeakReference.get(), R.layout.warning_dialog,R.style.WarningDialog);
    }

    /**
     * 登录
     * @param deviceCode 设备号
     * @param password   密码
     */
    @Override
    public void Login(final String deviceCode, final String password) {
        Transformations.switchMap(NetUtils.netConnected(contextWeakReference.get()),new Function<Boolean, LiveData<BaseResponse>>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public LiveData<BaseResponse> apply(Boolean connected) {
                if(!connected){
                    return mutableLiveData;
                }
                ArrayMap<String,String> map = new ArrayMap<>();
                map.put("deviceCode", deviceCode);
                map.put("password", password);
                httpSubscribe.login(map,new OnSuccessAndFault(new OnBaseListener<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse result) {
                        mutableLiveData.setValue(result);
                    }

                    @Override
                    public void onFault(BaseResponse result) {
                        mutableLiveData.setValue(result);
                    }
                }));
                return mutableLiveData;
            }
        });
    }
}
