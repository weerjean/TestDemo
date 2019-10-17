package com.weerjean.testdemo.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.hy.okhttp.OkHttpUtils;
import com.hy.okhttp.callback.StringCallback;
import com.hy.okhttp.utils.L;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.BaseToolbarActivity;
import com.weerjean.testdemo.utils.Constants;
import com.weerjean.testdemo.utils.DialogUtils;
import com.weerjean.testdemo.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by weerjean at 2019/8/23
 * description:
 */
public class DxwActivity extends BaseToolbarActivity implements View.OnClickListener{

    private static final String TAG = "DxwActivity";

    private Button mGetDateBtn;
    private Button mAddConditionBtn;
    private LinearLayout mConditionSLL;
    private RecyclerView mResultRv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dxw;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initEvent();
    }



    private void initView() {
        mGetDateBtn = findViewById(R.id.btn_get_data);
        mAddConditionBtn = findViewById(R.id.btn_add_condition);
        mConditionSLL = findViewById(R.id.ll_conditions);
        mResultRv = findViewById(R.id.rv_result);
    }

    private void initEvent() {
        mGetDateBtn.setOnClickListener(this);
        mAddConditionBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_get_data :
                getData();
                break;
            case R.id.btn_add_condition:
                addCondition();
                break;
            default:
                ToastUtils.toast(this,"此点击为绑定点击事件");
        }

    }

    public void getData() {
        String url = Constants.DXW_BASE_URL + Constants.DXW_GET_LIST_URL;

        Map map = getParams();

        OkHttpUtils.get().url(url).params(map).build().execute(new StringCallback() {

            private Dialog mDialog;

            @Override
            public void onBefore(Request request, int id) {
                mDialog = DialogUtils.getCommDialog(DxwActivity.this, "加载中");
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.toast(DxwActivity.this,"网络请求失败");
            }

            @Override
            public void onResponse(Call call, String response, int id) {
                ToastUtils.toast(DxwActivity.this,"网络请求成功");
                parseResponse(response);
            }

            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                if(mDialog!=null&&mDialog.isShowing()){
                    mDialog.dismiss();
                }
            }
        });

    }

    /**
     * 解析网络数据
     * @param response
     */
    private void parseResponse(String response) {

        if(!TextUtils.isEmpty(response)){

            try {
                JSONObject jsonObject = new JSONObject(response);
                String code = jsonObject.optString("code", "");
                String msg = jsonObject.optString("msg", "");
                JSONObject data = jsonObject.optJSONObject("data");

                if(code.equals("10000")){
                    // 接口返回成功
                    JSONObject stockList = data.optJSONObject("stock_list");
                    JSONArray list = stockList.optJSONArray("list");
                    for (int i = 0; i < list.length(); i++) {

                        // 获取bp信息
                        JSONArray item = (JSONArray) list.get(i);
                        // 基本信息
                        JSONObject info = (JSONObject) item.get(0);
                        // 涨幅
                        JSONObject gainJson = (JSONObject) item.get(1);

                        String text = info.optString("text", "");
                        String[] split = text.split("#");

                        String name = split[1];
                        String gpcode = split[2];
                        String gain = gainJson.optString("text");
                        // 存储股票信息

                    }

                }else{
                    // 接口返回失败
                    ToastUtils.toast(DxwActivity.this,msg);
                    L.d(TAG,msg);
                }




            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



    }

    private void addCondition() {

    }



    /**
     * ?pagecount=20&page=1&need_title=need_title&day=20190823&refreshFlag=refreshFlag&apiversion=7.2&backgroundcolor=white&device_id=b94:87:E0:1E:DF:4D
     * @return
     */
    public Map getParams() {

        HashMap<String, String> params = new HashMap<>();

        params.put("pagecount","40");
        params.put("page","1");
        params.put("need_title","need_title");
        params.put("day","20190823");
        params.put("refreshFlag","refreshFlag");
        params.put("apiversion","7.2");
        params.put("backgroundcolor","white");
        params.put("device_id","b84:80:E5:1E:De:49");

        return params;
    }
}
