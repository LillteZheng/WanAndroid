package com.zhengsr.wanandroid.moudle.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author by  zhengshaorui on 2019/10/12
 * Describe: activity 统一入口类
 */
public abstract class BaseDelegateActivity<T extends BasePresent> extends SupportActivity  implements IBaseView{


    protected Unbinder mUnbinder;
    private T mPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        //初始化沉浸式
        initImmersionBar();
        //mvp
        mPresent = getPresent();
        if (mPresent != null) {
            mPresent.attachView(this);
        }
        initView();
        initDataAndEvent();
    }



    public abstract int getLayoutId();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        if (mPresent != null) {
            mPresent.detachView();
            mPresent = null;
        }
    }

    public void initImmersionBar(){
        ImmersionBar.with(this)
                .init();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 非必加
        // 如果你的app可以横竖屏切换，适配了4.4或者华为emui3.1系统手机，并且navigationBarWithKitkatEnable为true，
        // 请务必在onConfigurationChanged方法里添加如下代码（同时满足这三个条件才需要加上代码哦：1、横竖屏可以切换；2、android4.4或者华为emui3.1系统手机；3、navigationBarWithKitkatEnable为true）
        // 否则请忽略
       initImmersionBar();
    }

    public T getPresent(){
        return null;
    }

    public void initView(){}
    public void initDataAndEvent(){}


    @Override
    public void showLoading() {

    }

    @Override
    public void loadSuccess() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showErrorMsg(String msg) {

    }
}
