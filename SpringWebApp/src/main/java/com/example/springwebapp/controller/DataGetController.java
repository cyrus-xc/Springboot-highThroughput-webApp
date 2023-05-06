package com.example.springwebapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springwebapp.pojo.Order;
import com.example.springwebapp.pojo.User;
import com.example.springwebapp.service.IOrderService;
import com.example.springwebapp.valueObject.RespBean;
import com.example.springwebapp.valueObject.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataGetController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("/result")
    public RespBean getResult(User user, Long itemID) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        Long OrderID = orderService.getResult(user, itemID);
        return RespBean.ok(OrderID);
    }

    @GetMapping("/getOrderDetail")
    public RespBean getOrderDetail(User user, Long orderID) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        Order order = orderService.getOne(new QueryWrapper<Order>().eq("id", orderID));
        return RespBean.ok(order);
    }
}