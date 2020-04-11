package com.example.mvvm_study.VM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/11 15:51
 * @描述
 */
public class LoginViewModel extends BaseViewModel {
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> passWord = new ObservableField<>("");

    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });
}
