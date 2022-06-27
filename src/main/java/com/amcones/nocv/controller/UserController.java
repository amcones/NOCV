package com.amcones.nocv.controller;

import com.amcones.nocv.entity.BanJi;
import com.amcones.nocv.entity.User;
import com.amcones.nocv.entity.XueYuan;
import com.amcones.nocv.service.BanJiService;
import com.amcones.nocv.service.UserService;
import com.amcones.nocv.service.XueYuanService;
import com.amcones.nocv.view.DataView;
import com.amcones.nocv.view.UserView;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BanJiService banJiService;

    @Autowired
    private XueYuanService xueYuanService;

    @RequestMapping("toUser")
    public String toUser(){
        return "user/user";
    }

    @RequestMapping("/toChangePassword")
    public String toChangePassword(){
        return "user/changepassword";
    }

    @RequestMapping("/toUserInfo")
    public String toUserInfo(){
        return "user/userInfo";
    }

    @RequestMapping("/loadAllUser")
    @ResponseBody
    public DataView getAllUser(UserView userView){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User>page=new Page<>(userView.getPage(),userView.getLimit());
        queryWrapper.like(StringUtils.isNotBlank(userView.getUsername()),"username",userView.getUsername());
        queryWrapper.eq(StringUtils.isNotBlank(userView.getPhone()),"phone",userView.getPhone());
        IPage<User> iPage = userService.page(page, queryWrapper);

        for(User user : iPage.getRecords()){
            if(user.getBanJiId()!=null){
                BanJi banji = banJiService.getById(user.getBanJiId());
                user.setBanJiName(banji.getName());
            }
            if(user.getXueYuanId()!=null){
                XueYuan xueYuan = xueYuanService.getById(user.getXueYuanId());
                user.setXueYuanName(xueYuan.getName());
            }
            if(user.getTeacherId()!=null){
                User teacher = userService.getById(user.getTeacherId());
                user.setTeacherName(teacher.getUsername());
            }
        }

        return new DataView(iPage.getTotal(),iPage.getRecords());
    }
}
