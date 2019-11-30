package com.zhengsr.wanandroid.moudle.fragment.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.bean.WechatBean;
import com.zhengsr.wanandroid.moudle.activity.LoginActivity;
import com.zhengsr.wanandroid.moudle.activity.WebViewActivity;
import com.zhengsr.wanandroid.moudle.adapter.HomeAdapter;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.WechatPresent;
import com.zhengsr.wanandroid.view.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther by zhengshaorui on 2019/11/30
 * describe:
 */
public class WechatDetailFragment extends BaseNetFragment<WechatPresent> implements BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.OnItemChildClickListener, IContractView.IWechatDetailView<ArticleData> {

    private HomeAdapter mAdapter;
    private WechatBean mBean;
    private boolean isSearchLogic = false;

    public static WechatDetailFragment newInstance(WechatBean bean) {

        Bundle args = new Bundle();
        args.putSerializable("bean", bean);
        WechatDetailFragment fragment = new WechatDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_ly)
    Group mSearchLy;
    @BindView(R.id.searchview)
    SearchView mSearchView;

    private int mCurPage = 1;
    private int mSearchPage = 1;
    private int mMaxPage;
    private int mSearchMaxPage;
    private List<ArticleData> mArticleBeans = new ArrayList<>();
    private List<ArticleData> mCacheArticles = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_viewpager_detail;
    }

    @Override
    public WechatPresent getPresent() {
        mPresent = new WechatPresent(this);
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
        mSearchLy.setVisibility(View.VISIBLE);
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        Bundle arguments = getArguments();
        if (arguments != null) {
            mBean = (WechatBean) arguments.getSerializable("bean");
            mPresent.getWechatAuthor(mCurPage, mBean);
        }

        mSearchView.setListener(new SearchView.TextChangeListener() {
            @Override
            public void isEmpty(boolean isEmpty) {
                if (isEmpty && !mCacheArticles.isEmpty()) {
                    mArticleBeans.clear();
                    mArticleBeans.addAll(mCacheArticles);
                    mAdapter.notifyDataSetChanged();
                    isSearchLogic = false;
                }
            }
        });
    }

    @Override
    public void getWechatArticle(int maxPage, List<ArticleData> datas, boolean isRefresh) {
        mMaxPage = maxPage;
        if (isRefresh) {
            mArticleBeans.clear();
        }
        mArticleBeans.addAll(datas);
        mAdapter.notifyDataSetChanged();
        View emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.nothing_layout, null);
        mAdapter.setEmptyView(emptyView);
        mCacheArticles.clear();
        mCacheArticles.addAll(mArticleBeans);
    }

    @Override
    public void getSearchArticle(int maxPage, List<ArticleData> datas, boolean isRefresh) {
        mSearchMaxPage = maxPage;
        if (maxPage == 0) {
            isSearchLogic = false;
            Toast.makeText(_mActivity, "未搜索到数据", Toast.LENGTH_SHORT).show();
        } else {
            isSearchLogic = true;
            if (isRefresh) {
                mArticleBeans.clear();

            }
            mArticleBeans.addAll(datas);
            mAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.search_tv)
    public void onCLick(View view) {
        String msg = mSearchView.getMsg();

        mPresent.searchKeyMsg(mCurPage, mBean.getId(), msg, true);
        mArticleBeans.clear();
        mAdapter.notifyDataSetChanged();
        hideSoftInput();

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
        intent.putExtra("bean", webBean);
        WechatDetailFragment.this.startActivity(intent);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_article_like:
                //是否登录
                boolean isLogin = isLogin();
                if (!isLogin) {
                    WechatDetailFragment.this.startActivity(new Intent(_mActivity, LoginActivity.class));
                } else {
                    ArticleData data = mArticleBeans.get(position);
                    if (data.isCollect()) {
                        mPresent.removeArticle(position, data);
                    } else {
                        mPresent.addArticle(position, data);
                    }

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void addArticleSuccess(int position, ArticleData data) {
        mAdapter.setData(position, data);
        Toast.makeText(_mActivity, "收藏成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeArticleSuccess(int position, ArticleData data) {
        mAdapter.setData(position, data);
        Toast.makeText(_mActivity, "已取消收藏", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void reFreshMore() {
        super.reFreshMore();
        if (!isSearchLogic) {
            mCurPage = 1;
            mPresent.getWechatAuthor(mCurPage, mBean);
        } else {
            mSearchPage = 1;
            String msg = mSearchView.getMsg();
            mPresent.searchKeyMsg(mSearchPage, mBean.getId(), msg, true);
        }
    }

    @Override
    public void loadMore() {
        super.loadMore();
        if (!isSearchLogic) {
            if (mCurPage >= mMaxPage) {
                Toast.makeText(_mActivity, "已经到底啦", Toast.LENGTH_SHORT).show();
            }
            mCurPage++;
            mPresent.loadMore(mCurPage, mBean);
        } else {
            if (mSearchPage >= mSearchMaxPage) {
                Toast.makeText(_mActivity, "已经到底啦", Toast.LENGTH_SHORT).show();
            }
            String msg = mSearchView.getMsg();
            mSearchPage++;
            mPresent.searchKeyMsg(mSearchPage, mBean.getId(), msg, false);
        }
    }


}
