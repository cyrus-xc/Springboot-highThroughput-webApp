package com.example.springwebapp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springwebapp.pojo.Order;
import com.example.springwebapp.pojo.User;
import com.example.springwebapp.valueObject.GoodsValueObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chen XU
 * @since 2023-05-02
 */
public interface IOrderService extends IService<Order> {

    Long getResult(User user, Long itemID);

    Order createOrder(GoodsValueObject item, User user);
}
