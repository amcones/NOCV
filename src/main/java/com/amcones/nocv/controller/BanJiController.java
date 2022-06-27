package com.amcones.nocv.controller;

import com.amcones.nocv.entity.BanJi;
import com.amcones.nocv.entity.XueYuan;
import com.amcones.nocv.service.BanJiService;
import com.amcones.nocv.service.XueYuanService;
import com.amcones.nocv.view.BanJiView;
import com.amcones.nocv.view.DataView;
import com.amcones.nocv.view.XueYuanView;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/banji")
public class BanJiController {
    @Autowired
    private BanJiService banJiService;

    @RequestMapping("/toBanJi")
    public String toNews(){
        return "banji/banji";
    }

    @RequestMapping("/listBanJi")
    @ResponseBody
    public DataView listNews(BanJiView banJiView){
        QueryWrapper<BanJi> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(banJiView.getName()),"name",banJiView.getName());
        IPage<BanJi> iPage = new Page<>(banJiView.getPage(),banJiView.getLimit());
        banJiService.page(iPage,queryWrapper);
        return new DataView(iPage.getTotal(),iPage.getRecords());
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public DataView deleteById(Integer id){
        banJiService.removeById(id);
        DataView dataView = new DataView();
        dataView.setMsg("删除成功");
        dataView.setCode(200);
        return dataView;
    }

    @RequestMapping("/addOrUpdateBanJi")
    @ResponseBody
    public DataView addOrUpdateNews(BanJi banJi){
        banJiService.saveOrUpdate(banJi);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("新增或修改成功");
        return dataView;
    }
}
