package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.VehicleTypeMapper;
import com.huiyou.msfw.model.VehicleType;
import com.huiyou.msfw.model.VehicleTypeExample;
import com.huiyou.msfw.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Autowired
    private VehicleTypeMapper vehicleTypeMapper;

    @Override
    public List<VehicleType> selectVehicleTypeByVehicleTypeExample(VehicleTypeExample vehicleTypeExample) {
        return vehicleTypeMapper.selectByExample(vehicleTypeExample);
    }
}
