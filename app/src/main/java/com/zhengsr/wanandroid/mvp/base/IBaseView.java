package com.zhengsr.wanandroid.mvp.base;

/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe:
 */
public interface IBaseView {
    /**
     * 显示加载
     */
    void showLoading() ;

    /**
     * 隐藏加载
     */
    void loadSuccess() ;

    /**
     * 加载失败
     */
    void showError();

    /**
     * 显示失败信息
     */
    void showErrorMsg(String msg);
}
