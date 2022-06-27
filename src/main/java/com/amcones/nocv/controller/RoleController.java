package com.amcones.nocv.controller;

import com.amcones.nocv.entity.Menu;
import com.amcones.nocv.entity.Role;
import com.amcones.nocv.service.MenuService;
import com.amcones.nocv.service.RoleService;
import com.amcones.nocv.utils.TreeNode;
import com.amcones.nocv.view.DataView;
import com.amcones.nocv.view.RoleView;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/toRole")
    public String toRole(){
        return "role/role";
    }

    @RequestMapping("loadAllRole")
    @ResponseBody
    public DataView loadAllRole(RoleView roleView){
        IPage<Role> page = new Page<>(roleView.getPage(),roleView.getLimit());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(roleView.getName()),"name",roleView.getName());
        queryWrapper.like(StringUtils.isNotBlank(roleView.getRemark()),"remark",roleView.getRemark());
        roleService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());

    }

    @RequestMapping("addRole")
    @ResponseBody
    public DataView addRole(Role role){
        roleService.save(role);
        DataView dataView = new DataView();
        dataView.setMsg("添加角色成功");
        dataView.setCode(200);
        return dataView;
    }

    @RequestMapping("deleteRole")
    @ResponseBody
    public DataView deleteRole(Role role){
        roleService.removeById(role.getId());
        DataView dataView = new DataView();
        dataView.setMsg("删除角色成功");
        dataView.setCode(200);
        return dataView;
    }

    @RequestMapping("updateRole")
    @ResponseBody
    public DataView updateRole(Role role){
        roleService.saveOrUpdate(role);
        DataView dataView = new DataView();
        dataView.setMsg("修改角色成功");
        dataView.setCode(200);
        return dataView;
    }

    @RequestMapping("initPermissionByRoleId")
    @ResponseBody
    public DataView initPermissionByRoleId(Integer roleId){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        List<Menu> allPermissions = menuService.list();
        List<Integer>currentRolePermissions = roleService.queryAllPermissionByRid(roleId);
        List<Menu> menus = null;
        if(currentRolePermissions.size()>0){
            queryWrapper.in("id",currentRolePermissions);
            menus = menuService.list(queryWrapper);
        }else{
            menus=new ArrayList<>();
        }
        List<TreeNode>nodes = new ArrayList<>();
        for(Menu allPermission:allPermissions){
            String checkArr="0";
            for(Menu currentPermission:menus){
                if(allPermission.getId().equals(currentPermission.getId())){
                    checkArr="1";
                    break;
                }
            }
            Boolean spread = allPermission.getOpen() == null || allPermission.getOpen() == 1;
            nodes.add(new TreeNode(allPermission.getId(),allPermission.getPid(),allPermission.getTitle(),spread,checkArr));
        }
        return new DataView(nodes);
    }

    @RequestMapping("/saveRolePermission")
    @ResponseBody
    public DataView saveRolePermission(Integer rid,Integer[] mids){
        roleService.deleteRoleByRid(rid);
        if(mids!=null&&mids.length>0){
            for(Integer mid:mids){
                roleService.saveRoleMenu(rid,mid);
            }
        }
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("菜单权限分配成功");
        return dataView;
    }

}
