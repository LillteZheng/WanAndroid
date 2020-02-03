package com.zhengsr.wanandroid.moudle.fragment.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.mvp.base.BasePresent;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe: 网络基类
 */
public abstract class BaseNetFragment<T extends BasePresent> extends BaseMvpFragment<T>  {
    private static final int NORMAL_VIEW = 1;
    private static final int LOADING_VIEW = 2;
    private static final int ERROR_VIEW = 3;

    protected View mNormalView;
    protected View mErrorView;
    protected View mLoadingView;

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        /**
         * 规定normalview的id
         */
        if (getView() == null){
            return;
        }
        mNormalView = getView().findViewById(R.id.normal_view);
        if (mNormalView == null){
            throw new RuntimeException("you show add view use normal_view id");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)){
            throw new RuntimeException("normal_view must be viewgroup");
        }

        ViewGroup parent = (ViewGroup) mNormalView.getParent();
        mLoadingView = LayoutInflater.from(_mActivity).inflate(R.layout.loadingview_layout,parent,false);
        mErrorView = LayoutInflater.from(_mActivity).inflate(R.layout.load_error_layout,parent,false);
        /**
         * 把三个 view 都添加进来,并先让它不显示
         */
        parent.addView(mErrorView);
        parent.addView(mLoadingView);
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mNormalView.setVisibility(View.GONE);

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
    public void showLoading() {
        super.showLoading();
        showCurrentView(LOADING_VIEW);
    }

    @Override
    public void showError() {
        super.showError();
        showCurrentView(ERROR_VIEW);
    }

    @Override
    public void loadSuccess() {
        super.loadSuccess();
        showCurrentView(NORMAL_VIEW);
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

    public void reload(){

    }

    @Override
    public void pop() {
        super.pop();
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
        }
    }
}
