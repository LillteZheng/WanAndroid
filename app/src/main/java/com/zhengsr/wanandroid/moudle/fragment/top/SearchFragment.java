package com.zhengsr.wanandroid.moudle.fragment.top;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.HotKeyBean;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.SearchPresent;
import com.zhengsr.wanandroid.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class SearchFragment extends BaseMvpFragment<SearchPresent> implements IContractView.ISearchView {
    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.recycler_hotsearch)
    RecyclerView mHotRecyclerview;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public SearchPresent getPresent() {
        mPresent = new SearchPresent(this);
        return mPresent;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        getLeftIconView().setTag(TAG_BACK);
        GridLayoutManager manager = new GridLayoutManager(_mActivity,4);
        mHotRecyclerview.setLayoutManager(manager);
        mHotRecyclerview.setAdapter(mHotAdapter);

    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.getHotkey();
    }

    BaseQuickAdapter mHotAdapter = new BaseQuickAdapter<HotKeyBean,BaseViewHolder>(R.layout.item_textview) {

      @Override
      protected void convert(@NonNull BaseViewHolder helper, HotKeyBean item) {
          TextView textView = helper.getView(R.id.item_text);
          textView.setTextColor(Color.WHITE);
          textView.setBackground(CommonUtils.getColorDrawable(4));
          textView.setText(item.getName());
      }
  };

    @Override
    public void getHotkeyData(List<HotKeyBean> datas) {
        mHotAdapter.setNewData(datas);
    }
}
