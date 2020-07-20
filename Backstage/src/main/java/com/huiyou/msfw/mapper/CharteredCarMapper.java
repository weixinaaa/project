package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.CharteredCar;
import com.huiyou.msfw.model.CharteredCarExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface CharteredCarMapper extends BaseMapper<CharteredCar, CharteredCarExample, Long> {
}