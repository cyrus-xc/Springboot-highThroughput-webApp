package com.example.springwebapp.controller;

import com.example.springwebapp.pojo.User;
import com.example.springwebapp.service.IGoodsService;
import com.example.springwebapp.service.IUserService;
import com.example.springwebapp.valueObject.GoodsValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IGoodsService goodsService;

//    @RequestMapping(value = "/toList", produces = "text/html;charset-utf-8")
//    @ResponseBody
    @RequestMapping("/toList")
    public String toList(Model model, User user) {
//        if (ticket.isEmpty()){
//            return "redirect:/login";
//        }
////        User user = (User) session.getAttribute(ticket);
//        User user = userService.getUserByCookie(ticket, request, response);
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.listGoods());

        return "goodsList";
    }

    @RequestMapping("/toDetail/{id}")
    public String toDetail(Model model, User user, @PathVariable("id") long id) {
        GoodsValueObject item =  goodsService.getGoodByID(id);
        Date startTime = item.getStartTime();
        Date endTime = item.getEndTime();
        Date curTime = new Date();
        int status = 0;
        int remainSeconds = 0;
        if (curTime.before(startTime)) {
            status = 0; // Not start
            remainSeconds = ((int) startTime.getTime() - (int) curTime.getTime())/1000;
        }else if (curTime.after(endTime)) {
            status = 2; // End
        }else {
            status = 1; // Start
        }
        model.addAttribute("user", user);
        model.addAttribute("status", status);
        model.addAttribute("goods", item);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goodsDetail";
//        DetailValueObject detailValueObject = new DetailValueObject();
//        detailValueObject.setUser(user);
//        detailValueObject.setGoods(item);
//        detailValueObject.setRemainSeconds(remainSeconds);
//        detailValueObject.setStatus(status);
//        return RespBean.ok(detailValueObject);
    }
}
