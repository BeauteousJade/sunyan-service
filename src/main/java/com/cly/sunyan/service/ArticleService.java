package com.cly.sunyan.service;

import com.cly.sunyan.bean.ArticleKeyWord;
import com.cly.sunyan.bean.KeyWord;
import com.cly.sunyan.bean.article.Article;
import com.cly.sunyan.bean.article.ArticleNode;
import com.cly.sunyan.dao.ArticleDao;
import com.cly.sunyan.dao.ArticleKeyWordDao;
import com.cly.sunyan.util.FileUtil;
import com.cly.sunyan.util.StringUtil;
import com.cly.sunyan.util.gson.GsonUtil;
import com.hankcs.hanlp.mining.word.WordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
    @Autowired
    ArticleKeyWordDao articleKeyWordDao;


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
            List<WordInfo> wordInfoList = article.generateKeyWordList();
            List<KeyWord> keyWordList = extractKeyWord(wordInfoList);
            article.setKeyWordList(keyWordList);
            article.setId(StringUtil.generateId());
            article.setTime(System.currentTimeMillis());
            article.setAuthor(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("account").toString());
            articleDao.uploadArticle(article);

            List<ArticleKeyWord> articleKeyWordList = new ArrayList<>();
            for (int i = 0; i < article.getKeyWordList().size(); i++) {
                ArticleKeyWord articleKeyWord = new ArticleKeyWord();
                articleKeyWord.setId(StringUtil.generateId());
                articleKeyWord.setArticleId(article.getId());
                articleKeyWord.setKeyWord(article.getKeyWordList().get(i).getKeyWord());
                articleKeyWord.setRatio(keyWordList.get(i).getRatio());
                articleKeyWord.setRecentTime(System.currentTimeMillis());
                articleKeyWordList.add(articleKeyWord);
            }
            if (articleKeyWordDao.insertArticleKeyWord(articleKeyWordList) > 0) {
                return article;
            }
        }
        return null;
    }

    private List<KeyWord> extractKeyWord(List<WordInfo> wordInfoList) {
        List<KeyWord> keyWordList = new ArrayList<>();
        for (int i = 0; i < wordInfoList.size(); i++) {
            KeyWord keyWord = new KeyWord();
            keyWord.setKeyWord(wordInfoList.get(i).text);
            keyWord.setRatio(wordInfoList.get(i).entropy);
            keyWordList.add(keyWord);
        }
        return keyWordList;
    }

    public List<Article> recommendArticle() {
        String account = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("account").toString();
        return articleDao.recommendArticle(account);
    }

    public List<Article> mayLikeArticle() {
        String account = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("account").toString();
        return articleDao.mayLikeArticle(account, System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000);
    }
}
