package com.zhengsr.wanandroid.bean;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class RankBean {
    private int coinCount;
    private int level;
    private int rank;
    private int userId;
    private String username;



    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "RankBean{" +
                "coinCount=" + coinCount +
                ", level=" + level +
                ", rank=" + rank +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
