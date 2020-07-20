package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.CarRental;
import com.huiyou.msfw.model.DriverRegistered;
import com.huiyou.msfw.model.DriverRegisteredExample;
import java.util.List;
import java.util.Map;

import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface DriverRegisteredMapper extends BaseMapper<DriverRegistered, DriverRegisteredExample, Long> {
    /**
     * 司机查询，全查+地址
     * @param driverRegistered
     * @return
     */
    List<DriverRegistered> selDriverRegistered(DriverRegistered driverRegistered);

    /**
     * 司机，删除
     * @param id
     * @return
     */
    @Update("DELETE FROM driver_registered WHERE id=#{id}")
    Integer delDriver(Integer id);

}