package com.zhengsr.wanandroid.moudle.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.zhengsr.viewpagerlib.indicator.TabIndicator;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.present.ProjectPresent;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class ProjectFragment extends BaseNetFragment<ProjectPresent> {


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
    }

}
