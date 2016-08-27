package com.weerjean.testdemo.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.weerjean.testdemo.BaseToolbarActivity;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.utils.ToastUtils;

/**
 * Created by weerjean on 2016/7/9.
 * description：
 */
public class RecyclerViewActivity extends BaseToolbarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 方式2：代码设置方式
        //用代码的方式添加 菜单
        SubMenu menu0 = menu.addSubMenu(100, 0, 0, "List显示");
        menu0.addSubMenu(0, 0, 0, "标准");
        menu0.addSubMenu(0, 1, 0, "垂直反向");
        menu0.addSubMenu(0, 2, 0, "水平");
        menu0.addSubMenu(0, 3, 0, "水平反向");
        SubMenu menu1 = menu.addSubMenu(100, 1, 0, "Grid显示");
        menu1.addSubMenu(1, 0, 0, "标准");
        menu1.addSubMenu(1, 1, 0, "垂直反向");
        menu1.addSubMenu(1, 2, 0, "水平");
        menu1.addSubMenu(1, 3, 0, "水平反向");
        SubMenu menu2 = menu.addSubMenu(100, 2, 0, "瀑布流显示");
        menu2.addSubMenu(2, 0, 0, "标准");
        menu2.addSubMenu(2, 1, 0, "垂直反向");
        menu2.addSubMenu(2, 2, 0, "水平");
        menu2.addSubMenu(2, 3, 0, "水平反向");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getGroupId()){
            case 0:
                switch (item.getItemId()){
                    case 0:
                        ToastUtils.toast(this,"ListView标准显示");

                        break;
                    case 1:
                        ToastUtils.toast(this,"ListView垂直反向");
                        break;
                    case 2:
                        ToastUtils.toast(this,"ListView水平显示");
                        break;
                    case 3:
                        ToastUtils.toast(this,"ListView水平反向");
                        break;
                }
                break;
            case 1:
                switch (item.getItemId()){
                    case 0:
                        ToastUtils.toast(this,"GridView标准显示");
                        break;
                    case 1:
                        ToastUtils.toast(this,"GridView垂直反向");
                        break;
                    case 2:
                        ToastUtils.toast(this,"GridView水平显示");
                        break;
                    case 3:
                        ToastUtils.toast(this,"GridView水平反向");
                        break;
                }
                break;
            case 2:
                switch (item.getItemId()){
                    case 0:
                        ToastUtils.toast(this,"瀑布流标准显示");
                        break;
                    case 1:
                        ToastUtils.toast(this,"瀑布流垂直反向");
                        break;
                    case 2:
                        ToastUtils.toast(this,"瀑布流水平显示");
                        break;
                    case 3:
                        ToastUtils.toast(this,"瀑布流水平反向");
                        break;
                }
                break;
            default:


        }

        return true;
    }

    @Override
    protected void initView() {
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recylerview);
    }
}
