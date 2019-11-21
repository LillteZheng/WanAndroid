package com.zhengsr.wanandroid.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class DpUtils {
    /**
     * 拿到dp
     * @param dp
     * @return
     */
    public static int getIntDpSize(Context context,int dp){
        return (int) getDpSize(context,dp);
    }
    /**
     * 拿到dp
     * @param dp
     * @return
     */
    public static float getDpSize(Context context,float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
