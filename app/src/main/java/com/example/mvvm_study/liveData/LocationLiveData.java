package com.example.mvvm_study.liveData;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.MainThread;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;

import com.apkfuns.logutils.LogUtils;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/16 17:13
 * @描述 监测当前位置信息的数据存储器类
 */
public class LocationLiveData extends LiveData<Location> {
    private static LocationLiveData sInstance;
    private        LocationManager  locationManager;
    private static Context          context;

    // GPS定位
    private final static String GPS_LOCATION = LocationManager.GPS_PROVIDER;
    // 网络定位
    private final static String NETWORK_LOCATION = LocationManager.NETWORK_PROVIDER;
    // 解码经纬度最大结果数目
    private final static int MAX_RESULTS = 1;
    // 时间更新间隔，单位：ms
    private final static long MIN_TIME = 1000;
    // 位置刷新距离，单位：m
    private final static float MIN_DISTANCE = (float) 0.01;


    @MainThread
    public static LocationLiveData get(Context ctxt) {
        if (sInstance == null) {
            context = ctxt;
            sInstance = new LocationLiveData(context.getApplicationContext());
        }
        return sInstance;
    }

    public LocationListener locationListener = new LocationListener() {
        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {

        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {

        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            setValue(location);
        }
    };

    private LocationLiveData(Context context) {
        locationManager = (LocationManager) context.getSystemService(
                Context.LOCATION_SERVICE);
    }

    @Override
    protected void onActive() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(NETWORK_LOCATION, MIN_TIME, MIN_DISTANCE, locationListener);
    }

    @Override
    protected void onInactive() {
        LogUtils.d("====onInactive===");
        locationManager.removeUpdates(locationListener);
    }
}
