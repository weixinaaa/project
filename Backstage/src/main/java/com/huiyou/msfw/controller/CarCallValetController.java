package com.huiyou.msfw.controller;

import com.huiyou.msfw.model.CarCallValet;
import com.huiyou.msfw.model.CarCallValetExample;
import com.huiyou.msfw.service.CarCallValetService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarCallValetController {


    @Autowired
    private CarCallValetService carCallValetService;

    SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");

    /**
     * 叫车/代驾信息具体信息查询
     * @param type
     * @return
     */
    @CrossOrigin
    @RequestMapping("/carCallValet/getByType")
    @ResponseBody
    public BaseResultEntity getByType(String type){
        CarCallValetExample carCallValetExample = new CarCallValetExample();
        carCallValetExample.or().andTypeEqualTo(type);
        List<CarCallValet> carCallValets = carCallValetService.getCarCallValetByExample(carCallValetExample);
        List<Map<String,Object>> mapList = new ArrayList<>();
        for(CarCallValet carCallValet:carCallValets){
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("id",carCallValet.getId());
            dataMap.put("departureTime",sdf.format(carCallValet.getDepartureTime()));//发车时间
            dataMap.put("useTime",carCallValet.getUseTime());//用时 （/H）
            dataMap.put("amountofmoney",carCallValet.getAmountofmoney());//金额
            mapList.add(dataMap);
        }
        if(carCallValets.size()>0){
            return ResultEntity.success("成功!",mapList);
        }else{
            return ResultEntity.fail();
        }

    }
}
