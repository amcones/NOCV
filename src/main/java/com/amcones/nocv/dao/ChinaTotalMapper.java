package com.amcones.nocv.dao;

import com.amcones.nocv.entity.ChinaTotal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

public interface ChinaTotalMapper extends BaseMapper<ChinaTotal> {

    @Select("select max(id) from china_total")
    Integer maxID();
}
