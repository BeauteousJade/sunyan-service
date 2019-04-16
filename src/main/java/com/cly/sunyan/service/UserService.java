package com.cly.sunyan.service;

import com.cly.sunyan.bean.User;
import com.cly.sunyan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@Repository
public class UserService {

    @Autowired
    UserDao userDao;

    public User register(String account, String password) {
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setUserName("default");
        user.setAvatar("https://upload.jianshu.io/users/upload_avatars/9124992/c56d68b9-89af-48a2-a93a-48e7dcac778f.jpg");
        user.setTime(System.currentTimeMillis());
        if (userDao.register(user) == 1) {
            return user;
        }
        return null;
    }

    public User login(String account, String password) {
        User user = userDao.loginUser(account, password);
        if (user != null) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (servletRequestAttributes != null) {
                servletRequestAttributes.getRequest().getSession().setAttribute("account", user.getAccount());
                return user;
            }
        }
        return user;
    }
}
