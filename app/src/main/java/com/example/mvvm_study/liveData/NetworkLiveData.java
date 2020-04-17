package com.example.mvvm_study.liveData;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mvvm_study.Utils.ConstUtils;
import com.example.mvvm_study.broadcastReceiver.NetworkReceiver;

import me.goldze.mvvmhabit.bus.RxBus;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/17 10:59
 * @描述 监测网络变化的数据存储器类
 */
public class NetworkLiveData extends LiveData<String> {
    private final        Context         mContext;
    static               NetworkLiveData mNetworkLiveData;
    private              NetworkReceiver mNetworkReceiver;
    private final        IntentFilter    mIntentFilter;
    private              String          tip_msg;//网络变化的提示消息
    private static final String          TAG = "NetworkLiveData";
    private boolean isNetConnected;//网络是否连接
    public boolean isNetConnected() {
        return isNetConnected;
    }

    public NetworkLiveData(Context context) {
        mContext = context.getApplicationContext();
        mNetworkReceiver = new NetworkReceiver();
        mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    public static NetworkLiveData getInstance(Context context) {
        if (mNetworkLiveData == null) {
            mNetworkLiveData = new NetworkLiveData(context);
        }
        return mNetworkLiveData;
    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.d(TAG, "onActive:");
        //注册广播
        mContext.registerReceiver(mNetworkReceiver, mIntentFilter);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.d(TAG, "onInactive: ");
        //反注册广播
        mContext.unregisterReceiver(mNetworkReceiver);
    }


    private class NetworkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conn.getActiveNetworkInfo();
            if (networkInfo == null) {
                tip_msg = "未接入网络";
            } else if (!networkInfo.isConnected()) {
                tip_msg = "网络未连接";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                //wifi
                tip_msg = "";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                //手机网络
                tip_msg = "";
            }
            if(!TextUtils.isEmpty(tip_msg)){
                isNetConnected = false;
                mNetworkLiveData.setValue(tip_msg);
            }else {
                isNetConnected = true;
            }
        }
    }
}
