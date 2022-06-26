package com.amcones.nocv.service.impl;

import com.amcones.nocv.entity.Menu;
import com.amcones.nocv.dao.MenuMapper;
import com.amcones.nocv.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
