package com.amcones.nocv.controller;

import com.amcones.nocv.entity.LineTrend;
import com.amcones.nocv.entity.NocvData;
import com.amcones.nocv.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping("/query")
    @ResponseBody
    public List<NocvData> queryData() throws ParseException {
        /*QueryWrapper<NocvData> queryWrapper = new QueryWrapper<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(new Date());
        queryWrapper.ge("update_time",format.parse(format1));*/

        List<NocvData> list = indexService.listOrderByIdLimit();
        return list;
    }

    @RequestMapping("/toPie")
    public String toPie() {
        return "pie";
    }

    @RequestMapping("/queryPie")
    @ResponseBody
    public List<NocvData> queryPieData() {
        List<NocvData> list = indexService.listOrderByIdLimit();
        return list;
    }

    @RequestMapping("/toBar")
    public String toBar() {
        return "bar";
    }

    @RequestMapping("/queryBar")
    @ResponseBody
    public List<NocvData> queryBarData() {
        List<NocvData> list = indexService.listOrderByIdLimit();
        return list;
    }

    @RequestMapping("/toTrend")
    public String toTrend() {
        return "trend";
    }

    @RequestMapping("/queryTrend")
    @ResponseBody
    public Map<String, List<Object>> queryTrendData() throws ParseException {
        List<LineTrend> list7Day = indexService.findSevenData();
        List<Integer> confirmList = new ArrayList<>();
        List<Integer> isolationList = new ArrayList<>();
        List<Integer> cureList = new ArrayList<>();
        List<Integer> deadList = new ArrayList<>();
        List<Integer> similarList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (LineTrend data : list7Day) {
            confirmList.add(data.getConfirm());
            isolationList.add(data.getIsolation());
            cureList.add(data.getCure());
            deadList.add(data.getDead());
            similarList.add(data.getSimilar());
            dateList.add(format.format(data.getCreateTime()));
        }
        Collections.reverse(confirmList);
        Collections.reverse(isolationList);
        Collections.reverse(cureList);
        Collections.reverse(deadList);
        Collections.reverse(similarList);
        Collections.reverse(dateList);
        Map map = new HashMap();
        map.put("confirmList", confirmList);
        map.put("isolationList", isolationList);
        map.put("cureList", cureList);
        map.put("deadList", deadList);
        map.put("similarList", similarList);
        map.put("dateList", dateList);
        return map;
    }

    @RequestMapping("/toGlobal")
    public String toGlobal() {
        return "global";
    }
}
