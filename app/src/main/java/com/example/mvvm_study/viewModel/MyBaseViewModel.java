package com.example.mvvm_study.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.mvvm_study.Utils.ConstUtils;
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
//    public MutableLiveData<BaseResponse> getLiveData() {
//        return mLiveData;
//    }
//    MutableLiveData<BaseResponse> mLiveData;
    public  boolean    isConnected;

    public MyBaseViewModel(@NonNull Application application) {
        super(application);
        //监测每个activity的网络连接情况
//        mLiveData =  (MutableLiveData<BaseResponse>) Transformations.switchMap(NetUtils.netConnected(application),new Function<Boolean, LiveData<BaseResponse>>() {
//            @Override
//            public LiveData<BaseResponse> apply(Boolean connected) {
//                MutableLiveData<BaseResponse> applyData = new MutableLiveData<>();
//                BaseResponse mBaseResponse = new BaseResponse();
//                if(!connected){
//                    Log.d("MyBaseViewModel","网络未连接");
//                    mBaseResponse.code = ConstUtils.NETNOTCONNECTED;
//                    mBaseResponse.message = "网络未连接";
//                    isConnected = false;
//                }else {
//                    Log.d("MyBaseViewModel","网络已连接");
//                    mBaseResponse.code = ConstUtils.NETCONNECTED;
//                    isConnected = true;
//                }
//                applyData.setValue(mBaseResponse);
//                return applyData;
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
