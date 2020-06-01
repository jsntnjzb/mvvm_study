package com.example.mvvm_study.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.example.mvvm_study.R;
import com.example.mvvm_study.Utils.ConstUtils;
import com.example.mvvm_study.Utils.UIRunner;
import com.example.mvvm_study.http.HttpImpl;
import com.jeremyliao.liveeventbus.utils.AppUtils;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.StringUtils;

/**
 * 启动界面
 */
public class StartActivity extends Activity {
    HttpImpl impl;
    public static Handler mHandler;
    private HandlerThread mHandlerThread;
    Intent intent;
    private void launchActivity(final Intent intent, long delayMs) {
//        UIRunner.runOnUI(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(intent);
//                finish();
//            }
//        }, delayMs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mHandlerThread == null) {
            mHandlerThread = new HandlerThread("StartActivity");
        }
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
//                switch (msg.what){
//                    case ConstUtils.AUTOLOGINSUCCESS:
//                        /**
//                         * 判断service是否存活
//                         * */
//                        boolean isServiceRunning = AppUtils.isServiceRunning(StartActivity.this, "com.njrhzn.ew.kiosk.Service.ForegroundService");
//                        intent = new Intent(StartActivity.this, MainActivity.class);
//                        if (isServiceRunning) {
//                            intent.setAction(ConstUtils.FOREGROUNDSERVICEISRUNNING);
//                        } else {
//                            intent.setAction(ConstUtils.FOREGROUNDSERVICEISNOTRUNNING);
//                        }
//                        launchActivity(intent, 2000);
//                        break;
//                    case ConstUtils.AUTOLOGINFAILED:
//                        //跳转到登录页
//                        intent = new Intent(StartActivity.this, LoginActivity.class);
//                        launchActivity(intent,2000);
//                        break;
//                }
                return false;
            }
        });

        //判断是否已经登录过
        final String equipmentId = SPUtils.getInstance().getString("equipmentId","");
        final String pwd = SPUtils.getInstance().getString("pwd","");

        if(!StringUtils.isTrimEmpty(equipmentId) && !StringUtils.isTrimEmpty(pwd)){
            //自动登录
            impl = new HttpImpl(new WeakReference<Context>(this));
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                  //  impl.appStartAutoLogin(equipmentId,pwd);
                }
            });
        }else {
            //登陆页
            launchActivity(null,2000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);//动画效果
        mHandlerThread.quit();
        mHandlerThread = null;
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
    }
}
