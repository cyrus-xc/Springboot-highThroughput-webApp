package com.example.springwebapp.utils;

import java.util.UUID;
public class UIDGenerator {
    public static String generateUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}