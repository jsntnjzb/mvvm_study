package com.example.mvvm_study.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.mvvm_study.Utils.NetUtils;
import com.example.mvvm_study.http.entities.BaseResponse;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/4/13 10:20
 * @描述
 */
public abstract class MyBaseViewModel extends BaseViewModel {
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        ABSENT.setValue(null);
    }
    LiveData<BaseResponse> mBaseLiveData;
    public LiveData<BaseResponse> getBaseLiveData() {
        return mBaseLiveData;
    }
    public  boolean    isConnected;

    public MyBaseViewModel(@NonNull Application application) {
        super(application);
        mBaseLiveData =  Transformations.switchMap(NetUtils.netConnected(application),new Function<Boolean, LiveData<BaseResponse>>() {
            @Override
            public LiveData<BaseResponse> apply(Boolean connected) {
                if(!connected){
                    Log.d("MyBaseViewModel","网络未连接");
                    return ABSENT;
                }
                Log.d("MyBaseViewModel","网络已连接");
                MutableLiveData<BaseResponse> applyData = new MutableLiveData<>();
                BaseResponse mBaseResponse = new BaseResponse();
                mBaseResponse.isConnected = true;
                applyData. setValue(mBaseResponse);
                return applyData;
            }
        });
    }
}
