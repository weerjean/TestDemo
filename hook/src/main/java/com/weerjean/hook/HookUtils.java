package com.weerjean.hook;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * Created by : weerjean on 18/11/26 下午4:12.
 * Description :hook工具类
 */
public class HookUtils implements IXposedHookLoadPackage {

    private static final String TAG = "HookUtils";

    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {

        XposedBridge.log("handleLoadPackage: " + loadPackageParam.packageName);
        Log.d(TAG,"handleLoadPackage:"+loadPackageParam.packageName );
//        if (loadPackageParam.packageName.equals("com.weerjean.testdemo")){
//            XposedHelpers.findAndHookMethod("com.weerjean.testdemo.hook.HookActivity", loadPackageParam.classLoader, "isModuleActive", XC_MethodReplacement
//                    .returnConstant(true));
//        }


        if (loadPackageParam.packageName.equals("com.weerjean.testdemo")) {
            Class clazz = loadPackageParam.classLoader.loadClass("com.weerjean.testdemo.hook.HookActivity");

            XposedHelpers.findAndHookMethod(clazz, "isModuleActive", new XC_MethodHook() {

                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Log.d(TAG, "beforeHookedMethod");
                }
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d(TAG, "afterHookedMethod");
                    param.setResult(true);
                }
            });
        }

    }
}
