package com.example.springwebapp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springwebapp.pojo.User;
import com.example.springwebapp.valueObject.LoginValueObject;
import com.example.springwebapp.valueObject.RespBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 *  Service Interface
 * </p>
 *
 * @author Chen XU
 * @since 2023-04-20
 */
public interface IUserService extends IService<User> {

    /**
     * Implement login service function here
     *
     * @param data
     * @param request
     * @param response
     * @return
     */
    RespBean doLogin(LoginValueObject data, HttpServletRequest request, HttpServletResponse response);
    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);
}
