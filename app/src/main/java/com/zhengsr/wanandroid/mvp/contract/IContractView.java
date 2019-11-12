package com.zhengsr.wanandroid.mvp.contract;


import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.mvp.base.IBaseView;

import java.util.List;

/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe: 接口的统一管理类，这样方便查找和管理
 */
public interface IContractView {

    interface IHomeView extends IBaseView {
        void loadBanner(List<BannerBean> bannerBeans);
    }

}
