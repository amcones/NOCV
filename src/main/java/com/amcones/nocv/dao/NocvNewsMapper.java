package com.amcones.nocv.dao;

import com.amcones.nocv.entity.NocvNews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NocvNewsMapper extends BaseMapper<NocvNews> {
    @Select("select * from nocv_news order by create_time desc limit 5")
    List<NocvNews> listNewsLimit5();
}
