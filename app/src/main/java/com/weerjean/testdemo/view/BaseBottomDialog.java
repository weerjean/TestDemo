package com.weerjean.testdemo.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.weerjean.testdemo.R;

public abstract class BaseBottomDialog extends Dialog {

    public BaseBottomDialog(Context context) {
        super(context);
        init();
    }

    protected void init(){
        Window win = this.getWindow();
        win.requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.windowAnimations = R.style.AnimBottom;
        lp.gravity = Gravity.BOTTOM;
        win.setAttributes(lp);
        win.setBackgroundDrawableResource(android.R.color.transparent);
    }

    protected abstract int getLayoutId();

}
