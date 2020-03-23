package com.zhengsr.wanandroid.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhengsr.viewpagerlib.anim.MzTransformer;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.CircleIndicator;
import com.zhengsr.viewpagerlib.indicator.TextIndicator;
import com.zhengsr.viewpagerlib.view.BannerViewPager;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.BannerBean;
import com.zhengsr.wanandroid.bean.WebBean;
import com.zhengsr.wanandroid.moudle.activity.WebViewActivity;
import com.zhengsr.wanandroid.moudle.fragment.HomeFragment;
import com.zhengsr.wanandroid.utils.Lgg;

import java.util.List;

/**
 * Created by zhengshaorui
 * time: 2018/9/2
 */

public class BannerView extends FrameLayout {


    private View mView;
    private BannerViewPager mBannerViewPager;

    public BannerView(@NonNull Context context) {
        this(context,null);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        removeAllViews();
        mView = LayoutInflater.from(getContext()).inflate(R.layout.banner_layout,this,false);
        addView(mView);

        mBannerViewPager = mView.findViewById(R.id.banner);
        CircleIndicator indicator = mView.findViewById(R.id.banner_indicator);

        mBannerViewPager.addIndicator(indicator);
    }


    public void setData(List<BannerBean> beans){
        if (beans != null && beans.size() >0){
            mBannerViewPager.setPageListener(R.layout.banner_item_layout, beans, new PageHelperListener<BannerBean>() {
                @Override
                public void bindView(View view, BannerBean data, int position) {
                    String title = data.getTitle().replaceAll("&ldquo;","\"").replaceAll("&rdquo;","\"");
                    setText(view,R.id.banner_text,title);
                    ImageView imageView = view.findViewById(R.id.banner_icon);
                    Glide.with(getContext())
                            .load(data.getImagePath())
                            .into(imageView);
                }

                @Override
                public void onItemClick(View view, BannerBean bean, int position) {
                    super.onItemClick(view, bean, position);
                    WebBean webBean = new WebBean();
                    webBean.id = bean.getId();
                    webBean.title = bean.getTitle();
                    webBean.url = bean.getUrl();
                    webBean.isShowIcon = false;
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("bean",webBean);
                    getContext().startActivity(intent);
                }
            });

        }
    }




}
