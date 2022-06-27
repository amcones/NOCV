package com.amcones.nocv.controller;

import com.amcones.nocv.entity.HeSuan;
import com.amcones.nocv.entity.Vaccine;
import com.amcones.nocv.service.VaccineService;
import com.amcones.nocv.view.DataView;
import com.amcones.nocv.view.HeSuanView;
import com.amcones.nocv.view.VaccineView;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/vaccine")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;
    @RequestMapping("/toVaccine")
    public String toVaccine(){
        return "vaccine/vaccine";
    }

    @RequestMapping("/loadAllVaccine")
    @ResponseBody
    public DataView loadAllVaccine(VaccineView vaccineView){
        IPage<Vaccine> page = new Page<>(vaccineView.getPage(),vaccineView.getLimit());
        QueryWrapper<Vaccine> queryWrapper = new QueryWrapper<>();
        vaccineService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }

    @RequestMapping("/addVaccine")
    @ResponseBody
    public DataView addVaccine(Vaccine vaccine){
        vaccineService.save(vaccine);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("添加疫苗记录成功");
        return dataView;
    }

    @RequestMapping("/updateVaccine")
    @ResponseBody
    public DataView updateVaccine(Vaccine vaccine){
        vaccineService.updateById(vaccine);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("修改疫苗记录成功");
        return dataView;
    }

    @RequestMapping("/deleteVaccine")
    @ResponseBody
    public DataView deleteVaccine(Integer id){
        vaccineService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除疫苗记录成功");
        return dataView;
    }
}
