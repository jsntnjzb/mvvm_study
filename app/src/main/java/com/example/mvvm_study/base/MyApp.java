package com.example.mvvm_study.base;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mvvm_study.R;
import com.example.mvvm_study.ui.MainActivity;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.crash.CaocConfig;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/11 15:35
 * @描述
 */
public class MyApp extends BaseApplication {
    //ARouter调试开关
    private boolean isDebugARouter = true;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(isDebugARouter){
            ARouter.openLog();
            ARouter.openDebug();
        }
        //初始化
        ARouter.init(this);

        //是否开启日志打印
        KLog.init(true);
        //配置全局异常崩溃操作
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(MainActivity.class) //重新启动后的activity
                //.errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
                //.eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
