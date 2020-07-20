package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.DriverServiceMapper;
import com.huiyou.msfw.model.DriverService;
import com.huiyou.msfw.model.DriverServiceExample;
import com.huiyou.msfw.service.DriverServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceServiceImpl implements DriverServiceService {

    @Autowired
    private DriverServiceMapper driverServiceMapper;

    @Override
    public List<DriverService> selectDriverServiceAllByDriverServiceExample(DriverServiceExample driverServiceExample) {
        return driverServiceMapper.selectByExample(driverServiceExample);
    }

    @Override
    public Integer countDriverServiceByDriverServiceExample(DriverServiceExample driverServiceExample) {
        return driverServiceMapper.countByExample(driverServiceExample);
    }

    @Override
    public DriverService getDriverServiceById(Long id) {
        return driverServiceMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateDriverServiceByDriverService(DriverService driverService) {
        return driverServiceMapper.updateByPrimaryKeySelective(driverService);
    }

    @Override
    public Integer insertDriverServiceByDriverService(DriverService driverService) {
        return driverServiceMapper.insertSelective(driverService);
    }
}
