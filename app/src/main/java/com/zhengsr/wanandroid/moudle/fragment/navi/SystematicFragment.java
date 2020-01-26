package com.zhengsr.wanandroid.moudle.fragment.navi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.SystematicBean;
import com.zhengsr.wanandroid.bean.NaviChildrenBean;
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
public class SystematicFragment extends BaseNetFragment<NaviPresent> implements IContractView.ISystematicView,  BaseQuickAdapter.OnItemClickListener {


    private NaviAdapter mAdapter;

    public static SystematicFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SystematicFragment fragment = new SystematicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.normal_view)
    RecyclerView mRecyclerView;
    private List<SystematicBean> mNaviBeans = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.recycler_layout;
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
        mAdapter = new NaviAdapter(R.layout.item_navi, mNaviBeans);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.startLoad();
    }

    @Override
    public void getTreeKnowledge(List<SystematicBean> beans) {
        if (beans != null) {
            for (SystematicBean bean : beans) {
                StringBuilder sb = new StringBuilder();
                for (NaviChildrenBean child : bean.getChildren()) {
                   sb.append(child.getName()+"  ");
                }
                bean.childAppendString = sb.toString();
            }
            mNaviBeans.addAll(beans);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SystematicBean bean = mNaviBeans.get(position);
        useParentStart(NaviTabFragment.newInstance(bean));
    }


    class NaviAdapter extends BaseQuickAdapter<SystematicBean, BaseViewHolder>{

        public NaviAdapter(int layoutResId, @Nullable List<SystematicBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, SystematicBean item) {

            helper.setText(R.id.item_navi_name,item.getName())
                    .setText(R.id.item_navi_child,item.childAppendString);
        }
    }
}
