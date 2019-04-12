package com.cly.sunyan.dao;

import com.cly.sunyan.bean.article.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ArticleDao {

    @Insert("insert into article(id, author, content, time) values(#{id}, #{author}, #{articleNodeList, typeHandler=com.cly.sunyan.util.ListToArticleNodeTypeHandler}, #{time})")
    int uploadArticle(Article article);

}
