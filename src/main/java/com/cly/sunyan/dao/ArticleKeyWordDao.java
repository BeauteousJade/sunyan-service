package com.cly.sunyan.dao;

import com.cly.sunyan.bean.ArticleKeyWord;
import com.cly.sunyan.bean.KeyWord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleKeyWordDao {

    @Insert("<script>" +
            "insert into articleKeyWord(id,articleId,keyWord,ratio,recentTime) VALUES" +
            "<foreach collection=\"articleKeyWordList\" item=\"item\" index=\"index\"  separator=\",\">" +
            "(#{item.id},#{item.articleId},#{item.keyWord},#{item.ratio},#{item.recentTime})" +
            "</foreach>" +
            "</script>"
    )
    int insertArticleKeyWord(@Param("articleKeyWordList") List<ArticleKeyWord> articleKeyWordList);

    @Select("select * from articleKeyWord where articleId = #{articleId}")
    List<KeyWord> findArticleKeyWordByArticleId(@Param("articleId") String articleId);
}
