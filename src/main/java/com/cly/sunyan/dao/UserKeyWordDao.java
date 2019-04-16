package com.cly.sunyan.dao;

import com.cly.sunyan.bean.KeyWord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserKeyWordDao {

    @Insert("insert into userKeyWord(account, keyWord, ratio,recentTime, time) values(#{account}, #{keyWord.keyWord}, #{keyWord.ratio}, #{recentTime}, #{time})")
    int insertUserKeyWord(@Param("keyWord") KeyWord keyWord, @Param("account") String account, @Param("recentTime") long recentTime, @Param("time") long time);

    @Insert("update userKeyWord set ratio = ratio + #{keyWord.ratio}, recentTime = #{recentTime} where account = #{account} and keyWord = #{keyWord.keyWord}")
    int updateUserKeyWord(@Param("keyWord") KeyWord keyWord, @Param("account") String account, @Param("recentTime") long recentTime);

    @Select("select count(1) from userKeyWord where account = #{account} and keyWord = #{keyWord}")
    int findUserKeyWord(@Param("account") String account, @Param("keyWord") String keyWord);
}
