package com.example.mvvm_study.event;

import android.view.View;

import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @创建者 Chuhui
 * @创建时间 2020/6/7 8:18
 * @描述
 */
public class CustomViewEvent {
    public OnBackListener mOnBackListener;

    public OnBackListener getOnBackListener() {
        return mOnBackListener;
    }


    /**
     * Admin_head_Layout自定义控件
     * 按钮点击回退事件
     */
    public class OnBackListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ToastUtils.showLong("后退了!!!");
        }
    }

}
