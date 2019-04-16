package com.cly.sunyan.controller;

import com.cly.sunyan.bean.article.Article;
import com.cly.sunyan.service.ArticleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/uploadArticle")
    public Article uploadArticle(@RequestParam("json") String contentJson, @Param("file") List<MultipartFile> fileList) {
        return articleService.updateArticle(contentJson, fileList);
    }

    @PostMapping("/recommendArticle")
    public List<Article> recommendArticle() {
        return articleService.recommendArticle();
    }

    @PostMapping("/mayLikeArticle")
    public List<Article> mayLikeArticle() {
        return articleService.mayLikeArticle();
    }
}
