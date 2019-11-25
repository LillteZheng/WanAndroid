package com.zhengsr.wanandroid.moudle.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.RankBean;
import com.zhengsr.wanandroid.bean.RankListBean;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.UserPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class RankFragment extends BaseNetFragment<UserPresent> implements IContractView.IRankInfoView {

    private RankAdapter mRankAdapter;

    public static RankFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RankFragment fragment = new RankFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private UserPresent mPresent;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private List<RankBean> mRankBeans = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_rank;
    }

    @Override
    public UserPresent getPresent() {
        mPresent = UserPresent.create(this);
        return mPresent;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        initToolbar();
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(manager);
        mRankAdapter = new RankAdapter(R.layout.item_ranklayout,mRankBeans);
        mRecyclerView.setAdapter(mRankAdapter);
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        mPresent.getRankInfo(true);
    }

    private void initToolbar() {
        getBarTitleView().setText("积分版");
        ImageView imageView = getLeftIconView();
        imageView.setImageResource(R.mipmap.back);
        imageView.setTag(TAG_BACK);
        imageView.setPadding(10,10,10,10);
        imageView.setColorFilter(Color.WHITE);
        getRightIconView().setVisibility(View.GONE);

    }

    @Override
    public void rankInfo(RankListBean listBean) {
        mRankBeans.clear();
        mRankBeans.addAll(listBean.getDatas());

        mRankAdapter.notifyDataSetChanged();
    }

    class RankAdapter extends BaseQuickAdapter<RankBean,BaseViewHolder>{

        public RankAdapter(int layoutResId, @Nullable List<RankBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, RankBean item) {
            ImageView imageView = helper.getView(R.id.item_rank_iv);
            imageView.setVisibility(View.VISIBLE);
            helper.setText(R.id.item_rank_tv,item.getRank()+"")
                    .setText(R.id.item_author,item.getUsername())
                    .setText(R.id.item_coin,item.getCoinCount()+"");
            int index = helper.getAdapterPosition()+1;
            if (index == 1){
                imageView.setImageResource(R.mipmap.ic_rank_1);
            }else if (index == 2){
                imageView.setImageResource(R.mipmap.ic_rank_2);
            }else if (index == 3){
                imageView.setImageResource(R.mipmap.ic_rank_3);
            }else{
                imageView.setVisibility(View.GONE);

            }
        }
    }

    @Override
    public void reFreshMore() {
        super.reFreshMore();
        mPresent.refreshRank();
    }


}
