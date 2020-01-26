package com.zhengsr.wanandroid.mvp.present;

import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.NaviBean;
import com.zhengsr.wanandroid.bean.SystematicBean;
import com.zhengsr.wanandroid.bean.NaviChildrenBean;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.net.CusSubscribe;
import com.zhengsr.wanandroid.utils.RxUtils;

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
                .subscribeWith(new CusSubscribe<List<SystematicBean>>(mView){
                    @Override
                    public void onNext(List<SystematicBean> naviBean) {
                        super.onNext(naviBean);
                        if (mView instanceof IContractView.ISystematicView){
                            ((IContractView.ISystematicView) mView).getTreeKnowledge(naviBean);
                        }
                    }
                })
        );
    }

    public void getSystematicDetail(int page, NaviChildrenBean bean) {
        getSystematicDetail(page,bean,true);
    }

    private void getSystematicDetail(int page, NaviChildrenBean bean, boolean isRefresh){
        if (isRefresh){
            mView.showLoading();
        }
        addSubscribe(
                mDataManager.getSystematicDetail(page,bean.getId())
                        .compose(RxUtils.rxScheduers())
                        .compose(RxUtils.handleResult())
                        .subscribeWith(new CusSubscribe<PageDataInfo<List<ArticleData>>>(mView){
                            @Override
                            public void onNext(PageDataInfo<List<ArticleData>> listPageDataInfo) {
                                super.onNext(listPageDataInfo);
                                if (mView instanceof IContractView.ISystematicDetail){
                                    ((IContractView.ISystematicDetail) mView).getSystematicDetail(listPageDataInfo.getPageCount(),
                                            listPageDataInfo.getDatas(),isRefresh);
                                }
                            }
                        })
        );
    }

    public void loadMore(int page,NaviChildrenBean bean){
        getSystematicDetail(page,bean,false);
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
                                if (mView instanceof IContractView.ISystematicDetail){
                                    ((IContractView.ISystematicDetail) mView).addArticleSuccess(position,data);
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
                                if (mView instanceof IContractView.ISystematicDetail){
                                    ((IContractView.ISystematicDetail) mView).removeArticleSuccess(position,data);
                                }
                            }
                        })
        );
    }

    public void getNaviData(){
        mView.showLoading();
        addSubscribe(
                mDataManager.getNaviData()
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<List<NaviBean>>(mView){
                    @Override
                    public void onNext(List<NaviBean> articleData) {
                        super.onNext(articleData);
                        if (mView instanceof IContractView.INaviView){
                            ((IContractView.INaviView) mView).getNaviData(articleData);
                        }
                    }
                })
        );
    }
}
