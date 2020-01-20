package com.zhengsr.wanandroid.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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

import java.util.List;

/**
 * Created by zhengshaorui
 * time: 2018/9/2
 */

public class BannerView extends FrameLayout {


    private View mView;

    public BannerView(@NonNull Context context) {
        this(context,null);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        removeAllViews();
        mView = LayoutInflater.from(getContext()).inflate(R.layout.banner_layout,this,false);
        addView(mView);
    }


    public void setData(List<BannerBean> beans){
        if (beans != null && beans.size() >0){
            BannerViewPager bannerViewPager = mView.findViewById(R.id.banner);
            CircleIndicator indicator = mView.findViewById(R.id.banner_indicator);

            bannerViewPager.addIndicator(indicator);

            bannerViewPager.setPageListener(R.layout.banner_item_layout, beans, new PageHelperListener<BannerBean>() {
                @Override
                public void bindView(View view, BannerBean data, int position) {
                    setText(view,R.id.banner_text,data.getTitle());
                    ImageView imageView = view.findViewById(R.id.banner_icon);
                    Glide.with(getContext())
                            .load(data.getImagePath())
                            .into(imageView);
                }

                @Override
                public void onItemClick(View view, BannerBean data, int position) {
                    super.onItemClick(view, data, position);
                    if (mListener != null){
                        mListener.itemClick(view,data);
                    }
                }
            });

        }
    }
    private BannerItemClickListener mListener;
    public void setBannerItemClickListener(BannerItemClickListener listener){
        mListener = listener;
    }

    public interface  BannerItemClickListener{
        void itemClick(View view, BannerBean bannerBean);
    }



}
