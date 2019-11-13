package com.zhengsr.wanandroid.moudle.activity;

import android.os.Bundle;
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
public abstract class DelegateActivity<T extends BasePresent> extends SupportActivity implements IBaseView {

    protected T mPresent;
    protected Unbinder mUnbinder;

    protected View mNormalView;
    protected FrameLayout mParentView;
    protected View mLoadingView;
    protected View mErrorView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentView = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.frame_base,null);
        setContentView(mParentView);
        mNormalView = LayoutInflater.from(this).inflate(getLayoutId(),mParentView,false);
        //绑定butterknife
        mUnbinder = ButterKnife.bind(this,mNormalView);
        ImmersionBar.with(this)
                .init();
        configOtherView();

        if (init()) {
            mParentView.addView(mNormalView);

            //mvp
            mPresent = getPresent();
            if (mPresent != null){
                mPresent.attachView(this);
            }

            initView();
            initData();
        }
    }

    private void configOtherView() {
        mLoadingView = LayoutInflater.from(this).inflate(R.layout.loadingview_layout,mParentView,false);
        mErrorView = LayoutInflater.from(this).inflate(R.layout.load_error_layout,mParentView,false);
        /**
         * 失败时，提供加载按钮
         */
        mErrorView.findViewById(R.id.error_reload_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresent != null) {
            mPresent.detachView();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

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
        showError();
    }

    public boolean init(){
        return true;
    }

    /**
     * 子类实现
     */

    public  T getPresent(){
        return null;
    }

    public abstract int getLayoutId();
    public void initView(){}
    public void initData(){}
    public void reload(){};
}
