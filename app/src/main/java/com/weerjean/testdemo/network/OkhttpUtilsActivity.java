package com.weerjean.testdemo.network;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.paem.okhttp.OkHttpUtils;
import com.paem.okhttp.utils.L;
import com.weerjean.testdemo.BaseToolbarActivity;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.utils.Constants;
import com.weerjean.testdemo.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by weerjean on 2016/7/19  20:24
 * description:
 */
public class OkhttpUtilsActivity extends BaseToolbarActivity implements View.OnClickListener {

    private Toolbar toolbar_1;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_okhttp;
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
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);
        btn_10 = (Button) findViewById(R.id.btn_10);
        btn_10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                sycGet();
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
            case R.id.btn_9:

                break;
            case R.id.btn_10:

                break;
        }
    }

    private void sycGet() {

        try {
            Response response = OkHttpUtils.get().url(Constants.JSON_URL).build().execute();

            ToastUtils.toast(this,response.body().string());
        } catch (IOException e) {
            L.e(e.getMessage());
        }catch (Exception e){
            L.e(e.getMessage());
        }
    }
}
