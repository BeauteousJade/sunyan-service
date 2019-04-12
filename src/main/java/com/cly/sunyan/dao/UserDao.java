package com.cly.sunyan.dao;

import com.cly.sunyan.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    @Insert("insert into user(account, password, time) select account, password, time from dual where account = #{account}")
    int register(User user);

    @Select("select account, userName, time from user where account = #{account}")
    User loginUser(@Param("account") String account, @Param("password") String password);
}
