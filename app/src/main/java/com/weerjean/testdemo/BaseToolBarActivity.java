package com.weerjean.testdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.weerjean.testdemo.base.BaseActivity;

/**
 * Created by weerjean on 2016/7/24.
 */
public abstract class BaseToolbarActivity extends BaseActivity {

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();



    }

    @Override
    protected abstract int getLayoutId();

    protected void initToolBar() {
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.toolbar_1);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_1);
        //setSupportActionBar(mToolbar);
    }

    @Override
    protected void initView() {

    }
}
