package com.example.springwebapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springwebapp.mapper.OrderMapper;
import com.example.springwebapp.pojo.Goods;
import com.example.springwebapp.pojo.Order;
import com.example.springwebapp.pojo.User;
import com.example.springwebapp.service.IGoodsService;
import com.example.springwebapp.service.IOrderService;
import com.example.springwebapp.valueObject.GoodsValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order createOrder(GoodsValueObject item, User user) {
        Goods goods = goodsService.getOne(new QueryWrapper<Goods>().eq("id", item.getId()));
        goods.setStock(goods.getStock() - 1);
        goodsService.updateById(goods);
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(item.getId());
        order.setGoodsName(item.getName());
        order.setGoodsCount(1);
        order.setGoodsPrice(item.getPrice());
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);




        return order;
    }
}
