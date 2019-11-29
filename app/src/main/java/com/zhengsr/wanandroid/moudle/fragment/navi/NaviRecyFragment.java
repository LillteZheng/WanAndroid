package com.zhengsr.wanandroid.moudle.fragment.navi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.NaviChildrenBean;
import com.zhengsr.wanandroid.bean.ProjectListBean;
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.moudle.activity.LoginActivity;
import com.zhengsr.wanandroid.moudle.activity.WebViewActivity;
import com.zhengsr.wanandroid.moudle.adapter.HomeAdapter;
import com.zhengsr.wanandroid.moudle.fragment.HomeFragment;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.NaviPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class NaviRecyFragment extends BaseNetFragment<NaviPresent> implements IContractView.INaviDetailView<ArticleData>,
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    private HomeAdapter mAdapter;
    private NaviChildrenBean mNaviChildBean;

    public static NaviRecyFragment newInstance(NaviChildrenBean bean) {

        Bundle args = new Bundle();
        args.putSerializable("bean",bean);
        NaviRecyFragment fragment = new NaviRecyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private int mCurPage = 0;
    private int mMaxPage;
    private List<ArticleData> mArticleBeans = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_viewpager_detail;
    }

    @Override
    public NaviPresent getPresent() {
        mPresent = new NaviPresent(this);
        return mPresent;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new HomeAdapter(R.layout.item_article_recy_layout, mArticleBeans);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        Bundle arguments = getArguments();
        if (arguments != null) {
            mNaviChildBean = (NaviChildrenBean) arguments.getSerializable("bean");
            mPresent.getDetailNavi(mCurPage, mNaviChildBean);
        }
    }



    @Override
    public void getNaviDetail(int maxPage, List<ArticleData> datas, boolean isRefresh) {
        mMaxPage = maxPage;
        if (isRefresh){
            mArticleBeans.clear();
        }
        mArticleBeans.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_article_like:
                //是否登录
                boolean isLogin = isLogin();
                if (!isLogin){
                    NaviRecyFragment.this.startActivity(new Intent(_mActivity, LoginActivity.class));
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
        NaviRecyFragment.this.startActivity(intent);
    }



    @Override
    public void addArticleSuccess(int position, ArticleData data) {
        mAdapter.setData(position,data);
        Toast.makeText(_mActivity, "收藏成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeArticleSuccess(int position, ArticleData data) {
        mAdapter.setData(position,data);
        Toast.makeText(_mActivity, "已取消收藏", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void reFreshMore() {
        super.reFreshMore();
        mPresent.getDetailNavi(mCurPage,mNaviChildBean);
    }

    @Override
    public void loadMore() {
        super.loadMore();
        if (mCurPage >= mMaxPage){
            Toast.makeText(_mActivity, "已经到底啦", Toast.LENGTH_SHORT).show();
        }
        mPresent.loadMore(mCurPage,mNaviChildBean);
    }
}
