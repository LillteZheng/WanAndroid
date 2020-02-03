package com.zhengsr.wanandroid.mvp.base;

import android.view.View;

import com.zhengsr.wanandroid.Constant;
import com.zhengsr.wanandroid.mvp.model.DataManager;
import com.zhengsr.wanandroid.utils.SpfUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe:公用present
 */
public class BasePresent<T extends IBaseView> {

    protected DataManager mDataManager;
    protected T mView;

    public BasePresent(T view){
        mView = view;
        mDataManager = DataManager.getInstance();
    }

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
        return SpfUtils.get(Constant.KEY_IS_LOGIN,false);
    }

    public void setLogin(boolean isLogin){
        SpfUtils.put(Constant.KEY_IS_LOGIN,isLogin);
    }

    public String getUserName(){
        return SpfUtils.get(Constant.KEY_USERNAME,"");
    }
    public void setUserName(String userName){
        SpfUtils.put(Constant.KEY_USERNAME,userName);
    }
    public String getPassword(){
        return SpfUtils.get(Constant.KEY_PASSWORD,"");
    }
    public void setPassword(String password){
        SpfUtils.put(Constant.KEY_PASSWORD,password);
    }


    public void setNightMode(boolean isNight){
        SpfUtils.put(Constant.KEY_IS_NIGHT,isNight);
    }

    public boolean isNightMode(){
        return SpfUtils.get(Constant.KEY_IS_NIGHT,false);
    }
}
