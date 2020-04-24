package com.example.mvvm_study.ui;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mvvm_study.BR;
import com.example.mvvm_study.R;
import com.example.mvvm_study.base.ARouterPath;
import com.example.mvvm_study.databinding.ActivityAdminMenuBinding;
import com.example.mvvm_study.liveData.NetworkLiveData;
import com.example.mvvm_study.viewModel.AdminMenuViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/16 9:25
 * @描述 管理员模式activity
 */

@Route(path = ARouterPath.AdminMenuAty)
public class AdminMenuActivity extends BaseActivity<ActivityAdminMenuBinding, AdminMenuViewModel> {
    Observer<String> mNetObserver;
    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_admin_menu;
    }

    @Override
    public int initVariableId() {
        return BR.adminMenu;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        mNetObserver = new Observer<String>() {
            @Override
            public void onChanged(String message) {
                if(!TextUtils.isEmpty(message)){
                   // mWarningDialog.show(false,message);
                    //mLoginViewModel.isConnected = false;
                }else {
                    //mLoginViewModel.isConnected = true;
                }
            }
        };
        //网络监测回调
        NetworkLiveData.getInstance(this).observe(this,mNetObserver);
    }
}
