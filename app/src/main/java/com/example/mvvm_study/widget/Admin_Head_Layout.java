package com.example.mvvm_study.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mvvm_study.R;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/3/16 16:19
 * @描述 管理设置页面头部
 */
public class Admin_Head_Layout extends ConstraintLayout implements View.OnClickListener{
    private Context mContext;
    ImageButton btn_switch_mode,btn_back;
    TextView tv_title;
    boolean isShowBack = false;
    String title;

    public Admin_Head_Layout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public Admin_Head_Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initAttr(attrs);
        initView();
    }

    public Admin_Head_Layout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs);
        initView();
    }

    private void initAttr(AttributeSet attrs){
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.head_layout_style);
        isShowBack = ta.getBoolean(R.styleable.head_layout_style_isShowBAck,false);
        title = ta.getString(R.styleable.head_layout_style_title);
        ta.recycle();
    }

    private void initView(){
        View mView = LayoutInflater.from(mContext).inflate(R.layout.admin_head_layout,this,true);
//        btn_switch_mode = mView.findViewById(R.id.img_btn_switch_mode);
//        btn_switch_mode.setOnClickListener(this);
//        btn_back = mView.findViewById(R.id.img_btn_back);
//        btn_back.setOnClickListener(this);
//        tv_title = mView.findViewById(R.id.tv_title);
//        tv_title.setText(title);
//        if(isShowBack){
//            btn_back.setVisibility(View.VISIBLE);
//        }else{
//            btn_back.setVisibility(View.GONE);
//        }
    }

    @Override
    public void onClick(View v) {
//        Activity activity;
//        switch (v.getId()){
//            case R.id.img_btn_switch_mode:
//                activity = (Activity)mContext;
//                UiUtils.startNewActivity(mContext, MainActivity.class,0);
//                activity.finish();
//                break;
//            case R.id.img_btn_back:
//                activity = (Activity)mContext;
//                activity.finish();
//                break;
//        }
    }
}
