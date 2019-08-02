package com.weerjean.testdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.view.CustomTitleBar;

/**
 * Created by : weerjean on 18/12/18 下午5:16.
 * Description :自定义TitleBar基类
 */
public abstract class BaseCustomTitleBarActivity extends BaseActivity {

    protected CustomTitleBar mTitleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTtileBar();
    }

    protected void initTtileBar(){
        mTitleBar = new CustomTitleBar(this);
        // 获取根view
        ViewGroup rootView = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        // 将toolbar添加到根view
        rootView.addView(mTitleBar,0);

        mTitleBar.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.translate_left_enter, R.anim.translate_right_exit);
            }
        });

    }

    /**
     * 设置title左边图标
     * @param resId drawbale资源id
     */
    public void setIvLeft(int resId) {
        mTitleBar.setIvLeft(resId);
    }

    /**
     * 设置title标题文字
     * @param title
     */
    public void setTitle(String title) {
        mTitleBar.setTitle(title);
    }

    /**
     * 设置title右边文字
     * @param text
     */
    public void setTvRight(String text) {
        mTitleBar.setTvRight(text);
    }

    /**
     * 设置title右边图标
     * @param resId
     */
    public void setIvRight(int resId) {
        mTitleBar.setIvRight(resId);
    }

}
