package com.cly.sunyan.dao;

import com.cly.sunyan.bean.article.Article;
import com.cly.sunyan.handler.ListToArticleNodeTypeHandler;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleDao {

    @Insert("insert into article(id, author, content, time) values(#{id}, #{author}, #{articleNodeList, typeHandler=com.cly.sunyan.handler.ListToArticleNodeTypeHandler}, #{time})")
    int uploadArticle(Article article);

    @Select("select * from article where id in (select articleId from articleKeyWord where" +
            " keyWord in (select keyWord from userKeyWord  where account = #{account} order by ratio DESC))")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "content", property = "articleNodeList", typeHandler = ListToArticleNodeTypeHandler.class),
            @Result(property = "keyWordList", column = "id", many = @Many(select = "com.cly.sunyan.dao.ArticleKeyWordDao.findArticleKeyWordByArticleId"))
    })
    List<Article> recommendArticle(@Param("account") String account);


    @Select("select * from article where id in (select articleId from articleKeyWord where keyWord in " +
            "(select keyWord from userKeyWord where account = #{account} and recentTime >= #{time} order by recentTime DESC))")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "content", property = "articleNodeList", typeHandler = ListToArticleNodeTypeHandler.class),
            @Result(property = "keyWordList", column = "id", many = @Many(select = "com.cly.sunyan.dao.ArticleKeyWordDao.findArticleKeyWordByArticleId"))
    })
    List<Article> mayLikeArticle(@Param("account") String account, @Param("time") long time);
}
