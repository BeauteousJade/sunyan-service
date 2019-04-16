package com.cly.sunyan.bean;

public class ArticleKeyWord {

    private String id;
    private String articleId;
    private String keyWord;
    private float ratio;
    private long recentTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public long getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(long recentTime) {
        this.recentTime = recentTime;
    }
}
