package com.weerjean.testdemo.test;

import android.util.Log;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by : weiwenjie986 on 18/11/13 下午7:34. Description :
 */
public class HttpTest {

    private static final String TAG = "HttpTest";

    public void initOkhttpClient(){

        try {
            OkHttpClient okHttpClient = new Builder()
                .sslSocketFactory(HttpUtils.getInstance().mSSLSocketFactory, HttpUtils.getInstance().mX509TrustManager)
                .build();

            Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()){
                Log.d(TAG,"请求成功："+response.body().string());

            } else {
                Log.d(TAG,"请求失败："+response.body().string());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
