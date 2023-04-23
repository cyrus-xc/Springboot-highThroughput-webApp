package com.example.springwebapp.variableObject;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginVaribaleObject {
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
