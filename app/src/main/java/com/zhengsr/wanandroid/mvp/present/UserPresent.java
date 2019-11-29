package com.zhengsr.wanandroid.mvp.present;

import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.CollectBean;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.bean.BaseResponse;
import com.zhengsr.wanandroid.bean.LoginBean;
import com.zhengsr.wanandroid.bean.RankBean;
import com.zhengsr.wanandroid.bean.RankListBean;
import com.zhengsr.wanandroid.bean.RegisterBean;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.model.DataManager;
import com.zhengsr.wanandroid.net.CusSubscribe;
import com.zhengsr.wanandroid.utils.Lgg;
import com.zhengsr.wanandroid.utils.RxUtils;

import java.util.List;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe: 登录，注册等复用present
 */
public class UserPresent extends BasePresent<IBaseView> {

    private int mRankPage = 0;
    private int mCollectPage = 0;
    private int mMaxCollectPage = -1;

    public UserPresent(IBaseView view) {
        super(view);
    }


    /**
     * 登录
     * @param userName
     * @param password
     */
    public void login(String userName,final String password){
        addSubscribe(
                mDataManager.login(userName,password)
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<LoginBean>(mView){
                    @Override
                    public void onNext(LoginBean bean) {
                        super.onNext(bean);
                        setLogin(true);
                        setUserName(bean.getUsername());
                        setPassword(password);
                        if (mView instanceof IContractView.ILoginView){
                            ((IContractView.ILoginView) mView).loginInfo(bean);
                        }
                    }
                })
        );
    }

    /**
     * 获取积分版
     * @param showLoading
     */
    public void getRankInfo(boolean showLoading){
        if (showLoading) {
            mView.showLoading();
        }
        addSubscribe(
                mDataManager.getRankInfo(mRankPage)
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<RankListBean>(mView) {

                    @Override
                    public void onNext(RankListBean listBean) {
                        super.onNext(listBean);
                        if (mView instanceof IContractView.IRankInfoView){
                            ((IContractView.IRankInfoView) mView).rankInfo(listBean);
                        }
                    }
                })
        );
    }

    public void refreshRank() {
        getRankInfo(false);
    }


    /**
     * 拿到个人信息
     */

    public void getUserInfo(){
        addSubscribe(
                mDataManager.getUserRankInfo()
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<RankBean>(mView){
                    @Override
                    public void onNext(RankBean rankBean) {
                        super.onNext(rankBean);
                        if (mView instanceof IContractView.IUserInfoView){
                            ((IContractView.IUserInfoView) mView).getInfoUser(rankBean);
                        }
                    }
                })
        );
    }

    /**
     * 退出登录
     */
    public void logout(){
        addSubscribe(
                mDataManager.logout()
                .compose(RxUtils.rxScheduers())
                .subscribeWith(new CusSubscribe<BaseResponse>(mView){
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        super.onNext(baseResponse);
                        setLogin(false);
                        setUserName("");
                        setPassword("");
                        if (mView instanceof IContractView.IUserInfoView){
                            ((IContractView.IUserInfoView) mView).logoutSuccess();
                        }
                    }
                })
        );
    }

    /**
     * 注册
     * @param userName
     * @param password
     * @param rePassword
     */
    public void register(String userName,String password,String rePassword){
        addSubscribe(
                mDataManager.register(userName,password,rePassword)
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<RegisterBean>(mView){
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        super.onNext(registerBean);
                        if (mView instanceof IContractView.ILoginView){
                            ((IContractView.ILoginView) mView).registerInfo(registerBean);
                        }
                    }
                })
        );
    }

    /**
     * 获取收藏页面
     */
    private void getMyCollect(boolean showLoading,boolean isRefresh){
        if (showLoading) {
            mView.showLoading();
        }
        addSubscribe(

                mDataManager.getMyCollect(mCollectPage)
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<PageDataInfo<List<CollectBean>>>(mView){
                    @Override
                    public void onNext(PageDataInfo<List<CollectBean>> listPageDataInfo) {
                        super.onNext(listPageDataInfo);
                        if (mView instanceof IContractView.IArticleView){
                            ((IContractView.IArticleView) mView).loadArticle(listPageDataInfo.getDatas(),isRefresh);
                        }
                    }
                })


        );
    }

    public void getMyCollect(){
        getMyCollect(true,true);
    }

    public void refreshCollect(){
        mCollectPage = 0;
        getMyCollect(false,true);
    }
    public void loadMoreCollect(){
        mCollectPage ++;
        getMyCollect(false,false);
    }
    public boolean isLastestPage(){
        if (mMaxCollectPage != -1){
            if (mCollectPage+1 >= mMaxCollectPage){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }


    public void removeArticle(int position,int id){
        addSubscribe(
                mDataManager.removeArticle(id)
                        .compose(RxUtils.rxScheduers())
                        .subscribeWith(new CusSubscribe<BaseResponse>(mView){
                            @Override
                            public void onNext(BaseResponse baseResponse) {
                                super.onNext(baseResponse);
                                if (mView instanceof IContractView.IArticleView) {
                                    ((IContractView.IArticleView) mView).removeArticleSuccess(position,null);
                                }
                            }
                        })
        );
    }

}
