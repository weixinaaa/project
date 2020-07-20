package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.VehicleType;
import com.huiyou.msfw.model.VehicleTypeExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface VehicleTypeMapper extends BaseMapper<VehicleType, VehicleTypeExample, Long> {
}