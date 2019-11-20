package com.zhengsr.wanandroid.mvp.present;

import android.annotation.SuppressLint;

import com.zhengsr.ariesuilib.utils.AriesUtils;
import com.zhengsr.wanandroid.Constant;
import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.ArticleListBean;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.model.DataManager;
import com.zhengsr.wanandroid.net.CusSubscribe;
import com.zhengsr.wanandroid.utils.Lgg;
import com.zhengsr.wanandroid.utils.RxUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function3;

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
         */
        addSubscribe(
                Observable.zip(mDataManager.getBanner(), mDataManager.getArticles(mCurNum), (BiFunction<BaseResponse<List<BannerBean>>,
                        BaseResponse<ArticleListBean>, HashMap>) (banners, articles) -> {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put(Constant.BANNER, banners.getData());
                    map.put(Constant.ARTICLE, articles.getData());
                    return map;
                }).compose(RxUtils.rxScheduers())
                        .subscribeWith(new CusSubscribe<Map>(mView) {
                            @Override
                            public void onNext(Map map) {
                                List<BannerBean> bannerBeans = cast(map.get(Constant.BANNER));
                                ArticleListBean articleListBean = cast(map.get(Constant.ARTICLE));
                                mView.loadMainData(bannerBeans,articleListBean);
                                mView.loadSuccess();
                            }
                        })

        );

    }

    private Observable<Integer> getObservableInteger(){
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        });
    }

    private HashMap<String,Object> loadDataToMap(
            List<BannerBean> bannerBeans,
            List<ArticleData> articleBeans){

        HashMap<String,Object> map = new HashMap<>();
        map.put(Constant.BANNER,bannerBeans);
        map.put(Constant.ARTICLE,articleBeans);
        return map;
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
                .subscribeWith(new CusSubscribe<ArticleListBean>(mView) {
                    @Override
                    public void onNext(ArticleListBean articleListBean) {
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
