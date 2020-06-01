package com.example.mvvm_study.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvvm_study.R;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/3/3 13:00
 * @描述 预警提示框
 */
public class WarningDialog extends Dialog {
    private int       layoutResID;//布局文件Id
    private View      mView;
    private TextView  tv_tip;
    private ImageView iv;
    Timer    timer;
    Activity activity;
    Context  mContext;
    public static final int DIALOG_SHOW_TIME_SHORT = 2000;

    public WarningDialog(@NonNull Context context) {
        super(context);
    }

    public WarningDialog(@NonNull Context context, int layoutResID, int themeResId) {
        super(context, themeResId);
        this.layoutResID = layoutResID;
        if (context == null) {
            return;
        }
        mContext = context;
        if (mContext instanceof Activity) {
            activity = (Activity) context;
        }


        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);//设置dialog显示居中
        mView = LayoutInflater.from(context).inflate(this.layoutResID, null);
        setContentView(mView);
        tv_tip = mView.findViewById(R.id.tv_msg);
        iv = mView.findViewById(R.id.iv);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        setCanceledOnTouchOutside(true);//点击外部Dialog消失
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    void showMsg(boolean isSuccess, String msg) {
        if (isSuccess) {
            iv.setImageResource(R.mipmap.load_success);
        } else {
            iv.setImageResource(R.mipmap.load_fail);
        }
        tv_tip.setText(msg);
    }

    public void show(boolean isSuccess, String msg) {
        if (activity != null && !activity.isFinishing()) {
            if (!isShowing()) {
                showMsg(isSuccess, msg);
                if (timer == null) {
                    timer = new Timer();
                }
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                }, DIALOG_SHOW_TIME_SHORT);
                super.show();
            }
        }
    }

    public void showErrorMsg(String msg) {
        if (mContext instanceof Activity) {
            if (activity != null) {
                if (!activity.isFinishing()) {
                    if (!isShowing()) {
                        showMsg(false, msg);
                    }
                }
            }
        } else {
            if (!isShowing()) {
                showMsg(false, msg);
            }
        }
        super.show();
    }

    public void dismiss() {
        if (isShowing()) {
            super.dismiss();
        }
    }

}
