package com.amcones.nocv.service;

import com.amcones.nocv.entity.NocvNews;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface NocvNewsService extends IService<NocvNews> {
    List<NocvNews> listNewsLimit5();
}
