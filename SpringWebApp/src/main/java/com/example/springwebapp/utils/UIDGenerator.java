package com.example.springwebapp.utils;

import java.util.UUID;
/**
* UUID工具类
*
* @author zhoubin
* @since 1.0.0
*/
public class UIDGenerator {
    public static String generateUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}