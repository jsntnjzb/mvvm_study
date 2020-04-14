package com.example.mvvm_study.ui;

import android.Manifest;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvm_study.BR;
import com.example.mvvm_study.R;
import com.example.mvvm_study.databinding.ActivityLoginBinding;
import com.example.mvvm_study.http.entities.BaseResponse;
import com.example.mvvm_study.viewModel.LoginViewModel;
import com.example.mvvm_study.widget.WarningDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseActivity;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>{
    LoginViewModel mLoginViewModel;
    WarningDialog mWarningDialog;
    Observer<BaseResponse> mObserver;

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
        if(mLoginViewModel.showCheckPermissions()){
            rxLocationPermissionRequest();
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        mObserver = new Observer<BaseResponse>() {
            @Override
            public void onChanged(final BaseResponse baseResponse) {
                mWarningDialog.show(false,baseResponse.message);
            }
        };
        mLoginViewModel.getLiveData().observe(this,mObserver);
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
//        mLoginViewModel.getLiveData().removeObserver();
        mLoginViewModel.onCleared();
        mWarningDialog.dismiss();
        mWarningDialog = null;
    }

    //    private Button btn_login,btn_cancel;
//    private EditText        edit_password;
//    private SwipeBackLayout mSwipeBackLayout;
//    private              AlertDialog         alertDialog;
//    private              AlertDialog         mDialog;
//    EditText    edit_equipmentId;
//    HttpImpl impl;
//    String equipmentId,pwd;
//    public static Handler mHandler;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        checkPermission();
////        setSwipeBackEnable(true);
////        mSwipeBackLayout = getSwipeBackLayout();
////        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
//    }
//

//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==NOT_NOTICE){
//            //由于不知道是否选择了允许所以需要再次判断
//            checkPermission();
//        }
//    }
//
//    @Override
//    protected void afterServiceConnected(IBinder iBinder) {
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//    }
//
//    @Override
//    protected void initData() {
//        if(SPUtils.contains(LoginActivity.this,"equipmentId")){
//            equipmentId = String.valueOf(SPUtils.get(LoginActivity.this,"equipmentId",""));
//            edit_equipmentId.setText(equipmentId);
//        }
//        if(SPUtils.contains(LoginActivity.this,"pwd")){
//            pwd = String.valueOf(SPUtils.get(LoginActivity.this,"pwd",""));
//            edit_password.setText(pwd);
//        }
//        if(mHandler==null){
//            mHandler = new Handler(Looper.getMainLooper(),loginCallBack);
//        }
//        impl = new HttpImpl(new WeakReference<Context>(LoginActivity.this));
//    }
//
//    @Override
//    protected void initView() {
//        btn_login = findViewById(R.id.btn_login);
//        btn_login.setOnClickListener(this);
//        edit_password = findViewById(R.id.edit_password);
//        btn_cancel = findViewById(R.id.btn_cancel);
//        btn_cancel.setOnClickListener(this);
//        edit_equipmentId = findViewById(R.id.edit_equipmentId);
//        if(mWarningDialog==null){
//            mWarningDialog = new WarningDialog(this,R.layout.warning_dialog,R.style.WarningDialog);
//        }
//    }
//
//    @Override
//    protected void addActivity() {
//        AppManager.getAppManager().addActivity(this);
//    }
//
//    @Override
//    protected void finishActivity() {
//        AppManager.getAppManager().finishActivity(this);
//    }
//
//    @Override
//    protected int layoutId() {
//        return R.layout.activity_login;
//    }
//
//    @Override
//    protected void registerReceiver() {
//
//    }
//
//    @Override
//    protected void unregisterReceiver() {
//
//    }
//
//    @Override
//    protected void removeMessage() {
//        if(mHandler!=null){
//            mHandler.removeCallbacksAndMessages(null);
//            mHandler = null;
//        }
//    }
//
//    Handler.Callback loginCallBack = new Handler.Callback() {
//        @Override
//        public boolean handleMessage(@NonNull Message msg) {
//            switch (msg.what){
//                case ConstUtils.LOGINSUCCESSFUL:
//                    //登录成功
//                    btn_login.setEnabled(true);
//                    MyApp.getInstance().equipmentId = equipmentId;
////                    MyApp.getInstance().isLoginSuccess = true;
//                    SPUtils.put(LoginActivity.this,"equipmentId",equipmentId);
//                    SPUtils.put(LoginActivity.this,"pwd",pwd);
//                    LogUtils.d("登录成功");
//                    Intent intent = new Intent(LoginActivity.this,ForegroundService.class);
//                    intent.setAction(ConstUtils.LOGINSUCCESS);
//                    ForegroundService.startService(LoginActivity.this,intent);
//                    UiUtils.startNewActivity(LoginActivity.this,AdminMenuActivity.class,0);
//                    LoginActivity.this.finish();
//                    break;
//                case ConstUtils.LOGINFAILED:
//                    //弹出框提示错误信息
//                    btn_login.setEnabled(true);
//                    mWarningDialog.show(false,msg.obj.toString());
//                    break;
//                default:
//                    break;
//            }
//            return false;
//        }
//    };
//

//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_login:
//                equipmentId = edit_equipmentId.getText().toString().trim();
//                pwd = edit_password.getText().toString().trim();
//                if (equipmentId.length() <= 0) {
//                    mWarningDialog.show(false,"请输入设备号");
//                    return;
//                }
//                if (pwd.length() <= 0) {
//                    mWarningDialog.show(false,"请输入密码");
//                    return;
//                }
//                impl.Login(equipmentId, pwd);
//                break;
//            case R.id.btn_cancel:
//                finish();
//                break;
//        }
//    }
}
