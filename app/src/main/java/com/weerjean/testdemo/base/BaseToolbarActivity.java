package com.weerjean.testdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;
import com.weerjean.testdemo.R;

/**
 * Created by weerjean on 2016/7/24.
 */
public abstract class BaseToolbarActivity extends BaseActivity {

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    @Override
    protected abstract int getLayoutId();

    protected void initToolbar() {
        // 获取根view
        ViewGroup rootView = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        mToolbar = (Toolbar)getLayoutInflater().inflate(R.layout.toolbar_1,null);
        // 将toolbar添加到根view
        rootView.addView(mToolbar,0);
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        // 如果不设置主标题，ToolBar上默认显示的AppName
//        setTitle("ToolBar");
        setSupportActionBar(mToolbar);
//        mToolbar.setLogo(R.mipmap.logo_qzone);
//        mToolbar.setTitle("Titile");
//        mToolbar.setSubtitle("SubTitile");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.translate_left_enter, R.anim.translate_right_exit);
                }
        });
    }


    protected void setTitle(String title){
        mToolbar.setTitle(title);
    }

}
