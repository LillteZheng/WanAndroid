package com.zhengsr.wanandroid.moudle.fragment.navi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.tablib.view.adapter.LabelFlowAdapter;
import com.zhengsr.tablib.view.adapter.TabFlowAdapter;
import com.zhengsr.tablib.view.flow.LabelFlowLayout;
import com.zhengsr.tablib.view.flow.TabFlowLayout;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.bean.NaviBean;
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.moudle.activity.WebViewActivity;
import com.zhengsr.wanandroid.moudle.fragment.HomeFragment;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.NaviPresent;
import com.zhengsr.wanandroid.utils.CommonUtils;
import com.zhengsr.wanandroid.utils.Lgg;

import java.util.List;

import butterknife.BindView;

/**
 * @auther by zhengshaorui on 2020/1/23
 * describe: 导航数据
 */
public class NaviFragment extends BaseNetFragment<NaviPresent> implements IContractView.INaviView {
    private static final String TAG = "NaviFragment";
    private LinearLayoutManager mManager;

    public static NaviFragment newInstance() {
        Bundle args = new Bundle();
        NaviFragment fragment = new NaviFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.tabflow)
    TabFlowLayout mTabFlowLayout;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_navi;
    }

    @Override
    public NaviPresent getPresent() {
        mPresent = new NaviPresent(this);
        return mPresent;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(mManager);


    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.getNaviData();


    }



    private boolean isNeedScroll;
    private int mCurPosition;
    @Override
    public void getNaviData(List<NaviBean> datas) {


        mTabFlowLayout.setAdapter(new TabFlowAdapter<NaviBean>(R.layout.item_textview,datas) {
            @Override
            public void bindView(View view, NaviBean data, int position) {
                setText(view,R.id.item_text,data.getName());
                setTextColor(view, R.id.item_text, getResources().getColor(R.color.colorText));
            }

            @Override
            public void onItemSelectState(View view, boolean isSelected) {
                super.onItemSelectState(view, isSelected);
                if (isSelected){
                    setTextColor(view, R.id.item_text, getResources().getColor(R.color.tab_color));
                }else {
                    setTextColor(view, R.id.item_text, getResources().getColor(R.color.colorText));
                }
            }

            @Override
            public void onItemClick(View view, NaviBean data, int position) {
                super.onItemClick(view, data, position);
                int firstPosition = mManager.findFirstVisibleItemPosition();
                int lastPosition = mManager.findLastVisibleItemPosition();
                mCurPosition = position;

                /**
                 * 目标在 可见视图的上面
                 */
                if (position <= firstPosition) {
                    mRecyclerView.smoothScrollToPosition(position);
                    //防止不刷新视图
                    mRecyclerView.requestLayout();
                } else if (position <= lastPosition) {
                    //往下点，且 position 在中间，但是 lastposition 的数据也能看到.所以把它置顶
                    /**
                     * 目标在 first 和 last 的中间
                     */
                    int top = mRecyclerView.getChildAt(position - firstPosition).getTop();
                    if (top > 0) {
                        mRecyclerView.smoothScrollBy(0, top);
                    }

                } else {
                    /**
                     * 目标在可视视图的下面
                     */
                    //该函数让它滚动到可视界面
                    mRecyclerView.scrollToPosition(position);
                    //此时recycler 的item还未滚动到顶端，需要重新再让它滚动改一下
                    isNeedScroll = true;
                }
            }
        });


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){

                    int firstPosition = mManager.findFirstVisibleItemPosition();
                    if (!mTabFlowLayout.isItemClick()) {
                        mTabFlowLayout.setItemSelected(firstPosition);
                        mTabFlowLayout.setItemClickStatus(true);
                    }else{
                        /**
                         * 如果上次为点击事件，则先还原，下次滑动时，监听即可
                         */
                        mTabFlowLayout.setItemClickStatus(false);
                    }

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isNeedScroll){
                    isNeedScroll = false;
                    int index = mCurPosition - mManager.findFirstVisibleItemPosition();
                    if (index >= 0 && mRecyclerView.getChildCount() > index){
                        int top = mRecyclerView.getChildAt(index).getTop();
                        mRecyclerView.smoothScrollBy(0,top);
                    }
                }
            }
        });

        mRecyclerView.setAdapter(new NaviAdapter(R.layout.item_navi_detail,datas));
    }


    class NaviAdapter extends BaseQuickAdapter<NaviBean,BaseViewHolder>{

        public NaviAdapter(int layoutResId, @Nullable List<NaviBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, NaviBean item) {
            helper.setText(R.id.item_navi_title,item.getName());
            LabelFlowLayout flowLayout = helper.getView(R.id.labelflow);
            flowLayout.setAdapter(new LabelAdapter(R.layout.item_textview,item.getArticles()));
        }
    }

    class LabelAdapter extends LabelFlowAdapter<ArticleData>{

        public LabelAdapter(int layoutId, List<ArticleData> data) {
            super(layoutId, data);
        }

        @Override
        public void bindView(View view, ArticleData data, int position) {
            setText(view,R.id.item_text,data.getTitle())
                    .setTextColor(view,R.id.item_text, Color.WHITE);
            view.setBackground(CommonUtils.getColorDrawable(10));
        }

        @Override
        public void onItemClick(View view, ArticleData data, int position) {
            super.onItemClick(view, data, position);
            ArticleData bean = data;
            WebBean webBean = new WebBean();
            webBean.id = bean.getId();
            webBean.title = bean.getTitle();
            webBean.isShowIcon = false;
            webBean.url = bean.getLink();
            Intent intent = new Intent(_mActivity, WebViewActivity.class);
            intent.putExtra("bean",webBean);
            NaviFragment.this.startActivity(intent);
        }
    }
}
