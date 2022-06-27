package com.amcones.nocv.controller;

import com.amcones.nocv.entity.HeSuan;
import com.amcones.nocv.service.HeSuanService;
import com.amcones.nocv.view.DataView;
import com.amcones.nocv.view.HeSuanView;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hesuan")
public class HeSuanController {
    @Autowired
    private HeSuanService heSuanService;

    @RequestMapping("/toHeSuan")
    public String toHeSuan(){
        return "hesuan/hesuan";
    }

    @RequestMapping("/loadAllHeSuan")
    @ResponseBody
    public DataView loadAllHeSuan(HeSuanView heSuanView){
        IPage<HeSuan> page = new Page<>(heSuanView.getPage(),heSuanView.getLimit());
        QueryWrapper<HeSuan> queryWrapper = new QueryWrapper<>();
        heSuanService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }

    @RequestMapping("/addHeSuan")
    @ResponseBody
    public DataView addHeSuan(HeSuan heSuan){
        heSuanService.save(heSuan);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("添加核酸检测成功");
        return dataView;
    }

    @RequestMapping("/updateHeSuan")
    @ResponseBody
    public DataView updateHeSuan(HeSuan heSuan){
        heSuanService.updateById(heSuan);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("修改核酸检测成功");
        return dataView;
    }

    @RequestMapping("/deleteHeSuan")
    @ResponseBody
    public DataView deleteHeSuan(Integer id){
        heSuanService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除核酸检测成功");
        return dataView;
    }
}
