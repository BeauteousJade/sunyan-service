package com.cly.sunyan.bean;

public class UserKeyWord {
    private String account;
    private String keyWord;
    private float ratio;
    private long recentTime;
    private long time;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public long getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(long recentTime) {
        this.recentTime = recentTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
