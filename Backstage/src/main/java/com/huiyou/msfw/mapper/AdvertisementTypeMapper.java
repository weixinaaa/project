package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.AdvertisementType;
import com.huiyou.msfw.model.AdvertisementTypeExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface AdvertisementTypeMapper extends BaseMapper<AdvertisementType, AdvertisementTypeExample, Long> {
    int delete(@Param("id") Long id);
}