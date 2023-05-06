package com.example.springwebapp.controller;


import com.example.springwebapp.pojo.User;
import com.example.springwebapp.valueObject.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *   Testing class for UserController
 * </p>
 *
 * @author Chen XU
 * @since 2023-04-20
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user) {
        return RespBean.ok(user);
    }
}
