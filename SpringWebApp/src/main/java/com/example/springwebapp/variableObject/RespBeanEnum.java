package com.example.springwebapp.variableObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "ERROR"),
    LOGIN_ERROR(500, "Login Error: Username or password is incorrect");

    private final Integer code;
    private final String msg;
}
