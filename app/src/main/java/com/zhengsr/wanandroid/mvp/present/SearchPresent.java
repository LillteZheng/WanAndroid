package com.zhengsr.wanandroid.mvp.present;

import com.zhengsr.wanandroid.bean.HotKeyBean;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.net.CusSubscribe;
import com.zhengsr.wanandroid.utils.RxUtils;

import java.util.List;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class SearchPresent extends BasePresent<IContractView.ISearchView> {
    public SearchPresent(IContractView.ISearchView view) {
        super(view);
    }

    public void getHotkey(){
        addSubscribe(
                mDataManager.getHotkeyBean()
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<List<HotKeyBean>>(mView){
                    @Override
                    public void onNext(List<HotKeyBean> hotKeyBeans) {
                        super.onNext(hotKeyBeans);
                        mView.getHotkeyData(hotKeyBeans);
                    }
                })
        );
    }
}
