package com.amcones.nocv.controller;

import com.amcones.nocv.entity.HealthClock;
import com.amcones.nocv.service.HealthClockService;
import com.amcones.nocv.view.DataView;
import com.amcones.nocv.view.HealthClockView;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthClockController {

    @Autowired
    private HealthClockService healthClockService;

    @RequestMapping("/toHealthClock")
    public String toHealthClock(){
        return "admin/healthclock";
    }

    @RequestMapping("/listHealthClock")
    @ResponseBody
    public DataView listHealthClock(HealthClockView healthClockView){
        IPage<HealthClock> page = new Page<>(healthClockView.getPage(),healthClockView.getLimit());
        QueryWrapper<HealthClock>queryWrapper = new QueryWrapper<>();
        queryWrapper.like(healthClockView.getUsername()!=null,"username",healthClockView.getUsername());
        queryWrapper.eq(healthClockView.getPhone()!=null,"phone",healthClockView.getPhone());
        healthClockService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }

    @RequestMapping("/addHealthClock")
    @ResponseBody
    public DataView addHealthClock(HealthClock healthClock){
        boolean b = healthClockService.saveOrUpdate(healthClock);
        DataView dataView = new DataView();
        if(b){
            dataView.setCode(200);
            dataView.setMsg("添加成功");
            return dataView;
        }
        dataView.setCode(100);
        dataView.setMsg("添加失败");
        return dataView;
    }

    @RequestMapping("/deleteHealthClockById")
    @ResponseBody
    public DataView deleteHealthClockById(Integer id){
        healthClockService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除数据成功");
        return dataView;
    }
}
