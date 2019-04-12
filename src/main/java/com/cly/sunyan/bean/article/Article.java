package com.cly.sunyan.bean.article;

import java.util.List;

public class Article {

    private String id;
    private String author;
    private List<ArticleNode> articleNodeList;
    private long time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<ArticleNode> getArticleNodeList() {
        return articleNodeList;
    }

    public void setArticleNodeList(List<ArticleNode> articleNodeList) {
        this.articleNodeList = articleNodeList;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
