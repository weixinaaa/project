package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.CarTypeMapper;
import com.huiyou.msfw.model.CarType;
import com.huiyou.msfw.model.CarTypeExample;
import com.huiyou.msfw.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarTypeServiceImpl implements CarTypeService {
    @Autowired
    private CarTypeMapper carTypeMapper;

    @Override
    public List<CarType> selectByExample(CarTypeExample carTypeExample) {
        return carTypeMapper.selectByExample(carTypeExample);
    }

}
