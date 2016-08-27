package com.weerjean.testdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.weerjean.testdemo.base.BaseActivity;

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
        mToolbar = (Toolbar) findViewById(R.id.toolbar_1);
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
                // TODO: 2016/8/27  添加界面切换动画
            }
        });
    }

    @Override
    protected void initView() {

    }


    protected void setTitle(String title){
        mToolbar.setTitle(title);
    }

}
