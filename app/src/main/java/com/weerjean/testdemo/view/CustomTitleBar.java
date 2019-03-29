package com.weerjean.testdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.weerjean.testdemo.R;


/**
 * Created by : weiwenjie986 on 18/12/18 下午4:49.
 * Description :自定义Titlebar
 */

public class CustomTitleBar extends RelativeLayout {

    private ImageView ivLeft;
    private TextView tvTitle;
    private TextView tvRight;
    private ImageView ivRight;


    public CustomTitleBar(Context context) {
        super(context);
        initView(context,null);
    }

    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    //初始化视图
    private void initView(final Context context, AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this);
        ivLeft = (ImageView) inflate.findViewById(R.id.iv_left);
        tvTitle = (TextView) inflate.findViewById(R.id.tv_title);
        tvRight = (TextView) inflate.findViewById(R.id.tv_right);
        ivRight = (ImageView) inflate.findViewById(R.id.iv_right);

        init(context,attributeSet);
    }

    //初始化资源文件
    public void init(Context context, AttributeSet attributeSet){
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTitleBar);
        String title = typedArray.getString(R.styleable.CustomTitleBar_title);//标题
        int leftIcon = typedArray.getResourceId(R.styleable.CustomTitleBar_left_icon, R.mipmap.ic_back);//左边图片
        int rightIcon = typedArray.getResourceId(R.styleable.CustomTitleBar_right_icon, R.mipmap.more);//右边图片
        String rightText = typedArray.getString(R.styleable.CustomTitleBar_right_text);//右边文字
        int titleBarType = typedArray.getInt(R.styleable.CustomTitleBar_titlebar_type, 10);//标题栏类型,默认为10

        //赋值进去我们的标题栏
        tvTitle.setText(title);
        ivLeft.setImageResource(leftIcon);
        tvRight.setText(rightText);
        ivRight.setImageResource(rightIcon);

        //可以传入type值,可自定义判断值
        if(titleBarType == 10){//不传入,默认为10,显示更多 文字,隐藏更多图标按钮
            ivRight.setVisibility(View.GONE);
            tvRight.setVisibility(View.VISIBLE);
        }else if(titleBarType == 11){//传入11,显示更多图标按钮,隐藏更多 文字
            tvRight.setVisibility(View.GONE);
            ivRight.setVisibility(View.VISIBLE);
        }
    }

    public void setIvLeft(int resId) {
        Drawable leftDrawalbe = getContext().getResources().getDrawable(resId);
        ivLeft.setImageDrawable(leftDrawalbe);
        ivLeft.setVisibility(View.VISIBLE);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTvRight(String text) {
        tvRight.setText(text);
    }

    public void setIvRight(int resId) {
        Drawable leftDrawalbe = getContext().getResources().getDrawable(resId);
        ivRight.setImageDrawable(leftDrawalbe);
        ivRight.setVisibility(View.VISIBLE);
    }

    //左边图片点击事件
    public void setLeftIconOnClickListener(OnClickListener l){
        ivLeft.setOnClickListener(l);
    }

    //右边图片点击事件
    public void setRightIconOnClickListener(OnClickListener l){
        ivLeft.setOnClickListener(l);
    }

    //右边文字点击事件
    public void setRightTextOnClickListener(OnClickListener l){
        ivLeft.setOnClickListener(l);
    }
}