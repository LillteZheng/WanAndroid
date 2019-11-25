package com.zhengsr.wanandroid.moudle.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.zhengsr.viewpagerlib.indicator.TabIndicator;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ProjectListBean;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.ProjectPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class ProjectFragment extends BaseNetFragment<ProjectPresent> implements IContractView.IProjectListView {


    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabindicator)
    TabIndicator mTabIndicator;
    public static ProjectFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public ProjectPresent getPresent() {
        mPresent = ProjectPresent.create(this);
        return mPresent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        getBarTitleView().setText("项目");
        mTabIndicator.setViewPagerSwitchSpeed(mViewPager,600);
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.startLoad();
    }

    @Override
    public void getProjectList(List<ProjectListBean> projectListBeans) {
        List<String> titles = new ArrayList<>();
        for (ProjectListBean bean : projectListBeans) {
            titles.add(bean.getName());
        }
        mTabIndicator.setTabData(mViewPager, titles, new TabIndicator.TabClickListener() {
            @Override
            public void onClick(int position) {
                mViewPager.setCurrentItem(position);
            }
        });
    }
}
