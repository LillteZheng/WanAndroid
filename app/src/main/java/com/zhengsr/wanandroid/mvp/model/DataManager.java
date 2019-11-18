package com.zhengsr.wanandroid.mvp.model;



import com.zhengsr.wanandroid.bean.ArticleListBean;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.net.HttpCreate;
import com.zhengsr.wanandroid.net.HttpServerApi;

import java.util.List;

import io.reactivex.Observable;

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
    public Observable<BaseResponse<ArticleListBean>> getArticles(int num){
        return mServerApi.getArticle(num);
    }

}
