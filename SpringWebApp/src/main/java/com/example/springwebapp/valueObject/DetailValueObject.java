package com.example.springwebapp.valueObject;

import com.example.springwebapp.mapper.GoodsMapper;
import com.example.springwebapp.pojo.User;
import lombok.Data;

@Data
public class DetailValueObject {
    private User user;
    private GoodsValueObject goods;
    private int remainSeconds;
    private int status;
}
