package com.weerjean.testdemo.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.weerjean.testdemo.BaseActivity;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.utils.MyUtils;

/**
 * Created by weerjean on 2016/7/10.
 */
public class ToolBarActivity extends BaseActivity {

    private Toolbar mToolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        initView();
        initEvent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 方式1:在xml中设置
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        // 方式2：代码设置方式
        //用代码的方式添加 菜单
        menu.addSubMenu(0, 1, 0, "前进");
//        menu.addSubMenu(0, 2, 0, "后退");
//        menu.addSubMenu(0, 3, 0, "停止");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_settings1:
                MyUtils.toast(this, item.getTitle()+"被点击了");
                break;
            case R.id.action_settings2:
                MyUtils.toast(this, item.getTitle()+"被点击了");
                break;

        }

        return true;
    }

    private void initEvent() {

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar_1);
        initActionBar();
    }

    private void initActionBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar_1);
        mToolBar.setNavigationIcon(R.mipmap.ic_back);
        // 如果不设置主标题，ToolBar上默认显示的AppName
        setTitle("ToolBar");
        setSupportActionBar(mToolBar);
        mToolBar.setLogo(R.mipmap.logo_qzone);
        mToolBar.setTitle("Titile");
        mToolBar.setSubtitle("SubTitile");

    }


}
