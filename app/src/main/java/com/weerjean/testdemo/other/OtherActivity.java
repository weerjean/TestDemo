package com.weerjean.testdemo.other;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.BaseToolbarActivity;
import com.weerjean.testdemo.utils.BitmapUtils;
import com.weerjean.testdemo.utils.CommenUtils;
import com.weerjean.testdemo.utils.CommenUtils.ProcessInfo;
import com.weerjean.testdemo.utils.FileUtils;
import com.weerjean.testdemo.utils.ScreenShotUtils;
import com.weerjean.testdemo.utils.ToastUtils;
import com.weerjean.testdemo.view.MyClickableSpan;
import com.weerjean.testdemo.view.MyClickableSpan.OnLinkClickListener;
import com.weerjean.testdemo.webview.BaseWebActivity;
import java.util.List;

/**
 * Created by : weiwenjie986 on 18/11/1 下午3:20. Description :
 */
public class OtherActivity extends BaseToolbarActivity implements OnClickListener {

    private TextView mTextView;
    private ImageView mImageView;
    public static int SCREENSHOT_REQUEST_CODE = 1;
    private int count ;

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
        Button other5Btn = (Button) findViewById(R.id.btn_other_5);
        Button other6Btn = (Button) findViewById(R.id.btn_other_6);
        Button other7Btn = (Button) findViewById(R.id.btn_other_7);
        Button other8Btn = (Button) findViewById(R.id.btn_other_8);
        Button other9Btn = (Button) findViewById(R.id.btn_other_9);
        Button other10Btn = (Button) findViewById(R.id.btn_other_10);
        Button other11Btn = (Button) findViewById(R.id.btn_other_11);
        Button other12Btn = (Button) findViewById(R.id.btn_other_12);
        otherScreenShotBtn.setOnClickListener(this);
        other2Btn.setOnClickListener(this);
        other3Btn.setOnClickListener(this);
        other4Btn.setOnClickListener(this);
        other5Btn.setOnClickListener(this);
        other6Btn.setOnClickListener(this);
        other7Btn.setOnClickListener(this);
        other8Btn.setOnClickListener(this);
        other9Btn.setOnClickListener(this);
        other10Btn.setOnClickListener(this);
        other11Btn.setOnClickListener(this);
        other12Btn.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other;
    }


    @Override
    public void onClick(View v) {
        count++;
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
                // 压缩图片
                Bitmap bitmap2 = ScreenShotUtils.getSnapshotBitmap(this);
                long currentTimeMillis = System.currentTimeMillis();
                String filepath = FileUtils.getExternalFilesDir(this)+"screenshot/"+currentTimeMillis+".jpg";
                BitmapUtils.saveBitmapToFile(bitmap2,filepath,CompressFormat.JPEG,100);
                Bitmap bitmap3 = BitmapUtils.compressBitmap(bitmap2, 50);
                String filepath2 = FileUtils.getExternalFilesDir(this)+"screenshot/"+currentTimeMillis+"_compress"+".jpg";
                BitmapUtils.saveBitmapToFile(bitmap3,filepath2,CompressFormat.JPEG,100);
                mImageView.setImageBitmap(bitmap3);
                break;
            case R.id.btn_other_4:

                String str = "测试数据";
                SpannableStringBuilder ssb = new SpannableStringBuilder(str);
                ssb.setSpan(new MyClickableSpan(new OnLinkClickListener() {
                    @Override
                    public void onLinkClick(View view) {
                    }
                }),0,str.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);


                mTextView.setText(ssb,TextView.BufferType.SPANNABLE);

                break;
            case R.id.btn_other_5:
                boolean isUseedVpn = CommenUtils.isVpnUsed();
                boolean isWifiProxy = CommenUtils.isWifiProxy();
                boolean isInstallHookTools = CommenUtils.isInstalledHookTools();
                String isRootSystem = CommenUtils.isRootSystem();

                mTextView.setText("是否使用了VPN:"+isUseedVpn+"\r\n"+"是否使用了代理："+isWifiProxy+"\r\n"+"是否安装了hook工具："+isInstallHookTools+isWifiProxy+"\r\n"+"是否root："+isRootSystem);

                break;
            case R.id.btn_other_6:
//                List<ProcessInfo> runningProcesses = CommenUtils.getProcessInfo2(this);
                List<ProcessInfo> processInfo = CommenUtils.getProcessInfo(this);

                mTextView.setText("进程列表:"+ processInfo);

                break;
            case R.id.btn_other_7:
                List<ProcessInfo> runningProcesses2 = CommenUtils.getProcessInfo2(this);

                mTextView.setText("进程列表:"+ runningProcesses2);

                break;
            case R.id.btn_other_8:
                List<ProcessInfo> runningProcesses3 = CommenUtils.getProcessInfo3(this);

                mTextView.setText("进程列表:"+ runningProcesses3);

                break;
            case R.id.btn_other_9:

                if(isHooked()){
                    mTextView.setText("hook isHooked()方法成功");
                    ToastUtils.toast(this,"hook isHooked()方法成功");
                }else{
                    mTextView.setText("hook isHooked()方法失败");
                    ToastUtils.toast(this,"hook isHooked()方法失败");
                }
                break;

            case R.id.btn_other_10:

                String string = "";
                byte[] bytes = string.getBytes();
                int length = bytes.length;
                mTextView.setText(count+":length="+length);
                break;
            case R.id.btn_other_11:

                mTextView.setText(count+"");
                break;
            case R.id.btn_other_12:

                mTextView.setText(count+"");
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

    public boolean isHooked(){
        return false;
    }

}
