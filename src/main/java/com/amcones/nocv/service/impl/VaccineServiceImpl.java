package com.amcones.nocv.service.impl;

import com.amcones.nocv.dao.VaccineMapper;
import com.amcones.nocv.entity.Vaccine;
import com.amcones.nocv.service.VaccineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class VaccineServiceImpl extends ServiceImpl<VaccineMapper, Vaccine> implements VaccineService {
}
