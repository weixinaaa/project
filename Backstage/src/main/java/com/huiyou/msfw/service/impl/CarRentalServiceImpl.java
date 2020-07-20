package com.huiyou.msfw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huiyou.msfw.mapper.CarRentalMapper;
import com.huiyou.msfw.model.CarRental;
import com.huiyou.msfw.model.CarRentalExample;
import com.huiyou.msfw.service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarRentalServiceImpl implements CarRentalService {
    @Autowired
    private CarRentalMapper carRentalMapper;

    @Override
    public Integer countByExample(CarRentalExample carRentalExample) {
        return carRentalMapper.countByExample(carRentalExample);
    }

    @Override
    public Map<String, Object> selCarRental(Integer pageNum, Integer pageSize,CarRental carRental) {
        if(pageNum != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<CarRental> carRentals = carRentalMapper.selCarRental(carRental);
        PageInfo<CarRental> carRentalPageInfo = new PageInfo<>(carRentals);
        long count = carRentalPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("count",count);
        map.put("data",carRentals);
        map.put("msg","成功");
        map.put("code",0);
        return map;
    }

    @Override
    public Integer InsertCarRental(CarRental carRental) {
        return carRentalMapper.insertSelective(carRental);
    }

    public Integer updateCarRental(CarRental carRental) {
        return carRentalMapper.updateCarRental(carRental);
    }


}
