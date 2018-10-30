package com.paem.okhttp.utils;

import android.util.Log;

/**
 * Created by paem on 15/11/6.
 */
public class L
{
    public static boolean debug = false;
    public static String TAG = "OkHttp";
    public static final int LOG_LV_D = 0x0001;
    public static final int LOG_LV_I = 0x0002;
    public static final int LOG_LV_W = 0x0003;
    public static final int LOG_LV_E = 0x0004;


    public static void e(String msg)
    {
        if (debug)
        {
            Log.e(TAG, msg);
        }
    }

    public static void d(String tag,String msg){
        if (debug)
        {
            Log.d(tag, msg);
        }
    }


    public static void p(int logLv, String msg){

        if (debug){

            switch (logLv){
                case LOG_LV_D :
                    Log.d(TAG,msg);
                    break;
                case LOG_LV_I :
                    Log.i(TAG,msg);
                    break;
                case LOG_LV_W :
                    Log.w(TAG,msg);
                    break;
                case LOG_LV_E :
                    Log.e(TAG,msg);
                    break;
                default:
                    Log.v(TAG,msg);
            }
        }

    }

}

