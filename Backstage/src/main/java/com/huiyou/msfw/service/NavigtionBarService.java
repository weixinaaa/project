package com.huiyou.msfw.service;

import com.huiyou.msfw.model.NavigationBarType;
import com.huiyou.msfw.model.NavigationBarTypeExample;
import com.huiyou.msfw.model.NavigtionBar;
import com.huiyou.msfw.model.NavigtionBarExample;

import java.util.List;

public interface NavigtionBarService {
    List<NavigationBarType> selectNavigationBarType(NavigationBarTypeExample navigationBarTypeExample);

    List<NavigtionBar> selectnavigtionBar(NavigtionBarExample navigtionBarExample);
}
