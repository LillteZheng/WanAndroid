package com.zhengsr.wanandroid.moudle.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.PageDataInfo;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.moudle.adapter.HomeAdapter;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.HomePresent;
import com.zhengsr.wanandroid.view.BannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class HomeFragment extends BaseNetFragment<HomePresent> implements IContractView.IHomeView, BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.OnItemClickListener, BannerView.BannerItemClickListener {


    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private HomePresent mHomePresent;
    private BannerView mBannerView;
    private HomeAdapter mHomeAdapter;
    private List<ArticleData> mArticleBeans = new ArrayList<>();
    @Override
    public HomePresent getPresent() {
        mHomePresent = HomePresent.create(this);
        return mHomePresent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(manager);
        mHomeAdapter = new HomeAdapter(R.layout.item_article_recy_layout, mArticleBeans);
        mBannerView = new BannerView(_mActivity);
        mHomeAdapter.addHeaderView(mBannerView);
        mRecyclerView.setAdapter(mHomeAdapter);
        mHomeAdapter.setOnItemChildClickListener(this);
        mHomeAdapter.setOnItemClickListener(this);
        mBannerView.setBannerItemClickListener(this);
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mHomePresent.startLoad(true);
    }




    @Override
    public void loadMainData(List<BannerBean> bannerBeans, List<ArticleData> articles) {
        if (bannerBeans != null) {
            mBannerView.setData(bannerBeans);
        }
        if (articles != null){
            mArticleBeans.clear();
            mArticleBeans.addAll(articles);
        }
        mHomeAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadArticle(PageDataInfo articleListBean) {
        if (articleListBean != null) {
            mArticleBeans.addAll(articleListBean.getDatas());
            mHomeAdapter.notifyDataSetChanged();
        }
    }



    @Override
    public void reload() {
        super.reload();
        mHomePresent.startLoad(true);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_article_like:
                //是否登录
                boolean isLogin = isLogin();
                if (!isLogin){
                   // useParentStart(LoginActivity.newInstance());
                }
                break;
            default:break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void itemClick(View view, BannerBean bannerBean) {

    }

    @Override
    public void reFreshMore() {
        super.reFreshMore();
        mHomePresent.onRefresh();
    }

    @Override
    public void loadMore() {
        super.loadMore();
        mHomePresent.loadMore();
    }
}
