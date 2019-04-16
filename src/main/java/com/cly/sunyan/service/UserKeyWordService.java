package com.cly.sunyan.service;

import com.cly.sunyan.bean.KeyWord;
import com.cly.sunyan.bean.article.Article;
import com.cly.sunyan.dao.UserKeyWordDao;
import com.cly.sunyan.util.gson.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
@Repository
public class UserKeyWordService {

    @Autowired
    UserKeyWordDao userKeyWordDao;

    public boolean updateKeyWord(String json) {
        Article article = GsonUtil.jsonToObject(json, Article.class);
        if (article != null) {
            String account = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("account").toString();
            List<KeyWord> keyWordList = article.getKeyWordList();
            for (KeyWord keyWord : keyWordList) {
                if (userKeyWordDao.findUserKeyWord(account, keyWord.getKeyWord()) == 0) {
                    userKeyWordDao.insertUserKeyWord(keyWord, account, System.currentTimeMillis(), System.currentTimeMillis());
                } else {
                    userKeyWordDao.updateUserKeyWord(keyWord, account, System.currentTimeMillis());
                }
            }
            return true;
        }
        return false;
    }
}
