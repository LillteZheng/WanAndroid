package com.zhengsr.wanandroid.mvp.present;

import com.zhengsr.wanandroid.bean.ProjectListBean;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;
import com.zhengsr.wanandroid.mvp.model.DataManager;
import com.zhengsr.wanandroid.net.CusSubscribe;
import com.zhengsr.wanandroid.utils.RxUtils;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class ProjectPresent extends BasePresent {
    private DataManager mDataManager;
    private IBaseView mView;
    public static ProjectPresent create(IBaseView view){
        return new ProjectPresent(view);
    }
    private ProjectPresent(IBaseView view){
        mDataManager = DataManager.getInstance();
        mView = view;
    }


    public void startLoad(){

    }

    private void startLoad(boolean showloading){
        if (showloading){
            mView.showLoading();
        }
        addSubscribe(
                mDataManager.getProjectSort()
                .compose(RxUtils.rxScheduers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CusSubscribe<ProjectListBean>(mView){
                    
                })
        );
    }
}
