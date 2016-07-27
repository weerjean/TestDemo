package com.weerjean.testdemo.network;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.weerjean.testdemo.BaseToolbarActivity;
import com.weerjean.testdemo.R;

/**
 * Created by weerjean on 2016/7/19  20:33
 * description:
 */
public class NetWorkActivity extends BaseToolbarActivity implements View.OnClickListener {

    private Toolbar toolbar_1;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_network;
    }

    protected void initView() {
        toolbar_1 = (Toolbar) findViewById(R.id.toolbar_1);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                Intent intent = new Intent(this, OkhttpUtilsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_2:

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
        }
    }
}
