package com.zhengsr.wanandroid.mvp.model;



import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.LoginBean;
import com.zhengsr.wanandroid.bean.ProjectListBean;
import com.zhengsr.wanandroid.bean.RankBean;
import com.zhengsr.wanandroid.bean.RankListBean;
import com.zhengsr.wanandroid.bean.RegisterBean;
import com.zhengsr.wanandroid.net.HttpCreate;
import com.zhengsr.wanandroid.net.HttpServerApi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Path;

/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe: 数据统一管理类，这里用单利实现
 */
public class DataManager {
    private HttpServerApi mServerApi;
    private static class Holder{
        static DataManager HODLER = new DataManager();
    }


    public static DataManager getInstance(){
        return Holder.HODLER;
    }
    private DataManager(){
        mServerApi = HttpCreate.getServer();
    }


    /**
     * 统一拿到json数据
     * 测试数据
     */
    public Observable<String> getJson(String url){
        return mServerApi.getJson(url);
    }

    /**
     * 拿到banner
     * @return
     */
    public Observable<BaseResponse<List<BannerBean>>> getBanner(){
        return mServerApi.getBanner();
    }

    /**
     * 拿到文章列表
     * @param num
     * @return
     */
    public Observable<BaseResponse<PageDataInfo>> getArticles(int num){
        return mServerApi.getArticle(num);
    }

    /**
     * 拿到置顶文章
     * @return
     */
    public Observable<BaseResponse<List<ArticleData>>> getTopArticle(){
        return mServerApi.getTopArticle();
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public Observable<BaseResponse<LoginBean>> login(String userName,String password){
        return mServerApi.loginIn(userName,password);
    }

    /**
     * 获取积分排行版
     * @param page
     * @return
     */
    public Observable<BaseResponse<RankListBean>> getRankInfo(int page){
        return mServerApi.getRankInfo(page);
    }

    /**
     * 获取个人积分
     * @return
     */
    public Observable<BaseResponse<RankBean>> getUserRankInfo(){
        return mServerApi.getUserRank();
    }

    public  Observable<BaseResponse> logout(){
        return mServerApi.logout();
    }

    /**
     * 注册
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    public  Observable<BaseResponse<RegisterBean>> register(String username,
            String password,  String repassword){
        return mServerApi.register(username,password,repassword);
    }

    /**
     * 拿到收藏列表
     * @param page
     * @return
     */
    public  Observable<BaseResponse<PageDataInfo>> getMyCollect(int page){
        return mServerApi.getMyCollect(page);
    }

    /**
     * 项目分类
     * @return
     */
    public  Observable<BaseResponse<List<ProjectListBean>>> getProjectSort(){
        return mServerApi.getProjectSort();
    }

    /**
     * 获取项目数据
     * @param page
     * @param cid
     * @return
     */
    public Observable<BaseResponse<PageDataInfo>> getProjectInfo(int page, int cid){
        return mServerApi.getProjectInfo(page,cid);
    }
}
