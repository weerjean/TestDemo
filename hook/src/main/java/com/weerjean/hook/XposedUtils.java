package com.weerjean.hook;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * Created by : weerjean on 18/12/10 下午6:06.
 * Description :hook 工具类
 */
public class XposedUtils implements IXposedHookLoadPackage {

    private static final String TAG = "XposedUtils";

    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {

        Log.d(TAG,"handleLoadPackage:"+loadPackageParam.packageName);

        if(loadPackageParam.packageName.equals("com.weerjean.testdemo")) {
            Log.d(TAG,"packageName="+"com.weerjean.testdemo");
            //方式一：测试不生效
//            XposedHelpers.findAndHookMethod("com.weerjean.testdemo.other.OtherActivity", loadPackageParam.classLoader, "isHooked",
//                    XC_MethodReplacement.returnConstant(true));

            //方式二：测试不生效
//            XposedHelpers.findAndHookMethod("com.weerjean.testdemo.other.OtherActivity", loadPackageParam.classLoader,
//                "isHooked", new XC_MethodHook() {
//
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                        Log.d(TAG,"beforeHookedMethod:getResoult="+param.getResult());
//                        param.setResult(true);
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        Log.d(TAG,"afterHookedMethod:getResoult="+param.getResult());
//                        param.setResult(true);
//                    }
//                });

            //方式三：测试有效
            Class clazz = loadPackageParam.classLoader.loadClass("com.weerjean.testdemo.other.OtherActivity");

            XposedHelpers.findAndHookMethod(clazz, "isHooked", new XC_MethodHook() {

                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
//                    Log.d(TAG,"beforeHookedMethod:getResoult="+param.getResult());

                }
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //Log.d(TAG,"afterHookedMethod:getResoult="+param.getResult());
                    param.setResult(true);
                }
            });

        }
    }

}
