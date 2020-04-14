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
    public MyBaseViewModel(@NonNull Application application) {
        super(application);
    }

//    public abstract void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

    public abstract void onRestart();

//    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);
}
