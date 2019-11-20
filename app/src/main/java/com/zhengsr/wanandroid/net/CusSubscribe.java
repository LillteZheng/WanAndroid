package com.zhengsr.wanandroid.net;

import android.text.TextUtils;
import android.util.Log;


import com.zhengsr.wanandroid.mvp.base.IBaseView;
import com.zhengsr.wanandroid.utils.Lgg;

import io.reactivex.observers.ResourceObserver;

public abstract class CusSubscribe<T> extends ResourceObserver<T> {
    private IBaseView mView;
    private String mErrorMsg;
    private boolean isShowError;
    public CusSubscribe(IBaseView iview) {
        mView = iview;
    }

    public CusSubscribe(IBaseView iview, String errormsg) {
        mView = iview;
        mErrorMsg = errormsg;
    }

    public CusSubscribe(IBaseView iview, String errormsg, boolean showerror) {
        mView = iview;
        mErrorMsg = errormsg;
        isShowError = showerror;
    }

    @Override
    public void onError(Throwable e) {
        Lgg.d("error: "+e.toString());
        if (mView == null){
            return;
        }
        if (!TextUtils.isEmpty(mErrorMsg)){
            mView.showErrorMsg(mErrorMsg);
        }else{
            mView.showErrorMsg(e.toString());
        }
        if (isShowError){
            mView.showError();
        }

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(T t) {
        mView.loadSuccess();
    }
}