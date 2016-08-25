package com.weerjean.testdemo.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.weerjean.testdemo.BaseToolbarActivity;
import com.weerjean.testdemo.R;

/**
 * Created by weerjean on 2016/7/9.
 */
public class RecyclerViewActivity extends BaseToolbarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void initView() {
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recylerview);
    }
}
