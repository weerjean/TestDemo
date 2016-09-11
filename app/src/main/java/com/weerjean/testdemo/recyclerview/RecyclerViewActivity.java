package com.weerjean.testdemo.recyclerview;

import android.app.Dialog;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paem.okhttp.OkHttpUtils;
import com.paem.okhttp.callback.StringCallback;
import com.weerjean.testdemo.base.BaseToolbarActivity;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.recyclerview.adapter.ItemData;
import com.weerjean.testdemo.recyclerview.adapter.MyRecyclerViewAdapter;
import com.weerjean.testdemo.utils.DialogUtils;
import com.weerjean.testdemo.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by weerjean on 2016/7/9.
 * description：
 */
public class RecyclerViewActivity extends BaseToolbarActivity {

    // RecylerView 需要显示的数据
    private List<ItemData> mItemDatas;
    private RecyclerView mRecyclerView;
    // RecyclerView必须有一个LayoutManager
    private RecyclerView.LayoutManager mLayoutManager;
    // 最后一个可见Item位置
    private int mLastVisibleItem;
    // 分段加载的页数
    private int mPageCount = 9;
    private MyRecyclerViewAdapter mAdapter;
    private Dialog mDialog;
    //    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initDate();
        initEvent();
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

        if (mItemDatas != null) {

            switch (item.getGroupId()) {
                case 0:
                    switch (item.getItemId()) {
                        case 0:
                            ToastUtils.toast(this, "ListView标准显示");
                            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                        case 1:
                            ToastUtils.toast(this, "ListView垂直反向");
                            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                        case 2:
                            ToastUtils.toast(this, "ListView水平显示");
                            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                        case 3:
                            ToastUtils.toast(this, "ListView水平反向");
                            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                    }
                    break;
                case 1:
                    switch (item.getItemId()) {
                        case 0:
                            ToastUtils.toast(this, "GridView标准显示");
                            mLayoutManager = new GridLayoutManager(this,3);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                        case 1:
                            ToastUtils.toast(this, "GridView垂直反向");
                            mLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,true);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                        case 2:
                            ToastUtils.toast(this, "GridView水平显示");
                            mLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                        case 3:
                            ToastUtils.toast(this, "GridView水平反向");
                            mLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,true);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                    }
                    break;
                case 2:
                    switch (item.getItemId()) {
                        case 0:
                            ToastUtils.toast(this, "瀑布流标准显示");
                            mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                        case 1:
                            ToastUtils.toast(this, "瀑布流垂直反向");
                            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                            staggeredGridLayoutManager.setReverseLayout(true);
                            mLayoutManager=staggeredGridLayoutManager;
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                        case 2:
                            ToastUtils.toast(this, "瀑布流水平显示");
                            mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                        case 3:
                            ToastUtils.toast(this, "瀑布流水平反向");
                            StaggeredGridLayoutManager staggeredGridLayoutManager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
                            staggeredGridLayoutManager2.setReverseLayout(true);
                            mLayoutManager=staggeredGridLayoutManager2;
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(this, mItemDatas);
                            mRecyclerView.setAdapter(mAdapter);
                            break;
                    }
                    break;
                default:


            }

        } else {
            ToastUtils.toast(this, "数据为空");
            initDate();
        }
        return true;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recylerview);
//        mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshLayout);

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        }); // 设置分割线


    }

    private void initDate() {

        OkHttpUtils.get().url("http://gank.io/api/data/福利/10/" + mPageCount)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onBefore(Request request, int id) {
                        if(mDialog==null){
                            mDialog = DialogUtils.getCommDialog(RecyclerViewActivity.this, "加载中...");
                        }
                        mDialog.show();

                    }

                    @Override
                    public void onAfter(int id) {
                        mDialog.dismiss();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.toast(RecyclerViewActivity.this, "数据请求失败");
                    }

                    @Override
                    public void onResponse(Call call, String response, int id) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String jsonData = jsonObject.getString("results");
                            if (mItemDatas == null) {
                                mItemDatas = new ArrayList<ItemData>();
                            }

                            ArrayList<ItemData> itemDatas = new Gson().fromJson(jsonData, new TypeToken<List<ItemData>>() {
                            }.getType());
                            mItemDatas.addAll(itemDatas);
                            mPageCount++;
                            if (mAdapter != null) mAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }


    private void initEvent() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisibleItem + 2 >= mLayoutManager.getItemCount()) {
                    initDate();
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(mLayoutManager instanceof LinearLayoutManager){
                    LinearLayoutManager linearLayoutManager=(LinearLayoutManager)mLayoutManager;
                    // 获取最后一个被显示的Item的位置
                    mLastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                }else if(mLayoutManager instanceof GridLayoutManager){
                    GridLayoutManager gridLayoutManager =(GridLayoutManager)mLayoutManager;
                    mLastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();

                }else{
                    StaggeredGridLayoutManager staggeredGridLayoutManager =(StaggeredGridLayoutManager)mLayoutManager;
                    int[] lastVisibleItemPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
                    Arrays.sort(lastVisibleItemPositions);
                    mLastVisibleItem=lastVisibleItemPositions[lastVisibleItemPositions.length-1];
                }

            }
        });
    }
}
