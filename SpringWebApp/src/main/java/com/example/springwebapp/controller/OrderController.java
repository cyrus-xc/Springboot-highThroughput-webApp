package com.example.springwebapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springwebapp.pojo.Order;
import com.example.springwebapp.pojo.User;
import com.example.springwebapp.service.IGoodsService;
import com.example.springwebapp.service.IOrderService;
import com.example.springwebapp.service.IUserService;
import com.example.springwebapp.valueObject.GoodsValueObject;
import com.example.springwebapp.valueObject.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chen XU
 * @since 2023-05-02
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/createOrder")
    public String createOrder(Model model, User user, long itemID) {
        if (user == null){
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        GoodsValueObject item =  goodsService.getGoodByID(itemID);
        if (item.getStock() < 1) { // if out of stock
            model.addAttribute("errmsg", RespBeanEnum.valueOf("EMPTY_STOCK").getMsg());
            return "orderFail";
        }
        // check if the user has already bought one item
        Order order = orderService.getOne(new QueryWrapper<Order>().eq("user_id", user.getId()).eq("goods_id", itemID));
        if (order != null) { // already bought
            model.addAttribute("errmsg", RespBeanEnum.valueOf("REPEAT_ERROR").getMsg());
            return "orderFail";
        }
        // create order
        order = orderService.createOrder(item, user);
        model.addAttribute("order", order);
        model.addAttribute("goods", item);
        return "orderDetail";
    }
}
