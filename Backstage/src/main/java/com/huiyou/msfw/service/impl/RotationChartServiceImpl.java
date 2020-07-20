package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.RotationChartMapper;
import com.huiyou.msfw.model.RotationChart;
import com.huiyou.msfw.model.RotationChartExample;
import com.huiyou.msfw.service.RotationChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotationChartServiceImpl implements RotationChartService {

    @Autowired
    private RotationChartMapper rotationChartMapper;

    @Override
    public List<RotationChart> selectRotationCharts(RotationChartExample rotationChartExample) {
        return rotationChartMapper.selectByExample(rotationChartExample);
    }
}
