package com.example.mvvm_study.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/22 17:25
 * @描述
 */
public class ForegroundService extends Service {
    private static final String        TAG = "ForegroundService";
    private static final String ID = "channel_1";
    private static final String NAME = "前台服务";
    // 启动notification的id，两次启动应是同一个id
    private final static int NOTIFICATION_ID = android.os.Process.myPid();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG,"ForegroundService  onCreate");
        super.onCreate();
//        initData();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
