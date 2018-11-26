package com.weerjean.testdemo.webview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.BaseToolbarActivity;
import com.weerjean.testdemo.utils.ScreenShotUtils;

/**
 * Created by : weiwenjie986 on 18/11/1 下午3:59. Description :
 */
public class BaseWebActivity extends BaseToolbarActivity {

    private BaseWebview mBaseWebview;

    private String picPath ="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

        mToolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                picPath = ScreenShotUtils.getSnapshotFile(BaseWebActivity.this);
                Intent intent = getIntent();
                intent.putExtra("picPath", picPath);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mBaseWebview.setWebViewClient(new BaseWebviewClient(){

            @Override
            public void onPageFinished(final WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
    }

    private void initData() {
        mBaseWebview.loadUrl("https://www.baidu.com");
    }

    private void initView() {
        LinearLayout rootView = (LinearLayout) findViewById(R.id.ll_basewebactiviry_root);
//        mToolbar = (Toolbar)findViewById(R.id.toolbar_1);
        mBaseWebview = new BaseWebview(this);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            mBaseWebview.enableSlowWholeDocumentDraw();
        }
        rootView.addView(mBaseWebview);
    }

    @Override
    protected int getLayoutId(){
        return R.layout.activity_basewebview;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
