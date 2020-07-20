package com.huiyou.msfw.service;

import com.huiyou.msfw.model.CarType;
import com.huiyou.msfw.model.CarTypeExample;

import java.util.List;

public interface CarTypeService {

    List<CarType> selectByExample(CarTypeExample carTypeExample);
}

