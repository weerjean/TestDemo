package com.weerjean.testdemo.base;

import android.app.Application;

import com.paem.okhttp.OkHttpUtils;
import com.paem.okhttp.cookie.CookieJarImpl;
import com.paem.okhttp.cookie.store.PersistentCookieStore;
import com.paem.okhttp.https.HttpsUtils;
import com.paem.okhttp.log.LoggerInterceptor;
import com.weerjean.testdemo.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
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

         builder.connectTimeout(40000, TimeUnit.MILLISECONDS)
                .readTimeout(40000, TimeUnit.MILLISECONDS)
                .writeTimeout(40000, TimeUnit.MILLISECONDS)
                .cookieJar(new CookieJarImpl(new PersistentCookieStore(this)));

        if(Constants.IS_DEBUG){

            try {
//            HttpsUtils.SSLParams sslParams=HttpsUtils.getSslSocketFactory(null,null,null);
                InputStream in1 = getAssets().open("1qianbao.cer");
                InputStream in2 = getAssets().open("pingan.cer");
                InputStream in3 = getAssets().open("stg_1qianbao.cer");
                InputStream in4 = getAssets().open("pingan_new.cer");
                InputStream in5 = getAssets().open("paf_stg.cer");
                HttpsUtils.SSLParams sslParams=HttpsUtils.getSslSocketFactory(new InputStream[]{in1,in2,in3,in4,in5},null,null);
                builder.addInterceptor(new LoggerInterceptor(Constants.TAG_OKHTTP,true))
                        .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{

            try {
                InputStream in1 = getAssets().open("1qianbao.cer");
                InputStream in2 = getAssets().open("pingan.cer");
                InputStream in3 = getAssets().open("stg_1qianbao.cer");
                InputStream in4 = getAssets().open("pingan_new.cer");
                HttpsUtils.SSLParams sslParams=HttpsUtils.getSslSocketFactory(new InputStream[]{in1,in2,in3,in4},null,null);
                builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        OkHttpUtils.initClient(builder.build());

    }

    public static MyApplication getInstance(){
        return application;
    }

}
