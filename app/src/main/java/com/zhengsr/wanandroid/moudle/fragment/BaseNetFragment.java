package com.zhengsr.wanandroid.moudle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhengsr.wanandroid.mvp.base.BasePresent;


/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe: 基于 mvp + rxjava + retrofit
 */
public abstract class BaseNetFragment<T extends BasePresent> extends DelegateFragment<T> {
    /**
     * static
     */
    private static final String TAG = "BaseNetFragment";
    private static final int NORMAL_VIEW = 1;
    private static final int LOADING_VIEW = 2;
    private static final int ERROR_VIEW = 3;



    @Override
    public void onLayInitView() {
        super.onLayInitView();
        /**
         * 三种状态添加进去
         */
        mParentView.addView(mLoadingView);
        mParentView.addView(mErrorView);
        mParentView.addView(mNormalView);


        mPresent = getPresent();
        if (mPresent != null) {
            mPresent.attachView(this);
        }

        if (showNormalView()){
            showCurrentView(NORMAL_VIEW);
        }
        initView(mNormalView);
        initData();
    }

    /**
     * logic
     */



    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);



    }





    @Override
    protected boolean init() {
        return false;
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

    @Override
    public void showErrorMsg(String msg) {
        super.showErrorMsg(msg);
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
    public boolean firstLoadAnim() {
        return true;
    }

    /**
     * 默认加载正常页面，如果返回false，则先显示加载动画的页面
     * @return
     */
    protected boolean showNormalView(){
        return true;
    }



}
