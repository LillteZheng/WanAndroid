package com.zhengsr.wanandroid.mvp.contract;


import com.zhengsr.wanandroid.bean.ArticleListBean;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.LoginBean;
import com.zhengsr.wanandroid.bean.RankListBean;
import com.zhengsr.wanandroid.mvp.base.IBaseView;

import java.util.List;

/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe: 接口的统一管理类，这样方便查找和管理
 */
public interface IContractView {

    interface IHomeView extends IBaseView {
        void loadMainData(List<BannerBean> bannerBeans, ArticleListBean articleListBean);
        void loadArticle(ArticleListBean articleListBean);
    }
    interface ILoginView extends IBaseView{
        void getUserInfo(LoginBean loginBean);
    }

    interface IRankInfoView extends IBaseView{
        void rankInfo(RankListBean listBean);
    }

}
