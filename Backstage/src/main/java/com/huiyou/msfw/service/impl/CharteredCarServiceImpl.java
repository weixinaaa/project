package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.CharteredCarMapper;
import com.huiyou.msfw.model.CharteredCar;
import com.huiyou.msfw.service.CharteredCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CharteredCarServiceImpl implements CharteredCarService {

    @Autowired
    private CharteredCarMapper charteredCarMapper;

    @Override
    public Integer insertCharteredCar(CharteredCar carCallValet) {
        return charteredCarMapper.insertSelective(carCallValet);
    }
}
