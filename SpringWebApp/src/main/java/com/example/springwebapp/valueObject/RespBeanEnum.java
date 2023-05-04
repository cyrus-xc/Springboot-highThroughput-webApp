package com.example.springwebapp.valueObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "ERROR"),
    LOGIN_ERROR(500211, "Login Error: Username or password is incorrect"),
    EMPTY_STOCK(500311, "Empty Stock"),
    REPEAT_ERROR(500312, "Already Bought, second order is not allowed")
    ;

    private final Integer code;
    private final String msg;
}
