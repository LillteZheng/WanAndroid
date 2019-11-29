package com.zhengsr.wanandroid.mvp.present;

import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.NaviBean;
import com.zhengsr.wanandroid.bean.NaviChildrenBean;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.net.CusSubscribe;
import com.zhengsr.wanandroid.utils.RxUtils;

import java.nio.file.Path;
import java.util.List;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class NaviPresent extends BasePresent<IBaseView> {


    public NaviPresent(IBaseView view) {
        super(view);
    }

    public void startLoad(){
        mView.showLoading();
        addSubscribe(
                mDataManager.getTreeKnowledge()
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<List<NaviBean>>(mView){
                    @Override
                    public void onNext(List<NaviBean> naviBean) {
                        super.onNext(naviBean);
                        if (mView instanceof IContractView.INaviView){
                            ((IContractView.INaviView) mView).getTreeKnowledge(naviBean);
                        }
                    }
                })
        );
    }

    public void getDetailNavi(int page, NaviChildrenBean bean) {
        getDetailNavi(page,bean,true);
    }

    private void getDetailNavi(int page,NaviChildrenBean bean,boolean isRefresh){
        if (isRefresh){
            mView.showLoading();
        }
        addSubscribe(
                mDataManager.getNaviDetail(page,bean.getId())
                        .compose(RxUtils.rxScheduers())
                        .compose(RxUtils.handleResult())
                        .subscribeWith(new CusSubscribe<PageDataInfo<List<ArticleData>>>(mView){
                            @Override
                            public void onNext(PageDataInfo<List<ArticleData>> listPageDataInfo) {
                                super.onNext(listPageDataInfo);
                                if (mView instanceof IContractView.INaviDetailView){
                                    ((IContractView.INaviDetailView) mView).getNaviDetail(listPageDataInfo.getPageCount(),
                                            listPageDataInfo.getDatas(),isRefresh);
                                }
                            }
                        })
        );
    }

    public void loadMore(int page,NaviChildrenBean bean){
        getDetailNavi(page,bean,false);
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
                                if (mView instanceof IContractView.INaviDetailView){
                                    ((IContractView.INaviDetailView) mView).addArticleSuccess(position,data);
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
                                if (mView instanceof IContractView.INaviDetailView){
                                    ((IContractView.INaviDetailView) mView).removeArticleSuccess(position,data);
                                }
                            }
                        })
        );
    }
}
