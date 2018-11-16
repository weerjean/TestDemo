package com.weerjean.testdemo.view;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by : weiwenjie986 on 18/11/6 下午3:07.
 * Description :修改TextView中可点击字体的颜色
 */
public class MyClickableSpan extends ClickableSpan {

    public interface OnLinkClickListener {
        void onLinkClick(View view);
    }

    private OnLinkClickListener listener;

    public MyClickableSpan(OnLinkClickListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    public void onClick(View widget) {
        listener.onLinkClick(widget);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.parseColor("#EB6100")); // 设置字体颜色
//        ds.setUnderlineText(false); //去掉下划线
    }

}
