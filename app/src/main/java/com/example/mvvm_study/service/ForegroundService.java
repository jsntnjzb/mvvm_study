package com.example.mvvm_study.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.example.mvvm_study.ui.MainActivity;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/22 17:25
 * @描述
 */
public class ForegroundService extends Service {
    private static final String TAG             = "ForegroundService";
    private static final String ID              = "channel_1";
    private static final String NAME            = "前台服务";
    // 启动notification的id，两次启动应是同一个id
    private final static int    NOTIFICATION_ID = android.os.Process.myPid();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void initData() {
        //开启位前台服务模式
        setForeground();
    }

    private void setForeground() {
        // 要注意的是android4.3之后Service.startForeground() 会强制弹出通知栏，解决办法是再
        // 启动一个service和推送共用一个通知栏，然后stop这个service使得通知栏消失。
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(NOTIFICATION_ID, getNotification());
            return;
        }
        startForeground(NOTIFICATION_ID, getNotification());
    }

    private Notification getNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext())
                .setContentTitle("服务运行于前台")
                .setContentText("ForegroundService被设为前台进程")
                .setTicker("ForegroundService正在后台运行...")
                .setPriority(Notification.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis())
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //修改安卓8.1以上系统报错
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(false);//如果使用中的设备支持通知灯，则说明此通知通道是否应显示灯
            notificationChannel.setShowBadge(false);//是否显示角标
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            manager.createNotificationChannel(notificationChannel);
            builder.setChannelId(ID);
        }

        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        return notification;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "ForegroundService  onCreate");
        super.onCreate();
        initData();
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
