package com.amcones.nocv.service;

import com.amcones.nocv.entity.LineTrend;
import com.amcones.nocv.entity.NocvData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IndexService extends IService<NocvData> {
    List<NocvData> listOrderByIdLimit();

    List<LineTrend> findSevenData();
}
