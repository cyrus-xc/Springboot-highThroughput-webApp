package com.example.springwebapp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springwebapp.pojo.Goods;
import com.example.springwebapp.valueObject.GoodsValueObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chen XU
 * @since 2023-05-02
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * retrieve goods list
     * @return
     */
    List<GoodsValueObject> listGoods();
}
