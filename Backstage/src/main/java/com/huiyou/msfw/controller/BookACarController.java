package com.huiyou.msfw.controller;

import com.huiyou.msfw.model.*;
import com.huiyou.msfw.service.BookingVehicleService;
import com.huiyou.msfw.service.CarCallValetService;
import com.huiyou.msfw.service.CharteredCarService;
import com.huiyou.msfw.service.VehicleTypeService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BookACarController {


    @Autowired
    private BookingVehicleService bookingVehicleService;

    @Autowired
    private CharteredCarService charteredCarService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    /**
     * 车型
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/bookACar/getVehicleTypeAll")
    public BaseResultEntity getVehicleTypeAll(){
        VehicleTypeExample vehicleTypeExample = new VehicleTypeExample();
        vehicleTypeExample.or().andIsDelEqualTo(0);
        List<VehicleType> vehicleTypes = vehicleTypeService.selectVehicleTypeByVehicleTypeExample(vehicleTypeExample);
        if(vehicleTypes.size()>0){
            return ResultEntity.success("成功",vehicleTypes);
        }else{
            return ResultEntity.fail();
        }
    }

    /**
     * 预约车
     * @param bookingVehicle
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/bookACar/addBookingVehicle")
    public BaseResultEntity addBookingVehicle(BookingVehicle bookingVehicle,String time) throws ParseException {
        Date time1 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        bookingVehicle.setTimeOfAppointment(time1);
        Integer res = bookingVehicleService.insertBookingVehicle(bookingVehicle);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    /**
     * 包车
     * @param carCallValet
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/bookACar/addCharteredCar")
    public BaseResultEntity addBookingVehicle(String endTime2,CharteredCar carCallValet,String time) throws ParseException {
        Date time1 =  new SimpleDateFormat("yyyy-MM-dd").parse(time);
        Date endTime1 =  new SimpleDateFormat("yyyy-MM-dd").parse(endTime2);
        carCallValet.setTimeOfAppointment(time1);
        carCallValet.setEndTime(endTime1);
        Integer res = charteredCarService.insertCharteredCar(carCallValet);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

}
