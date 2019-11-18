package com.zhengsr.wanandroid.net;



import com.zhengsr.wanandroid.bean.ArticleListBean;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe: 统一网络服务接口类
 */
public interface HttpServerApi {
    @GET
    Observable<String> getJson(@Url String url);


    /**
     * https://www.wanandroid.com/banner/json
     * 获取 Banner 数据
     * @return
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerBean>>> getBanner();

    /**
     * 获取文章
     * https://www.wanandroid.com/article/list/num/json
     * @param num 页码
     * @return
     */
    @GET("article/list/{num}/json")
    Observable<BaseResponse<ArticleListBean>> getArticle(@Path("num") int num);

}
