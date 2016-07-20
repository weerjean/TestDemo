package com.weerjean.testdemo.base;

import android.app.Application;

import com.paem.okhttp.OkHttpUtils;
import com.paem.okhttp.https.HttpsUtils;
import com.paem.okhttp.log.LoggerInterceptor;
import com.weerjean.testdemo.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by weerjean on 2016/6/26.
 */
public class MyApplication extends Application{

    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        initOkhttp();
    }

    private void initOkhttp() {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        builder.connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS);

        if(Constants.IS_DEBUG){

            HttpsUtils.SSLParams sslParams=HttpsUtils.getSslSocketFactory(null,null,null);
            builder.addInterceptor(new LoggerInterceptor(Constants.TAG_OKHTTP,true))
                    .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager);

        }else{
            HttpsUtils.SSLParams sslParams=HttpsUtils.getSslSocketFactory(null,null,null);
            builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        }

        OkHttpUtils.initClient(builder.build());

    }

    public static MyApplication getInstance(){
        return application;
    }

}
