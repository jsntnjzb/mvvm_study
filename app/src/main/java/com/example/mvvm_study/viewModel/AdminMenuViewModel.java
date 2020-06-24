package com.example.mvvm_study.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/16 14:42
 * @描述
 */
public class AdminMenuViewModel extends MyBaseViewModel {
    public boolean isShowBAck;//是否显示back按钮
    public String title="管理员模式";//头部标题

    public AdminMenuViewModel(@NonNull Application application) {
        super(application);
    }

    //设备配置页点击事件
    public BindingCommand to_EquipmentConfiguration_ClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });

    //设备销量统计点击事件
    public BindingCommand to_EquipmentSales_ClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });
}
