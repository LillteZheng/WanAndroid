package com.zhengsr.wanandroid.net;

import android.text.TextUtils;
import android.util.Log;


import com.zhengsr.wanandroid.mvp.base.IBaseView;
import com.zhengsr.wanandroid.utils.Lgg;

import io.reactivex.observers.ResourceObserver;

public abstract class CusSubscribe<T> extends ResourceObserver<T> {
    private IBaseView mView;
    private String mErrorMsg;
    private boolean isShowSuccess = true;
    public CusSubscribe(IBaseView iview) {
        mView = iview;
    }

    public CusSubscribe(IBaseView iview, boolean showLoadSuccess) {
        mView = iview;
        isShowSuccess = showLoadSuccess;
    }


    @Override
    public void onError(Throwable e) {
        Lgg.e("error: "+e.getMessage());
        if (mView == null){
            return;
        }
        if (!TextUtils.isEmpty(mErrorMsg)){
            mView.showErrorMsg(mErrorMsg);
        }else{
            mView.showErrorMsg(e.getMessage());
        }


    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(T t) {
        if (isShowSuccess) {
            mView.loadSuccess();
        }
    }
}