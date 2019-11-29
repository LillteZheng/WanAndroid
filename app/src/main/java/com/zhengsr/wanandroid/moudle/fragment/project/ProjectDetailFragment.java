package com.zhengsr.wanandroid.moudle.fragment.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ProjectBean;
import com.zhengsr.wanandroid.bean.ProjectListBean;
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.moudle.activity.WebViewActivity;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.ProjectPresent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class ProjectDetailFragment extends BaseNetFragment<ProjectPresent> implements IContractView.IProjectDetailView, BaseQuickAdapter.OnItemClickListener {
    private int mCurPage = 1;
    private ProjectAdapter mAdapter;
    private ProjectListBean mBean;
    private int mMaxPage;
    private SimpleDateFormat mSdf = new SimpleDateFormat("yyyy-MM-dd");
    public static ProjectDetailFragment newInstance(ProjectListBean bean) {

        Bundle args = new Bundle();
        args.putSerializable("bean", bean);
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private List<ProjectBean> mData = new ArrayList<>();

    @Override
    public ProjectPresent getPresent() {
        mPresent = new ProjectPresent(this);
        return mPresent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_viewpager_detail;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ProjectAdapter(R.layout.item_project_layout, mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        Bundle arguments = getArguments();
        if (arguments != null) {
            mBean = (ProjectListBean) arguments.getSerializable("bean");
            mPresent.getDetailProject(mCurPage, mBean.getId());
        }
    }

    @Override
    public void getProjectDetail(List<ProjectBean> beans, int maxPage,boolean isRefresh) {
        mMaxPage = maxPage;
        if (isRefresh) {
            mData.clear();
        }
        mData.addAll(beans);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ProjectBean bean = mData.get(position);

        WebBean webBean = new WebBean();
        webBean.id = bean.getId();
        webBean.title = bean.getTitle();
        webBean.url = bean.getLink();
        webBean.isShowIcon = false;
        Intent intent = new Intent(_mActivity, WebViewActivity.class);
        intent.putExtra("bean",webBean);
        ProjectDetailFragment.this.startActivity(intent);
    }

    class ProjectAdapter extends BaseQuickAdapter<ProjectBean, BaseViewHolder> {

        public ProjectAdapter(int layoutResId, @Nullable List<ProjectBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, ProjectBean item) {
            ImageView imageView = helper.getView(R.id.item_project_icon);
            Glide.with(_mActivity)
                    .load(item.getEnvelopePic())
                    .placeholder(R.mipmap.loading)
                    .into(imageView);
            helper.setText(R.id.item_project_title, item.getTitle())
                    .setText(R.id.item_project_describe, item.getDesc())
                    .setText(R.id.item_project_time, transferLongToDate(item.getPublishTime()))
                    .setText(R.id.item_project_author, item.getAuthor());

        }
    }


    @Override
    public void reFreshMore() {
        super.reFreshMore();
        mCurPage = 1;
        mPresent.getDetailProject(mCurPage,mBean.getId());

    }

    @Override
    public void loadMore() {
        super.loadMore();
        if (mCurPage >= mMaxPage){
            Toast.makeText(_mActivity, "已经到底啦", Toast.LENGTH_SHORT).show();
        }else {
            mCurPage++;
            mPresent.loadMore(mCurPage, mBean.getId());
        }
    }

    /**
     * 转时间
     * @param millSec
     * @return
     */
    private String transferLongToDate(Long millSec) {

        Date date = new Date(millSec);
        return mSdf.format(date);
    }
}
