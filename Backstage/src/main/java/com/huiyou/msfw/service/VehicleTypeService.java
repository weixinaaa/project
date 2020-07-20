package com.huiyou.msfw.service;

import com.huiyou.msfw.model.VehicleType;
import com.huiyou.msfw.model.VehicleTypeExample;

import java.util.List;

public interface VehicleTypeService {
    List<VehicleType> selectVehicleTypeByVehicleTypeExample(VehicleTypeExample vehicleTypeExample);
}
