package com.example.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/demo")
public class controller {
    /**
     * Description: test request mapping
     *
     * @RequestMapping("/hello") 用于映射一个请求
     * @param model
     * @return
     */
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "HelloWorld");

        return "hello";
    }
}