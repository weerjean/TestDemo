package com.weerjean.testdemo.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.weerjean.testdemo.R;
import com.weerjean.testdemo.utils.ToastUtils;

/**
 * Created by weerjean on 2016/7/10.
 */
public class ToolbarActivity extends AppCompatActivity{

    private Toolbar mToolbar;

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
                ToastUtils.toast(this, item.getTitle() + "被点击了");
                break;
            case R.id.action_settings2:
                ToastUtils.toast(this, item.getTitle() + "被点击了");
                break;

        }

        return true;
    }

    private void initEvent() {

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_1);
        initActionBar();
    }

    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_1);
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        // 如果不设置主标题，ToolBar上默认显示的AppName
        setTitle("ToolBar");
        setSupportActionBar(mToolbar);
        mToolbar.setLogo(R.mipmap.logo_qzone);
        mToolbar.setTitle("Titile");
        mToolbar.setSubtitle("SubTitile");

    }


}
