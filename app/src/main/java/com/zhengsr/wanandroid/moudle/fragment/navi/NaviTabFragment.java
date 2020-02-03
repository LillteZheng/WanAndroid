package com.zhengsr.wanandroid.moudle.fragment.navi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.zhengsr.tablib.view.adapter.TabFlowAdapter;
import com.zhengsr.tablib.view.flow.TabFlowLayout;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.SystematicBean;
import com.zhengsr.wanandroid.bean.NaviChildrenBean;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class NaviTabFragment extends BaseMvpFragment {


    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabflow)
    TabFlowLayout mTabFlowLayout;
    private SystematicBean mBean;

    public static NaviTabFragment newInstance(SystematicBean bean) {

        Bundle args = new Bundle();
        args.putSerializable("bean", bean);
        NaviTabFragment fragment = new NaviTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab_viewpager;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        getBarTitleView().setText("项目");
        initToolbar();
    }

    private void initToolbar() {
        ImageView imageView = getLeftIconView();
        imageView.setImageResource(R.mipmap.back);
        imageView.setTag(TAG_BACK);

        imageView.setPadding(10, 10, 10, 10);
        getRightIconView().setVisibility(View.GONE);

    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        Bundle arguments = getArguments();
        if (arguments != null) {
        }
        mBean = (SystematicBean) arguments.getSerializable("bean");
        getBarTitleView().setText(mBean.getName());
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        for (NaviChildrenBean child : mBean.getChildren()) {
            String title = child.getName().replaceAll("&amp;", "和");
            titles.add(title);
            fragments.add(NaviRecyFragment.newInstance(child));
        }
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragments));

        mViewPager.setOffscreenPageLimit(3);
        mTabFlowLayout.setViewPager(mViewPager, R.id.item_text);

        mTabFlowLayout.setAdapter(new TabFlowAdapter<String>(R.layout.item_tab, titles) {

            @Override
            public void bindView(View view, String data, int position) {
                setText(view, R.id.item_text, data);
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
