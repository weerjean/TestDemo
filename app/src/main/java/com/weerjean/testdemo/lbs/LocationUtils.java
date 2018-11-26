package com.weerjean.testdemo.lbs;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.weerjean.testdemo.MainActivity;
import com.weerjean.testdemo.R;

/**
 * Created by : weiwenjie986 on 18/10/31 下午1:35. Description :
 */
public class LocationUtils {

    private static final String TAG = "LocationUtils";

    private static volatile LocationUtils mLocationUtils;

    public LocationClient mLocationClient = null;

    private LocationUtils(){}

    public static LocationUtils getInstance(){
        if(mLocationUtils==null){
            synchronized (LocationUtils.class){
                if(mLocationUtils==null){
                    mLocationUtils = new LocationUtils();
                }
            }
        }
        return mLocationUtils;
    }

    public void initLocation(Context context){



        //声明LocationClient类
        mLocationClient = new LocationClient(context);

        // 获取定位参数
        LocationClientOption option = getLocationClientOption();

        //设置定位参数
        mLocationClient.setLocOption(option);

        // 开启室内定位模式（重复调用也没问题），开启后，定位SDK会融合各种定位信息（GPS,WI-FI，蓝牙，传感器等）连续平滑的输出定位结果；
        mLocationClient.startIndoorMode();

    }

    /**
     * 开启定位
     */
    public void startLocation(){
        //调用LocationClient的start()方法，便可发起定位请求
        mLocationClient.start();
    }

    /**
     * 停止定位
     */
    public void stopLocation(){
        //调用LocationClient的start()方法，便可发起定位请求
        mLocationClient.stop();
    }

    /**
     * 获取定位服务
     * @param listener 回调的listener
     */
    public void getLocation(BDAbstractLocationListener listener){
        //注册监听函数
        mLocationClient.registerLocationListener(listener);
    }

    /**
     * 开启前台定位服务
     */
    public void openFrontLocation(Context context){
        /*
        Android 8.0系统为实现降低功耗，无论应用的目标SDK版本为多少，Android 8.0系统都会对后台应用获取用户当前位置的频率进行限制，只允许后台应用每小时接收几次位置更新。
        根据Android 8.0的开发指引，为了适配这一系统特性，百度地图定位SDK新增加接口。它的实现原理就是开发者通过新接口生成一个前台服务通知，使得开发者自己的应用退到后台的时候，仍有前台通知在，规避了Android 8.0系统对后台定位的限制。
        注意：如果您的App在退到后台时本身就有前台服务通知，则无需按照本章节的介绍做适配。
        */
        //Android开启前台定位服务：
        Notification.Builder builder = new Notification.Builder (context);
        //获取一个Notification构造器
        Intent nfIntent = new Intent(context, MainActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(context, 0, nfIntent, 0)) // 设置PendingIntent
            .setContentTitle("正在进行后台定位") // 设置下拉列表里的标题
            .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
            .setContentText("后台定位通知") // 设置上下文内容
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        mLocationClient.enableLocInForeground(1001, notification);// 调起前台定位
    }

    /**
     * 关闭前台定位服务
     */
    public void closeFrontLocation(){
        //停止前台定位服务：
        mLocationClient.disableLocInForeground(true);// 关闭前台定位，同时移除通知栏
    }


    /**
     * 获取option，设置定位参数
     * @return LocationClientOption
     */
    private LocationClientOption getLocationClientOption() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认GCJ02
        //GCJ02：国测局坐标；
        //BD09ll：百度经纬度坐标；
        //BD09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

        option.setScanSpan(1000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5*60*1000);
        //可选，V7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

//        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        option.setIsNeedLocationDescribe(true);
        //可选，是否需要位置描述信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的位置信息，此处必须为true

        option.setIsNeedLocationPoiList(true);
        //可选，是否需要周边POI信息，默认为不需要，即参数为false
        //如果开发者需要获得周边POI信息，此处必须为true

        return option;
    }


}
