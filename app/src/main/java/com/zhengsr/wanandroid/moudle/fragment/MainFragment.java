package com.zhengsr.wanandroid.moudle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhengsr.ariesuilib.wieght.bottom.CusBottomLayout;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseDelegateFragment;
import com.zhengsr.wanandroid.moudle.fragment.mine.MineFragment;
import com.zhengsr.wanandroid.moudle.fragment.project.ProjectFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class MainFragment extends BaseDelegateFragment implements CusBottomLayout.IBottomClickListener {

    public static MainFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private SupportFragment[] mFragments = new SupportFragment[5];
    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view) {
        CusBottomLayout layout = view.findViewById(R.id.bottom_ly);
        layout.setBottomClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(HomeFragment.class);
        if (firstFragment == null){
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = NagiFragment.newInstance();
            mFragments[2] = WechatFragment.newInstance();
            mFragments[3] = ProjectFragment.newInstance();
            mFragments[4] = MineFragment.newInstance();

            getSupportDelegate().loadMultipleRootFragment(R.id.bottom_content, 0,
                    mFragments);
        }else{
            //需要重新赋值，此时这个 mfragmengt 是null 的
            mFragments[0] = firstFragment;
            mFragments[1] = findChildFragment(NagiFragment.class);
            mFragments[2] = findChildFragment(WechatFragment.class);
            mFragments[3] = findChildFragment(ProjectFragment.class);
            mFragments[4] = findChildFragment(MineFragment.class);

        }

    }

    @Override
    public void onBottomClick(View view, int curPosition, int prePosition) {
        if (curPosition != prePosition) {
            showHideFragment(mFragments[curPosition], mFragments[prePosition]);
        }
    }
}
