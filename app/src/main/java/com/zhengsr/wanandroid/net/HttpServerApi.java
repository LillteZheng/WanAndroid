package com.zhengsr.wanandroid.net;



import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.CollectBean;
import com.zhengsr.wanandroid.bean.HotKeyBean;
import com.zhengsr.wanandroid.bean.NaviBean;
import com.zhengsr.wanandroid.bean.SystematicBean;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.LoginBean;
import com.zhengsr.wanandroid.bean.ProjectBean;
import com.zhengsr.wanandroid.bean.ProjectListBean;
import com.zhengsr.wanandroid.bean.RankBean;
import com.zhengsr.wanandroid.bean.RankListBean;
import com.zhengsr.wanandroid.bean.RegisterBean;
import com.zhengsr.wanandroid.bean.ShareBean;
import com.zhengsr.wanandroid.bean.UsefulWebBean;
import com.zhengsr.wanandroid.bean.WechatBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    Observable<BaseResponse<PageDataInfo<List<ArticleData>>>> getArticle(@Path("num") int num);

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
     * 我的分享
     * https://wanandroid.com/user/lg/private_articles/1/json
     */
    @GET("user/lg/private_articles/{page}/json")
    Observable<BaseResponse<ShareBean>> getMyShareData(@Path("page") int page);
    /**
     * 删除分享文章
     * https://wanandroid.com/lg/user_article/delete/9475/json
     */
    @POST("lg/user_article/delete/{id}/json")
    Observable<BaseResponse> deleteMyShare(@Path("id") int id);

    /**
     * 分享文章
     * https://www.wanandroid.com/lg/user_article/add/json
     */
    @POST("lg/user_article/add/json")
    @FormUrlEncoded
    Observable<BaseResponse> shareArticle(@Field("title") String title,@Field("link") String link);

    /**
     * 收藏站外文章
     * https://www.wanandroid.com/lg/collect/add/json
     * post
     */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Observable<BaseResponse<CollectBean>> addLinkArticle(@Field("title") String title,
                                        @Field("author") String author,
                                        @Field("link") String link);

    /**
     * 我的收藏
     * https://www.wanandroid.com/lg/collect/list/0/json
     * @return
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<PageDataInfo<List<CollectBean>>>> getMyCollect(@Path("page") int page);

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
    @GET("project/list/{page}/json")
    Observable<BaseResponse<PageDataInfo<List<ProjectBean>>>> getProjectInfo(@Path("page") int page,@Query("cid") int cid);

    /**
     * 添加收藏
     * https://www.wanandroid.com/lg/collect/1165/json
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse> addArticle(@Path("id") int id);
    /**
     * 添加收藏
     * https://www.wanandroid.com/lg/uncollect_originId/2333/json
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse> removeArticle(@Path("id") int id);

    /**
     * 获取体系
     * https://www.wanandroid.com/tree/json
     */
    @GET("tree/json")
    Observable<BaseResponse<List<SystematicBean>>> getTreeKnowledge();

    /**
     * 获取系列的具体内容
     * https://www.wanandroid.com/article/list/0/json?cid=60
     *
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<PageDataInfo<List<ArticleData>>>> getNaviDetail(@Path("page") int page,@Query("cid") int cid);

    /**
     * https://www.wanandroid.com/navi/json
     * 获取导航数据
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NaviBean>>> getNaviData();

    /**
     * 获取公众号
     * https://wanandroid.com/wxarticle/chapters/json
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<WechatBean>>> getWechatList();

    /**
     * 获取某个公众号的具体内容
     * https://wanandroid.com/wxarticle/list/408/1/json
     */
    @GET("wxarticle/list/{id}/{page}/json ")
    Observable<BaseResponse<PageDataInfo<List<ArticleData>>>> getWechatAuthor(@Path("id") int id,@Path("page") int page);

    /**
     * https://wanandroid.com/wxarticle/list/408/1/json?k=Java
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<PageDataInfo<List<ArticleData>>>> searchWechatBykey(@Path("id") int id,
                                                                                @Path("page") int page,
                                                                                @Query("k") String keyMsg);

    /**
     * https://wanandroid.com/user_article/list/页码/json
     * 从0开始
     * @return
     */
    @GET("user_article/list/{page}/json")
    Observable<BaseResponse<PageDataInfo<List<ArticleData>>>> getSquareDatas(@Path("page") int page);

    /**
     * https://www.wanandroid.com/user/2/share_articles/页码/json
     * 获取分享人的分享文章列表
     */
    @GET("user/{id}/share_articles/{page}/json")
    Observable<BaseResponse<ShareBean>> getUserShareData(@Path("id") int id, @Path("page") int page);


    /**
     * 获取热搜
     * http://www.wanandroid.com/hotkey/json
     */
    @GET("hotkey/json")
    Observable<BaseResponse<List<HotKeyBean>>> getHotkeyBean();

    /**
     * 搜索关键字
     * https://www.wanandroid.com/article/query/0/json
     */
    @POST("article/query/{page}/json")
    Observable<BaseResponse<PageDataInfo<List<ArticleData>>>> searchArticleByKeyWord(@Path("page") int page,
                                                                                     @Query("k") String word);

    /**
     * https://www.wanandroid.com/friend/json
     * 常用网站
     */

    @GET("friend/json")
    Observable<BaseResponse<List<UsefulWebBean>>> getUsefulWeb();

}
