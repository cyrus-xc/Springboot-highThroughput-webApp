package com.example.springwebapp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springwebapp.mapper.UserMapper;
import com.example.springwebapp.pojo.User;
import com.example.springwebapp.service.IUserService;
import com.example.springwebapp.utils.CookieUtil;
import com.example.springwebapp.utils.UIDGenerator;
import com.example.springwebapp.utils.md5;
import com.example.springwebapp.valueObject.LoginValueObject;
import com.example.springwebapp.valueObject.RespBean;
import com.example.springwebapp.valueObject.RespBeanEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Login logic
     *
     * @param data
     * @param request
     * @param response
     * @return
     */
    @Override
    public RespBean doLogin(LoginValueObject data, HttpServletRequest request, HttpServletResponse response) {
        // Implement login logic here
        String username = data.getUserName();
        String password = data.getPassword();
//        if (username.isEmpty() || password.isEmpty()) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }

        // Query the database to see if the user exists
        User user = userMapper.selectById(username);
        if (user == null) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        // Check if the password is correct
        if (!(md5.saltedPassToDBPass(password, user.getSalt()).equals(user.getPassword()))) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        // Login success:
        String ticket = UIDGenerator.generateUID();

        // Store the user information in Redis
        redisTemplate.opsForValue().set("user:" + ticket, user, 7200L, TimeUnit.SECONDS);
//        request.getSession().setAttribute(UID, user);
        CookieUtil.setCookie(request, response, "userTicket", ticket);

        return RespBean.ok(ticket);
    }

    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (userTicket.isEmpty()) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }
}
