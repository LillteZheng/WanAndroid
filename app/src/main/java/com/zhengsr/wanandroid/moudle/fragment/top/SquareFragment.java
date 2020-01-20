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
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.moudle.activity.LoginActivity;
import com.zhengsr.wanandroid.moudle.activity.WebViewActivity;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.TopPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther by zhengshaorui on 2019/12/1
 * describe:
 */
public class SquareFragment extends BaseNetFragment<TopPresent> implements BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.OnItemClickListener, IContractView.ISquareView<ArticleData> {
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private int mCurPage = 0;
    private int mMaxPage;
    private int mSharePage = 1;
    private int mShareMaxPage = 1;
    private List<ArticleData> mArticleBeans = new ArrayList<>();
    private SquareAdapter mAdapter;
    private boolean isShare;
    private ArticleData mShareItem;

    public static SquareFragment newInstance(ArticleData data) {
        
        Bundle args = new Bundle();
        args.putSerializable("data",data);
        SquareFragment fragment = new SquareFragment();
        fragment.setArguments(args);
        return fragment;
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
        mAdapter = new SquareAdapter(R.layout.item_article_recy_layout, mArticleBeans);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemClickListener(this);
        initToolbar();
    }

    @Override
    public TopPresent getPresent() {
        mPresent = new TopPresent(this);
        return mPresent;
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        Bundle arguments = getArguments();
        mShareItem = (ArticleData) arguments.getSerializable("data");
        if (mShareItem != null){
            String author = (mShareItem.getAuthor() != null && mShareItem.getAuthor().length() > 0) ? mShareItem.getAuthor(): mShareItem.getShareUser();
            getBarTitleView().setText(author+" 分享的文章");
            isShare = true;
            mPresent.getUserShareData(mShareItem.getUserId(),mSharePage,true,true);
            View view = LayoutInflater.from(_mActivity).inflate(R.layout.nothing_layout,null);
            mAdapter.setEmptyView(view);
        }else{
            getBarTitleView().setText("广场");
            isShare = false;
            mPresent.getSqureData(mCurPage,true,true);
        }



    }
    private void initToolbar() {

        ImageView imageView = getLeftIconView();
        imageView.setImageResource(R.mipmap.back);
        imageView.setTag(TAG_BACK);
        imageView.setPadding(10,10,10,10);
        imageView.setColorFilter(Color.WHITE);
        getRightIconView().setVisibility(View.GONE);

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        boolean isLogin = isLogin();
        if (!isLogin){
            SquareFragment.this.startActivity(new Intent(_mActivity, LoginActivity.class));
            return;
        }
        switch (view.getId()){
            case R.id.item_article_like:
                //是否登录
                ArticleData data = mArticleBeans.get(position);
                if (data.isCollect()){
                    mPresent.removeArticle(position,data);
                }else{
                    mPresent.addArticle(position,data);
                }
                break;
            case R.id.item_article_share:
                start(SquareFragment.newInstance(mArticleBeans.get(position)));
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
        webBean.isCollect = true;
        webBean.position = position;
        webBean.url = bean.getLink();
        Intent intent = new Intent(_mActivity, WebViewActivity.class);
        intent.putExtra("bean",webBean);
        SquareFragment.this.startActivityForResult(intent,1);
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
                    mAdapter.setData(bean.position,articleData);
                }
            }
        }
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
    public void getSquareData(int maxPage, List<ArticleData> datas,boolean isRefresh) {
        mMaxPage = maxPage;
        if (isRefresh) {
            mArticleBeans.clear();
        }
        mArticleBeans.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getShareData(int maxPage, List<ArticleData> datas, boolean isRefresh) {
        mShareMaxPage = maxPage;
        if (isRefresh){
            mArticleBeans.clear();
        }
        mArticleBeans.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void reFreshMore() {
        super.reFreshMore();
        if (!isShare) {
            mCurPage = 0;
            mPresent.getSqureData(mCurPage, false, true);
        }else{
            mCurPage = 1;
            mPresent.getUserShareData(mSharePage,mShareItem.getUserId(),false,true);
        }
    }

    @Override
    public void loadMore() {
        super.loadMore();
        if (!isShare) {
            if (mCurPage >= mMaxPage) {
                Toast.makeText(_mActivity, "已经到底了", Toast.LENGTH_SHORT).show();
                return;
            }
            mCurPage++;
            mPresent.getSqureData(mCurPage, false, false);
        }else{
            if (mSharePage >= mShareMaxPage) {
                Toast.makeText(_mActivity, "已经到底了", Toast.LENGTH_SHORT).show();
                return;
            }
            mSharePage++;
            mPresent.getUserShareData(mCurPage, mShareItem.getUserId(),false, false);
        }
    }

    class SquareAdapter extends BaseQuickAdapter<ArticleData, BaseViewHolder> {

        private boolean isCollected = false;

        public SquareAdapter(int layoutResId, @Nullable List<ArticleData> data) {
            super(layoutResId, data);
        }

        public void setCollected(boolean collected) {
            isCollected = collected;
        }



        @Override
        protected void convert(BaseViewHolder helper, ArticleData item) {
            String msg;
            if (!TextUtils.isEmpty(item.getSuperChapterName())){
                msg = item.getSuperChapterName()+"/"+item.getChapterName();
            }else{
                msg = item.getChapterName();
            }
            if (!isShare) {
                helper.setVisible(R.id.item_article_share, true);
            }
            String author = (item.getAuthor() != null && item.getAuthor().length() > 0) ? item.getAuthor():item.getShareUser();
            helper.setText(R.id.item_article_author,author)
                    .setText(R.id.item_article_chapat, msg)
                    .setText(R.id.item_article_title,item.getTitle())
                    .setText(R.id.item_article_time,item.getNiceDate())
                    .addOnClickListener(R.id.item_article_like)
                    .addOnClickListener(R.id.item_article_share);

            if (item.isCollect() || isCollected) {
                helper.setImageResource(R.id.item_article_like, R.drawable.icon_like_article_select);
            } else {
                helper.setImageResource(R.id.item_article_like, R.drawable.icon_like_article_not_selected);
            }

        }


    }
}
