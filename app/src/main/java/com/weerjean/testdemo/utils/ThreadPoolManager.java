package com.weerjean.testdemo.utils;

import com.weerjean.testdemo.network.NetWorkActivity;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by : weiwenjie986 on 18/10/29 下午8:32.
 * Description :线程池管理工具类
 */

public class ThreadPoolManager {

    private static volatile ThreadPoolManager mInstance;
    private static  ScheduledThreadPoolExecutor mExecutor;

    private ThreadPoolManager(){
        int num = Runtime.getRuntime().availableProcessors();
        mExecutor = new ScheduledThreadPoolExecutor(num * 2 + 1);
    }

    public static ThreadPoolManager getInstance(){

        if(mInstance==null){
            synchronized (ThreadPoolManager.class){
                if(mInstance==null){
                    mInstance = new ThreadPoolManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 添加任务
     * @param task 任务线程
     */
    public void execut(Runnable task){
        mExecutor.submit(task);
    }


}
