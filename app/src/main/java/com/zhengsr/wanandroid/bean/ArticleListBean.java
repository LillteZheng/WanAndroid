package com.zhengsr.wanandroid.bean;

import java.util.List;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class ArticleListBean {
    private int curPage;

    private List<ArticleData> datas ;

    private int offset;

    private boolean over;

    private int pageCount;

    private int size;

    private int total;

    public void setCurPage(int curPage){
        this.curPage = curPage;
    }
    public int getCurPage(){
        return this.curPage;
    }
    public void setDatas(List<ArticleData> datas){
        this.datas = datas;
    }
    public List<ArticleData> getDatas(){
        return this.datas;
    }
    public void setOffset(int offset){
        this.offset = offset;
    }
    public int getOffset(){
        return this.offset;
    }
    public void setOver(boolean over){
        this.over = over;
    }
    public boolean getOver(){
        return this.over;
    }
    public void setPageCount(int pageCount){
        this.pageCount = pageCount;
    }
    public int getPageCount(){
        return this.pageCount;
    }
    public void setSize(int size){
        this.size = size;
    }
    public int getSize(){
        return this.size;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
}
