package com.zhengsr.wanandroid.moudle.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ArticleData;
import com.zhengsr.wanandroid.utils.Lgg;

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
        String author = (item.getAuthor() != null && item.getAuthor().length() > 0) ? item.getAuthor():item.getShareUser();
        helper.setText(R.id.item_article_author,author)
                .setText(R.id.item_article_chapat, msg)
                .setText(R.id.item_article_title,item.getTitle())
                .setText(R.id.item_article_time,item.getNiceDate())
                .addOnClickListener(R.id.item_article_like);

        if (item.isCollect() || isCollected) {
            helper.setImageResource(R.id.item_article_like, R.drawable.icon_like_article_select);
        } else {
            helper.setImageResource(R.id.item_article_like, R.drawable.icon_like_article_not_selected);
        }

    }


}
