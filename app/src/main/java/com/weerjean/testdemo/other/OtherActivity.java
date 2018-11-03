package com.weerjean.testdemo.other;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.BaseToolbarActivity;
import com.weerjean.testdemo.utils.ScreenShotUtils;
import com.weerjean.testdemo.utils.ToastUtils;
import com.weerjean.testdemo.webview.BaseWebActivity;

/**
 * Created by : weiwenjie986 on 18/11/1 下午3:20. Description :
 */
public class OtherActivity extends BaseToolbarActivity implements OnClickListener {

    private TextView mTextView;
    private ImageView mImageView;

    public static int SCREENSHOT_REQUEST_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        mTextView  = (TextView) findViewById(R.id.tv_other_result);
        mImageView = (ImageView) findViewById(R.id.iv_other_screenshot_show);
        Button otherScreenShotBtn = (Button) findViewById(R.id.btn_other_screenshot);
        Button other2Btn = (Button) findViewById(R.id.btn_other_2);
        Button other3Btn = (Button) findViewById(R.id.btn_other_3);
        Button other4Btn = (Button) findViewById(R.id.btn_other_4);
        otherScreenShotBtn.setOnClickListener(this);
        other2Btn.setOnClickListener(this);
        other3Btn.setOnClickListener(this);
        other4Btn.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_other_screenshot:
                //调用截图
                Bitmap bitmap = ScreenShotUtils.captureScreen(this);
                mImageView.setImageBitmap(bitmap);
                break;
            case R.id.btn_other_2:

                Intent intent = new Intent(this, BaseWebActivity.class);
                startActivityForResult(intent,SCREENSHOT_REQUEST_CODE);

                break;
            case R.id.btn_other_3:
                break;
            case R.id.btn_other_4:
                break;
            default:
                ToastUtils.toast(this,"点击无效，没有找到此按钮");

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCREENSHOT_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                try {
                    String picPath = data.getStringExtra("picPath");
                    Bitmap bitmap = BitmapFactory.decodeFile(picPath);
                    mImageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
