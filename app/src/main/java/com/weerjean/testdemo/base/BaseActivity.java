package com.weerjean.testdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by weerjean on 2016/7/10.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initDate();
        initEvent();

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected void initDate(){

    }

    protected void initEvent(){

    }
}