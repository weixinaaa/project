package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.NavigationBarTypeMapper;
import com.huiyou.msfw.mapper.NavigtionBarMapper;
import com.huiyou.msfw.model.NavigationBarType;
import com.huiyou.msfw.model.NavigationBarTypeExample;
import com.huiyou.msfw.model.NavigtionBar;
import com.huiyou.msfw.model.NavigtionBarExample;
import com.huiyou.msfw.service.NavigtionBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigtionBarServiceImpl implements NavigtionBarService {

    @Autowired
    private NavigationBarTypeMapper navigationBarTypeMapper;
    @Autowired
    private NavigtionBarMapper navigtionBarMapper;

    @Override
    public List<NavigationBarType> selectNavigationBarType(NavigationBarTypeExample navigationBarTypeExample) {
        return navigationBarTypeMapper.selectByExample(navigationBarTypeExample);
    }

    @Override
    public List<NavigtionBar> selectnavigtionBar(NavigtionBarExample navigtionBarExample) {
        return navigtionBarMapper.selectByExample(navigtionBarExample);
    }
}
