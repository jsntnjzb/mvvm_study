package com.example.mvvm_study.Utils;

import android.os.Handler;
import android.os.Looper;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/13 16:22
 * @描述
 */
public class UIRunner {
    static Handler mHandler;

    public static void runOnUI(Runnable runnable) {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        mHandler.post(runnable);
    }
}
