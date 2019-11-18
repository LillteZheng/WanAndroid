package com.zhengsr.wanandroid.moudle.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhengsr.wanandroid.R;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class NagiFragment extends BaseNetFragment {

    @BindView(R.id.title)
    TextView mTextView;

    public static NagiFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NagiFragment fragment = new NagiFragment();
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
        mTextView.setText("体系");
    }

}
