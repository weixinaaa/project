package com.huiyou.msfw.service;


import com.huiyou.msfw.model.DriverService;
import com.huiyou.msfw.model.DriverServiceExample;
import com.huiyou.msfw.model.UserIncome;

import java.util.List;

public interface DriverServiceService {
    List<DriverService> selectDriverServiceAllByDriverServiceExample(DriverServiceExample driverServiceExample);

    Integer countDriverServiceByDriverServiceExample(DriverServiceExample driverServiceExample);

    DriverService getDriverServiceById(Long id);

    Integer updateDriverServiceByDriverService(DriverService driverService);

    Integer insertDriverServiceByDriverService(DriverService driverService);
}
