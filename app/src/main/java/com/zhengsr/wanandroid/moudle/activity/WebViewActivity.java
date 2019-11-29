package com.zhengsr.wanandroid.moudle.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.ArticlePresent;
import com.zhengsr.wanandroid.utils.NetUtils;
import com.zhengsr.zweblib.ZWebHelper;
import com.zhengsr.zweblib.widght.ZWebChromeClient;

public class WebViewActivity extends BaseDelegateActivity<ArticlePresent> implements View.OnClickListener,
        IContractView.IAddOrCancelArticleView<WebBean> {
    private static final String TAG = "WebViewActivity";
    private WebBean mBean;
    private ImageView mRightIv;
    private TextView mTitleTv;
    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public ArticlePresent getPresent() {
        mPresent = new ArticlePresent(this);
        return mPresent;
    }

    private void initToolbar() {

        ImageView imageView = getLeftIconView();
        imageView.setImageResource(R.mipmap.back);
        imageView.setPadding(10,10,10,10);
        imageView.setColorFilter(Color.WHITE);
        imageView.setOnClickListener(this);

        mRightIv = getRightIconView();
        mTitleTv = getBarTitleView();
        mRightIv.setOnClickListener(this);

    }

    @Override
    public void initView() {
        initToolbar();
        Intent intent = getIntent();
        if (intent == null){
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            return;
        }
        mBean = (WebBean) intent.getSerializableExtra("bean");
        if (!mBean.isShowIcon){
            mRightIv.setVisibility(View.GONE);
        }
        mTitleTv.setText(mBean.title);
        if (mBean.isCollect){
            mRightIv.setImageResource(R.drawable.icon_like_article_select);
        }else{
            mRightIv.setImageResource(R.drawable.icon_like_article_not_selected);
        }

        FrameLayout frameLayout = findViewById(R.id.web_contain);
        View errorView = LayoutInflater.from(this).inflate(R.layout.load_error_layout,null);

        //设置webview
        ZWebHelper.with(this)
                .url(mBean.url)
                .errorView(errorView)
                .parentView(frameLayout)
                .webChromeClient(new ChromeSetting())
                .go();

        WebSettings webSettings = ZWebHelper.getWebSettings();

        //缓存,自动缓存了
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabaseEnabled(true);
        if (NetUtils.isNetworkConnected()) {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }else{
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_left_iv:
                Intent intent = new Intent();
                intent.putExtra("bean",mBean);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case R.id.toolbar_right_iv:
                if (mBean.isCollect){
                    mPresent.removeArticle(mBean.position,mBean);
                }else{
                    mPresent.addArticle(mBean.position,mBean);
                }
                break;
                default:break;
        }
    }

    @Override
    public void addArticleSuccess(int position, WebBean data) {
        Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        mBean.isCollect = true;
        mRightIv.setImageResource(R.drawable.icon_like_article_select);
    }

    @Override
    public void removeArticleSuccess(int position, WebBean data) {
        Toast.makeText(this, "已取消收藏", Toast.LENGTH_SHORT).show();
        mBean.isCollect = false;
        mRightIv.setImageResource(R.drawable.icon_like_article_not_selected);
    }


    class ChromeSetting extends ZWebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.putExtra("bean",mBean);
            setResult(Activity.RESULT_OK, intent);
        }
        return ZWebHelper.canGoBack(keyCode)||super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        ZWebHelper.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        ZWebHelper.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        ZWebHelper.onDestroy();
        super.onDestroy();
    }
}
