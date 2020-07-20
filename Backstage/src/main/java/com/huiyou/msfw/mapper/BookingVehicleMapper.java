package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.BookingVehicle;
import com.huiyou.msfw.model.BookingVehicleExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BookingVehicleMapper extends BaseMapper<BookingVehicle, BookingVehicleExample, Long> {
}