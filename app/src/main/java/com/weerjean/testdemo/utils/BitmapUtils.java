package com.weerjean.testdemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.hy.okhttp.utils.L;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by : weerjean on 18/11/5 下午4:45. Description :
 */
public class BitmapUtils {

    /**
     * 以同步方式将指定的bitmap压缩至小于等于size的大小;
     * 如果size小于等于0或者bitmap已经小于等于size了，则直接返回bitmap
     * 测试不能压缩至最大尺寸以下
     * @param bitmap
     * @param maxSize 图片的最大尺寸
     * @return
     */
    public static Bitmap compressBitmap(Bitmap bitmap, int maxSize) {

        Bitmap compressBitmap = null;
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//将压缩后的图片保存到baos中
        int options = 100;
        int baosLength = baos.toByteArray().length;


        while (baosLength / 1024 > maxSize) {//循环判断如果压缩后图片是否大于maxSize大于继续压缩
            baos.reset();//重置baos即让下一次的写入覆盖之前的内容
            options = Math.max(0, options - 10);//图片质量每次减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//将压缩后的图片保存到baos中
            baosLength = baos.toByteArray().length;
            if (options == 0)//如果图片的质量已降到最低则，不再进行压缩
                break;
        }
        byte[] buf = baos.toByteArray();
        bais = new ByteArrayInputStream(buf);
        compressBitmap = BitmapFactory.decodeStream(bais);

        return compressBitmap;
    }


    /**
     * 把bitmap保存到指定路径的文件中
     * @param bitmap
     * @param filePath
     * @param format
     * @param quality
     * @return
     */
    public static boolean saveBitmapToFile(Bitmap bitmap, String filePath, Bitmap.CompressFormat format, int quality) {
        if(bitmap == null) {
            return false;
        }
        if(quality < 0 || quality > 100) {
            return false;
        }

        File file = new File(filePath);
        if(!file.getParentFile().exists()) {
            boolean result = file.getParentFile().mkdirs();
            if(!result) {
                return false;
            }
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            return bitmap.compress(format, quality, fos);
        }
        catch(FileNotFoundException e) {
            L.e( e.toString());
        }
        finally {
            try {
                if(fos != null) {
                    fos.close();
                }
            }
            catch(IOException e) {
                L.e(e.toString());
            }
        }

        return false;
    }


}
