package com.example.mvvm_study.liveData;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mvvm_study.Utils.ServiceUtils;
import com.example.mvvm_study.broadcastReceiver.NetworkReceiver;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/22 17:37
 * @描述 监测service状态数据存储
 */
public class ServiceLiveData extends LiveData {
    static               ServiceLiveData mServiceLiveData;
    private final        Context         mContext;
    boolean isServiceRunning;
    public ServiceLiveData(Context context) {
        mContext = context.getApplicationContext();
    }

    public static ServiceLiveData getInstance(Context context) {
        if (mServiceLiveData == null) {
            mServiceLiveData = new ServiceLiveData(context);
        }
        return mServiceLiveData;
    }

    @Override
    protected void onActive() {
        super.onActive();
        isServiceRunning = ServiceUtils.isServiceRunning(mContext,"com.example.mvvm_study.service.ForegroundService");
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }

    public static void isServiceRunning(){

    }
}
