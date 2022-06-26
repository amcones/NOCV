package com.amcones.nocv.service.impl;

import com.amcones.nocv.dao.IndexMapper;
import com.amcones.nocv.entity.LineTrend;
import com.amcones.nocv.entity.NocvData;
import com.amcones.nocv.service.IndexService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl extends ServiceImpl<IndexMapper, NocvData> implements IndexService {
    @Autowired
    private IndexMapper indexMapper;
    @Override
    public List<NocvData> listOrderByIdLimit(){
        return indexMapper.listOrderByIdLimit();
    }

    @Override
    public List<LineTrend> findSevenData() {
        List<LineTrend> list = indexMapper.findSevenData();
        return list;
    }
}
