package com.zhengsr.wanandroid.mvp.base;

import com.zhengsr.wanandroid.utils.SpfUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe:公用present
 */
public class BasePresent<T> {
    private static final String KEY_IS_LOGIN = "KEY_IS_LOGIN";
    private static final String KEY_USERNAME = "KEY_USERNAME";
    private static final String KEY_PASSWORD = "KEY_PASSWORD";
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
     * 添加 disposable
     */
    protected void addSubscribe(Disposable disposable){
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }


    /**
     * 是否已经登录
     * @return
     */
    public boolean isLogin(){
        return SpfUtils.get(KEY_IS_LOGIN,false);
    }

    public void setLogin(boolean isLogin){
        SpfUtils.put(KEY_IS_LOGIN,isLogin);
    }

    public boolean getUserName(){
        return SpfUtils.get(KEY_USERNAME,null);
    }
    public void setUserName(String userName){
        SpfUtils.put(KEY_USERNAME,userName);
    }
    public boolean getPassword(){
        return SpfUtils.get(KEY_PASSWORD,null);
    }
    public void setPassword(String password){
        SpfUtils.put(KEY_PASSWORD,password);
    }
}
