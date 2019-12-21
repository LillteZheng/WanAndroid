package com.zhengsr.wanandroid.mvp.present;

import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.HotKeyBean;
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
public class SearchPresent extends BasePresent<IBaseView> {
    public SearchPresent(IBaseView view) {
        super(view);
    }

    public void getHotkey() {
        addSubscribe(
                mDataManager.getHotkeyBean()
                        .compose(RxUtils.rxScheduers())
                        .compose(RxUtils.handleResult())
                        .subscribeWith(new CusSubscribe<List<HotKeyBean>>(mView) {
                            @Override
                            public void onNext(List<HotKeyBean> hotKeyBeans) {
                                super.onNext(hotKeyBeans);
                                if (mView instanceof IContractView.IHotKeyView) {
                                    //mView.getHotkeyData(hotKeyBeans);
                                    ((IContractView.IHotKeyView) mView).getHotkeyData(hotKeyBeans);
                                }
                            }
                        })
        );
    }

    public void searchMore(int page, String keyWord, boolean isRefresh) {
        addSubscribe(
                mDataManager.searchArticleByKeyWord(page, keyWord)
                        .compose(RxUtils.rxScheduers())
                        .compose(RxUtils.handleResult())
                        .subscribeWith(new CusSubscribe<PageDataInfo<List<ArticleData>>>(mView) {
                            @Override
                            public void onNext(PageDataInfo<List<ArticleData>> info) {
                                super.onNext(info);
                                if (mView instanceof IContractView.ISearchView){
                                    ((IContractView.ISearchView) mView).getSearchData(info.getPageCount(),info.getDatas(),isRefresh);
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
                                if (mView instanceof IContractView.ISearchView){
                                    ((IContractView.ISearchView) mView).addArticleSuccess(position,data);
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
                                if (mView instanceof IContractView.ISearchView){
                                    ((IContractView.ISearchView) mView).removeArticleSuccess(position,data);
                                }
                            }
                        })
        );
    }

}
