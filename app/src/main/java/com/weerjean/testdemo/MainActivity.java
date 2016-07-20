package com.weerjean.testdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.weerjean.testdemo.base.BaseActivity;
import com.weerjean.testdemo.network.NetWorkActivity;
import com.weerjean.testdemo.toolbar.ToolBarActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolBar;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_10;
    private Button btn_11;
    private Button btn_12;
    private Button btn_13;
    private Button btn_14;
    private Button btn_15;
    private Button btn_16;
    private Button btn_17;
    private Button btn_18;
    private Button btn_19;
    private Button btn_20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mToolBar = (Toolbar) findViewById(R.id.toolbar_1);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_8.setOnClickListener(this);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);
        btn_10 = (Button) findViewById(R.id.btn_10);
        btn_10.setOnClickListener(this);
        btn_11 = (Button) findViewById(R.id.btn_11);
        btn_11.setOnClickListener(this);
        btn_12 = (Button) findViewById(R.id.btn_12);
        btn_12.setOnClickListener(this);
        btn_13 = (Button) findViewById(R.id.btn_13);
        btn_13.setOnClickListener(this);
        btn_14 = (Button) findViewById(R.id.btn_14);
        btn_14.setOnClickListener(this);
        btn_15 = (Button) findViewById(R.id.btn_15);
        btn_15.setOnClickListener(this);
        btn_16 = (Button) findViewById(R.id.btn_16);
        btn_16.setOnClickListener(this);
        btn_17 = (Button) findViewById(R.id.btn_17);
        btn_17.setOnClickListener(this);
        btn_18 = (Button) findViewById(R.id.btn_18);
        btn_18.setOnClickListener(this);
        btn_19 = (Button) findViewById(R.id.btn_19);
        btn_19.setOnClickListener(this);
        btn_20 = (Button) findViewById(R.id.btn_20);
        btn_20.setOnClickListener(this);
        initActionBar();
    }

    private void initActionBar() {
        // 如果不设置title，ToolBar上默认显示的AppName
        setTitle("主页");
        setSupportActionBar(mToolBar);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                Intent intent = new Intent(this, ToolBarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_2:
                Intent intent2 = new Intent(this, NetWorkActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_3:

                break;
            case R.id.btn_4:

                break;
            case R.id.btn_5:

                break;
            case R.id.btn_6:

                break;
            case R.id.btn_7:

                break;
            case R.id.btn_8:

                break;
            case R.id.btn_9:

                break;
            case R.id.btn_10:

                break;
            case R.id.btn_11:

                break;
            case R.id.btn_12:

                break;
            case R.id.btn_13:

                break;
            case R.id.btn_14:

                break;
            case R.id.btn_15:

                break;
            case R.id.btn_16:

                break;
            case R.id.btn_17:

                break;
            case R.id.btn_18:

                break;
            case R.id.btn_19:

                break;
            case R.id.btn_20:

                break;
        }
    }
}
