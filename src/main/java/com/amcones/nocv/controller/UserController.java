package com.amcones.nocv.controller;

import com.amcones.nocv.entity.BanJi;
import com.amcones.nocv.entity.User;
import com.amcones.nocv.entity.XueYuan;
import com.amcones.nocv.service.BanJiService;
import com.amcones.nocv.service.RoleService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BanJiService banJiService;

    @Autowired
    private XueYuanService xueYuanService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("toUser")
    public String toUser() {
        return "user/user";
    }

    @RequestMapping("/toChangePassword")
    public String toChangePassword() {
        return "user/changepassword";
    }

    @RequestMapping("/toUserInfo")
    public String toUserInfo() {
        return "user/userInfo";
    }

    @RequestMapping("/loadAllUser")
    @ResponseBody
    public DataView getAllUser(UserView userView) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User> page = new Page<>(userView.getPage(), userView.getLimit());
        queryWrapper.like(StringUtils.isNotBlank(userView.getUsername()), "username", userView.getUsername());
        queryWrapper.eq(StringUtils.isNotBlank(userView.getPhone()), "phone", userView.getPhone());
        IPage<User> iPage = userService.page(page, queryWrapper);

        for (User user : iPage.getRecords()) {
            if (user.getBanJiId() != null) {
                BanJi banji = banJiService.getById(user.getBanJiId());
                user.setBanJiName(banji.getName());
            }
            if (user.getXueYuanId() != null) {
                XueYuan xueYuan = xueYuanService.getById(user.getXueYuanId());
                user.setXueYuanName(xueYuan.getName());
            }
            if (user.getTeacherId() != null) {
                User teacher = userService.getById(user.getTeacherId());
                user.setTeacherName(teacher.getUsername());
            }
        }

        return new DataView(iPage.getTotal(), iPage.getRecords());
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public DataView addUser(User user) {
        userService.save(user);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("添加用户成功");
        return dataView;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public DataView updateUser(User user) {
        userService.updateById(user);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("修改用户成功");
        return dataView;
    }

    @RequestMapping("/deleteUser/{id}")
    @ResponseBody
    public DataView deleteUser(@PathVariable("id") Integer id) {
        userService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除用户成功");
        return dataView;
    }

    @RequestMapping("/listAllBanJi")
    @ResponseBody
    public List<BanJi> listAllBanJi() {
        List<BanJi> list = banJiService.list();
        return list;
    }

    @RequestMapping("/listAllXueYuan")
    @ResponseBody
    public List<XueYuan> listAllXueYuan() {
        List<XueYuan> list = xueYuanService.list();
        return list;
    }

    @RequestMapping("/resetPwd/{id}")
    @ResponseBody
    public DataView resetPwd(@PathVariable("id") Integer id) {
        User user = new User();
        user.setId(id);
        user.setPassword("123456");
        userService.updateById(user);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("用户重置密码成功");
        return dataView;
    }

    @RequestMapping("/initRoleByUserId")
    @ResponseBody
    public DataView initRoleByUserId(Integer id) {
        List<Map<String, Object>> listMaps = roleService.listMaps();
        List<Integer> currentUserRoleIds = roleService.queryUserRoleById(id);
        for (Map<String, Object> map : listMaps) {
            Boolean LAY_CHECKED = false;
            Integer roleId = (Integer) map.get("id");
            for(Integer rid:currentUserRoleIds){
                if(rid.equals(roleId)){
                    LAY_CHECKED = true;
                    break;
                }
            }
            map.put("LAY_CHECKED",LAY_CHECKED);
        }
        return new DataView(Long.valueOf(listMaps.size()),listMaps);
    }

    @RequestMapping("/saveUserRole")
    @ResponseBody
    public DataView saveUserRole(Integer uid,Integer[] ids){
        userService.saveUserRole(uid,ids);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("用户角色分配成功");
        return dataView;

    }

}
