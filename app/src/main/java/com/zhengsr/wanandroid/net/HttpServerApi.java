package com.zhengsr.wanandroid.net;



import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.LoginBean;
import com.zhengsr.wanandroid.bean.ProjectBean;
import com.zhengsr.wanandroid.bean.ProjectListBean;
import com.zhengsr.wanandroid.bean.RankBean;
import com.zhengsr.wanandroid.bean.RankListBean;
import com.zhengsr.wanandroid.bean.RegisterBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
    Observable<BaseResponse<PageDataInfo>> getArticle(@Path("num") int num);

    /**
     * 获取置顶文章
     * https://www.wanandroid.com/article/top/json
     * @return
     */
    @GET("article/top/json")
    Observable<BaseResponse<List<ArticleData>>> getTopArticle();

    /**
     * 登录
     * https://www.wanandroid.com/user/login
     * @param username
     * @param password
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginBean>> loginIn(@Field("username") String username,
                                                @Field("password") String password);

    /**
     * 获取积分排行版
     * https://www.wanandroid.com/coin/rank/page/json
     * @param page 页码
     * @return
     */
    @GET("coin/rank/{page}/json")
    Observable<BaseResponse<RankListBean>> getRankInfo(@Path("page") int page);

    /**
     * 获取个人积分，需要登录后
     * https://www.wanandroid.com/lg/coin/userinfo/json
     * @return
     */
    @GET("lg/coin/userinfo/json")
    Observable<BaseResponse<RankBean>> getUserRank();

    /**
     * 退出登录
     * https://www.wanandroid.com/user/logout/json
     */
    @GET("user/logout/json")
    Observable<BaseResponse> logout();


    /**
     * 注册
     * https://www.wanandroid.com/user/register
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse<RegisterBean>> register(@Field("username") String username, @Field("password")
            String password, @Field("repassword") String repassword);

    /**
     * https://www.wanandroid.com/lg/collect/list/0/json
     * @return
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<PageDataInfo>> getMyCollect(@Path("page") int page);

    /**
     * 项目分类
     * https://www.wanandroid.com/project/tree/json
     * @return
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectListBean>>> getProjectSort();

    /**
     * 项目列表数据
     * https://www.wanandroid.com/project/list/1/json?cid=294
     * @return
     */
    @GET("project/list/{page}/json?cid={cid}")
    Observable<BaseResponse<PageDataInfo>> getProjectInfo(@Path("page") int page,@Path("cid") int cid);
}
