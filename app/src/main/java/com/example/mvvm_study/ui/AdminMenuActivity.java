package com.example.mvvm_study.ui;

import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mvvm_study.BR;
import com.example.mvvm_study.R;
import com.example.mvvm_study.base.ARouterPath;
import com.example.mvvm_study.databinding.ActivityAdminMenuBinding;
import com.example.mvvm_study.liveData.NetworkLiveData;
import com.example.mvvm_study.viewModel.AdminMenuViewModel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/16 9:25
 * @描述 管理员模式activity
 */

@Route(path = ARouterPath.AdminMenuAty)
public class AdminMenuActivity extends BaseActivity<ActivityAdminMenuBinding, AdminMenuViewModel> {
    Observer<String>   mNetObserver;
    Observer<Boolean>  cancelObserver;
    AdminMenuViewModel mAdminMenuViewModel;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_admin_menu;
    }

    @Override
    public int initVariableId() {
        return BR.adminMenu;
    }

    @Override
    public AdminMenuViewModel initViewModel() {
        mAdminMenuViewModel = ViewModelProviders.of(this).get(AdminMenuViewModel.class);
        return mAdminMenuViewModel;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        mNetObserver = new Observer<String>() {
            @Override
            public void onChanged(String message) {
                if (!TextUtils.isEmpty(message)) {
                    // mWarningDialog.show(false,message);
                    //mLoginViewModel.isConnected = false;
                } else {
                    //mLoginViewModel.isConnected = true;
                }
            }
        };
        //网络监测回调
        NetworkLiveData.getInstance(this).observe(this, mNetObserver);

        //取消
        cancelObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    AppManager.getAppManager().finishActivity(AdminMenuActivity.this);
                }
            }
        };
        mAdminMenuViewModel.getUC().getFinishLiveData().observe(this, cancelObserver);
    }
}
