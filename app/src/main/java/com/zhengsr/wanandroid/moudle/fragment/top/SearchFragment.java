package com.zhengsr.wanandroid.moudle.fragment.top;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.tablib.view.adapter.LabelFlowAdapter;
import com.zhengsr.tablib.view.flow.LabelFlowLayout;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.HotKeyBean;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.SearchPresent;
import com.zhengsr.wanandroid.utils.CommonUtils;
import com.zhengsr.wanandroid.utils.Lgg;
import com.zhengsr.wanandroid.utils.SpfUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class SearchFragment extends BaseMvpFragment<SearchPresent> implements IContractView.IHotKeyView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    private static final String SPF_NAME = "history";
    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.recycler_histroy)
    RecyclerView mRecyclerview;
    @BindView(R.id.search_ed)
    EditText mSearchEd;
    @BindView(R.id.search_clear_his_tv)
    TextView mClearHistory;
    @BindView(R.id.labelflow)
    LabelFlowLayout mFlowLayout;


    private List<HotKeyBean> mDatas = new ArrayList<>();
    private LabelAdaper mLabelAdaper;


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
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecyclerview.setLayoutManager(manager);
        mRecyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        Map<String, String> historys =  SpfUtils.getAll(SPF_NAME);
        mClearHistory.setVisibility(View.GONE);
        if (historys != null && !historys.isEmpty()) {
            List<String> data = new ArrayList<>();
            for (Map.Entry<String, String> stringEntry : historys.entrySet()) {
                data.add(stringEntry.getKey());
            }
            mAdapter.setNewData(data);
            mClearHistory.setVisibility(View.VISIBLE);
        }

        mLabelAdaper = new LabelAdaper(R.layout.item_textview,mDatas);
        mFlowLayout.setAdapter(mLabelAdaper);

    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.getHotkey();
    }

    @OnClick({R.id.search_tv,R.id.search_clear_his_tv})
    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.search_tv) {
            String text = mSearchEd.getText().toString();
            start(SearchDetailFragment.newInstance(text));
            if (!SpfUtils.isExist(SPF_NAME, text)) {
                mAdapter.addData(text);
                SpfUtils.put(SPF_NAME, text, text);
            }
            mClearHistory.setVisibility(View.VISIBLE);
        }else if (id == R.id.search_clear_his_tv){
            SpfUtils.clear(SPF_NAME);
            mAdapter.setNewData(null);
            mClearHistory.setVisibility(View.GONE);
        }

    }

    BaseQuickAdapter mAdapter = new BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_search_history) {

      @Override
      protected void convert(@NonNull BaseViewHolder helper, String text) {
          helper.setText(R.id.item_text,text)
                  .addOnClickListener(R.id.item_search_his_iv);
      }
  };

    @Override
    public void getHotkeyData(List<HotKeyBean> datas) {
     //   mHotAdapter.setNewData(datas);
        mDatas.clear();
        mDatas.addAll(datas);
        mLabelAdaper.notifyDataChanged();
    }

    class LabelAdaper extends LabelFlowAdapter<HotKeyBean>{

        public LabelAdaper(int layoutId, List<HotKeyBean> data) {
            super(layoutId, data);
        }

        @Override
        public void bindView(View view, HotKeyBean data, int position) {
            setText(view,R.id.item_text,data.getName())
                    .setTextColor(view,R.id.item_text,Color.WHITE);
            view.setBackground(CommonUtils.getColorDrawable(10));
        }

        @Override
        public void onItemClick(View view, HotKeyBean data, int position) {
            super.onItemClick(view, data, position);
            start(SearchDetailFragment.newInstance(data.getName()));
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String text = (String) mAdapter.getData().get(position);
        start(SearchDetailFragment.newInstance(text));
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        String text = (String) mAdapter.getData().get(position);
        SpfUtils.remove(SPF_NAME,text);
        mAdapter.remove(position);
        if (mAdapter.getData().isEmpty()){
            mClearHistory.setVisibility(View.GONE);
        }
    }
}
