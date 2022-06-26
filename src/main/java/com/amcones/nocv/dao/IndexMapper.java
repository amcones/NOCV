package com.amcones.nocv.dao;

import com.amcones.nocv.entity.LineTrend;
import com.amcones.nocv.entity.NocvData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IndexMapper extends BaseMapper<NocvData> {

    @Select("select * from nocv_data order by id desc limit 34")
    List<NocvData> listOrderByIdLimit();

    @Select("select * from line_trend order by create_time desc limit 7")
    List<LineTrend> findSevenData();
}
