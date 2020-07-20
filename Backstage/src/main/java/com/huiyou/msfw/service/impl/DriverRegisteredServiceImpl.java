package com.huiyou.msfw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huiyou.msfw.mapper.DriverRegisteredMapper;
import com.huiyou.msfw.model.CarRental;
import com.huiyou.msfw.model.DriverRegistered;
import com.huiyou.msfw.model.DriverRegisteredExample;
import com.huiyou.msfw.service.DriverRegisteredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverRegisteredServiceImpl implements DriverRegisteredService {
    @Autowired
    private DriverRegisteredMapper driverRegisteredMapper;

    @Override
    public Integer updateDriver(DriverRegistered driverRegistered) {
        return driverRegisteredMapper.updateByPrimaryKeySelective(driverRegistered);
    }

    @Override
    public Integer delDriver(Integer id) {
        return driverRegisteredMapper.delDriver(id);
    }

    @Override
    public Integer InsertDriverRegistered(DriverRegistered driverRegistered) {
        return driverRegisteredMapper.insertSelective(driverRegistered);
    }

    @Override
    public List<DriverRegistered> selectByDriverRegisteredExample(DriverRegisteredExample driverRegisteredExample) {
        return driverRegisteredMapper.selectByExample(driverRegisteredExample);
    }

    @Override
    public List<DriverRegistered> selDriverRegistered(DriverRegistered driverRegistered) {
        return driverRegisteredMapper.selDriverRegistered(driverRegistered);
    }

    @Override
    public Integer countByDriverRegisteredExample(DriverRegisteredExample driverRegisteredExample) {
        return driverRegisteredMapper.countByExample(driverRegisteredExample);
    }

    @Override
    public Map<String, Object> selDriver(Integer pageNum, Integer pageSize,DriverRegistered driverRegistered) {

        if(pageNum != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<DriverRegistered> drivers = driverRegisteredMapper.selDriverRegistered(driverRegistered);
        PageInfo<DriverRegistered> driverPageInfo = new PageInfo<>(drivers);
        long count = driverPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("count",count);
        map.put("data",drivers);
        map.put("msg","成功");
        map.put("code",0);
        return map;
    }
}
