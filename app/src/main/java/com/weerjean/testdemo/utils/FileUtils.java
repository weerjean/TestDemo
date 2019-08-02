package com.weerjean.testdemo.utils;

import android.content.Context;
import java.io.File;

/**
 * Created by : weerjean on 18/11/5 下午5:00. Description :
 */
public class FileUtils {

    /**
     * 获取文件目录
     * @param context
     * @return
     */
    public static String getExternalFilesDir(Context context){
        return context.getExternalFilesDir(null)+"/TestDemo/";

    }

}
