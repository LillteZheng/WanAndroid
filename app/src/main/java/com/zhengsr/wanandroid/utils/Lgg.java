package com.zhengsr.wanandroid.utils;

import android.util.Log;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class Lgg {
    private static final String TAG = "Lgg";
    public static boolean ENABLE = true;
    public static void d(String msg){
        if (ENABLE){
            Log.d(TAG, "zsr - "+msg);
        }
    }
}
