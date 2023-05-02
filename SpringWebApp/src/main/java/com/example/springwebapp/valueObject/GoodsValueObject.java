package com.example.springwebapp.valueObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsValueObject {
    private BigDecimal price;
    private String name;
    private Integer stock;
    private Date startDate;
    private Date endDate;
}
