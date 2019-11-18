package com.zhengsr.wanandroid.moudle.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.mvp.base.BasePresent;
import com.zhengsr.wanandroid.mvp.base.IBaseView;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * created by @author zhengshaorui on 2019/10/8
 * Describe: ISupportFragment 工具类
 */
public abstract class DelegateFragment<T extends BasePresent> extends SupportFragment implements IBaseView {
    private static final String TAG = "DelegateFragment";
    protected Unbinder mUnbinder;
    protected T mPresent;

    /**
     * view
     */
    protected FrameLayout mParentView;
    protected View mLoadingView;
    protected View mErrorView;
    protected View mNormalView;
    /**
     * logic
     */
    protected boolean isFirstLoadAnim = true;

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //默认进入的动画为淡入
        if (firstLoadAnim() && enter && isFirstLoadAnim){
            return AnimationUtils.loadAnimation(_mActivity,R.anim.fade_in);
        }
        return getSupportDelegate().onCreateAnimation(transit,enter,nextAnim);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mParentView = (FrameLayout) inflater.inflate(R.layout.frame_base,container,false);
        //防止触摸穿透问题
        mParentView.setClickable(true);
        mParentView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        if (getLayoutId() != -1) {
            mNormalView = inflater.inflate(getLayoutId(), container, false);
        }
        mUnbinder = ButterKnife.bind(this, mNormalView);
        if (init()) {
            mParentView.addView(mNormalView);
        }

        configOtherView();
        return mParentView;
    }

    /**
     * 加载其他布局
     */
    private void configOtherView() {
        mLoadingView = LayoutInflater.from(_mActivity).inflate(R.layout.loadingview_layout,mParentView,false);
        mErrorView = LayoutInflater.from(_mActivity).inflate(R.layout.load_error_layout,mParentView,false);
        /**
         * 失败时，提供加载按钮
         */
        mErrorView.findViewById(R.id.error_reload_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (setTitleBar() != -1) {
            View titleBar = view.findViewById(setTitleBar());
            ImmersionBar.setTitleBar(_mActivity, titleBar);
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        //这样能避免fragment 动画问题
        onLayInitView();
    }

    public void onLayInitView(){
        isFirstLoadAnim = false;
        if (init()) {
            //关联mvp
            mPresent = getPresent();
            if (mPresent != null) {
                mPresent.attachView(this);
            }
            initView(mNormalView);
            initData();
        }
    }

    protected int setTitleBar() {
        return R.id.toolbar;
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        //请在onSupportVisible实现沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //旋转屏幕为什么要重新设置布局与状态栏重叠呢？因为旋转屏幕有可能使状态栏高度不一样，如果你是使用的静态方法修复的，所以要重新调用修复

    }

    public void initImmersionBar() {
        ImmersionBar.with(this)
                .keyboardEnable(true)
                .statusBarDarkFont(false)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.main_color)
                .init();
    }

    private boolean isImmersionBarEnabled() {
        return true;
    }



    @Override
    public void onDestroy() {
        if (mPresent != null) {
            mPresent.detachView();
            mPresent = null;
        }

        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        super.onDestroy();
    }

    protected boolean init(){
        return true;
    }



    @Override
    public void showLoading() {
    }


    @Override
    public void loadSuccess() {
    }

    @Override
    public void showError() {
    }

    @Override
    public void showErrorMsg(String msg) {
    }

    /**
     * 拿到layoutId
     * @return
     */
    public abstract int getLayoutId();

    public void initView(View view){

    }
    public void initData(){}
    /**
     * 需要子类去实现
     */
    public  T getPresent(){
        return null;
    }

    public void reload(){}

    /**
     * 是否使用默认淡入动画
     * @return
     */
    public boolean firstLoadAnim(){
        return false;
    }

    protected void useParentStart(SupportFragment fragment){
        ((MainFragment)(getParentFragment())).start(fragment);
    }
}
