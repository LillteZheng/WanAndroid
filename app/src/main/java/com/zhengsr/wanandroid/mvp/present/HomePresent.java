package com.zhengsr.wanandroid.mvp.present;

import android.annotation.SuppressLint;

import com.zhengsr.wanandroid.Constant;
import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.LoginBean;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.model.DataManager;
import com.zhengsr.wanandroid.net.CusSubscribe;
import com.zhengsr.wanandroid.utils.Lgg;
import com.zhengsr.wanandroid.utils.RxUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function4;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class HomePresent extends BasePresent<IContractView.IHomeView> {
    private DataManager mDataManager;
    private int mCurNum;
    private IContractView.IHomeView mView;

    public static HomePresent create(IContractView.IHomeView view) {
        return new HomePresent(view);
    }

    private HomePresent(IContractView.IHomeView view) {
        mView = view;
        mDataManager = DataManager.getInstance();
        mCurNum = 0;

    }

    /**
     * 开始加载数据
     * @param showLoading 是否显示加载view
     */
    @SuppressLint("CheckResult")
    public void startLoad(boolean showLoading) {
        if (showLoading){
            mView.showLoading();
        }

        /**
         * 通过zip，把 banner 和 主页的信息放到一个map中，这样可以一起返回给主页调用
         * 且每次，我们都是先登录试试
         */
        Observable<BaseResponse<LoginBean>> loginObservable = mDataManager.login(getUserName(), getPassword());
        addSubscribe(
                Observable.zip(loginObservable, mDataManager.getBanner(), mDataManager.getTopArticle(), mDataManager.getArticles(mCurNum),
                        new Function4<BaseResponse<LoginBean>, BaseResponse<List<BannerBean>>,
                                BaseResponse<List<ArticleData>>,
                                BaseResponse<PageDataInfo>, HashMap<String,Object>>() {
                            @Override
                            public HashMap<String, Object> apply(BaseResponse<LoginBean> login, BaseResponse<List<BannerBean>> banners,
                                                                 BaseResponse<List<ArticleData>> topArticles,
                                                                 BaseResponse<PageDataInfo> articles) throws Exception {
                                HashMap<String, Object> map = new HashMap<>();
                                map.put(Constant.LOGIN, login);
                                map.put(Constant.BANNER, banners.getData());
                                map.put(Constant.TOPARTICLE, topArticles.getData());
                                map.put(Constant.ARTICLE, articles.getData());
                                return map;
                            }
                        }).compose(RxUtils.rxScheduers())
                        .subscribeWith(new CusSubscribe<HashMap<String,Object>>(mView){
                            @Override
                            public void onNext(HashMap<String, Object> map) {
                                super.onNext(map);
                                BaseResponse<LoginBean> loginResponse = cast(map.get(Constant.LOGIN));
                                if (loginResponse != null && loginResponse.getErrorCode() != BaseResponse.SUCCESS){
                                    setLogin(false);
                                    setUserName("");
                                    setPassword("");
                                }else{
                                    setLogin(true);
                                    Lgg.d("自动登录成功: ");
                                }
                                List<BannerBean> bannerBeans = cast(map.get(Constant.BANNER));
                                List<ArticleData> articleData = new ArrayList<>();
                                PageDataInfo articleListBean = cast(map.get(Constant.ARTICLE));
                                articleData.addAll(cast(map.get(Constant.TOPARTICLE)));
                                articleData.addAll(articleListBean.getDatas());
                                mView.loadMainData(bannerBeans,articleData);
                            }
                        })

        );

    }





    /**
     * 重新加载
     */
    public void onRefresh() {
        mCurNum = 0;
        startLoad(false);
    }

    /**
     * 加载更多
     */
    public void loadMore(){
        mCurNum ++;
        addSubscribe(
                mDataManager.getArticles(mCurNum)
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<PageDataInfo>(mView) {
                    @Override
                    public void onNext(PageDataInfo articleListBean) {
                        mView.loadArticle(articleListBean);
                    }
                })
        );
    }

    /**
     * 泛型转换工具方法 eg:object ==> map<String, String>
     *
     * @param object Object
     * @param <T> 转换得到的泛型对象
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }


}
