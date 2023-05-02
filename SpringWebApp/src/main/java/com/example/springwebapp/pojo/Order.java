package com.example.springwebapp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chen XU
 * @since 2023-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long goodsId;

    private String goodsName;

    private Integer goodsCount;

    private BigDecimal goodsPrice;

    /**
     * order status, 0 for new, 1 for paid, 2 for shipped, 3 for delivered, 4 for finished
     */
    private Integer status;

    private Date createDate;

    private Date payDate;


}
