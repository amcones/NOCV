package com.amcones.nocv.service;

import com.amcones.nocv.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {

    User login(String username, String password);

    void saveUserRole(Integer uid, Integer[] ids);
}
