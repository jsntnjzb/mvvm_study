package com.example.mvvm_study.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @创建者 Chuhui
 * @创建时间 2020/6/7 16:40
 * @描述
 */
class Admin_Head_Layout_ViewModel extends BaseViewModel {
    public Admin_Head_Layout_ViewModel(@NonNull Application application) {
        super(application);
    }

    //回退
    public BindingCommand back_ClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            getUC().getFinishLiveData().setValue(true);
            finish();
        }
    });
}
