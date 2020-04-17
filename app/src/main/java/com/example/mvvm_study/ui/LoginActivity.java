package com.example.mvvm_study.ui;

import android.Manifest;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mvvm_study.BR;
import com.example.mvvm_study.broadcastReceiver.NetworkReceiver;
import com.example.mvvm_study.R;
import com.example.mvvm_study.base.ARouterPath;
import com.example.mvvm_study.databinding.ActivityLoginBinding;
import com.example.mvvm_study.http.entities.BaseResponse;
import com.example.mvvm_study.liveData.NetworkLiveData;
import com.example.mvvm_study.viewModel.LoginViewModel;
import com.example.mvvm_study.widget.WarningDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.SPUtils;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>{
    LoginViewModel mLoginViewModel;
    WarningDialog mWarningDialog;
    Observer<String> mObserver;

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
        //动态权限申请
        if(mLoginViewModel.showCheckPermissions()){
            rxLocationPermissionRequest();
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        //监听错误消息
        mObserver = new Observer<String>() {
            @Override
            public void onChanged(String message) {
                mWarningDialog.show(false,message);
                //网络是否连接
                mLoginViewModel.isConnected = NetworkLiveData.getInstance(LoginActivity.this).isNetConnected();
//                if(baseResponse!=null){
//                    if(!TextUtils.isEmpty(baseResponse.message)){
//                        mWarningDialog.show(false,baseResponse.message);
//                    }
//                    if(baseResponse.code==200){
//                        //保存设备号,密码
//                        SPUtils.getInstance().put("equipmentId",mLoginViewModel.userName.get());
//                        SPUtils.getInstance().put("pwd",mLoginViewModel.passWord.get());
//                        //开启service
//
//                        //登录成功,跳转至管理员模式activity
//                        ARouter.getInstance().build(ARouterPath.AdminMenuAty).navigation();
//                        AppManager.getAppManager().finishActivity(LoginActivity.this);
//                        finish();
//                    }
//                }
            }
        };
        mLoginViewModel.mLiveData.observe(this,mObserver);
        //网络监测
        NetworkLiveData.getInstance(this).observe(this,mObserver);
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
        mLoginViewModel.mLiveData.removeObserver(mObserver);
        mWarningDialog.dismiss();
        mWarningDialog = null;
    }
}
