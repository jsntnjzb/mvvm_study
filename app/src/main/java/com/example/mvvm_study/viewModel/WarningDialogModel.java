package com.example.mvvm_study.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/13 18:42
 * @描述
 */
public class WarningDialogModel extends MyBaseViewModel {
    public WarningDialogModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onRestart() {

    }


    //图片的资源Id
    public ObservableField<Integer> iv_source = new ObservableField<>(-1);
    //显示消息
    public ObservableField<String>    msg = new ObservableField<>("");
}
