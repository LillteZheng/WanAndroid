package com.zhengsr.wanandroid.bean;

import java.io.Serializable;

public class WebBean implements Serializable {
    public String url;
    public String title;
    public int position;
    public int id;
    public boolean isCollect;
    public boolean isShowIcon = true;
    public int originId;

    @Override
    public String toString() {
        return "WebBean{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", position=" + position +
                ", id=" + id +
                ", isCollect=" + isCollect +
                ", isShowIcon=" + isShowIcon +
                ", originId=" + originId +
                '}';
    }
}