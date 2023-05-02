package com.example.springwebapp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springwebapp.mapper.GoodsMapper;
import com.example.springwebapp.pojo.Goods;
import com.example.springwebapp.service.IGoodsService;
import com.example.springwebapp.valueObject.GoodsValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chen XU
 * @since 2023-05-02
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsValueObject> listGoods() {
        return goodsMapper.listGoods();
    }
}
