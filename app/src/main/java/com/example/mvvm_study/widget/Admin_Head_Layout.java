package com.example.mvvm_study.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.mvvm_study.R;
import com.example.mvvm_study.Utils.ConstUtils;
import com.example.mvvm_study.Utils.DisplayUtil;
import com.example.mvvm_study.ui.MainActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.text.DecimalFormat;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/3/16 16:19
 * @描述 管理设置页面头部
 */
public class Admin_Head_Layout extends ConstraintLayout implements View.OnClickListener {
    private Context mContext;
    ImageButton btn_switch_mode, btn_back;
    TextView tv_title;
    boolean isShowBack = false;
    String title;
    int childWidth, childHeight, setting_height = 0;
    private OnBackListener   mOnBackListener;
    private OnSwitchListener mOnSwitchListener;

    public void setOnBackListener(OnBackListener onBackListener) {
        mOnBackListener = onBackListener;
    }

    public void setOnSwitchListener(OnSwitchListener onSwitchListener) {
        mOnSwitchListener = onSwitchListener;
    }

    private interface OnBackListener{
        void back();
    }

    private interface OnSwitchListener{
        void switchMode();
    }

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

    private void initAttr(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.head_layout_style);
        isShowBack = ta.getBoolean(R.styleable.head_layout_style_isShowBAck, false);
        title = ta.getString(R.styleable.head_layout_style_title);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //测量所有子View
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //        //获取屏幕尺寸
        int[] sizes = DisplayUtil.getScreenRelatedInformation(mContext);
        DecimalFormat df = new DecimalFormat("0");
        setting_height = Integer.valueOf(df.format(sizes[1] * ConstUtils.new_admin_head_height_p));
        setMeasuredDimension(widthSize, setting_height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        View child;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                left = child.getLeft();
                childWidth = child.getMeasuredWidth();
                childHeight = child.getMeasuredHeight();
                child.layout(left, (setting_height - childHeight) / 2, left + childWidth, setting_height - child.getBottom());
                left += childWidth;
            }
        }
    }

    private void initView() {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.admin_head_layout, this, true);
        btn_switch_mode = mView.findViewById(R.id.img_btn_switch_mode);
        btn_switch_mode.setOnClickListener(this);
        btn_back = mView.findViewById(R.id.img_btn_back);
        btn_back.setOnClickListener(this);
        tv_title = mView.findViewById(R.id.tv_title);
        tv_title.setText(title);
        if (isShowBack) {
            btn_back.setVisibility(View.VISIBLE);
        } else {
            btn_back.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_switch_mode:
                if(mOnSwitchListener!=null){
                    mOnSwitchListener.switchMode();
                }
                break;
            case R.id.img_btn_back:
                if(mOnBackListener!=null){
                    mOnBackListener.back();
                }
                break;
        }
    }
}
