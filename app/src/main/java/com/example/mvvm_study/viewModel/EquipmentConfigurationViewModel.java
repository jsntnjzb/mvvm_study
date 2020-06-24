package com.example.mvvm_study.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

/**
 * @创建者 Chuhui
 * @创建时间 2020/6/21 14:02
 * @描述
 */
public class EquipmentConfigurationViewModel extends MyBaseViewModel{
    public boolean isShowBAck;//是否显示back按钮
    public String title="管理员模式";//头部标题

    public EquipmentConfigurationViewModel(@NonNull Application application) {
        super(application);
    }
}
