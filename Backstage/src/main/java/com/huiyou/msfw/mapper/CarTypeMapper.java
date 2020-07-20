package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.CarType;
import com.huiyou.msfw.model.CarTypeExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface CarTypeMapper extends BaseMapper<CarType, CarTypeExample, Long> {
}