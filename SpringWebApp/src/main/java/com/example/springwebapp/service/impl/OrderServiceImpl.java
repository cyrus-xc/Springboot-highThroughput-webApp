package com.example.springwebapp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springwebapp.mapper.OrderMapper;
import com.example.springwebapp.pojo.Order;
import com.example.springwebapp.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chen XU
 * @since 2023-05-02
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
