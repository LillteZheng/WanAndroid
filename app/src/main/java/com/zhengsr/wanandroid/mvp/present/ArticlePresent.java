package com.zhengsr.wanandroid.mvp.present;

import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.model.DataManager;
import com.zhengsr.wanandroid.net.CusSubscribe;
import com.zhengsr.wanandroid.utils.RxUtils;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class ArticlePresent extends BasePresent<IContractView.IAddOrCancelArticleView> {


    public ArticlePresent(IContractView.IAddOrCancelArticleView view) {
        super(view);
    }

    public void addArticle(int position, WebBean data) {
        addSubscribe(
                mDataManager.addArticle(data.id)
                        .compose(RxUtils.rxScheduers())
                        .subscribeWith(new CusSubscribe<BaseResponse>(mView) {
                            @Override
                            public void onNext(BaseResponse baseResponse) {
                                super.onNext(baseResponse);
                                data.isCollect = true;
                                mView.addArticleSuccess(position, data);
                            }
                        })
        );
    }

    public void removeArticle(int position, WebBean data) {
        addSubscribe(
                mDataManager.removeArticle(data.id)
                        .compose(RxUtils.rxScheduers())
                        .subscribeWith(new CusSubscribe<BaseResponse>(mView) {
                            @Override
                            public void onNext(BaseResponse baseResponse) {
                                super.onNext(baseResponse);
                                data.isCollect = false;
                                mView.removeArticleSuccess(position, data);
                            }
                        })
        );
    }
}
