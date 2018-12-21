package com.weerjean.testdemo.titlebar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.BaseCustomTitleBarActivity;

/**
 * Created by : weiwenjie986 on 18/12/18 下午5:24. Description :
 */
public class MyTitleBarActivity extends BaseCustomTitleBarActivity implements OnClickListener {

    private TextView mTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_customtitlebar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTtile();
        initView();
    }

    private void initTtile() {
        setTitle("自定义标题");
        setIvLeft(R.mipmap.li_back);
        setTvRight("文字");
        setIvRight(R.mipmap.li_goright);
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.tv_testtitle);
    }

    @Override
    public void onClick(View v) {
        mTextView.setText("控件"+v.getId()+"被点击了");
    }
}
