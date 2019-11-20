package com.zhengsr.wanandroid.moudle.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class WechatFragment extends BaseMvpFragment {

    @BindView(R.id.title)
    TextView mTextView;

    public static WechatFragment newInstance() {
        
        Bundle args = new Bundle();
        
        WechatFragment fragment = new WechatFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_navi;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mTextView.setText("公众号");
    }


}
