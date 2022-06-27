package com.amcones.nocv.service.impl;

import com.amcones.nocv.dao.ChinaTotalMapper;
import com.amcones.nocv.entity.ChinaTotal;
import com.amcones.nocv.service.ChinaTotalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChinaTotalServiceImpl extends ServiceImpl<ChinaTotalMapper, ChinaTotal> implements ChinaTotalService {
    @Autowired
    private ChinaTotalMapper chinaTotalMapper;
    @Override
    public Integer maxID() {
        return chinaTotalMapper.maxID();
    }
}
