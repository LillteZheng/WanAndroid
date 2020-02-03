package com.zhengsr.wanandroid.moudle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.moudle.activity.LoginActivity;
import com.zhengsr.wanandroid.moudle.activity.WebViewActivity;
import com.zhengsr.wanandroid.moudle.adapter.HomeAdapter;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.HomePresent;
import com.zhengsr.wanandroid.utils.Lgg;
import com.zhengsr.wanandroid.view.BannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class HomeFragment extends BaseNetFragment<HomePresent> implements IContractView.IHomeView<ArticleData>, BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.OnItemClickListener {


    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private BannerView mBannerView;
    private HomeAdapter mHomeAdapter;
    private List<ArticleData> mArticleBeans = new ArrayList<>();
    @Override
    public HomePresent getPresent() {
        mPresent = new HomePresent(this);
        return mPresent;
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
        showWebIcon();
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.startLoad(true);
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
    public void loadArticle(List<ArticleData> articles) {
        if (articles != null) {
            mArticleBeans.addAll(articles);
            mHomeAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void reload() {
        super.reload();
        mPresent.startLoad(true);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_article_like:
                //是否登录
                boolean isLogin = isLogin();
                if (!isLogin){
                    HomeFragment.this.startActivity(new Intent(_mActivity,LoginActivity.class));
                }else{
                    ArticleData data = mArticleBeans.get(position);
                    if (data.isCollect()){
                        mPresent.removeArticle(position,data);
                    }else{
                        mPresent.addArticle(position,data);
                    }

                }
                break;
            default:break;
        }
    }



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ArticleData bean = mArticleBeans.get(position);
        WebBean webBean = new WebBean();
        webBean.id = bean.getId();
        webBean.title = bean.getTitle();
        webBean.isCollect = bean.isCollect();
        webBean.position = position;
        webBean.url = bean.getLink();
        Intent intent = new Intent(_mActivity, WebViewActivity.class);
        intent.putExtra("bean",webBean);
        HomeFragment.this.startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (data != null){
                WebBean bean = (WebBean) data.getSerializableExtra("bean");
                if (bean != null) {
                    ArticleData articleData = mArticleBeans.get(bean.position);
                    articleData.setCollect(bean.isCollect);
                    mHomeAdapter.setData(bean.position,articleData);
                }
            }
        }
    }


    @Override
    public void reFreshMore() {
        super.reFreshMore();
        mPresent.onRefresh();
    }

    @Override
    public void loadMore() {
        super.loadMore();
        mPresent.loadMore();
    }

    @Override
    public void addArticleSuccess(int position, ArticleData data) {
        mHomeAdapter.setData(position,data);
        Toast.makeText(_mActivity, "收藏成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeArticleSuccess(int position, ArticleData data) {
        mHomeAdapter.setData(position,data);
        Toast.makeText(_mActivity, "已取消收藏", Toast.LENGTH_SHORT).show();
    }
}
