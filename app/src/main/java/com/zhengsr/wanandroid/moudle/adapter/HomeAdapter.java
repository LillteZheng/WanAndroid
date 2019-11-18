package com.zhengsr.wanandroid.moudle.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ArticleData;

import java.util.List;

/**
 * Created by zhengshaorui
 * time: 2018/9/1
 */

public class HomeAdapter extends BaseQuickAdapter<ArticleData, BaseViewHolder> {

    private boolean isCollected = false;

    public HomeAdapter(int layoutResId, @Nullable List<ArticleData> data) {
        super(layoutResId, data);
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public void notifyItem(int position, ArticleData item){

    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleData item) {
        String msg;
        if (!TextUtils.isEmpty(item.getSuperChapterName())){
            msg = item.getSuperChapterName()+"/"+item.getChapterName();
        }else{
            msg = item.getChapterName();
        }
        helper.setText(R.id.item_article_author,item.getShareUser())
                .setText(R.id.item_article_chapat, msg)
                .setText(R.id.item_article_title,item.getTitle())
                .setText(R.id.item_article_time,item.getNiceDate())
                .addOnClickListener(R.id.item_article_lick);

        if (item.getCollect() || isCollected) {
            helper.setImageResource(R.id.item_article_lick, R.drawable.icon_like_article_select);
        } else {
            helper.setImageResource(R.id.item_article_lick, R.drawable.icon_like_article_not_selected);
        }

    }


}
