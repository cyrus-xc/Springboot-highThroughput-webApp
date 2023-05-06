package com.example.springwebapp.controller;


import com.example.springwebapp.pojo.RabbitMessage;
import com.example.springwebapp.pojo.Order;
import com.example.springwebapp.pojo.User;
import com.example.springwebapp.rabbitmq.MQSender;
import com.example.springwebapp.service.IGoodsService;
import com.example.springwebapp.service.IOrderService;
import com.example.springwebapp.service.IUserService;
import com.example.springwebapp.valueObject.GoodsValueObject;
import com.example.springwebapp.valueObject.RespBean;
import com.example.springwebapp.valueObject.RespBeanEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class OrderController implements InitializingBean {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MQSender rabbitMQSender;
    @Autowired
    private IOrderService orderService;

    private Map<Long, Boolean> localStockMap = new HashMap<>(); // id, stock status: true - out of stock, false - in stock

    @RequestMapping("/createOrder")
    public String createOrder(Model model, User user, long itemID) {
        if (user == null){
            return "redirect:/login";
        }

        // Check in redis: if user have already bought this item
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Order order = (Order) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + itemID);
        if (order != null) { // already bought
            model.addAttribute("errmsg", RespBeanEnum.valueOf("REPEAT_ERROR").getMsg());
            return "orderFail";
        }
        // pre-deduct stock
        Long stock = valueOperations.decrement("goods:" + itemID); // Atomic operation
        if (stock != null && stock < 0) { // if out of stock
            localStockMap.put(itemID, true);
            valueOperations.increment("goods:" + itemID); // rollback
            model.addAttribute("errmsg", RespBeanEnum.valueOf("EMPTY_STOCK").getMsg());
            return "orderFail";
        }

        // create order - old practice with SQL query
//        GoodsValueObject item =  goodsService.getGoodByID(itemID);
//        order = orderService.createOrder(item, user);

        // create order - new practice with RabbitMQ
        RabbitMessage message = new RabbitMessage(user, itemID);
        rabbitMQSender.sendOrderMsg(message);

        model.addAttribute("user", user);
        model.addAttribute("orderSuccess", false);
        model.addAttribute("itemID", itemID);
        return "orderDetail";
    }



    /**
     * System initialization, load all goods into redis
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsValueObject> list = goodsService.listGoods();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        // load all goods stock count to Redis
        for (GoodsValueObject item : list) {
            redisTemplate.opsForValue().set("goods:" + item.getId(), item.getStock());
            int stock = item.getStock();
            if (stock <= 0) {
                localStockMap.put(item.getId(), true); // out of stock
            } else {
                localStockMap.put(item.getId(), false); // in stock
            }
        }
    }
}
