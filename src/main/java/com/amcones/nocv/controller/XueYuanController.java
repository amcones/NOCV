package com.amcones.nocv.controller;

import com.amcones.nocv.entity.NocvNews;
import com.amcones.nocv.entity.XueYuan;
import com.amcones.nocv.service.NocvNewsService;
import com.amcones.nocv.service.XueYuanService;
import com.amcones.nocv.view.DataView;
import com.amcones.nocv.view.NocvNewsView;
import com.amcones.nocv.view.XueYuanView;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/xueyuan")
public class XueYuanController {
    @Autowired
    private XueYuanService xueYuanService;

    @RequestMapping("/toXueYuan")
    public String toNews(){
        return "xueyuan/xueyuan";
    }

    @RequestMapping("/listXueYuan")
    @ResponseBody
    public DataView listNews(XueYuanView xueYuanView){
        QueryWrapper<XueYuan> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(xueYuanView.getName()),"name",xueYuanView.getName());
        IPage<XueYuan> iPage = new Page<>(xueYuanView.getPage(),xueYuanView.getLimit());
        xueYuanService.page(iPage,queryWrapper);
        return new DataView(iPage.getTotal(),iPage.getRecords());
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public DataView deleteById(Integer id){
        xueYuanService.removeById(id);
        DataView dataView = new DataView();
        dataView.setMsg("删除成功");
        dataView.setCode(200);
        return dataView;
    }

    @RequestMapping("/addOrUpdateXueYuan")
    @ResponseBody
    public DataView addOrUpdateNews(XueYuan xueYuan){
        xueYuanService.saveOrUpdate(xueYuan);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("新增或修改成功");
        return dataView;
    }
}
