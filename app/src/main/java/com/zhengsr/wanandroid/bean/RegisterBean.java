package com.zhengsr.wanandroid.bean;

import java.util.List;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class RegisterBean {
    private List chapterTops ;

    private List collectIds ;

    private String email;

    private String icon;

    private int id;

    private String password;

    private String token;

    private int type;

    private String username;

    public void setChapterTops(List chapterTops){
        this.chapterTops = chapterTops;
    }
    public List getChapterTops(){
        return this.chapterTops;
    }
    public void setCollectIds(List collectIds){
        this.collectIds = collectIds;
    }
    public List getCollectIds(){
        return this.collectIds;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    public String getIcon(){
        return this.icon;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }


    @Override
    public String toString() {
        return "LogUpBean{" +
                "chapterTops=" + chapterTops +
                ", collectIds=" + collectIds +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", type=" + type +
                ", username='" + username + '\'' +
                '}';
    }
}
