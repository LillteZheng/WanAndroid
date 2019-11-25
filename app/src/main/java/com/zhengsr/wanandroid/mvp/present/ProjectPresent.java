package com.zhengsr.wanandroid.mvp.present;

import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;
import com.zhengsr.wanandroid.mvp.model.DataManager;

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
}
