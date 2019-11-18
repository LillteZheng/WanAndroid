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
            TextIndicator textIndicator = mView.findViewById(R.id.banner_indicator);
            PageBean bean = new PageBean.Builder<BannerBean>()
                    .data(beans)
                    .indicator(textIndicator)
                    .builder();
            bannerViewPager.setPageListener(bean, R.layout.banner_item_layout, new PageHelperListener<BannerBean>() {
                @Override
                public void getItemView(final View view, final BannerBean bannerBean) {
                    ImageView imageView = view.findViewById(R.id.banner_icon);
                    TextView textView = view.findViewById(R.id.banner_text);
                    Glide.with(getContext())
                            .load(bannerBean.getImagePath())
                            .into(imageView);
                    textView.setText(bannerBean.getTitle());

                    view.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mListener != null){
                                mListener.itemClick(view,bannerBean);
                            }
                        }
                    });

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
