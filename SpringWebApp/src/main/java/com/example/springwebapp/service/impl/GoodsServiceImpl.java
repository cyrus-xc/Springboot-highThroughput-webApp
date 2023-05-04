package com.example.springwebapp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springwebapp.mapper.GoodsMapper;
import com.example.springwebapp.pojo.Goods;
import com.example.springwebapp.service.IGoodsService;
import com.example.springwebapp.valueObject.GoodsValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsValueObject> listGoods() {
        return goodsMapper.listGoods();
    }

    @Override
    public GoodsValueObject getGoodByID(long id) {
        return goodsMapper.getGoodByID(id);
    }
}
