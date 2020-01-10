package com.zhengsr.wanandroid.moudle.fragment.project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhengsr.tablib.view.adapter.TabAdapter;
import com.zhengsr.tablib.view.flow.TabFlowLayout;
import com.zhengsr.viewpagerlib.indicator.TabIndicator;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ProjectListBean;
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
    @BindView(R.id.tabflow)
    TabFlowLayout mTabFlowLayout;
    public static ProjectFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public ProjectPresent getPresent() {
        mPresent = new ProjectPresent(this);
        return mPresent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab_viewpager;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        getBarTitleView().setText("项目");

    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.startLoad();

    }

    @Override
    public void getProjectList(List<ProjectListBean> projectListBeans) {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        for (ProjectListBean bean : projectListBeans) {
            String title = bean.getName().replaceAll("&amp;","和");
            titles.add(title);
            fragments.add(ProjectDetailFragment.newInstance(bean));
        }
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),fragments));
        mTabFlowLayout.setViewPager(mViewPager,R.id.item_text,getResources().getColor(R.color.black_ff),
                getResources().getColor(R.color.main_color));
        mTabFlowLayout.setAdapter(new TabAdapter<String>(R.layout.item_tab,titles) {

            @Override
            public void bindView(View view, String data, int position) {
                setText(view,R.id.item_text,data)
                        .setTextColor(view,R.id.item_text,R.color.black_ff);
            }

            @Override
            public void onItemClick(View view, String data, int position) {
                super.onItemClick(view, data, position);
                mViewPager.setCurrentItem(position);
            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments;
        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
