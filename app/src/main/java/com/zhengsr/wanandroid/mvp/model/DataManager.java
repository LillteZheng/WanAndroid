package com.zhengsr.wanandroid.mvp.model;



import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.CollectBean;
import com.zhengsr.wanandroid.bean.HotKeyBean;
import com.zhengsr.wanandroid.bean.NaviBean;
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
import com.zhengsr.wanandroid.bean.WechatBean;
import com.zhengsr.wanandroid.net.HttpCreate;
import com.zhengsr.wanandroid.net.HttpServerApi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    public Observable<BaseResponse<PageDataInfo<List<ArticleData>>>> getArticles(int num){
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
    public  Observable<BaseResponse<PageDataInfo<List<CollectBean>>>> getMyCollect(int page){
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

    public Observable<BaseResponse<PageDataInfo<List<ProjectBean>>>> getProjectInfo(int page, int cid){
        return mServerApi.getProjectInfo(page,cid);
    }

    /**
     * 添加收藏
     * @param id
     * @return
     */
    public Observable<BaseResponse> addArticle(int id){
        return mServerApi.addArticle(id);
    }

    /**
     * 取消收藏
     * @param id
     * @return
     */
    public Observable<BaseResponse> removeArticle(int id){

        return mServerApi.removeArticle(id);
    }

    /**
     * 获取知识体系
     * @return
     */
    public Observable<BaseResponse<List<NaviBean>>> getTreeKnowledge(){
        return mServerApi.getTreeKnowledge();
    }

    /**
     * 获取知识体系下具体的文章
     * @param page
     * @param cid
     * @return
     */
    public  Observable<BaseResponse<PageDataInfo<List<ArticleData>>>> getNaviDetail(int page, int cid){
        return mServerApi.getNaviDetail(page,cid);
    }

    /**
     * 获取微信公众号列表
     * @return
     */
    public Observable<BaseResponse<List<WechatBean>>> getWechatList(){
        return mServerApi.getWechatList();
    }

    /**
     * 获取某个公众号的具体文章
     * @param id
     * @param page
     * @return
     */
    public Observable<BaseResponse<PageDataInfo<List<ArticleData>>>> getWechatAuthor(int id, int page){
        return mServerApi.getWechatAuthor(id,page);
    }

    /**
     * 搜索公众号关键字
     * @param id
     * @param page
     * @param keyMsg
     * @return
     */
    public  Observable<BaseResponse<PageDataInfo<List<ArticleData>>>> searchWechatBykey(int id,
                                                                                        int page,
                                                                                        String keyMsg){
        return mServerApi.searchWechatBykey(id,page,keyMsg);
    }

    /**
     * 拿到广场数据
     * @param page
     * @return
     */
    public  Observable<BaseResponse<PageDataInfo<List<ArticleData>>>> getSquareDatas(int page){
        return mServerApi.getSquareDatas(page);
    }

    /**
     * 拿到某个id分享的列表
     * @param id
     * @param page
     * @return
     */
    public Observable<BaseResponse<ShareBean>> getUserShareData(int id, int page){
        return mServerApi.getUserShareData(id,page);
    }

    /**
     * 拿到我的分享
     * @param page
     * @return
     */
    public Observable<BaseResponse<ShareBean>> getMyShareData( int page){
        return mServerApi.getMyShareData(page);
    }

    /**
     * 删除分享
     * @param id
     * @return
     */
    public  Observable<BaseResponse> deleteMyShare( int id){
        return mServerApi.deleteMyShare(id);
    }

    /**
     * 添加分享
     * @param title
     * @param link
     * @return
     */
    public Observable<BaseResponse> shareArticle( String title, String link){
        return mServerApi.shareArticle(title,link);
    }

    public Observable<BaseResponse<List<HotKeyBean>>> getHotkeyBean(){
        return mServerApi.getHotkeyBean();
    }

}
