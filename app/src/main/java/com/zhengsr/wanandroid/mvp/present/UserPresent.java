package com.zhengsr.wanandroid.mvp.present;

import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.LoginBean;
import com.zhengsr.wanandroid.bean.RankBean;
import com.zhengsr.wanandroid.bean.RankListBean;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.model.DataManager;
import com.zhengsr.wanandroid.net.CusSubscribe;
import com.zhengsr.wanandroid.utils.Lgg;
import com.zhengsr.wanandroid.utils.RxUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function3;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe: 登录，注册等复用present
 */
public class UserPresent extends BasePresent<IBaseView> {
    private DataManager mDataManager;
    private IBaseView mView;
    private int mRankPage = 0;
    public static UserPresent create(IBaseView view){
        return new UserPresent(view);
    }
    private UserPresent(IBaseView view){
        mDataManager = DataManager.getInstance();
        mView = view;
    }

    public void login(String userName,String password){
        Observable<BaseResponse<LoginBean>> loginObservable = mDataManager.login(userName, password);
        Observable<String> json = mDataManager.getJson("https://www.wanandroid.com//lg/coin/list/1/json");
        Observable<BaseResponse<RankBean>> userRankInfo = mDataManager.getUserRankInfo();
        addSubscribe(
                Observable.zip(loginObservable, json, userRankInfo,
                        new Function3<BaseResponse<LoginBean>, String,
                                BaseResponse<RankBean>, HashMap<String,Object>>() {
                            @Override
                            public HashMap<String, Object> apply(BaseResponse<LoginBean> loginBeanBaseResponse,
                                                                 String s, BaseResponse<RankBean> rankBeanBaseResponse) throws Exception {
                                HashMap<String, Object> map = new HashMap<>();
                                Lgg.d("login: "+loginBeanBaseResponse.getData());
                                Lgg.d("json: "+s);
                                Lgg.d("rank: "+rankBeanBaseResponse.getData());
                                return map;
                            }
                        }).compose(RxUtils.rxScheduers())
                .subscribeWith(new CusSubscribe<HashMap<String, Object>>(mView) {
                    @Override
                    public void onNext(HashMap<String, Object> stringObjectHashMap) {

                    }
                })

        );
    }

    /**
     * 获取积分版
     * @param showLoading
     */
    public void getRankInfo(boolean showLoading){
        if (showLoading) {
            mView.showLoading();
        }
        addSubscribe(
                mDataManager.getRankInfo(mRankPage)
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<RankListBean>(mView) {

                    @Override
                    public void onNext(RankListBean listBean) {
                        super.onNext(listBean);
                        if (mView instanceof IContractView.IRankInfoView){
                            ((IContractView.IRankInfoView) mView).rankInfo(listBean);
                        }
                    }
                })
        );
    }
}
