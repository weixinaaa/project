package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.Position;
import com.huiyou.msfw.model.PositionExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface PositionMapper extends BaseMapper<Position, PositionExample, Long> {
}