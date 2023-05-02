package com.example.springwebapp.valueObject;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginValueObject {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
