package com.zhengsr.wanandroid;

import android.app.Application;
import android.content.Context;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class MainApplication extends Application {
    public static Context INSTANCE;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
