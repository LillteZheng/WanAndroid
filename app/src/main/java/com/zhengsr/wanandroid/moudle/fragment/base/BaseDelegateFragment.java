package com.zhengsr.wanandroid.moudle.fragment.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhengsr.wanandroid.Constant;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.moudle.fragment.MainFragment;
import com.zhengsr.wanandroid.utils.SpfUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe: 最后基础的类
 */
public abstract class BaseDelegateFragment extends SupportFragment {
    private static final String TAG = "BaseDelegateFragment";
    protected Unbinder mUnbinder;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, mView);
        //沉浸式，若不需要，可忽略
        if (setTitleBar() != -1) {
            View titleBar = mView.findViewById(setTitleBar());
            ImmersionBar.setTitleBar(_mActivity, titleBar);
        }
        initView(mView);
        return mView;
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        /**
         * 在懒加载中去实现
         */
        initDataAndEvent();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        //请在onSupportVisible实现沉浸式
        initImmersionBar();
    }

    public void initImmersionBar() {
        ImmersionBar.with(this)
                .keyboardEnable(true)
                .statusBarDarkFont(false)
                .init();
    }


    /**
     * 是为了沉浸式
     *
     * @return
     */
    protected int setTitleBar() {
        return R.id.toolbar;
    }

    /**
     * 初始化view可以放在这里，但不要做过多的数据操作或者网络操作
     */
    public void initView(View view) {
        View normalview = view.findViewById(R.id.normal_view);
        if (normalview instanceof SmartRefreshLayout) {
            SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout)normalview;
            smartRefreshLayout.setEnableLoadMore(true);
            smartRefreshLayout.setEnableRefresh(true);
            smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    reFreshMore();
                    smartRefreshLayout.finishRefresh(1000);

                }
            });
            smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    loadMore();
                    smartRefreshLayout.finishLoadMore(1000);
                }
            });
        }
    }

    /**
     * 初始化数据和可以做一些耗时操作
     */
    public void initDataAndEvent() {

    }

    /**
     * 获取layoutid
     *
     * @return
     */
    public abstract int getLayoutId();

    public void loadMore() {
    }

    public void reFreshMore() {
    }

    /**
     * 用父fragment 去启动，可以去掉底部导航栏
     *
     * @param fragment
     */
    protected void useParentStart(SupportFragment fragment) {
        ((MainFragment) (getParentFragment())).start(fragment);
    }

    /**
     * 是否已经登录
     *
     * @return
     */
    public boolean isLogin() {
        return SpfUtils.get(Constant.KEY_IS_LOGIN, false);
    }

    public void setLogin(boolean isLogin) {
        SpfUtils.put(Constant.KEY_IS_LOGIN, isLogin);
    }

    public String getUserName() {
        return SpfUtils.get(Constant.KEY_USERNAME, "");
    }

    public void setUserName(String userName) {
        SpfUtils.put(Constant.KEY_USERNAME, userName);
    }

    public String getPassword() {
        return SpfUtils.get(Constant.KEY_PASSWORD, "");
    }

    public void setPassword(String password) {
        SpfUtils.put(Constant.KEY_PASSWORD, password);
    }

    /**
     * 改变toolbar
     */
    public TextView getBarTitleView() {
        if (mView != null) {
            TextView textView = mView.findViewById(R.id.toolbar_title_tv);
            return textView;
        }
        return null;
    }

    /**
     * 获取toolbar 左边的icon
     *
     * @return
     */
    public ImageView getLeftIconView() {
        if (mView != null) {
            ImageView imageView = mView.findViewById(R.id.toolbar_left_iv);
            return imageView;
        }
        return null;
    }

    /**
     * 获取toolbar 右边的icon
     *
     * @return
     */
    public ImageView getRightIconView() {
        if (mView != null) {
            ImageView imageView = mView.findViewById(R.id.toolbar_right_iv);
            return imageView;
        }
        return null;
    }
}
