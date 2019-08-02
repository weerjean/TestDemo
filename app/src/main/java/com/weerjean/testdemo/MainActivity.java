package com.weerjean.testdemo;

import android.Manifest.permission;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.weerjean.testdemo.activity.PullRefreshActivity;
import com.weerjean.testdemo.base.BaseToolbarActivity;
import com.weerjean.testdemo.hook.HookActivity;
import com.weerjean.testdemo.lbs.LocationActivity;
import com.weerjean.testdemo.network.NetWorkActivity;
import com.weerjean.testdemo.other.OtherActivity;
import com.weerjean.testdemo.recyclerview.RecyclerViewActivity;
import com.weerjean.testdemo.rn.BaseRNActivty;
import com.weerjean.testdemo.sensor.SensorActivity;
import com.weerjean.testdemo.titlebar.MyTitleBarActivity;
import com.weerjean.testdemo.titlebar.ToolbarActivity;
import com.weerjean.testdemo.utils.ToastUtils;
import com.weerjean.testdemo.utils.permission.PermissionsActivity;
import com.weerjean.testdemo.utils.permission.PermissionsChecker;
import com.weerjean.testdemo.view.BottomDialog;

public class MainActivity extends BaseToolbarActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_10;
    private Button btn_11;
    private Button btn_12;
    private Button btn_13;
    private Button btn_14;
    private Button btn_15;
    private Button btn_16;
    private Button btn_17;
    private Button btn_18;
    private Button btn_19;
    private Button btn_20;


    private PermissionsChecker mPermissionsChecker; // 权限检测器

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
        permission.READ_EXTERNAL_STORAGE,
        permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int REQUEST_CODE = 0; // 请求码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initPermission();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    protected void initView() {
//        initActionBar();
//        mToolbar = (Toolbar) findViewById(R.id.toolbar_1);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_8.setOnClickListener(this);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);
        btn_10 = (Button) findViewById(R.id.btn_10);
        btn_10.setOnClickListener(this);
        btn_11 = (Button) findViewById(R.id.btn_11);
        btn_11.setOnClickListener(this);
        btn_12 = (Button) findViewById(R.id.btn_12);
        btn_12.setOnClickListener(this);
        btn_13 = (Button) findViewById(R.id.btn_13);
        btn_13.setOnClickListener(this);
        btn_14 = (Button) findViewById(R.id.btn_14);
        btn_14.setOnClickListener(this);
        btn_15 = (Button) findViewById(R.id.btn_15);
        btn_15.setOnClickListener(this);
        btn_16 = (Button) findViewById(R.id.btn_16);
        btn_16.setOnClickListener(this);
        btn_17 = (Button) findViewById(R.id.btn_17);
        btn_17.setOnClickListener(this);
        btn_18 = (Button) findViewById(R.id.btn_18);
        btn_18.setOnClickListener(this);
        btn_19 = (Button) findViewById(R.id.btn_19);
        btn_19.setOnClickListener(this);
        btn_20 = (Button) findViewById(R.id.btn_20);
        btn_20.setOnClickListener(this);
    }


//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                // 跳转到Toolbar测试页
                Intent intent = new Intent(this, ToolbarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_2:
                // 跳转到网络测试页
                Intent intent2 = new Intent(this, NetWorkActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_3:
                // 跳转到RecylerView测试页
                Intent intent3 = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_4:
                // 跳转到定位测试页面
                Intent intent4 = new Intent(this, LocationActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_5:
                Intent intent5 = new Intent(this, OtherActivity.class);
                startActivity(intent5);

                break;
            case R.id.btn_6:
            Intent intent6 = new Intent(this, HookActivity.class);
                startActivity(intent6);

                break;
            case R.id.btn_7:
                Intent intent7 = new Intent(this, MyTitleBarActivity.class);
                startActivity(intent7);

                break;
            case R.id.btn_8:
                Intent intent8 = new Intent(this, SensorActivity.class);
                startActivity(intent8);
                break;
            case R.id.btn_9:
                Intent intent9 = new Intent(this,BaseRNActivty.class);
                startActivity(intent9);

                break;
            case R.id.btn_10:
                Intent intent10 = new Intent(this, PullRefreshActivity.class);
                startActivity(intent10);

                break;
            case R.id.btn_11:
                final BottomDialog bottomDialog = new BottomDialog(this);
                bottomDialog.show();

                break;
            case R.id.btn_12:

                break;
            case R.id.btn_13:

                break;
            case R.id.btn_14:

                break;
            case R.id.btn_15:

                break;
            case R.id.btn_16:

                break;
            case R.id.btn_17:

                break;
            case R.id.btn_18:

                break;
            case R.id.btn_19:
                Log.d(TAG, null);
                break;
            case R.id.btn_20:
                Log.e(TAG, "");
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        }
    }

    /**
     * 初始化权限检查器
     */
    private void initPermission() {
        mPermissionsChecker = new PermissionsChecker(this);
    }

    /**
     * 打开权限获取页面
     */
    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            ToastUtils.toast(this,"为了不影响用户体验，请授予SD卡读写权限");
        }
    }


}
