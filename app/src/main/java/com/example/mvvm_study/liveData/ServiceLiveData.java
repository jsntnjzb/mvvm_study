package com.example.mvvm_study.liveData;

import android.content.Context;

import com.example.mvvm_study.Utils.ServiceUtils;

import androidx.lifecycle.LiveData;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/22 17:37
 * @描述 监测service状态数据存储
 */
public class ServiceLiveData extends LiveData {
    static        ServiceLiveData mServiceLiveData;
    private final Context         mContext;
    boolean isServiceRunning;//service是否正在运行

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
        isServiceRunning = ServiceUtils.isServiceRunning(mContext, "com.example.mvvm_study.service.ForegroundService");
        if (!isServiceRunning) {
            //启动service
            // ServiceUtils.startService();
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }
}
