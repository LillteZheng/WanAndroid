package com.zhengsr.wanandroid.moudle.fragment.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe: MVP 基类
 */
public abstract class BaseMvpFragment<T extends BasePresent> extends BaseDelegateFragment implements
        IBaseView {
    protected T mPresent;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresent = getPresent();
        if (mPresent != null) {
            mPresent.attachView(this);
        }
    }


    @Override
    public void onDestroyView() {
        if (mPresent != null) {
            mPresent.detachView();
            mPresent = null;
        }
        super.onDestroyView();
    }


    /**
     * 拿到present
     * @return
     */
    public  T getPresent(){
        return null;
    }


    /**
     * 接口类
     */

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
