package com.example.mvvm_study.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvvm_study.R;
import com.example.mvvm_study.Utils.ConstUtils;
import com.example.mvvm_study.Utils.DisplayUtil;
import com.example.mvvm_study.databinding.AdminHeadLayoutBinding;

import java.text.DecimalFormat;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.viewadapter.view.ViewAdapter;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/3/16 16:19
 * @描述 管理设置页面头部
 */
public class Admin_Head_Layout extends ConstraintLayout {
    private Context mContext;
    ImageButton btn_switch_mode, btn_back;
    TextView tv_title;
    boolean  isShowBack                              = false;
    String   title;
    int      childWidth, childHeight, setting_height = 0;
    private OnBackListener   mOnBackListener;
    private OnSwitchListener mOnSwitchListener;

    public void setOnBackListener(OnBackListener onBackListener) {
        mOnBackListener = onBackListener;
    }

    public void setOnSwitchListener(OnSwitchListener onSwitchListener) {
        mOnSwitchListener = onSwitchListener;
    }

    private interface OnBackListener {
        void back();
    }

    private interface OnSwitchListener {
        void switchMode();
    }

    public Admin_Head_Layout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Admin_Head_Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        //initAttr(attrs);
        initView();
    }

    public Admin_Head_Layout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        //initAttr(attrs);
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
        //获取屏幕尺寸
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
        LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AdminHeadLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.admin_head_layout, this, true);
        binding.setVariable(R.layout.admin_head_layout,null);
        btn_switch_mode = binding.imgBtnSwitchMode;
        btn_back = binding.imgBtnBack;
        tv_title = binding.tvTitle;
        tv_title.setText(title);
        if (isShowBack) {
            btn_back.setVisibility(View.VISIBLE);
        } else {
            btn_back.setVisibility(View.GONE);
        }
        ViewAdapter.onClickCommand(btn_back,back_ClickCommand,false);
        ViewAdapter.onClickCommand(btn_switch_mode,switch_mode_ClickCommand,false);
    }

    /**
     * 绑定标题
     * */
    @BindingAdapter("android:text")
    public static void setText(TextView textView, String text) {
        textView.setText(text);
    }

    //回退
    private BindingCommand back_ClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
           // getUC().getFinishLiveData().setValue(true);
            //finish();
        }
    });
    //切换模式
    private BindingCommand switch_mode_ClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            // getUC().getFinishLiveData().setValue(true);
            //finish();
            ToastUtils.showLong("切换模式!!!");
        }
    });

    //    public class BaseHeader extends FrameLayout {
    //          private BaseHeaderBinding mBinding;
    //          public BaseHeader(@NonNull Context context) { super(context); initView(context); }
    //          public BaseHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
    //              super(context, attrs); initView(context);
    //          }
    //
    //          public BaseHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    //              super(context, attrs, defStyleAttr); initView(context);
    //          }
    //
    //          @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    //          public BaseHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    //              super(context, attrs, defStyleAttr, defStyleRes);
    //              initView(context);
    //          }
    //          private void initView(Context context){
    //              LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    //              mBinding= DataBindingUtil.inflate(inflater,R.layout.base_header,null,false);
    //              mBinding.tvBack.setOnClickListener(new OnClickEvent() {
    //                  @Override
    //                  public void singleClick(View v) {
    //                      ((Activity)v.getContext()).finish(); } });
    //              addView(mBinding.getRoot());
    //          } }
}
