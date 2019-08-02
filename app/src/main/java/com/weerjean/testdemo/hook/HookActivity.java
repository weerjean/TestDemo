package com.weerjean.testdemo.hook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.BaseToolbarActivity;
import com.weerjean.testdemo.utils.ToastUtils;

/**
 * Created by : weerjean on 18/11/26 下午4:13. Description :
 */
public class HookActivity extends BaseToolbarActivity implements OnClickListener {

    private TextView mTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        initView();
    }

    private void initView() {
        mTextView  = (TextView) findViewById(R.id.tv_hook_result);
        mImageView = (ImageView) findViewById(R.id.iv_hook_show);
        Button hook1Btn = (Button) findViewById(R.id.btn_hook_1);
        Button hook2Btn = (Button) findViewById(R.id.btn_hook_2);
        Button hook3Btn = (Button) findViewById(R.id.btn_hook_3);
        Button hook4Btn = (Button) findViewById(R.id.btn_hook_4);
        Button hook5Btn = (Button) findViewById(R.id.btn_hook_5);
        Button hook6Btn = (Button) findViewById(R.id.btn_hook_6);
        Button hook7Btn = (Button) findViewById(R.id.btn_hook_7);
        Button hook8Btn = (Button) findViewById(R.id.btn_hook_8);
        hook1Btn.setOnClickListener(this);
        hook2Btn.setOnClickListener(this);
        hook3Btn.setOnClickListener(this);
        hook4Btn.setOnClickListener(this);
        hook5Btn.setOnClickListener(this);
        hook6Btn.setOnClickListener(this);
        hook7Btn.setOnClickListener(this);
        hook8Btn.setOnClickListener(this);
        
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_hook;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_hook_1:
                mTextView.setText("默认数据");
                break;
            case R.id.btn_hook_2:
                if(isModuleActive()){
                    ToastUtils.toast(this,"hook模块已启动");
                }else{
                    ToastUtils.toast(this,"hook模块未启动");
                }
                break;
            case R.id.btn_hook_3:

                break;
            case R.id.btn_hook_4:

                break;
            case R.id.btn_hook_5:

                break;
            case R.id.btn_hook_6:

                break;
            case R.id.btn_hook_7:

                break;
            case R.id.btn_hook_8:

                break;
            default:
                ToastUtils.toast(this,"点击失败，没有此控件");

        }
    }

    private boolean isModuleActive() {

        return false;

    }
}
