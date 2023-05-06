package com.example.springwebapp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RabbitMessage implements Serializable {

    private User user;
    private Long goodsID;

}
