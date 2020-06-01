package com.example.mvvm_study.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mvvm_study.BR;
import com.example.mvvm_study.R;
import com.example.mvvm_study.Utils.ConstUtils;
import com.example.mvvm_study.Utils.ServiceUtils;
import com.example.mvvm_study.base.ARouterPath;
import com.example.mvvm_study.databinding.ActivityLoginBinding;
import com.example.mvvm_study.liveData.NetworkLiveData;
import com.example.mvvm_study.liveData.ServiceLiveData;
import com.example.mvvm_study.service.ForegroundService;
import com.example.mvvm_study.viewModel.LoginViewModel;
import com.example.mvvm_study.widget.WarningDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.StringUtils;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>{
    LoginViewModel mLoginViewModel;
    WarningDialog mWarningDialog;
    Observer<String> mNetObserver,mLoginObserver;
    Observer<Boolean> cancelObserver;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.loginUser;
    }

    @Override
    public LoginViewModel initViewModel() {
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWarningDialog = new WarningDialog(this,R.layout.warning_dialog,R.style.WarningDialog);
    }


    @Override
    public void initData() {
        super.initData();
        AppManager.getAppManager().addActivity(this);
        //动态权限申请
        if(mLoginViewModel.showCheckPermissions()){
            rxLocationPermissionRequest();
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        mNetObserver = new Observer<String>() {
            @Override
            public void onChanged(String message) {
                if(!TextUtils.isEmpty(message)){
                    mWarningDialog.show(false,message);
                    mLoginViewModel.isConnected = false;
                }else {
                    mLoginViewModel.isConnected = true;
                }
            }
        };
        //网络监测回调
        NetworkLiveData.getInstance(this).observeForever(mNetObserver);

        mLoginObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!StringUtils.isTrimEmpty(s)){
                    mWarningDialog.show(false,s);
                }else {
                    //保存设备号,密码
                    SPUtils.getInstance().put("equipmentId",mLoginViewModel.userName.get());
                    SPUtils.getInstance().put("pwd",mLoginViewModel.passWord.get());
                    //开启service
                   // Intent intent = new Intent(LoginActivity.this, ForegroundService.class);
                   // intent.setAction(ConstUtils.LOGINSUCCESS);
                    //ServiceUtils.startService(LoginActivity.this,intent);
//                    ServiceLiveData.getInstance(LoginActivity.this).observeForever();

                    //登录成功,跳转至管理员模式activity
                    ARouter.getInstance().build(ARouterPath.AdminMenuAty).navigation();
                    AppManager.getAppManager().finishActivity(LoginActivity.this);
                }
            }
        };
        //登录结果监测回调
        mLoginViewModel.mLiveData.observe(this,mLoginObserver);

        //取消
        cancelObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    AppManager.getAppManager().finishActivity(LoginActivity.this);
                }
            }
        };
        mLoginViewModel.getUC().getFinishLiveData().observe(this,cancelObserver);
    }

    private void rxLocationPermissionRequest() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted) {
                    //授权

                } else {
                    // 权限被拒绝
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkLiveData.getInstance(this).removeObserver(mNetObserver);
        mWarningDialog.dismiss();
        mWarningDialog = null;
        AppManager.getAppManager().removeActivity(this);
    }
}
