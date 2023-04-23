package com.example.springwebapp.controller;

import com.example.springwebapp.service.IUserService;
import com.example.springwebapp.utils.UIDGenerator;
import com.example.springwebapp.variableObject.RespBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.springwebapp.variableObject.LoginVaribaleObject;

/* Implement login function here
 * @param username
 * @param password
 * @return
*/
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVaribaleObject data, HttpServletRequest request, HttpServletResponse response) {
//        log.info("userName: " + data.getUserName());
//        log.info("password: " + data.getPassword());
//        log.info("{}", data);

        return userService.doLogin(data, request, response); // Not implement the login logic here, jump to the service layer to implement
    }
}
