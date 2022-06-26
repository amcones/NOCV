package com.amcones.nocv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("menu")
@Data
public class Menu {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer pid;
    private String type;
    private String title;
    private String permission;
    private String icon;
    private String href;
    private Integer open;
    private Integer ordernum;
    private Integer available;
}
