package com.example.springwebapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springwebapp.mapper.OrderMapper;
import com.example.springwebapp.pojo.Goods;
import com.example.springwebapp.pojo.Order;
import com.example.springwebapp.pojo.User;
import com.example.springwebapp.service.IGoodsService;
import com.example.springwebapp.service.IOrderService;
import com.example.springwebapp.valueObject.GoodsValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    @Override
    public Order createOrder(GoodsValueObject item, User user) {
        Goods goods = goodsService.getOne(new QueryWrapper<Goods>().eq("id", item.getId()));
        goods.setStock(goods.getStock() - 1);
        boolean result = goodsService.update(new UpdateWrapper<Goods>().setSql("stock = stock - 1").eq("" +
                "id", goods.getId()).gt("stock", 0));
        if (!result) { // if fails
            return null;
        }
        // generate order
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setGoodsName(goods.getName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goods.getPrice());
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        // put order into redis
        redisTemplate.opsForValue().set("order:" + user.getId() + ":" + goods.getId(), order);

        return order;
    }
}
