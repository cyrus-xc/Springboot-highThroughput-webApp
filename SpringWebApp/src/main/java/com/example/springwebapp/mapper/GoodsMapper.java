package com.example.springwebapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springwebapp.pojo.Goods;
import com.example.springwebapp.valueObject.GoodsValueObject;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsValueObject> listGoods();
}
