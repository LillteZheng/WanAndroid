package com.zhengsr.wanandroid.mvp.present;

import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.bean.ShareBean;
import com.zhengsr.wanandroid.bean.UsefulWebBean;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.net.CusSubscribe;
import com.zhengsr.wanandroid.utils.Lgg;
import com.zhengsr.wanandroid.utils.RxUtils;

import java.util.List;

/**
 * @auther by zhengshaorui on 2019/12/1
 * describe:
 */
public class TopPresent extends BasePresent<IBaseView> {
    public TopPresent(IBaseView view) {
        super(view);
    }

    public void getSqureData(int page,boolean showLoading,boolean isRefresh){
        if (showLoading){
            mView.showLoading();
        }
        addSubscribe(
                mDataManager.getSquareDatas(page)
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<PageDataInfo<List<ArticleData>>>(mView){
                    @Override
                    public void onNext(PageDataInfo<List<ArticleData>> info) {
                        super.onNext(info);
                        if (mView instanceof IContractView.ISquareView){
                            ((IContractView.ISquareView) mView).getSquareData(info.getPageCount(),info.getDatas(),isRefresh);
                        }

                    }
                })
        );
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
                                if (mView instanceof IContractView.ISquareView){
                                    ((IContractView.ISquareView) mView).addArticleSuccess(position,data);
                                }
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
                                if (mView instanceof IContractView.ISquareView){
                                    ((IContractView.ISquareView) mView).removeArticleSuccess(position,data);
                                }
                            }
                        })
        );
    }


    public void getUserShareData(int id,int page,boolean showLoading,boolean isRefresh){
        if (showLoading){
            mView.showLoading();
        }
        addSubscribe(
                mDataManager.getUserShareData(id,page)
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<ShareBean>(mView){
                    @Override
                    public void onNext(ShareBean info) {
                        super.onNext(info);
                        ShareBean.CoinInfoBean coinInfo = info.getCoinInfo();
                        ShareBean.ShareArticlesBean shareArticles = info.getShareArticles();
                        //mView.getSearchData(info.getPageCount(),info.getDatas(),isRefresh);
                        if (mView instanceof IContractView.ISquareView){
                            ((IContractView.ISquareView) mView).getShareData(shareArticles.getPageCount(),shareArticles.getDatas(),isRefresh);
                        }
                    }
                })
        );
    }


    public void getUsefulWeb(){
        mView.showLoading();
        addSubscribe(
                mDataManager.getUsefulWeb()
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<List<UsefulWebBean>>(mView) {
                    @Override
                    public void onNext(List<UsefulWebBean> usefulWebBeans) {
                        super.onNext(usefulWebBeans);
                        if (mView instanceof IContractView.IUsefulWebView){
                            ((IContractView.IUsefulWebView) mView).getUsefulWeb(usefulWebBeans);
                        }
                    }
                })
        );
    }
}
