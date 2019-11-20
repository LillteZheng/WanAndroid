package com.zhengsr.wanandroid.moudle.fragment.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class MineFragment extends BaseMvpFragment implements BaseQuickAdapter.OnItemClickListener {


    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * static
     */
    private static int[] RESID = new int[]{
            R.mipmap.rank,R.mipmap.ic_share_article,R.mipmap.ic_collect,R.mipmap.ic_about,R.mipmap.ic_setting
    };
    private static String[] TEXT = new String[]{
            "积分版","我的分享","我的收藏","关于我","设置"
    };

    /**
     * UI
     */
    @BindView(R.id.mine_recyclerview)
    RecyclerView mRecyclerView;

    /**
     * logic
     */
    private List<MineBean> mData = new ArrayList<>();
    private MineAdapter mAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    public void initView(View view) {
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new MineAdapter(R.layout.item_mine,mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        for (int i = 0; i < TEXT.length; i++) {
            MineBean bean = new MineBean();
            bean.resId = RESID[i];
            bean.text = TEXT[i];
            mData.add(bean);
        }
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.mine_user_ly)
    public void onClick(View view){
        useParentStart(LoginFragment.newInstance());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (position == 0){
            useParentStart(RankFragment.newInstance());
        }else {
            useParentStart(LoginFragment.newInstance());
        }
    }

    class MineBean{
        public int resId;
        public String text;
    }

    class MineAdapter extends BaseQuickAdapter<MineBean, BaseViewHolder> {

        public MineAdapter(int layoutResId, @Nullable List<MineBean> data) {
            super(layoutResId, data);
        }
        @Override
        protected void convert(@NonNull BaseViewHolder helper, MineBean item) {
            ImageView imageView = helper.getView(R.id.item_mine_iv);
            imageView.setColorFilter(getResources().getColor(R.color.main_color));
            helper.setImageResource(R.id.item_mine_iv,item.resId)
                    .setText(R.id.item_mine_tv,item.text);
        }
    }

}
