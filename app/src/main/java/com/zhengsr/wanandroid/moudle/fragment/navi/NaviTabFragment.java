package com.zhengsr.wanandroid.moudle.fragment.navi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.zhengsr.tablib.view.adapter.TabAdapter;
import com.zhengsr.tablib.view.flow.TabFlowLayout;
import com.zhengsr.viewpagerlib.indicator.TabIndicator;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.NaviBean;
import com.zhengsr.wanandroid.bean.NaviChildrenBean;
import com.zhengsr.wanandroid.bean.ProjectListBean;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.moudle.fragment.project.ProjectDetailFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.NaviPresent;
import com.zhengsr.wanandroid.mvp.present.ProjectPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class NaviTabFragment extends BaseMvpFragment  {


    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabflow)
    TabFlowLayout mTabFlowLayout;
    private NaviBean mBean;

    public static NaviTabFragment newInstance(NaviBean bean) {
        
        Bundle args = new Bundle();
        args.putSerializable("bean",bean);
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
        imageView.setPadding(10,10,10,10);
        imageView.setColorFilter(Color.WHITE);
        getRightIconView().setVisibility(View.GONE);

    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        Bundle arguments = getArguments();
        if (arguments != null) {
        }
        mBean = (NaviBean) arguments.getSerializable("bean");
        getBarTitleView().setText(mBean.getName());
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        for (NaviChildrenBean child : mBean.getChildren()) {
            String title = child.getName().replaceAll("&amp;","和");
            titles.add(title);
            fragments.add(NaviRecyFragment.newInstance(child));
        }
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),fragments));

       mTabFlowLayout.setViewPager(mViewPager,R.id.item_text,getResources().getColor(R.color.black_ff),
               getResources().getColor(R.color.main_color));
       mTabFlowLayout.setAdapter(new TabAdapter<String>(R.layout.item_tab,titles) {

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
