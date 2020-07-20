package com.huiyou.msfw.service;

import com.huiyou.msfw.model.RotationChart;
import com.huiyou.msfw.model.RotationChartExample;

import java.util.List;

public interface RotationChartService {
    List<RotationChart> selectRotationCharts(RotationChartExample rotationChartExample);
}
