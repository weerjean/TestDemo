package com.weerjean.testdemo.lbs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.hy.okhttp.utils.L;
import com.weerjean.lbs.LocationTest;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.BaseToolbarActivity;
import com.weerjean.testdemo.utils.ToastUtils;
import java.util.List;

/**
 * Created by : weerjean on 18/10/31 下午4:39. Description :
 */
public class LocationActivity extends BaseToolbarActivity implements OnClickListener {

    private static final String TAG = "LocationActivity";

    private TextView mTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mTextView  = (TextView) findViewById(R.id.tv_location_result);
        Button locatStartBtn = (Button) findViewById(R.id.btn_location_start);
        Button locatStopBtn = (Button) findViewById(R.id.btn_location_stop);
        Button locatOpenFrontBtn = (Button) findViewById(R.id.btn_location_openfront);
        Button locatCloseFrontBtn = (Button) findViewById(R.id.btn_location_closefront);
        Button locatAllowMockBtn = (Button) findViewById(R.id.btn_location_allow_mock);
        Button locatIsDistortBtn = (Button) findViewById(R.id.btn_location_is_distort);
        locatStartBtn.setOnClickListener(this);
        locatStopBtn.setOnClickListener(this);
        locatOpenFrontBtn.setOnClickListener(this);
        locatCloseFrontBtn.setOnClickListener(this);
        locatAllowMockBtn.setOnClickListener(this);
        locatIsDistortBtn.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_location_start:
                // 获取定位
                getLocation();

                break;
            case R.id.btn_location_stop:
                LocationUtils.getInstance().stopLocation();
                break;

            case R.id.btn_location_openfront:
                LocationUtils.getInstance().openFrontLocation(getApplicationContext());
                break;
            case R.id.btn_location_closefront:
                LocationUtils.getInstance().closeFrontLocation();
                break;
            case R.id.btn_location_allow_mock:
                TestLBSUtils.isDistort(this);
                break;
            case R.id.btn_location_is_distort:
                boolean isDistort = TestLBSUtils.isDistort2(this);
                if(isDistort){
                    mTextView.setText("模拟定位被开启");
                }else{
                    mTextView.setText("模拟定位被关闭");
                }
                break;
            default:
                ToastUtils.toast(this,"点击失败没有找到此控件");
        }
    }

    private void getLocation() {
        if(ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat
                .requestPermissions(LocationActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},200);
        }else{
            Toast.makeText(LocationActivity.this,"已开启定位权限",Toast.LENGTH_LONG).show();
            LocationUtils.getInstance().startLocation();
            LocationUtils.getInstance().getLocation(new BDAbstractLocationListener() {
                @Override
                public void onReceiveLocation(BDLocation location) {
                    double latitude = location.getLatitude();    //获取纬度信息
                    double longitude = location.getLongitude();    //获取经度信息
                    float radius = location.getRadius();    //获取定位精度，默认值为0.0f

                    String coorType = location.getCoorType();//获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

                    int errorCode = location.getLocType();//获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

                    String addr = location.getAddrStr();    //获取详细地址信息
                    String country = location.getCountry();    //获取国家
                    String province = location.getProvince();    //获取省份
                    String city = location.getCity();    //获取城市
                    String district = location.getDistrict();    //获取区县
                    String street = location.getStreet();    //获取街道信息
                    String locationDescribe = location.getLocationDescribe();    //获取位置描述信息
                    List<Poi> poiList = location.getPoiList();//获取周边POI信息

                    String buildingID="";
                    String buildingName="";
                    String floor="";

                    if (location.getFloor() != null) {
                        // 当前支持高精度室内定位
                        buildingID = location.getBuildingID();// 百度内部建筑物ID
                        buildingName = location.getBuildingName();// 百度内部建筑物缩写
                        floor = location.getFloor();// 室内定位的楼层信息，如 f1,f2,b1,b2
                    }

                    String result = "errorCode："+errorCode+"\r\n"+
                        "latitude："+latitude+"\r\n"+
                        "longitude："+longitude+"\r\n"+
                        "radius："+radius+"\r\n"+
                        "coorType："+coorType+"\r\n"+
                        "addr："+addr+"\r\n"+
                        "country："+country+"\r\n"+
                        "province："+province+"\r\n"+
                        "city："+city+"\r\n"+
                        "district："+district+"\r\n"+
                        "street："+street+"\r\n"+
                        "locationDescribe："+locationDescribe+"\r\n"+
                        "poiList："+poiList+"\r\n"+
                        "buildingID："+buildingID+"\r\n"+
                        "buildingName："+buildingName+"\r\n"+
                        "floor："+floor+"\r\n"+
                        "location："+location.toString();

                    L.d(TAG,result);
                    mTextView.setText(result);
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //刚才的识别码
        if (requestCode == 200) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                Toast.makeText(LocationActivity.this, "已开启定位权限", Toast.LENGTH_LONG).show();
            } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                Toast.makeText(LocationActivity.this, "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(LocationActivity.this, "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
        }
    }

}
