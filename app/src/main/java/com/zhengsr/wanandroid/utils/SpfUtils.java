package com.zhengsr.wanandroid.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.zhengsr.wanandroid.MainApplication;

import java.util.Map;
import java.util.Set;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe: sharepreference 工具类
 */
public class SpfUtils {
    private static final String FILE_NAME = "qixin";
    private static Context sContext = MainApplication.INSTANCE;
    /**
     * 保存数据
     * @param key
     * @param value
     */
    public static <T> void put(String key,T value){
        SharedPreferences.Editor editor =
                sContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        if (value instanceof String
                ||value instanceof Integer
                ||value instanceof Boolean
                || value instanceof Float
                || value instanceof Long
                ||value instanceof Double) {
            //转换成 string，这样封边封装
            editor.putString(key, String.valueOf(value));
        }else{
            editor.putStringSet(key, (Set<String>) value);
        }
        editor.commit();
    }

    /**
     * 拿到数值
     * @param <T>
     * @return
     */
    public static <T> T get(String key,T defaultValue){
        SharedPreferences sp = sContext.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        if (defaultValue instanceof String
                ||defaultValue instanceof Integer
                || defaultValue instanceof Boolean
                || defaultValue instanceof Float
                || defaultValue instanceof Long
                ||defaultValue instanceof Double) {
            return (T) sp.getString(key, String.valueOf(defaultValue));
        }else {
            return (T) sp.getStringSet(key, (Set<String>) defaultValue);
        }
    }

    /**
     * 移除某个key值已经对应的值
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor =
                sContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences.Editor editor =
                sContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAll() {
        SharedPreferences sp = sContext.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sp.getAll();
    }

}