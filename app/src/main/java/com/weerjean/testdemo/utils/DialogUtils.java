package com.weerjean.testdemo.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by weerjean on 2016/9/4.
 * description：
 */
public class DialogUtils {

    public  static Dialog getCommDialog(Context context,String msg){

        ProgressDialog.Builder builder = new ProgressDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
        Dialog dialog = builder.setMessage(msg).create();

        return dialog;
    }

}
