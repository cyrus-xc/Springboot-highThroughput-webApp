package com.example.springwebapp.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chen XU
 * @since 2023-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID, Phone
     */
    private Long id;

    private String nickname;

    /**
     * MD5(MD5(password(plain text) + salt) + salt)
     */
    private String password;

    private String salt;

    /**
     * user avater pic url
     */
    private String avater;

    private Date lastLoginDate;

    private Integer loginCount;


}
