package com.zhengsr.wanandroid.mvp.contract;


import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.LoginBean;
import com.zhengsr.wanandroid.bean.RankBean;
import com.zhengsr.wanandroid.bean.RankListBean;
import com.zhengsr.wanandroid.bean.RegisterBean;
import com.zhengsr.wanandroid.mvp.base.IBaseView;

import java.util.List;

/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe: 接口的统一管理类，这样方便查找和管理
 */
public interface IContractView {

    /**
     * 获取首页banner和文章内容
     */
    interface IHomeView extends IBaseView {
        void loadMainData(List<BannerBean> bannerBeans, List<ArticleData> articles);
        void loadArticle(PageDataInfo articleListBean);
    }

    interface IArticleView extends IBaseView{
        void loadArticle(List<ArticleData> articleData,boolean isRefresh);
    }

    /**
     * 登录和注册
     */
    interface ILoginView extends IBaseView{
        void loginInfo(LoginBean loginBean);
        void registerInfo(RegisterBean bean);
    }

    /**
     * 获取积分
     */
    interface IRankInfoView extends IBaseView{
        void rankInfo(RankListBean listBean);
    }

    /**
     * 用户信息
     */
    interface IUserInfoView extends IBaseView{
        void getInfoUser(RankBean bean);
        void logoutSuccess();
    }

}
