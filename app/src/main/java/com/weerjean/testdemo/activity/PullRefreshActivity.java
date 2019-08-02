package com.weerjean.testdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.DividerItemDecoration;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.hy.okhttp.OkHttpUtils;
import com.hy.okhttp.callback.StringCallback;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.BaseToolbarActivity;
import com.weerjean.testdemo.bean.UpdateHistoryBean;
import com.weerjean.testdemo.recyclerview.adapter.ItemData;
import com.weerjean.testdemo.recyclerview.adapter.PullRefreshAdapter;
import com.weerjean.testdemo.utils.Constants;
import com.weerjean.testdemo.utils.ToastUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by weerjean at 2019/7/3
 * description:
 */
public class PullRefreshActivity extends BaseToolbarActivity {

    private static final String TAG = "PullRefreshActivity";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private PullRefreshAdapter mAdapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initRefreshLayout();
        initData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pull_refresh;
    }

    private void initRefreshLayout() {

        //下拉刷新的圆圈是否显示
        refreshLayout.setRefreshing(false);

        //设置下拉时圆圈的颜色（可以由多种颜色拼成）
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);

        //设置下拉时圆圈的背景颜色（这里设置成白色）
        refreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);

        //设置下拉刷新时的操作
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getNetWorkDate();

            }
        });

    }

    /**
     * 初始化视图
     */
    private void initView() {
        mRecyclerView = findViewById(R.id.rv_info_update_history);
        refreshLayout = findViewById(R.id.swiperefreshlayout);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //添加默认的分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PullRefreshAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.invalidate();
    }


    /**
     * 初始化数据
     */
    private void initData() {
        getNetWorkDate();
    }

    public void showToast(String msg) {
        ToastUtils.toast(this,msg);
    }

    public void showProgress(){
        refreshLayout.setRefreshing(true);

    }

    public void closeProgress(){
        refreshLayout.setRefreshing(false);
    }

    /**
     * 重新加载数据
     * @param list
     */
    public void refreshRV(List list){
        mAdapter.setData(list);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param list
     */
    public void addDataAndRefreshRV(List list){
        mAdapter.addData(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        closeProgress();
        super.onDestroy();
    }

    /**
     * 网络查询历史更新记录
     */
    public void getNetWorkDate() {

        String url = Constants.JSON_URL;

        OkHttpUtils.get().url(url).build().execute(new StringCallback() {

            @Override
            public void onBefore(Request request, int id) {
                showProgress();
            }

            @Override
            public void onAfter(int id) {
                closeProgress();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
                showToast("网络请求失败");

            }

            @Override
            public void onResponse(Call call, String response, int id) {
                ArrayList<ItemData> list = parseJson(response);
                if(list!=null && list.size()>0){
                    refreshRV(list);
                }
            }
        });
    }

    /**
     * 解析查询结果
     * @param result
     */
    private ArrayList<ItemData> parseJson(String result) {

        if(TextUtils.isEmpty(result)){
            showToast("返回数据为空");
        }else{
            try {
                JSONObject jsonObject = new org.json.JSONObject(result);
                boolean error = jsonObject.optBoolean("error", true);
                JSONArray results = jsonObject.optJSONArray("results");
                if(!error){
                    ArrayList<ItemData> list = new ArrayList<>();
                    for (int i = 0; i < results.length(); i++) {
                        String  object = results.optString(i);
                        Gson gson = new Gson();
                        ItemData itemData = gson.fromJson(object, ItemData.class);
                        list.add(itemData);
                    }
                    return list;
                }else{
                    Log.d(TAG,error+" "+result);
                    showToast("数据返回失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showToast("数据解析失败");
            }
        }
        return null;
    }

}
