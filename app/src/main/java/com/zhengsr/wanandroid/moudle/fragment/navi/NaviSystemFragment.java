package com.zhengsr.wanandroid.moudle.fragment.navi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhengsr.tablib.view.adapter.TabFlowAdapter;
import com.zhengsr.tablib.view.flow.TabFlowLayout;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.present.NaviPresent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @auther by zhengshaorui on 2020/1/23
 * describe:
 */
public class NaviSystemFragment extends BaseNetFragment<NaviPresent> {
    List<String> mTitle = Arrays.asList("导航","体系");
    @BindView(R.id.toolbar_title_tabflow)
    TabFlowLayout mFlowLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    public static NaviSystemFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NaviSystemFragment fragment = new NaviSystemFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.fragment_navisystem;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(NaviFragment.newInstance());
        fragments.add(SystematicFragment.newInstance());
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragments));

        mFlowLayout.setViewPager(mViewPager,R.id.item_text,
                getResources().getColor(R.color.unselect),
                getResources().getColor(R.color.white));

        mFlowLayout.setAdapter(new TabFlowAdapter<String>(R.layout.item_navi_text,mTitle) {
            @Override
            public void bindView(View view, String data, int position) {
                setText(view,R.id.item_text,data);
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
