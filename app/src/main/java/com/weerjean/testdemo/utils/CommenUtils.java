package com.weerjean.testdemo.utils;


import com.weerjean.testdemo.base.MyApplication;
import java.io.File;

/**
 * Created by : weiwenjie986 on 18/11/1 下午3:09. Description :
 */
public class CommenUtils {

    public static String getExtCacheDir(){

        String path =  MyApplication.getInstance().getExternalCacheDir().getAbsolutePath()+File.pathSeparator+"weerjean/";

        return path;
    }

}
