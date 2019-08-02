package com.weerjean.testdemo.view;

import android.content.Context;
import android.view.View;

import com.weerjean.testdemo.R;

public class BottomDialog extends BaseBottomDialog implements View.OnClickListener{

    public BottomDialog(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_dialog_ok).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_bottom;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dialog_ok:
                dismiss();
                break;
            default:
                return;
        }

    }
}
