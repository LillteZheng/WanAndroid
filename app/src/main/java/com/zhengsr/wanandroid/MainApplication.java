package com.zhengsr.wanandroid;

import android.app.Application;
import android.content.Context;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class MainApplication extends Application {

    public static Context INSTANCE;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = getApplicationContext();

    }
}
