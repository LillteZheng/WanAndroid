package com.zhengsr.wanandroid.moudle.fragment.mine;

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
import com.zhengsr.wanandroid.moudle.activity.WebViewActivity;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.UserPresent;
import com.zhengsr.wanandroid.window.AddShareDialog;
import com.zhengsr.wanandroid.window.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class ShareFragment extends BaseNetFragment<UserPresent> implements BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.OnItemClickListener, IContractView.IShareView {

    private LoadingDialog mDialog;

    public static ShareFragment newInstance() {

        Bundle args = new Bundle();

        ShareFragment fragment = new ShareFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private int mCurPage = 1;
    private int mMaxPage ;

    private CollectAdapter mAdapter;
    private List<ArticleData> mArticleDatas = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public UserPresent getPresent() {
        mPresent = new UserPresent(this);
        return mPresent;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new CollectAdapter(R.layout.item_article_recy_layout, mArticleDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemClickListener(this);
        initToolbar();
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.getMyShareData(mCurPage,true,true);
        View view = LayoutInflater.from(_mActivity).inflate(R.layout.nothing_layout,null);
        mAdapter.setEmptyView(view);
    }
    private void initToolbar() {
        getBarTitleView().setText("我的分享");
        ImageView imageView = getLeftIconView();
        imageView.setImageResource(R.mipmap.back);
        imageView.setTag(TAG_BACK);
        imageView.setPadding(10,10,10,10);
        imageView.setColorFilter(Color.WHITE);
        getRightIconView().setImageResource(R.mipmap.add);

        getRightIconView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddShareDialog(_mActivity).setListener(new AddShareDialog.ShareListener() {
                    @Override
                    public void getShare(String title, String link) {
                        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(link)){
                            mPresent.shareArticle(title,link);
                            mDialog = new LoadingDialog(_mActivity, "正在上传...");
                        }else{
                            Toast.makeText(_mActivity, "标题或链接不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_article_like:
                ArticleData data = mArticleDatas.get(position);
                mPresent.deleteShare(position,data.getId());
                break;
            default:break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ArticleData bean = mArticleDatas.get(position);
        WebBean webBean = new WebBean();
        webBean.id = bean.getId();
        webBean.title = bean.getTitle();
        webBean.isCollect = true;
        webBean.position = position;
        webBean.isShowIcon = false;
        webBean.url = bean.getLink();
        Intent intent = new Intent(_mActivity, WebViewActivity.class);
        intent.putExtra("bean",webBean);
        ShareFragment.this.startActivity(intent);
    }






    @Override
    public void reFreshMore() {
        super.reFreshMore();
        mCurPage = 1;
        mPresent.getMyShareData(mCurPage,false,true);
    }

    @Override
    public void loadMore() {
        super.loadMore();
        if (mCurPage >= mMaxPage){
            Toast.makeText(_mActivity, "没有更多文章了", Toast.LENGTH_SHORT).show();
        }else{
            mCurPage ++;
            mPresent.getMyShareData(mCurPage,false,false);
        }
    }





    @Override
    public void getShareData(int maxPage, List<ArticleData> datas, boolean isRefresh) {
        mMaxPage = maxPage;
        if (isRefresh){
            mArticleDatas.clear();
        }
        mArticleDatas.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteSuccess(int position) {
        Toast.makeText(_mActivity, "删除成功", Toast.LENGTH_SHORT).show();
        mArticleDatas.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void shareSuccess() {
        mDialog.dismiss();
        mCurPage = 1;
        mPresent.getMyShareData(mCurPage,false,true);
    }

    @Override
    public void showErrorMsg(String msg) {
        super.showErrorMsg(msg);
        mDialog.dismiss();
        Toast.makeText(_mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    class CollectAdapter extends BaseQuickAdapter<ArticleData, BaseViewHolder> {


        public CollectAdapter(int layoutResId, @Nullable List<ArticleData> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ArticleData item) {

            String msg;
            if (!TextUtils.isEmpty(item.getSuperChapterName())){
                msg = item.getSuperChapterName()+"/"+item.getChapterName();
            }else{
                msg = item.getChapterName();
            }

            String author = (item.getAuthor() != null && item.getAuthor().length() > 0) ? item.getAuthor():item.getShareUser();
            helper.setText(R.id.item_article_author,author)
                    .setText(R.id.item_article_chapat, msg)
                    .setText(R.id.item_article_title,item.getTitle())
                    .setText(R.id.item_article_time,item.getNiceDate())
                    .addOnClickListener(R.id.item_article_like);


            helper.setImageResource(R.id.item_article_like, R.drawable.icon_like_article_select);
        }

    }

}
