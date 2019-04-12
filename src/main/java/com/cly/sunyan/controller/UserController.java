package com.cly.sunyan.controller;

import com.cly.sunyan.bean.User;
import com.cly.sunyan.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User register(@Param("account") String account, @Param("password") String password) {
        return userService.register(account, password);
    }

    public User login(@Param("account") String account, @Param("password") String password) {
        return userService.login(account, password);
    }
}
