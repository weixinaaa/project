package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.DriverService;
import com.huiyou.msfw.model.DriverServiceExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface DriverServiceMapper extends BaseMapper<DriverService, DriverServiceExample, Long> {
}