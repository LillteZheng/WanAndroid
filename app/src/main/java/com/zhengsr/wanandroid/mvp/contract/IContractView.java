package com.zhengsr.wanandroid.mvp.contract;


import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.CollectBean;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.LoginBean;
import com.zhengsr.wanandroid.bean.NaviBean;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.bean.ProjectBean;
import com.zhengsr.wanandroid.bean.ProjectListBean;
import com.zhengsr.wanandroid.bean.RankBean;
import com.zhengsr.wanandroid.bean.RankListBean;
import com.zhengsr.wanandroid.bean.RegisterBean;
import com.zhengsr.wanandroid.bean.WechatBean;
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
    interface IHomeView<T> extends IAddOrCancelArticleView<T> {
        void loadMainData(List<BannerBean> bannerBeans, List<ArticleData> articles);
        void loadArticle(List<ArticleData> articles);
    }

    /**
     * 文章收藏与否
     */
    interface IAddOrCancelArticleView<T> extends IBaseView{
        void addArticleSuccess(int position,T data);
        void removeArticleSuccess(int position,T data);
    }

    interface IArticleView<T> extends IAddOrCancelArticleView<T> {
        void loadArticle(List<CollectBean> collectBeans, boolean isRefresh);
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

    // 项目接口
    interface IProjectListView extends IBaseView{
        void getProjectList(List<ProjectListBean> projectListBeans);
    }
    interface IProjectDetailView extends IBaseView{
        void getProjectDetail(List<ProjectBean> beans, int maxPage,boolean isRefresh);
    }

    /**
     * 知识体系
     */
    interface INaviView extends IBaseView{
        void getTreeKnowledge(List<NaviBean> beans);
    }
    interface INaviDetailView<T> extends IAddOrCancelArticleView<T>{
        void getNaviDetail(int maxPage,List<ArticleData> datas,boolean isRefresh);
    }

    interface IWechatView extends IBaseView{
        void getWechatList(List<WechatBean> beans);
    }

    interface IWechatDetailView<T> extends IAddOrCancelArticleView<T>{
        void getWechatArticle(int maxPage,List<ArticleData> datas,boolean isRefresh);
        void getSearchArticle(int maxPage,List<ArticleData> datas,boolean isRefresh);
    }

    interface ISquareView<T> extends IAddOrCancelArticleView<T>{
        void getSquareData(int maxPage,List<ArticleData> datas,boolean isRefresh);
        void getShareData(int maxPage,List<ArticleData> datas,boolean isRefresh);
    }

    interface IShareView extends IBaseView{
        void getShareData(int maxPage,List<ArticleData> datas,boolean isRefresh);
        void deleteSuccess(int position);
        void shareSuccess();
    }

}
