package com.weerjean.testdemo.lbs;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.wifi.WifiManager;
import android.nfc.Tag;
import android.os.Build;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.hy.okhttp.utils.L;

/**
 * Created by : weerjean on 18/11/1 上午9:50. Description :
 */
public class TestLBSUtils {

    private static final String TAG = "TestLBSUtils";

    /**
     *
     */
    public static void isDistort(Context context){

        LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        int anInt;
        boolean hasAddTestProvider = false;
        boolean canMockPosition = false;
        try {
            anInt = Secure.getInt(context.getContentResolver(), Secure.ALLOW_MOCK_LOCATION);
            int i = Secure.getInt(context.getContentResolver(), Secure.LOCATION_MODE);
            String result = Secure.getString(context.getContentResolver(), Secure.ALLOW_MOCK_LOCATION)+"";
            String result1 = Secure.getString(context.getContentResolver(), Secure.LOCATION_PROVIDERS_ALLOWED)+"";
            String result2 = Secure.getInt(context.getContentResolver(), Secure.DEVELOPMENT_SETTINGS_ENABLED)+"";


            L.d(TAG,"anInt:"+anInt);
        } catch (SettingNotFoundException e) {
            e.printStackTrace();
        }

        canMockPosition = (Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION, 0) != 0)
            || Build.VERSION.SDK_INT > 22;
        if (canMockPosition && hasAddTestProvider == false) {
            try {
                String providerStr = LocationManager.GPS_PROVIDER;
                LocationProvider provider = locationManager.getProvider(providerStr);
                L.d(TAG,provider.toString());
                LocationProvider provider2 = locationManager.getProvider(providerStr);
                L.d(TAG,provider2.toString());
                if (provider != null) {
                    locationManager.addTestProvider(
                        provider.getName()
                        , provider.requiresNetwork()
                        , provider.requiresSatellite()
                        , provider.requiresCell()
                        , provider.hasMonetaryCost()
                        , provider.supportsAltitude()
                        , provider.supportsSpeed()
                        , provider.supportsBearing()
                        , provider.getPowerRequirement()
                        , provider.getAccuracy());
                } else {
                    locationManager.addTestProvider(
                        providerStr
                        , true, true, false, false, true, true, true
                        , Criteria.POWER_HIGH, Criteria.ACCURACY_FINE);
                }
                locationManager.setTestProviderEnabled(providerStr, true);
                locationManager.setTestProviderStatus(providerStr, LocationProvider.AVAILABLE, null, System.currentTimeMillis());

                // 模拟位置可用
                hasAddTestProvider = true;
                canMockPosition = true;
            } catch (SecurityException e) {
                canMockPosition = false;
            }
        }

        if(canMockPosition){
            context.startActivity(new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS));
        }

        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);



        L.d(TAG,"hasAddTestProvider:"+hasAddTestProvider);
        L.d(TAG,"canMockPosition:"+canMockPosition);

    }

    public static boolean isDistort2(Context context){
        boolean isOpen = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION, 0) != 0;
        return isOpen;
    }



}
