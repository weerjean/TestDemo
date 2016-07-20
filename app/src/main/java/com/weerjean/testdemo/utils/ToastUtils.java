package com.weerjean.testdemo.utils;

import android.app.Activity;
import android.widget.Toast;

import static com.weerjean.testdemo.utils.Constants.IS_DEBUG;

/**
 * Created by weerjean on 2016/7/10.
 */
public class ToastUtils {

    public  static void toast(final Activity activity, final String msg){
        if(!IS_DEBUG){
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
