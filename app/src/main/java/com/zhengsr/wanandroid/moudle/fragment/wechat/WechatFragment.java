package com.zhengsr.wanandroid.moudle.fragment.wechat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhengsr.viewpagerlib.indicator.TabIndicator;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.WechatBean;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.moudle.fragment.navi.NaviRecyFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.WechatPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class WechatFragment extends BaseNetFragment<WechatPresent> implements IContractView.IWechatView {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabindicator)
    TabIndicator mTabIndicator;

    public static WechatFragment newInstance() {
        
        Bundle args = new Bundle();
        
        WechatFragment fragment = new WechatFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab_viewpager;
    }

    @Override
    public WechatPresent getPresent() {
        mPresent = new WechatPresent(this);
        return mPresent;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        getBarTitleView().setText("体系");
        mTabIndicator.setViewPagerSwitchSpeed(mViewPager,600);
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.getWevhatList();
    }

    @Override
    public void getWechatList(List<WechatBean> beans) {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();

        for (WechatBean bean : beans) {
            String title = bean.getName().replaceAll("&amp;","和");
            titles.add(title);
            fragments.add(WechatDetailFragment.newInstance(bean));
        }
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),fragments));
        mTabIndicator.setTabData(mViewPager, titles, new TabIndicator.TabClickListener() {
            @Override
            public void onClick(int position) {
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
