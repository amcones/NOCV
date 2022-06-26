package com.amcones.nocv.service.impl;

import com.amcones.nocv.dao.HealthClockMapper;
import com.amcones.nocv.entity.HealthClock;
import com.amcones.nocv.service.HealthClockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
@Service
public class HealthClockServiceImpl extends ServiceImpl<HealthClockMapper, HealthClock> implements HealthClockService {
}
