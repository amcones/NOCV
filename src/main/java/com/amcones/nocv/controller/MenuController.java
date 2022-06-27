package com.amcones.nocv.controller;

import com.amcones.nocv.entity.Menu;
import com.amcones.nocv.service.MenuService;
import com.amcones.nocv.utils.TreeNode;
import com.amcones.nocv.utils.TreeNodeBuilder;
import com.amcones.nocv.view.DataView;
import com.amcones.nocv.view.MenuView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/toMenu")
    public String toMenu(){
        return "menu/menu";
    }

    @RequestMapping("loadAllMenu")
    @ResponseBody
    public DataView loadAllMenu(MenuView menuView){
        IPage<Menu>page = new Page<>(menuView.getPage(),menuView.getLimit());
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(menuView.getTitle()),"title",menuView.getTitle());
        queryWrapper.orderByAsc("ordernum");
        menuService.page(page);
        return new DataView(page.getTotal(),page.getRecords());
    }

    @RequestMapping("/loadMenuManagerLeftTreeJson")
    @ResponseBody
    public DataView loadMenuManagerLeftTreeJson(){
        List<Menu> list = menuService.list();
        List<TreeNode> treeNodes = new ArrayList<>();
        for(Menu menu:list){
            Boolean open = menu.getOpen() == 1;
            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),open));
        }
        return new DataView(treeNodes);
    }
    @RequestMapping("/loadMenuMaxOrderNum")
    @ResponseBody
    public Map<String,Object> loadMenuMaxOrderNum(){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Menu>page = new Page<>(1,1);
        List<Menu>list=menuService.page(page,queryWrapper).getRecords();
        map.put("value",list.get(0).getOrdernum()+1);
        return map;
    }

    @RequestMapping("/addMenu")
    @ResponseBody
    public DataView addMenu(Menu menu){
        DataView dataView = new DataView();
        menu.setType("menu");
        boolean save = menuService.save(menu);
        if(!save){
            dataView.setCode(100);
            dataView.setMsg("数据插入失败");
        }else {
            dataView.setMsg("菜单插入成功");
            dataView.setCode(200);
        }
        return dataView;
    }

    @RequestMapping("/updateMenu")
    @ResponseBody
    public DataView updateMenu(Menu menu){
        menuService.updateById(menu);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("更新菜单成功");
        return dataView;
    }

    @RequestMapping("/checkMenuHasChildrenNode")
    @ResponseBody
    public Map<String,Object> checkChildrenNode(Menu menu){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Menu>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",menu.getId());
        List<Menu> list = menuService.list(queryWrapper);
        if(list.size()>0){
            map.put("value",true);
        }
        else{
            map.put("value",false);
        }
        return map;
    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public DataView deleteMenu(Menu menu){
        menuService.removeById(menu.getId());
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除菜单成功");
        return dataView;
    }

    @RequestMapping("/loadIndexLeftMenuJson")
    @ResponseBody
    public DataView loadIndexLeftMenuJson(Menu menu){
        List<Menu> list = menuService.list();
        List<TreeNode> treeNodes = new ArrayList<>();
        for(Menu m:list){
            Integer id=m.getId();
            Integer pid=m.getPid();
            String title=m.getTitle();
            String icon=m.getIcon();
            String href=m.getHref();
            Boolean open = m.getOpen().equals(1);
            treeNodes.add(new TreeNode(id,pid,title,icon,href,open));
        }
        List<TreeNode>nodeList = TreeNodeBuilder.build(treeNodes,0);
        return new DataView(nodeList);
    }
}
