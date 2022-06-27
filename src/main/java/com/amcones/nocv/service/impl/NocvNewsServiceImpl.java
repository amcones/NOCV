package com.amcones.nocv.service.impl;

import com.amcones.nocv.dao.NocvNewsMapper;
import com.amcones.nocv.entity.NocvNews;
import com.amcones.nocv.service.NocvNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NocvNewsServiceImpl extends ServiceImpl<NocvNewsMapper, NocvNews> implements NocvNewsService {
    @Autowired
    private NocvNewsMapper nocvNewsMapper;
    @Override
    public List<NocvNews> listNewsLimit5() {
        return nocvNewsMapper.listNewsLimit5();
    }
}
