package com.example.mvvm_study.http;

import android.content.Context;

import com.example.mvvm_study.R;
import com.example.mvvm_study.http.entities.BaseResponse;
import com.example.mvvm_study.http.service.OnBaseListener;


public class OnSuccessAndFault<T> extends OnBaseSuccessAndFault<BaseResponse<T>> {
    /**
     * 是否需要显示默认Loading
     */
    private boolean     showProgress = false;
    private Context     mContext;
//    private LoadingView mLoadingView;
    private String showMsg = "正在加载...";

    /**
     * @param mOnBaseListener 成功回调监听
     */
    public OnSuccessAndFault(OnBaseListener<BaseResponse<T>> mOnBaseListener) {
        super(mOnBaseListener);
    }

    /**
     * @param mOnBaseListener 成功回调监听
     * @param context         上下文
     */
    public OnSuccessAndFault(OnBaseListener<BaseResponse<T>> mOnBaseListener, Context context) {
        super(mOnBaseListener);
        this.mContext = context;
    }

    /**
     * @param mOnBaseListener 成功回调监听
     * @param context         上下文
     * @param showProgress    是否需要显示默认Loading
     */
    public OnSuccessAndFault(OnBaseListener<BaseResponse<T>> mOnBaseListener, Context context, boolean showProgress) {
        super(mOnBaseListener);
        this.mContext = context;
        this.showProgress = showProgress;
    }


    private void showProgressDialog() {
//        if (showProgress) {
//            if(mLoadingView==null){
//                mLoadingView = new LoadingView(mContext, R.layout.loading_view,R.style.shoppingCartDialog);
//            }
//            mLoadingView.show("正在加载...");
//        }
    }


    private void dismissProgressDialog() {
//        if(showProgress){
//            if(mLoadingView.isShowing()){
//                mLoadingView.dismiss();
//                mLoadingView = null;
//            }
//        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissProgressDialog();
        if (!this.isDisposed()) {
            this.dispose();
        }
    }

    /**
     * 回调
     */
    @Override
    public void onNext(BaseResponse<T> response) {
        if (response.code == 200) {
           mOnBaseListener.onSuccess(response);
        } else {
            mOnBaseListener.onFault(response);
        }
    }
}
