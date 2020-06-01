package com.example.mvvm_study.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/13 10:20
 * @描述
 */
public abstract class MyBaseViewModel extends BaseViewModel {
    public boolean isConnected = true;

    public MyBaseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
