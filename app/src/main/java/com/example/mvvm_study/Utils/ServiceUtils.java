package com.example.mvvm_study.Utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/16 15:55
 * @描述 服务相关工具类
 */
public class ServiceUtils {
    /**
     * 启动service
     * @param mContent 上下文
     * */
    public static void startService(Context mContent, Intent intent){
        //8.0以上启动service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mContent.startForegroundService(intent);
        } else {
            mContent.startService(intent);
        }
    }

    /**
     * 判断服务是否开启
     * @param context 上下文
     * @param ServiceName 服务名
     * @return {@code true}:服务运行中 <br>{@code false}:服务已经停止
     */
    public static boolean isServiceRunning(Context context, String ServiceName) {
        if (TextUtils.isEmpty(ServiceName)) {
            return false;
        }
        ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(1000);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().equals(ServiceName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 停止服务
     *
     * @param context   上下文
     * @param className 完整包名的服务类名
     * @return {@code true}: 停止成功<br>{@code false}: 停止失败
     */
    public static boolean stopService(Context context, String className) {
        try {
            Intent intent = new Intent(context, Class.forName(className));
            return context.stopService(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
