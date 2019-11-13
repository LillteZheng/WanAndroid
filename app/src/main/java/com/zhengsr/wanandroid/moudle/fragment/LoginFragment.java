package com.zhengsr.wanandroid.moudle.fragment;

import android.os.Bundle;

import com.zhengsr.wanandroid.R;

/**
 * @auther by zhengshaorui on 2019/11/13
 * describe:
 */
public class LoginFragment extends DelegateFragment {

    public static LoginFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected int setTitleBar() {
        return R.id.common_bar;
    }
}
