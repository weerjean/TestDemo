package com.weerjean.testdemo.utils;


import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import com.weerjean.testdemo.base.MyApplication;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by : weiwenjie986 on 18/11/1 下午3:09. Description :
 */
public class CommenUtils {

    public static String getExtCacheDir(){

        String path =  MyApplication.getInstance().getExternalCacheDir().getAbsolutePath()+File.pathSeparator+"weerjean/";

        return path;
    }

    /**
     * 匹配<a></a>转换为ClickableSpan
     * @param contentMessage
     * @param view
     * @return
     */
    public SpannableString getMessageToSpannable(String contentMessage, final View view) {
        String regEx = "<a>([\\s\\S]*?)</a>";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(contentMessage);

        List<String> listMessage = new ArrayList<>();//截取后的消息内容
        List<Integer> listLocation = new ArrayList<>();//截取的位置

        int i = 0;
        while (matcher.find()) {
            listLocation.add(matcher.start() - i * 7);
            listMessage.add(matcher.group().replace("<a>", "").replace("</a>", ""));
            i++;
        }

        SpannableString sps = new SpannableString(contentMessage.replace("<a>", "").replace("</a>", ""));

        for (i = 0; i < listMessage.size(); i++) {
            if (TextUtils.isEmpty(listMessage.get(i)))
                continue;

            final String content = listMessage.get(i);
            sps.setSpan(new ClickableSpan() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.parseColor("#EB6100"));       //设置文件颜色
                    ds.setUnderlineText(true);      //设置下划线
                }

                @Override
                public void onClick(View widget) {
                    view.callOnClick();
                }
            }, listLocation.get(i), listLocation.get(i) + content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return sps;
    }

}
