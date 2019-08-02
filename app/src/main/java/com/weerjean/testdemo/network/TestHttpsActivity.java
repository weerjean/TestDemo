package com.weerjean.testdemo.network;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.hy.okhttp.OkHttpUtils;
import com.hy.okhttp.callback.StringCallback;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.BaseToolbarActivity;
import com.weerjean.testdemo.utils.Constants;

import java.util.LinkedHashMap;

import okhttp3.Call;

/**
 * Created by weerjean on 2016/9/11.
 * description：用于测试https校验证书的有效性的测试类
 */
public class TestHttpsActivity extends BaseToolbarActivity implements View.OnClickListener {

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
    private LinkedHashMap<String, String> mLinkedHashMap;
    private TextView mTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_testhttps;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }



    private void initView() {
        mTextView = (TextView) findViewById(R.id.tv_testhttps);
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

    private void initData() {
        mLinkedHashMap = new LinkedHashMap<String, String>();
        mLinkedHashMap.put("os","A");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                okHttpUtilsDoGet(Constants.BASE_URL);
                break;
            case R.id.btn_2:
                //okHttpUtilsDoPost(Constants.BASE_URL);
                OkhttpUtils.post(this, Constants.BASE_URL);
                break;
            case R.id.btn_3:
                okHttpUtilsDoGet(Constants.JSON_URL);
                break;
            case R.id.btn_4:
                //okHttpUtilsDoPost(Constants.JSON_URL);
                OkhttpUtils.post(this, Constants.JSON_URL);
                break;
            case R.id.btn_5:
                okHttpUtilsDoGet(Constants.YQB_URL);
                break;
            case R.id.btn_6:
                //okHttpUtilsDoPost(Constants.YQB_URL);
                OkhttpUtils.post(this, Constants.YQB_URL);
                break;
            case R.id.btn_7:
                okHttpUtilsDoGet(Constants.YQB_URL);
                break;
            case R.id.btn_8:
               // okHttpUtilsDoPost(Constants.YQB_URL);
                OkhttpUtils.post(this, Constants.YQB_URL);
                break;
            case R.id.btn_9:
                okHttpUtilsDoGet(Constants.BAIDU_URL);
                break;
            case R.id.btn_10:
               // okHttpUtilsDoGet(Constants.BAIDU_URL);
                OkhttpUtils.post(this, Constants.BAIDU_URL);
                break;
        }
    }

    private void okHttpUtilsDoPost(String url) {
        OkHttpUtils.post().url(url).params(mLinkedHashMap).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTextView.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, String response, int id) {
                        mTextView.setText(response);
                    }
                });
    }

    private void okHttpUtilsDoGet(String url) {

        OkHttpUtils.get().url(url).params(mLinkedHashMap).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTextView.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, String response, int id) {
                        mTextView.setText(response);
                    }
                });
    }

}
