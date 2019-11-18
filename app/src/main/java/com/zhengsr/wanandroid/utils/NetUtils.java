package com.zhengsr.wanandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.zhengsr.wanandroid.MainApplication;


/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe:
 */
public class NetUtils {
    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MainApplication.INSTANCE.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
