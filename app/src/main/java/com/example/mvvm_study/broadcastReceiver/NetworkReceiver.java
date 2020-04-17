package com.example.mvvm_study.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.mvvm_study.Utils.ConstUtils;
import com.example.mvvm_study.http.entities.BaseResponse;
import com.example.mvvm_study.liveData.NetworkLiveData;

import me.goldze.mvvmhabit.bus.RxBus;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        BaseResponse baseResponse = new BaseResponse();
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();
        if(networkInfo==null){
            baseResponse.code = ConstUtils.HAVENONET;
            baseResponse.message = "未接入网络";
            baseResponse.isConnected = false;
            //RxBus.getDefault().post(baseResponse);
        }else if (!networkInfo.isConnected()) {
            //Toast.makeText(context, "请检查手机网络连接", Toast.LENGTH_SHORT).show();
            baseResponse.code = ConstUtils.NETNOTCONNECTED;
            baseResponse.message = "网络未连接";
            baseResponse.isConnected = false;
//            RxBus.getDefault().post(baseResponse);
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            //wifi
            baseResponse.code = ConstUtils.NETCONNECTED;
            baseResponse.isConnected = true;
//            RxBus.getDefault().post(baseResponse);
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            //手机网络
            baseResponse.code = ConstUtils.NETCONNECTED;
            baseResponse.isConnected = true;
//            RxBus.getDefault().post(baseResponse);
        }
    }
}
