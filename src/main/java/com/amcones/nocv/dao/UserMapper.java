package com.amcones.nocv.dao;

import com.amcones.nocv.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username} and password = #{password}")
    User login(String username, String password);
}
