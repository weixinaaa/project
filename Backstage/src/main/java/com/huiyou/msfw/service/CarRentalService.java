package com.huiyou.msfw.service;

import com.huiyou.msfw.model.CarRental;
import com.huiyou.msfw.model.CarRentalExample;

import java.util.Map;

public interface CarRentalService {
    Integer countByExample(CarRentalExample carRentalExample);

    /**
     * 查询车辆
     * @param pageNum 页数
     * @param pageSize 条数
     * @param carRental
     * @return
     */
    Map<String,Object> selCarRental(Integer pageNum, Integer pageSize,CarRental carRental);

    Integer InsertCarRental(CarRental carRental);

    Integer updateCarRental(CarRental carRental);



}
