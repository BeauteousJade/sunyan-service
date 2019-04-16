package com.cly.sunyan.configure;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object
            o) throws Exception {
        System.out.println(request.getSession().getAttribute("account"));
        if (request.getSession().getAttribute("account") == null) {
            // 拦截至登陆页面
            request.getRequestDispatcher("/user/login").forward(request, response);
            // false为不通过
            return false;
        }
        // true为通过
        return true;
    }
}
