package com.huiyou.msfw.service;

import com.huiyou.msfw.model.DriverRegistered;
import com.huiyou.msfw.model.DriverRegisteredExample;

import java.util.List;
import java.util.Map;

public interface DriverRegisteredService {

    Integer updateDriver(DriverRegistered driverRegistered);

    Integer delDriver(Integer id);

    Integer InsertDriverRegistered(DriverRegistered driverRegistered);

    List<DriverRegistered> selectByDriverRegisteredExample(DriverRegisteredExample driverRegisteredExample);

    /**
     * 司机查询，全查+地址
     * @param driverRegistered
     * @return
     */
    List<DriverRegistered> selDriverRegistered(DriverRegistered driverRegistered);


    Integer countByDriverRegisteredExample(DriverRegisteredExample driverRegisteredExample);

    Map<String, Object> selDriver(Integer pageNum, Integer pageSize,DriverRegistered driverRegistered);
}
