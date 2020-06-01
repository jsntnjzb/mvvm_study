package com.example.mvvm_study.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/22 18:21
 * @描述
 */
public class ServiceViewModel extends ViewModel {
    public MutableLiveData<String> mLiveData;

    /**
     * 判断是否是同一台设备登录
     */
    public boolean isSameDevice() {
        return false;
    }
}
