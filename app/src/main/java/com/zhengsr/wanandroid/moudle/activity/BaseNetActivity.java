package com.zhengsr.wanandroid.moudle.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhengsr.wanandroid.mvp.base.BasePresent;


/**
 * @author by  zhengshaorui on 2019/10/11
 * Describe: 默认网络的父类，即先加载 loading 页面
 */
public abstract class BaseNetActivity<T extends BasePresent> extends DelegateActivity<T> {
    /**
     * static
     */
    private static final int NORMAL_VIEW = 1;
    private static final int LOADING_VIEW = 2;
    private static final int ERROR_VIEW = 3;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 三种状态添加进去
         */
        mParentView.addView(mLoadingView);
        mParentView.addView(mErrorView);
        mParentView.addView(mNormalView);
        //mvp
        mPresent = getPresent();
        if (mPresent != null){
            mPresent.attachView(this);
        }
        initView();
        initData();
        if (showNormalView()){
            showCurrentView(NORMAL_VIEW);
        }
    }


    /**
     * 接口的一些基本实现
     */
    @Override
    public void showLoading() {
        super.showLoading();
        showCurrentView(LOADING_VIEW);
    }


    @Override
    public void loadSuccess() {
        super.loadSuccess();
        showCurrentView(NORMAL_VIEW);
    }

    @Override
    public void showError() {
        super.showError();
        showCurrentView(ERROR_VIEW);
    }


    /**
     * 切换不同的页面
     * @param type
     */
    private void showCurrentView(int type){
        if (mErrorView == null || mNormalView == null || mLoadingView == null){
            return;
        }

        mNormalView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);

        if (type == NORMAL_VIEW){
            mNormalView.setVisibility(View.VISIBLE);
        }else if (type == LOADING_VIEW){
            mLoadingView.setVisibility(View.VISIBLE);
        }else if (type == ERROR_VIEW){
            mErrorView.setVisibility(View.VISIBLE);
        }
    }



    @Override
    public boolean init() {
        return false;
    }

    /**
     * 默认加载正常页面，如果返回false，则先显示加载动画的页面
     * @return
     */
    protected boolean showNormalView(){
        return true;
    }


}
