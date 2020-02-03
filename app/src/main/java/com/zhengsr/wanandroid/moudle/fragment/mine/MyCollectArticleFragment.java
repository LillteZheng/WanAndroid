package com.zhengsr.wanandroid.moudle.fragment.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.CollectBean;
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.moudle.activity.WebViewActivity;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.UserPresent;
import com.zhengsr.wanandroid.utils.Lgg;
import com.zhengsr.wanandroid.window.AddDialog;
import com.zhengsr.wanandroid.window.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class MyCollectArticleFragment extends BaseNetFragment<UserPresent> implements BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.OnItemClickListener, IContractView.IArticleView<CollectBean> {

    public static MyCollectArticleFragment newInstance() {

        Bundle args = new Bundle();

        MyCollectArticleFragment fragment = new MyCollectArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private CollectAdapter mAdapter;
    private List<CollectBean> mCollectBeans = new ArrayList<>();
    private LoadingDialog mDialog;

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
        mAdapter = new CollectAdapter(R.layout.item_article_recy_layout, mCollectBeans);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemClickListener(this);
        initToolbar();
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.getMyCollect();
    }

    private void initToolbar() {
        getBarTitleView().setText("我的收藏");
        ImageView imageView = getLeftIconView();
        imageView.setImageResource(R.mipmap.back);
        imageView.setTag(TAG_BACK);
        imageView.setPadding(10, 10, 10, 10);
        imageView.setColorFilter(Color.WHITE);
        getRightIconView().setImageResource(R.mipmap.add);

        getRightIconView().setTag(TAG_ADD);
        getRightIconView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddDialog(_mActivity).title("收藏文章")
                        .showAuthor()
                        .btnMsg("收藏")
                        .secondMsg("https://www.wanandroid.com/")
                        .listener(new AddDialog.AddListenerAdapter() {
                            @Override
                            public void onMsg(String title, String author, String link) {
                                super.onMsg(title, author, link);
                                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(author) && !TextUtils.isEmpty(link)) {
                                   // Lgg.d("add: "+title+" "+author+" "+link);
                                    mPresent.addLinkArticle(title, author,link);
                                    mDialog = new LoadingDialog(_mActivity, "正在上传...");
                                } else {
                                    Toast.makeText(_mActivity, "标题或作者或链接不能为空", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_article_like:
                CollectBean data = mCollectBeans.get(position);

                mPresent.removeArticle(position, data.getOriginId());

                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CollectBean bean = mCollectBeans.get(position);
        WebBean webBean = new WebBean();
        webBean.id = bean.getOriginId();
        webBean.title = bean.getTitle();
        webBean.isCollect = true;
        webBean.position = position;
        webBean.url = bean.getLink();
        webBean.originId = bean.getOriginId();
        Intent intent = new Intent(_mActivity, WebViewActivity.class);
        intent.putExtra("bean", webBean);
        MyCollectArticleFragment.this.startActivityForResult(intent, 1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                WebBean bean = (WebBean) data.getSerializableExtra("bean");
                if (bean != null) {
                    if (!bean.isCollect) {
                       mAdapter.remove(bean.position);
                    }
                }
            }
        }
    }

    @Override
    public void loadArticle(List<CollectBean> CollectBeans, boolean isRefresh) {
        if (isRefresh) {
            mCollectBeans.clear();
        }
        mCollectBeans.addAll(CollectBeans);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addSuccess() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        Toast.makeText(_mActivity, "收藏成功", Toast.LENGTH_SHORT).show();
        mPresent.getMyCollect();
    }

    @Override
    public void showErrorMsg(String msg) {
            super.showErrorMsg(msg);
        if (mDialog != null) {
            mDialog.dismiss();
        }
        Toast.makeText(_mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void reFreshMore() {
        super.reFreshMore();
        mPresent.refreshCollect();
    }

    @Override
    public void loadMore() {
        super.loadMore();
        if (mPresent.isLastestPage()) {
            Toast.makeText(_mActivity, "没有更多文章了", Toast.LENGTH_SHORT).show();
        } else {
            mPresent.loadMoreCollect();
        }
    }


    @Override
    public void addArticleSuccess(int position, CollectBean data) {

    }

    @Override
    public void removeArticleSuccess(int position, CollectBean data) {
        mCollectBeans.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    class CollectAdapter extends BaseQuickAdapter<CollectBean, BaseViewHolder> {


        public CollectAdapter(int layoutResId, @Nullable List<CollectBean> data) {
            super(layoutResId, data);
        }


        @Override
        protected void convert(BaseViewHolder helper, CollectBean item) {

            helper.setText(R.id.item_article_author, item.getAuthor())
                    .setText(R.id.item_article_chapat, item.getChapterName())
                    .setText(R.id.item_article_title, item.getTitle())
                    .setText(R.id.item_article_time, item.getNiceDate())
                    .addOnClickListener(R.id.item_article_like);
            helper.setImageResource(R.id.item_article_like, R.drawable.icon_like_article_select);

        }

    }

}
