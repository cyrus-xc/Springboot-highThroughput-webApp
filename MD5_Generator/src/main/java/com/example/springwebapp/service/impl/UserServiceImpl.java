package com.example.springwebapp.service.impl;

import com.example.springwebapp.pojo.User;
import com.example.springwebapp.mapper.UserMapper;
import com.example.springwebapp.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chen XU
 * @since 2023-04-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
