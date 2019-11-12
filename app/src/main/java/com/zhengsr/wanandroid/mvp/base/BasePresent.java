package com.zhengsr.wanandroid.mvp.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe:公用present
 */
public class BasePresent<T> {
    /**
     * 防止 rxjava 内存泄漏问题，即程序退出了还在执行网络操作
     */
    private CompositeDisposable mCompositeDisposable;

    protected T view;
    /**
     * 关联view
     */
    public void attachView(T view){
        this.view = view;

    }

    /**
     * 让view失效
     */
    public void detachView(){
        view = null;
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable.dispose();
        }
    }

    /**
     *
     */
    protected void addSubscribe(Disposable disposable){
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}
