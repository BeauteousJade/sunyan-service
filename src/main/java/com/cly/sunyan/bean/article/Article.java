package com.cly.sunyan.bean.article;

import com.cly.sunyan.bean.KeyWord;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.mining.word.WordInfo;

import java.util.List;

public class Article {

    private String id;
    private String author;
    private List<KeyWord> keyWordList;
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

    public List<KeyWord> getKeyWordList() {
        return keyWordList;
    }

    public void setKeyWordList(List<KeyWord> keyWordList) {
        this.keyWordList = keyWordList;
    }

    public List<WordInfo> generateKeyWordList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ArticleNode articleNode : articleNodeList) {
            if (articleNode.getContent() != null) {
                stringBuilder.append(articleNode.getContent());
            }
        }
        return HanLP.extractWords(stringBuilder.toString(), 5);
    }
}
