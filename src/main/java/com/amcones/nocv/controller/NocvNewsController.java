package com.amcones.nocv.controller;

import com.amcones.nocv.entity.NocvNews;
import com.amcones.nocv.service.NocvNewsService;
import com.amcones.nocv.view.DataView;
import com.amcones.nocv.view.NocvNewsView;
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
@RequestMapping("/news")
public class NocvNewsController {
    @Autowired
    private NocvNewsService nocvNewsService;

    @RequestMapping("/toNews")
    public String toNews(){
        return "news/news";
    }

    @RequestMapping("/listNews")
    @ResponseBody
    public DataView listNews(NocvNewsView nocvNewsView){
        QueryWrapper<NocvNews> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(nocvNewsView.getTitle()),"title",nocvNewsView.getTitle());
        IPage<NocvNews> iPage = new Page<>(nocvNewsView.getPage(),nocvNewsView.getLimit());
        nocvNewsService.page(iPage,queryWrapper);
        return new DataView(iPage.getTotal(),iPage.getRecords());
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public DataView deleteById(Integer id){
        nocvNewsService.removeById(id);
        DataView dataView = new DataView();
        dataView.setMsg("删除成功");
        dataView.setCode(200);
        return dataView;
    }

    @RequestMapping("/addOrUpdateNews")
    @ResponseBody
    public DataView addOrUpdateNews(NocvNews nocvNews){
        nocvNews.setCreateTime(new Date());
        nocvNewsService.saveOrUpdate(nocvNews);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("新增或修改成功");
        return dataView;
    }
}
