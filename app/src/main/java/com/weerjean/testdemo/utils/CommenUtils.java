package com.weerjean.testdemo.utils;


import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Debug;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import com.hy.okhttp.utils.L;
import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import com.jaredrummler.android.processes.models.Stat;
import com.jaredrummler.android.processes.models.Statm;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.base.MyApplication;
import java.io.File;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by : weiwenjie986 on 18/11/1 下午3:09. Description :
 */
public class CommenUtils {

    private static final String TAG = "CommenUtils";

    public static String getExtCacheDir(){

        String path =  MyApplication.getInstance().getExternalCacheDir().getAbsolutePath()+File.pathSeparator+"weerjean/";

        return path;
    }

    /**
     * 匹配<a></a>转换为ClickableSpan
     * @param contentMessage
     * @param view
     * @return
     */
    public SpannableString getMessageToSpannable(String contentMessage, final View view) {
        String regEx = "<a>([\\s\\S]*?)</a>";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(contentMessage);

        List<String> listMessage = new ArrayList<>();//截取后的消息内容
        List<Integer> listLocation = new ArrayList<>();//截取的位置

        int i = 0;
        while (matcher.find()) {
            listLocation.add(matcher.start() - i * 7);
            listMessage.add(matcher.group().replace("<a>", "").replace("</a>", ""));
            i++;
        }

        SpannableString sps = new SpannableString(contentMessage.replace("<a>", "").replace("</a>", ""));

        for (i = 0; i < listMessage.size(); i++) {
            if (TextUtils.isEmpty(listMessage.get(i)))
                continue;

            final String content = listMessage.get(i);
            sps.setSpan(new ClickableSpan() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.parseColor("#EB6100"));       //设置文件颜色
                    ds.setUnderlineText(true);      //设置下划线
                }

                @Override
                public void onClick(View widget) {
                    view.callOnClick();
                }
            }, listLocation.get(i), listLocation.get(i) + content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return sps;
    }

    /**
     * 是否使用代理
     */
    public static boolean isWifiProxy(){
        final boolean is_ics_or_later = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
        String proxyAddress;
        int proxyPort;
        if (is_ics_or_later) {
            proxyAddress = System.getProperty("http.proxyHost");
            String portstr = System.getProperty("http.proxyPort");
            proxyPort = Integer.parseInt((portstr != null ? portstr : "-1"));
            System.out.println(proxyAddress + "~");
            System.out.println("port = " + proxyPort);
        }else {
            proxyAddress = android.net.Proxy.getHost(MyApplication.getInstance());
            proxyPort = android.net.Proxy.getPort(MyApplication.getInstance());
            Log.e("address = ", proxyAddress + "~");
            Log.e("port = ", proxyPort + "~");
        }
        return (!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1);
    }

    /**
     * 是否正在使用VPN
     */
    public static boolean isVpnUsed() {
        try {
            Enumeration<NetworkInterface> niList = NetworkInterface.getNetworkInterfaces();
            if(niList != null) {
                for (NetworkInterface intf : Collections.list(niList)) {
                    if(!intf.isUp() || intf.getInterfaceAddresses().size() == 0) {
                        continue;
                    }
                    Log.d("-----", "isVpnUsed() NetworkInterface Name: " + intf.getName());
                    if ("tun0".equals(intf.getName()) || "ppp0".equals(intf.getName())){
                        return true; // The VPN is up
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 判断是否安装hook工具
     * @return
     */
    public static boolean isInstalledHookTools(){

        PackageManager packageManager = MyApplication.getInstance().getPackageManager();

        List<PackageInfo> packages = packageManager.getInstalledPackages(0);
        for(int i=0;i<packages.size();i++) {
            PackageInfo packageInfo = packages.get(i);
            String packageName = packageInfo.packageName;
            String processName = packageInfo.applicationInfo.processName;

            L.d(TAG,packageName);
            L.d(TAG,processName);
            if (packageName.contains("xposed")||packageName.contains("substrate")){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取正在运行的进程列表，5.0及以下系统可用
     * Android6.0以上不可以，系统关闭了三方软件对getRunningAppProcesses的访问，出于安全考虑。
     */
    public  static List<ProcessInfo> getProcessInfo(Context context){

        ArrayList<ProcessInfo> processInfoList = new ArrayList<>();
        ActivityManager systemService = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = systemService.getRunningAppProcesses();

        PackageManager PM = context.getPackageManager();

        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {

            ProcessInfo processInfo = new ProcessInfo();

            processInfo.packageName= runningAppProcess.processName;

            //获取系统占用的内存大小
            int pid = runningAppProcess.pid;
            Debug.MemoryInfo[] processMemoryInfo = systemService.getProcessMemoryInfo(new int[]{pid});

            //获取已使用的大小：
            processInfo.memeSize= processMemoryInfo[0].getTotalPrivateDirty()*1024;

            //获取应用的名称
            try {
                ApplicationInfo applicationInfo = PM.getApplicationInfo(processInfo.packageName, 0);

                processInfo.name= applicationInfo.loadLabel(PM).toString();

                processInfo.icon= applicationInfo.loadIcon(PM);

                if ((applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==ApplicationInfo.FLAG_SYSTEM){
                    processInfo.isSystem=true;
                }else {
                    processInfo.isSystem=false;
                }
            } catch (PackageManager.NameNotFoundException e) {
                processInfo.name=processInfo.packageName;
                processInfo.icon=context.getResources().getDrawable(R.mipmap.ic_launcher);
                processInfo.isSystem=true;
                e.printStackTrace();
            }
            processInfoList.add(processInfo);
        }
        return processInfoList;
    }

    /**
     * 通过获取最近一分钟之内活动的进行获取进程列表，Android7.0以上不可用
     * @param context
     * @return
     */
    public static List<ProcessInfo> getProcessInfo2(Context context){
        ArrayList<ProcessInfo> processInfoList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager m = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            if (m != null) {
                long now = System.currentTimeMillis();
                //获取60秒之内的应用数据
                List<UsageStats> stats = m.queryUsageStats(UsageStatsManager.INTERVAL_BEST, now - 60 * 1000, now);
                ActivityManager systemService = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                Log.i(TAG, "Running app number in last 60 seconds : " + stats.size());
                //取得最近运行的一个app，即当前运行的app
                if ((stats != null) && (!stats.isEmpty())) {
                    for (int i = 0; i < stats.size(); i++) {
                       /* if (stats.get(i).getLastTimeUsed() > stats.get(j).getLastTimeUsed()) {
                            j = i;
                        }*/
                        int i1 = stats.get(i).describeContents();
                        String processName = stats.get(i).getPackageName();
                        Log.i(TAG, "top running app is : " + processName);
                        PackageManager PM = context.getPackageManager();
                        ProcessInfo processInfo=new ProcessInfo();
                        processInfo.packageName= processName;
                        //获取应用的名称
                        try {
                            ApplicationInfo applicationInfo = PM.getApplicationInfo(processInfo.packageName, 0);

                            processInfo.name= applicationInfo.loadLabel(PM).toString();

                            processInfo.icon= applicationInfo.loadIcon(PM);

                            if ((applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==ApplicationInfo.FLAG_SYSTEM){
                                processInfo.isSystem=true;
                            }else {
                                processInfo.isSystem=false;
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            processInfo.name=processInfo.packageName;
                            processInfo.icon=context.getResources().getDrawable(R.mipmap.ic_launcher);
                            processInfo.isSystem=true;
                            e.printStackTrace();
                        }
                        processInfoList.add(processInfo);
                    }
                }
            }
        }

        return processInfoList;
    }


    /**
     * 通过开源库，读取/proc/[pid]/maps文件中的信息获取进程列表，Android7.0以上不可用
     * @param context
     * @return
     */
    public static List<ProcessInfo> getProcessInfo3(Context context){

        ArrayList<ProcessInfo> processInfoList = new ArrayList<>();
        // Get a list of running apps
        try {
            List<AndroidAppProcess> processes = AndroidProcesses.getRunningAppProcesses();

            for (AndroidAppProcess process : processes) {
                // Get some information about the process
                ProcessInfo processInfo=new ProcessInfo();
                processInfo.name = process.name;

                Stat stat = process.stat();
                int pid = stat.getPid();
                int parentProcessId = stat.ppid();
                long startTime = stat.stime();
                int policy = stat.policy();
                char state = stat.state();

                Statm statm = process.statm();
                long totalSizeOfProcess = statm.getSize();
                long residentSetSize = statm.getResidentSetSize();

                PackageInfo packageInfo = process.getPackageInfo(context, 0);
                processInfo.packageName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
                processInfoList.add(processInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return processInfoList;
    }

    public static class ProcessInfo {

        public String name;
        public String packageName;
        public int memeSize;
        public Drawable icon;
        public long memory;
        public boolean isUser;
        public boolean isSystem;
    }

}
