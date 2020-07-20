package com.huiyou.msfw.controller;

import com.huiyou.msfw.model.*;
import com.huiyou.msfw.service.CarRentalService;
import com.huiyou.msfw.service.UserService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarRentalController {
    @Autowired
    private CarRentalService carRentalService;

    @Autowired
    private UserService userService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 租车查询
     * @param carRental
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("/carRental/getCarRental")
    @CrossOrigin
    public Map<String,Object> getCarRental(CarRental carRental,Integer page, Integer limit){
        return carRentalService.selCarRental(page, limit, carRental);
    }

}
