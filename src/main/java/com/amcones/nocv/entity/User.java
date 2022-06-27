package com.amcones.nocv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String salt;

    private String sex;
    private Integer age;
    private String address;
    private String img;
    private String phone;
    private String card;

    private Integer roleId;
    private Integer banJiId;
    private Integer xueYuanId;
    private Integer teacherId;

    @TableField(exist = false)
    private String banJiName;

    @TableField(exist = false)
    private String xueYuanName;

    @TableField(exist = false)
    private String teacherName;
}
