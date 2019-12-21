package com.zhengsr.wanandroid.moudle.fragment.top;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.HotKeyBean;
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.moudle.activity.LoginActivity;
import com.zhengsr.wanandroid.moudle.activity.WebViewActivity;
import com.zhengsr.wanandroid.moudle.adapter.HomeAdapter;
import com.zhengsr.wanandroid.moudle.fragment.HomeFragment;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.SearchPresent;
import com.zhengsr.wanandroid.window.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class SearchDetailFragment extends BaseNetFragment<SearchPresent> implements BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.OnItemClickListener, IContractView.ISearchView {

    private LoadingDialog mDialog;
    private String mKeyWord;

    public static SearchDetailFragment newInstance(String key) {

        Bundle args = new Bundle();
        args.putString("key",key);
        SearchDetailFragment fragment = new SearchDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private int mCurPage = 1;
    private int mMaxPage ;
    private HomeAdapter mAdapter;
    private List<ArticleData> mArticleDatas = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public SearchPresent getPresent() {
        mPresent = new SearchPresent(this);
        return mPresent;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new HomeAdapter(R.layout.item_article_recy_layout,mArticleDatas );
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemClickListener(this);
        initToolbar();
        hideSoftInput();
    }



    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        View view = LayoutInflater.from(_mActivity).inflate(R.layout.nothing_layout,null);
        mAdapter.setEmptyView(view);
        String key = getArguments().getString("key");
        mPresent.searchMore(mCurPage,mKeyWord,true);
    }
    private void initToolbar() {
        mKeyWord = getArguments().getString("key" );

        getBarTitleView().setText(mKeyWord);
        ImageView imageView = getLeftIconView();
        imageView.setImageResource(R.mipmap.back);
        imageView.setTag(TAG_BACK);
        imageView.setPadding(10,10,10,10);
        imageView.setColorFilter(Color.WHITE);

        getRightIconView().setVisibility(View.GONE);


    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ArticleData bean = mArticleDatas.get(position);
        WebBean webBean = new WebBean();
        webBean.id = bean.getId();
        webBean.title = bean.getTitle();
        webBean.isCollect = true;
        webBean.position = position;
        webBean.url = bean.getLink();
        Intent intent = new Intent(_mActivity, WebViewActivity.class);
        intent.putExtra("bean",webBean);
        SearchDetailFragment.this.startActivity(intent);
    }






    @Override
    public void reFreshMore() {
        super.reFreshMore();
        mCurPage = 0;
        mPresent.searchMore(mCurPage,mKeyWord,true);
    }

    @Override
    public void loadMore() {
        super.loadMore();
        if (mCurPage >= mMaxPage){
            Toast.makeText(_mActivity, "没有更多文章了", Toast.LENGTH_SHORT).show();
        }else{
            mCurPage ++;
            mPresent.searchMore(mCurPage,mKeyWord,false);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_article_like:
                //是否登录
                boolean isLogin = isLogin();
                if (!isLogin){
                    SearchDetailFragment.this.startActivity(new Intent(_mActivity, LoginActivity.class));
                }else{
                    ArticleData data = mArticleDatas.get(position);
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
    public void showErrorMsg(String msg) {
        super.showErrorMsg(msg);
        mDialog.dismiss();
        Toast.makeText(_mActivity, msg, Toast.LENGTH_SHORT).show();
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
    public void getSearchData(int maxPage, List<ArticleData> datas, boolean isRefresh) {
        mMaxPage = maxPage;
        if (isRefresh){
            mArticleDatas.clear();
        }
        mArticleDatas.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }



}
