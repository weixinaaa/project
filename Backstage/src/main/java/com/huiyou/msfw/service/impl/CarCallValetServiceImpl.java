package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.CarCallValetMapper;
import com.huiyou.msfw.model.CarCallValet;
import com.huiyou.msfw.model.CarCallValetExample;
import com.huiyou.msfw.service.CarCallValetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarCallValetServiceImpl implements CarCallValetService {

    @Autowired
    private CarCallValetMapper carCallValetMapper;

    @Override
    public List<CarCallValet> getCarCallValetByExample(CarCallValetExample carCallValetExample) {
        return carCallValetMapper.selectByExample(carCallValetExample);
    }
}
