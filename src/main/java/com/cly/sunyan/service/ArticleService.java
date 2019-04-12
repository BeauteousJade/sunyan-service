package com.cly.sunyan.service;

import com.cly.sunyan.bean.article.Article;
import com.cly.sunyan.bean.article.ArticleNode;
import com.cly.sunyan.dao.ArticleDao;
import com.cly.sunyan.util.FileUtil;
import com.cly.sunyan.util.StringUtil;
import com.cly.sunyan.util.gson.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class ArticleService {

    @Value("${file.rootPath}")
    String rootPath;
    @Value("localHost")
    String localHost;

    @Autowired
    ArticleDao articleDao;


    public Article updateArticle(String json, List<MultipartFile> fileList) {
        List<ArticleNode> articleNodeList = GsonUtil.jsonToArray(json, ArticleNode.class);
        if (articleNodeList != null) {
            List<String> filePathList = new ArrayList<>();
            for (MultipartFile file : fileList) {
                filePathList.add(FileUtil.writeFile(rootPath, "image", localHost, file));
            }
            for (int i = 0, j = 0; i < articleNodeList.size() && j < filePathList.size(); i++) {
                ArticleNode articleNode = articleNodeList.get(i);
                if (articleNode.getContent() == null && articleNode.getImage() != null) {
                    articleNode.setImage(filePathList.get(j));
                    j++;
                }
            }
            Article article = new Article();
            article.setArticleNodeList(articleNodeList);
            article.setId(StringUtil.generateId());
            article.setTime(System.currentTimeMillis());
            articleDao.uploadArticle(article);
            return article;
        }
        return null;
    }
}
