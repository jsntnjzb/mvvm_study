package com.example.mvvm_study.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.example.mvvm_study.BR;
import com.example.mvvm_study.R;
import com.example.mvvm_study.VM.LoginViewModel;
import com.example.mvvm_study.databinding.ActivityLoginBinding;
import me.goldze.mvvmhabit.base.BaseActivity;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements View.OnClickListener {
    @Override
    public void onClick(View v) {

    }

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public LoginViewModel initViewModel() {
        return ViewModelProviders.of(this).get(LoginViewModel.class);
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
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case LOCATION_CODE: {
//                for (int i = 0; i < permissions.length; i++) {
//                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                        //选择了“始终允许”
//                    }else {
//                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])){
//                            //用户选择了禁止不再询问
//                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                            builder.setTitle("permission")
//                                    .setMessage("点击允许才可以使用我们的app哦")
//                                    .setPositiveButton("允许", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int id) {
//                                            if (mDialog != null && mDialog.isShowing()) {
//                                                mDialog.dismiss();
//                                            }
//                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                            Uri uri = Uri.fromParts("package", getPackageName(), null);//注意就是"package",不用改成自己的包名
//                                            intent.setData(uri);
//                                            startActivityForResult(intent, NOT_NOTICE);
//                                        }
//                                    });
//                            mDialog = builder.create();
//                            mDialog.setCanceledOnTouchOutside(false);
//                            mDialog.show();
//                        }else {
//                            //选择禁止
//                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                            builder.setTitle("permission")
//                                    .setMessage("点击允许才可以使用我们的app哦")
//                                    .setPositiveButton("允许", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int id) {
//                                            if (alertDialog != null && alertDialog.isShowing()) {
//                                                alertDialog.dismiss();
//                                            }
//                                            checkPermission();
//                                        }
//                                    });
//                            alertDialog = builder.create();
//                            alertDialog.setCanceledOnTouchOutside(false);
//                            alertDialog.show();
//                        }
//
//                    }
//                }
//            }
//        }
//    }
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
//    /**
//     * 获取权限（如果没有开启权限，会弹出对话框，询问是否开启权限）
//     * */
//    private void checkPermission(){
//        if(showCheckPermissions()){
//            //获取位置权限
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                //请求权限
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_CODE);
//            } else {
//                //已经获取到了权限
//                //打开位置监听
//
//            }
//        }
//    }
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
