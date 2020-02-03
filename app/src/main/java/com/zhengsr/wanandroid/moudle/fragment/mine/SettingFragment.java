package com.zhengsr.wanandroid.moudle.fragment.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.wanandroid.Constant;
import com.zhengsr.wanandroid.MainApplication;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;
import com.zhengsr.wanandroid.mvp.present.SettingPresent;
import com.zhengsr.wanandroid.utils.Lgg;
import com.zhengsr.wanandroid.utils.SpfUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther by zhengshaorui on 2020/2/3
 * describe:
 */
public class SettingFragment extends BaseMvpFragment<SettingPresent> implements BaseQuickAdapter.OnItemChildClickListener {

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final int[] RESID = new int[]{
        R.mipmap.ic_night,
    };

    private static final String[] MSG = new String[]{
            "夜间模式",
    };

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    public SettingPresent getPresent() {
        mPresent = new SettingPresent(this);
        return mPresent;
    }

    @Override
    public void initView(View view) {

        getBarTitleView().setText("设置");
        showWebIcon();
        super.initView(view);
        LinearLayoutManager manager =  new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(manager);


        List<SettingBean> datas = new ArrayList<>();
        for (int i = 0; i < RESID.length; i++) {
            SettingBean bean = new SettingBean();
            bean.resId = RESID[i];
            bean.msg = MSG[i];

            switch (i){
                case 0:
                    bean.isCheck = SpfUtils.get(Constant.KEY_IS_NIGHT,false);
                    break;
            }

            datas.add(bean);
        }
        SettingAdapter adapter;
        mRecyclerView.setAdapter(adapter = new SettingAdapter(R.layout.item_settings,datas));
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                boolean isNight = mPresent.isNightMode();
                mPresent.setNightMode(!isNight);
                Lgg.d("isnight; "+mPresent.isNightMode());
                if (mPresent.isNightMode()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                MainApplication.HANDLER.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        _mActivity.recreate();
                    }
                },500);
                break;
            default:
                break;
        }
    }


    class SettingBean{
        int resId;
        String msg;
        boolean isCheck;
    }

    class SettingAdapter extends BaseQuickAdapter<SettingBean, BaseViewHolder>{

        public SettingAdapter(int layoutResId, @Nullable List<SettingBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, SettingBean item) {
            helper.setText(R.id.item_setting_tv,item.msg)
                    .setImageResource(R.id.item_setting_iv,item.resId)
                    .setChecked(R.id.item_setting_checkbox,item.isCheck)
                    .addOnClickListener(R.id.item_setting_checkbox);

        }

    }
}
