package com.weerjean.testdemo.recyclerview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.BaseActivity;

/**
 * Created by weerjean on 2016/7/9.
 */
public class RecyclerViewActivity extends BaseActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_recyclerview);
        initView();
    }

    private void initView() {


    }
}
