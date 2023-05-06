package com.example.springwebapp.rabbitmq;

import com.example.springwebapp.pojo.RabbitMessage;
import com.example.springwebapp.pojo.User;
import com.example.springwebapp.service.IGoodsService;
import com.example.springwebapp.service.IOrderService;
import com.example.springwebapp.valueObject.GoodsValueObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ObjectInputStream;

/**
 * RabbitMQ consumer
 */
@Service
@Slf4j
public class MQReceiver {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "orderQueue_topic")
    public void receive(RabbitMessage msg) {

        if (msg != null) {
            Long goodsID = msg.getGoodsID();
            User user = msg.getUser();
            log.info("Received message: " + user + " " + goodsID);

            // create order
            GoodsValueObject item = goodsService.getGoodByID(goodsID);
            orderService.createOrder(item, user);
        }
    }
}
