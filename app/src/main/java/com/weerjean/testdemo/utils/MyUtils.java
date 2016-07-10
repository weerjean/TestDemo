package com.weerjean.testdemo.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by weerjean on 2016/7/10.
 */
public class MyUtils {

    public  static void toast(final Activity activity, final String msg){
        if(!Constants.IS_DEBUG){
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
