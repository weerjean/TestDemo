package com.weerjean.testdemo.network;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.hy.okhttp.OkHttpUtils;
import com.hy.okhttp.callback.StringCallback;
import com.hy.okhttp.utils.L;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.BaseToolbarActivity;
import com.weerjean.testdemo.base.MyApplication;
import com.weerjean.testdemo.utils.Constants;
import com.weerjean.testdemo.utils.ThreadPoolManager;
import com.weerjean.testdemo.utils.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by weerjean on 2016/7/19  20:24
 * description:
 */
public class OkhttpUtilsActivity extends BaseToolbarActivity implements View.OnClickListener {

    private static final String TAG = "OkhttpUtilsActivity";
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
    private LinkedHashMap<String, String> mLinkedHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mLinkedHashMap = new LinkedHashMap<String, String>();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_okhttp;
    }

    protected void initView() {
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
                asycGet();
                break;
            case R.id.btn_3:
                sycPost();
                break;
            case R.id.btn_4:
                asycPostForm();
                break;
            case R.id.btn_5:
                postJson();
                asycPostForm1();
                break;
            case R.id.btn_6:
                postImage();
                asycPostForm2();
                break;
            case R.id.btn_7:
                asycPostForm3();
                postFile();
                break;
            case R.id.btn_8:
                asycPostForm4();
                postFileCallback();
                break;
            case R.id.btn_9:
                asycPostForm5();
                postImageCallback();
                break;
            case R.id.btn_10:
                asycPostForm6();
                break;
        }
    }

    private void postImageCallback() {
    }

    private void postFileCallback() {

    }

    private void postJson() {

    }

    private void postImage() {

    }

    private void postFile() {

    }


    private void asycPostForm() {

        File file = new File(MyApplication.getInstance().getCacheDir()+"\1.txt");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OkHttpUtils.post().url(Constants.BASE_URL)
                .params(mLinkedHashMap)
                .addFile("fileKey","fileName",file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,e.toString());
                    }

                    @Override
                    public void onResponse(Call call, String response, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,response);
                    }
                });

    }
    private void asycPostForm1() {

        File file = new File(MyApplication.getInstance().getCacheDir()+"\1.txt");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OkHttpUtils.post().url(Constants.YQB_URL)
                .params(mLinkedHashMap)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,e.toString());
                    }

                    @Override
                    public void onResponse(Call call, String response, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,response);
                    }
                });

    }
    private void asycPostForm2() {

        File file = new File(MyApplication.getInstance().getCacheDir()+"\1.txt");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OkHttpUtils.post().url(Constants.YQB_URL)
                .params(mLinkedHashMap)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,e.toString());
                    }

                    @Override
                    public void onResponse(Call call, String response, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,response);
                    }
                });

    }
    private void asycPostForm3() {

        File file = new File(MyApplication.getInstance().getCacheDir()+"\1.txt");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OkHttpUtils.post().url(Constants.BAIDU_URL)
                .params(mLinkedHashMap)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,e.toString());
                    }

                    @Override
                    public void onResponse(Call call, String response, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,response);
                    }
                });

    }
    private void asycPostForm4() {

        File file = new File(MyApplication.getInstance().getCacheDir()+"\1.txt");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OkHttpUtils.post().url(Constants.YQB_URL)
                .params(mLinkedHashMap)
                .addFile("fileKey","fileName",file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,e.toString());
                    }

                    @Override
                    public void onResponse(Call call, String response, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,response);
                    }
                });

    }
    private void asycPostForm5() {

        File file = new File(MyApplication.getInstance().getCacheDir()+"\1.txt");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OkHttpUtils.post().url(Constants.YQB_URL)
                .params(mLinkedHashMap)
                .addFile("fileKey","fileName",file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,e.toString());
                    }

                    @Override
                    public void onResponse(Call call, String response, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,response);
                    }
                });

    }
    private void asycPostForm6() {

        File file = new File(MyApplication.getInstance().getCacheDir()+"\1.txt");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OkHttpUtils.post().url(Constants.BAIDU_URL)
                .params(mLinkedHashMap)
                .addFile("fileKey","fileName",file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,e.toString());
                    }

                    @Override
                    public void onResponse(Call call, String response, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,response);
                    }
                });

    }

    private void sycPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = OkHttpUtils.post().url(Constants.JSON_URL).params(mLinkedHashMap).build().execute();

                    ToastUtils.toast(OkhttpUtilsActivity.this, response.body().string());
                } catch (Exception e) {
                    L.e(e.getMessage());
                }
            }
        }).start();

    }

    /**
     * 异步get，自己会创建子线程中，回调会回到主线程。
     */
    private void asycGet() {

        OkHttpUtils.get().url(Constants.YQB_URL)
                .params(mLinkedHashMap)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,e.toString());
                    }

                    @Override
                    public void onResponse(Call call, String response, int id) {
                        ToastUtils.toast(OkhttpUtilsActivity.this,response);
                    }
                });



    }

    /**
     * 同步get请求，需要在子线程中进行
     */
    private void sycGet() {

        ThreadPoolManager.getInstance().execut(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = OkHttpUtils.get()
                        .url(Constants.JSON_URL)
                        .params(mLinkedHashMap)
                        .build()
                        .execute();

                    ToastUtils.toast(OkhttpUtilsActivity.this, response.body().string());
                } catch (IOException e) {
                    L.e(e.getMessage());
                } catch (Exception e) {
                    L.e(e.getMessage());
                }
            }
        });

    }
}
