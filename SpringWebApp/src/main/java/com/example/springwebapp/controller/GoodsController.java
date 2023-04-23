package com.example.springwebapp.controller;

import com.example.springwebapp.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @RequestMapping("/toList")
    public String toList(HttpSession session, Model model, @CookieValue("userTicket") String ticket) {
        if (ticket.isEmpty()){
            return "redirect:/login";
        }
        User user = (User) session.getAttribute(ticket);
        if (user == null){
            return "redirect:/login";
        }
        model.addAttribute("user", user);

        return "goodsList";
    }
}
