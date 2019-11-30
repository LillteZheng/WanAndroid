package com.zhengsr.wanandroid.mvp.present;

import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.NaviChildrenBean;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.bean.WechatBean;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.net.CusSubscribe;
import com.zhengsr.wanandroid.utils.RxUtils;

import java.util.List;

/**
 * @auther by zhengshaorui on 2019/11/30
 * describe:
 */
public class WechatPresent extends BasePresent<IBaseView> {
    public WechatPresent(IBaseView view) {
        super(view);
    }

    public void getWevhatList(){
        mView.showLoading();
        addSubscribe(
                mDataManager.getWechatList()
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<List<WechatBean>>(mView){
                    @Override
                    public void onNext(List<WechatBean> wechatBeans) {
                        super.onNext(wechatBeans);
                        if (mView instanceof IContractView.IWechatView){
                            ((IContractView.IWechatView) mView).getWechatList(wechatBeans);
                        }
                    }
                })
        );
    }

    public void getWechatAuthor(int page, WechatBean bean) {
        getWechatAuthor(page,bean,true);
    }

    private void getWechatAuthor(int page,WechatBean bean,boolean isRefresh){
        if (isRefresh){
            mView.showLoading();
        }
        addSubscribe(
               mDataManager.getWechatAuthor(bean.getId(),page)
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<PageDataInfo<List<ArticleData>>>(mView){
                    @Override
                    public void onNext(PageDataInfo<List<ArticleData>> info) {
                        super.onNext(info);
                        if (mView instanceof IContractView.IWechatDetailView){
                            ((IContractView.IWechatDetailView) mView).getWechatArticle(info.getPageCount(),info.getDatas(),isRefresh);
                        }
                    }
                })
        );
    }

    public void loadMore(int page, WechatBean bean){
        getWechatAuthor(page,bean,false);
    }

    public void addArticle(int position, ArticleData data){
        addSubscribe(
                mDataManager.addArticle(data.getId())
                        .compose(RxUtils.rxScheduers())
                        .subscribeWith(new CusSubscribe<BaseResponse>(mView){
                            @Override
                            public void onNext(BaseResponse baseResponse) {
                                super.onNext(baseResponse);
                                data.setCollect(true);
                                if (mView instanceof IContractView.IWechatDetailView){
                                    ((IContractView.IWechatDetailView) mView).addArticleSuccess(position,data);
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
                                if (mView instanceof IContractView.IWechatDetailView){
                                    ((IContractView.IWechatDetailView) mView).removeArticleSuccess(position,data);
                                }
                            }
                        })
        );
    }

    public void searchKeyMsg(int page,int id,String keyMsg,boolean isRefresh){
        if (isRefresh){
            mView.showLoading();
        }
        addSubscribe(
                mDataManager.searchWechatBykey(id,page,keyMsg)
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<PageDataInfo<List<ArticleData>>>(mView){
                    @Override
                    public void onNext(PageDataInfo<List<ArticleData>> info) {
                        super.onNext(info);
                        if (mView instanceof IContractView.IWechatDetailView){
                            ((IContractView.IWechatDetailView) mView).getSearchArticle(info.getPageCount(),info.getDatas(),isRefresh);
                        }
                    }
                })
        );
    }
}
