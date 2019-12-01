package com.zhengsr.wanandroid.mvp.present;

import android.annotation.SuppressLint;
import android.support.v7.view.menu.MenuAdapter;

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
    private int mCurNum = 0;

    public HomePresent(IContractView.IHomeView view) {
        super(view);
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


                Observable.zip(loginObservable, mDataManager.getBanner(), mDataManager.getTopArticle(),
                        mDataManager.getArticles(mCurNum), new Function4<BaseResponse<LoginBean>, BaseResponse<List<BannerBean>>,
                                BaseResponse<List<ArticleData>>, BaseResponse<PageDataInfo<List<ArticleData>>>, HashMap<String,Object>>() {
                            @Override
                            public HashMap<String,Object> apply(BaseResponse<LoginBean> login, BaseResponse<List<BannerBean>> banners,
                                                BaseResponse<List<ArticleData>> topArticles, BaseResponse<PageDataInfo<List<ArticleData>>> articles) throws Exception {
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
                                   // Lgg.d("自动登录成功: ");
                                }
                                List<BannerBean> bannerBeans = cast(map.get(Constant.BANNER));
                                List<ArticleData> articleData = new ArrayList<>();
                                PageDataInfo<List<ArticleData>> articleListBean = cast(map.get(Constant.ARTICLE));
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
                .subscribeWith(new CusSubscribe<PageDataInfo<List<ArticleData>>>(mView) {
                    @Override
                    public void onNext(PageDataInfo<List<ArticleData>> articleListBean) {
                        mView.loadArticle(articleListBean.getDatas());
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


    public void addArticle(int position,ArticleData data){
        addSubscribe(
                mDataManager.addArticle(data.getId())
                .compose(RxUtils.rxScheduers())
                .subscribeWith(new CusSubscribe<BaseResponse>(mView){
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        super.onNext(baseResponse);
                        data.setCollect(true);
                        mView.addArticleSuccess(position,data);
                    }
                })
        );
    }
    public void removeArticle(int position,ArticleData data){
        addSubscribe(
                mDataManager.removeArticle(data.getId())
                        .compose(RxUtils.rxScheduers())
                        .subscribeWith(new CusSubscribe<BaseResponse>(mView){
                            @Override
                            public void onNext(BaseResponse baseResponse) {
                                super.onNext(baseResponse);
                                data.setCollect(false);
                                mView.removeArticleSuccess(position,data);
                            }
                        })
        );
    }
}
