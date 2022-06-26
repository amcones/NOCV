package com.amcones.nocv.service.impl;

import com.amcones.nocv.dao.UserMapper;
import com.amcones.nocv.entity.User;
import com.amcones.nocv.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.login(username,password);
    }

}
