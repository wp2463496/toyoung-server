package com.forty7.toyoung.impl;

import com.forty7.toyoung.service.UserService;
import com.forty7.toyoung.model.User;
import com.forty7.toyoung.repository.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    // 注入mapper类
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}