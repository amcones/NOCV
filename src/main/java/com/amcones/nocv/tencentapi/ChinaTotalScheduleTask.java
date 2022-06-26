package com.amcones.nocv.tencentapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amcones.nocv.entity.ChinaTotal;
import com.amcones.nocv.entity.NocvData;
import com.amcones.nocv.service.ChinaTotalService;
import com.amcones.nocv.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class ChinaTotalScheduleTask {
    @Autowired
    private ChinaTotalService chinaTotalService;
    @Autowired
    private IndexService indexService;

    @Scheduled(fixedDelay = 300000)
//    @Scheduled(cron="0 0 8,20")
    public void updateChinaTotalToDB() throws Exception {
        HttpUtils httpUtils = new HttpUtils();
        String rawdata = httpUtils.getData();

        JSONObject jsonObject = JSONObject.parseObject(rawdata);
        Object data = jsonObject.get("data");

        JSONObject jsonObjectData = JSONObject.parseObject(data.toString());
        Object chinaTotal = jsonObjectData.get("chinaTotal");
        Object lastUpdateTime = jsonObjectData.get("overseaLastUpdateTime");

        //中国整体数据
        JSONObject jsonObjectTotal = JSONObject.parseObject(chinaTotal.toString());
        Object total = jsonObjectTotal.get("total");

        //全国数据
        JSONObject totalData = JSONObject.parseObject(total.toString());
        Object confirm = totalData.get("confirm");
        Object input = totalData.get("input");
        Object severe = totalData.get("severe");
        Object heal = totalData.get("heal");
        Object dead = totalData.get("dead");
        Object suspect = totalData.get("suspect");

        ChinaTotal dataEntity = new ChinaTotal();
        dataEntity.setConfirm((Integer) confirm);
        dataEntity.setInput((Integer) input);
        dataEntity.setSevere((Integer) severe);
        dataEntity.setHeal((Integer) heal);
        dataEntity.setDead((Integer) dead);
        dataEntity.setSuspect((Integer) suspect);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        dataEntity.setUpdateTime(format.parse(String.valueOf(lastUpdateTime)));

        chinaTotalService.save(dataEntity);

        JSONArray areaTree=jsonObjectData.getJSONArray("areaTree");
        Object[] objects = areaTree.toArray();

        for (Object object : objects) {
            JSONObject jsonObject1 = JSONObject.parseObject(object.toString());
            Object name = jsonObject1.get("name");
            if(Objects.equals(name.toString(), "中国")){
                JSONObject jsonChinaObject = JSONObject.parseObject(object.toString());
                JSONArray children= jsonChinaObject.getJSONArray("children");
                Object[] objects1 = children.toArray();
                List<NocvData> list=new ArrayList<>();
                for (Object o : objects1) {
                    NocvData nocvData = new NocvData();
                    JSONObject jsonObject2 = JSONObject.parseObject(o.toString());
                    Object name1 = jsonObject2.get("name");
                    Object timePro = jsonObject2.get("lastUpdateTime");
                    Object total1 = jsonObject2.get("total");
                    JSONObject jsonObject3 = JSONObject.parseObject(total1.toString());
                    Object confirm1 = jsonObject3.get("confirm");

                    Object heal1 = jsonObject3.get("heal");
                    Object dead1 = jsonObject3.get("dead");

                    int curConfirm = Integer.parseInt(confirm1.toString()) - Integer.parseInt(heal1.toString()) - Integer.parseInt(dead1.toString());

                    //System.out.println(name1+":"+confirm1);
                    nocvData.setName(name1.toString());
                    nocvData.setValue(curConfirm);
                    if (timePro == null) {
                        nocvData.setUpdateTime(new Date());
                    } else {
                        nocvData.setUpdateTime(format.parse(String.valueOf(timePro)));
                    }
                    list.add(nocvData);
                }
                indexService.saveBatch(list);
            }
        }

    }
}
