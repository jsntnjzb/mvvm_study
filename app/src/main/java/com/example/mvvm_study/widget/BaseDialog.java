package com.example.mvvm_study.widget;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/13 17:57
 * @描述
 */
public abstract class BaseDialog<V extends ViewDataBinding, VM extends BaseViewModel> extends Dialog {
    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
}
