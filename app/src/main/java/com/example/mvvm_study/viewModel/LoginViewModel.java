package com.example.mvvm_study.viewModel;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.example.mvvm_study.http.HttpImpl;
import com.example.mvvm_study.http.entities.BaseResponse;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
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
    Context  mContext;
    HttpImpl mHttp;
    public  MutableLiveData<String> mLiveData;
    //订阅者
    private Disposable              mSubscription;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mContext = getApplication().getApplicationContext();
        mHttp = new HttpImpl(new WeakReference(mContext));
        mLiveData = new MutableLiveData<>();
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
                        switch (response.code) {
                            case 200:
                                /**
                                 * 登录成功
                                 * */
                                mLiveData.setValue("");
                                break;
                            default:
                                //发送登录结果到view
                                mLiveData.setValue(response.message);
                                break;
                        }
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
        mSubscription.dispose();
        super.onDestroy();
    }

    @Override
    public void onCleared() {
        super.onCleared();
    }

    /**
     * 动态授权
     * 是否应该检查权限
     *
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
        @Override
        public void call() {
            if (!isConnected) {
                mLiveData.setValue("网络未连接");
            } else if (TextUtils.isEmpty(userName.get())) {
                mLiveData.setValue("请输入设备号");
            } else if (TextUtils.isEmpty(passWord.get())) {
                mLiveData.setValue("请输入密码");
            } else {
                //调用登录接口
                mHttp.Login(userName.get(), passWord.get(), false);
            }
        }
    });

    //取消按钮点击事件
    public BindingCommand cancelOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            getUC().getFinishLiveData().postValue(true);
            finish();
        }
    });
}
