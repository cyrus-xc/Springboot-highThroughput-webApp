package com.example.springwebapp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

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
@TableName("t_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String title;

    private String img;

    private String detail;

    private BigDecimal price;

    /**
     * -1 means no limit
     */
    private Integer stock;

    /**
     * sale start time
     */
    private Date startTime;

    /**
     * sale end time
     */
    private Date endTime;


}
