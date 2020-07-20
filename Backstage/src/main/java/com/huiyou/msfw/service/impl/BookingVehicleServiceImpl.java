package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.BookingVehicleMapper;
import com.huiyou.msfw.model.BookingVehicle;
import com.huiyou.msfw.service.BookingVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingVehicleServiceImpl implements  BookingVehicleService{

    @Autowired
    private BookingVehicleMapper bookingVehicleMapper;

    @Override
    public Integer insertBookingVehicle(BookingVehicle bookingVehicle) {
        return bookingVehicleMapper.insertSelective(bookingVehicle);
    }
}
