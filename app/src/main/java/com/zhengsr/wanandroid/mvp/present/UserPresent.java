package com.zhengsr.wanandroid.mvp.present;

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
import com.zhengsr.wanandroid.utils.RxUtils;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe: 登录，注册等复用present
 */
public class UserPresent extends BasePresent<IBaseView> {
    private DataManager mDataManager;
    private IBaseView mView;
    private int mRankPage = 0;
    public static UserPresent create(IBaseView view){
        return new UserPresent(view);
    }
    private UserPresent(IBaseView view){
        mDataManager = DataManager.getInstance();
        mView = view;
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

}
