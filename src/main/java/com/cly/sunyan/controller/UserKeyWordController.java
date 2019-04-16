package com.cly.sunyan.controller;

import com.cly.sunyan.service.UserKeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userKeyWord")
public class UserKeyWordController {

    @Autowired
    UserKeyWordService userKeyWordService;

    @PostMapping("/updateKeyWord")
    public boolean updateKeyWord(@RequestParam("json") String json) {
        return userKeyWordService.updateKeyWord(json);
    }
}
