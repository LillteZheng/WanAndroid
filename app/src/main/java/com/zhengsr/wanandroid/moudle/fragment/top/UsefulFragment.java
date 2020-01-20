package com.zhengsr.wanandroid.moudle.fragment.top;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zhengsr.tablib.view.adapter.LabelFlowAdapter;
import com.zhengsr.tablib.view.flow.LabelFlowLayout;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.HotKeyBean;
import com.zhengsr.wanandroid.bean.UsefulWebBean;
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.moudle.activity.WebViewActivity;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.TopPresent;
import com.zhengsr.wanandroid.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @auther by zhengshaorui on 2020/1/20
 * describe:
 */
public class UsefulFragment extends BaseNetFragment<TopPresent> implements IContractView.IUsefulWebView {

    public static UsefulFragment newInstance() {
        
        Bundle args = new Bundle();
        
        UsefulFragment fragment = new UsefulFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.labelflow)
    LabelFlowLayout mFlowLayout;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_web;
    }

    @Override
    public TopPresent getPresent() {
        mPresent = new TopPresent(this);
        return mPresent;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        ImageView imageView = getLeftIconView();
        imageView.setImageResource(R.mipmap.back);
        imageView.setTag(TAG_BACK);
        imageView.setPadding(10,10,10,10);
        imageView.setColorFilter(Color.WHITE);
        getRightIconView().setVisibility(View.GONE);

        getBarTitleView().setText("常用网站");
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.getUsefulWeb();
    }

    @Override
    public void getUsefulWeb(List<UsefulWebBean> beans) {
        mFlowLayout.setAdapter(new LabelAdaper(R.layout.item_textview,beans));

    }

    class LabelAdaper extends LabelFlowAdapter<UsefulWebBean> {

        public LabelAdaper(int layoutId, List<UsefulWebBean> data) {
            super(layoutId, data);
        }

        @Override
        public void bindView(View view, UsefulWebBean data, int position) {
            setText(view,R.id.item_text,data.getName())
                    .setTextColor(view,R.id.item_text,Color.WHITE);
            view.setBackground(CommonUtils.getColorDrawable(10));
        }

        @Override
        public void onItemClick(View view, UsefulWebBean data, int position) {
            super.onItemClick(view, data, position);

            WebBean bean = new WebBean();
            bean.isShowIcon = false;
            bean.url = data.getLink();
            bean.title = data.getName();
            Intent intent = new Intent(_mActivity, WebViewActivity.class);
            intent.putExtra("bean",bean);
            UsefulFragment.this.startActivity(intent);
        }
    }
}
