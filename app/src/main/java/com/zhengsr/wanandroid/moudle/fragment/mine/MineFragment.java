package com.zhengsr.wanandroid.moudle.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.LoginBean;
import com.zhengsr.wanandroid.bean.RankBean;
import com.zhengsr.wanandroid.moudle.activity.LoginActivity;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.UserPresent;
import com.zhengsr.wanandroid.window.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class MineFragment extends BaseMvpFragment<UserPresent> implements BaseQuickAdapter.OnItemClickListener,
        IContractView.IUserInfoView {


    private static final int LOGIN = 1;
    private LoadingDialog mDialog;

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
            "积分排行榜","我的分享","我的收藏","关于软件","设置"
    };

    /**
     * UI
     */
    @BindView(R.id.mine_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.mine_username)
    TextView mUserNameTv;
    @BindView(R.id.mine_level)
    TextView mLevelTv;
    @BindView(R.id.mine_rank)
    TextView mRankTv;
    @BindView(R.id.mine_coin)
    TextView mCoinTv;
    @BindView(R.id.mine_logout)
    AppCompatButton mLogoutBtn;



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
    public UserPresent getPresent() {
        mPresent = new UserPresent(this);
        return mPresent;

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
        if (isLogin()){
            mUserNameTv.setText(getUserName());
            mPresent.getUserInfo();
            mLogoutBtn.setVisibility(View.VISIBLE);
        }else{
            mLogoutBtn.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.mine_user_setting,R.id.mine_logout})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_user_setting:
                if (isLogin()){

                }else {
                    MineFragment.this.startActivityForResult(new Intent(_mActivity, LoginActivity.class),LOGIN);
                }
                break;
            case R.id.mine_logout:
                mPresent.logout();
                break;
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (position == 0){
            useParentStart(RankFragment.newInstance());
        }else {
            if (isLogin()){
                switch (position){
                    case 1:
                        useParentStart(ShareFragment.newInstance());
                        break;
                    case 2:
                        useParentStart(MyCollectArticleFragment.newInstance());
                        break;
                        default:break;
                    case 3:

                        break;
                }
            }else {
                MineFragment.this.startActivityForResult(new Intent(_mActivity, LoginActivity.class), LOGIN);
            }
        }
    }

    @Override
    public void getInfoUser(RankBean bean) {
        if (bean != null) {
            mLevelTv.setText(String.valueOf(bean.getLevel()));
            mRankTv.setText(String.valueOf(bean.getRank()));
            mCoinTv.setText(String.valueOf(bean.getCoinCount()));
        }
    }

    @Override
    public void logoutSuccess() {
        mLogoutBtn.setVisibility(View.GONE);
        String msg = "---";
        mLevelTv.setText(msg);
        mRankTv.setText(msg);
        mCoinTv.setText(msg);
        mUserNameTv.setText("未登录");
        Toast.makeText(_mActivity, "退出成功", Toast.LENGTH_SHORT).show();
    }

    class MineBean{
        public int resId;
        public String text;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN){
            if (data != null) {
                LoginBean bean = (LoginBean) data.getSerializableExtra("bean");
                if (bean != null) {
                    mUserNameTv.setText(bean.getUsername());
                    mPresent.getUserInfo();
                    mLogoutBtn.setVisibility(View.VISIBLE);
                }
            }
        }
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
