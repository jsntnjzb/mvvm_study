package com.example.mvvm_study.viewModel;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_study.http.HttpImpl;
import com.example.mvvm_study.http.entities.BaseResponse;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/11 15:51
 * @描述 登录viewModel
 */
public class LoginViewModel extends MyBaseViewModel {
    Context                       mContext;
    public MutableLiveData<BaseResponse> mLiveData;
    HttpImpl                      mHttp;
    BaseResponse mBaseResponse;
    //订阅者
    private Disposable mSubscription;
    public LoginViewModel(@NonNull Application application){
        super(application);
        mContext = getApplication().getApplicationContext();
        mHttp = new HttpImpl(new WeakReference<Context>(mContext));
        mLiveData = new MutableLiveData<>();
        mBaseResponse = new BaseResponse();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    //注册RxBus
    @Override
    public void registerRxBus() {
        mSubscription = RxBus.getDefault().toObservable(BaseResponse.class)
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse response) throws Exception {

                        //mBaseResponse.message = s;
                        mLiveData.postValue(mBaseResponse);
                    }
                });
        //将订阅者加入管理站
        RxSubscriptions.add(mSubscription);
    }

    @Override
    public void removeRxBus() {
        //将订阅者从管理站中移除
        RxSubscriptions.remove(mSubscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCleared() {
        super.onCleared();

    }

    /**
     * 动态授权
     * 是否应该检查权限
     * @return
     */
    public boolean showCheckPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        } else {
            return false;
        }
    }


    //用户名的绑定
    public ObservableField<String> userName = new ObservableField("");
    //密码的绑定
    public ObservableField<String> passWord = new ObservableField<>("");

    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void call() {
            if(TextUtils.isEmpty(userName.get())){
                mBaseResponse.message = "请输入设备号";
                mLiveData.postValue(mBaseResponse);
            }else if(TextUtils.isEmpty(passWord.get())){
                mBaseResponse.message = "请输入密码";
                mLiveData.postValue(mBaseResponse);
            }else {
                if(!isConnected){
                    mBaseResponse.message = "请检查网络连接";
                    mLiveData.postValue(mBaseResponse);
                }else {
                    //调用登录接口
                    mHttp.Login(userName.get(),passWord.get());
                }
            }
        }
    });
}
