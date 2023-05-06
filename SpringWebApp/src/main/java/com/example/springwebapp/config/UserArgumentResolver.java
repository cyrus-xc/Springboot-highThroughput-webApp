package com.example.springwebapp.config;

import com.example.springwebapp.service.IUserService;
import com.example.springwebapp.utils.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
public class UserArgumentResolver implements org.springframework.web.method.support.HandlerMethodArgumentResolver {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supportsParameter(org.springframework.core.MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz == com.example.springwebapp.pojo.User.class;
    }

    @Override
    public Object resolveArgument(org.springframework.core.MethodParameter parameter, org.springframework.web.method.support.ModelAndViewContainer mavContainer, org.springframework.web.context.request.NativeWebRequest webRequest, org.springframework.web.bind.support.WebDataBinderFactory binderFactory) throws Exception {
        String ticket = CookieUtil.getCookieValue(webRequest.getNativeRequest(HttpServletRequest.class), "userTicket");
        if (StringUtils.isEmpty(ticket)) {
            return null;
        }
        return userService.getUserByCookie(ticket, webRequest.getNativeRequest(HttpServletRequest.class), webRequest.getNativeResponse(HttpServletResponse.class));
    }
}
