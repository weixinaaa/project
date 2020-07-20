package com.huiyou.msfw.controller.console;

import com.huiyou.msfw.mapper.CarRentalMapper;
import com.huiyou.msfw.mapper.CarTypeMapper;
import com.huiyou.msfw.model.CarRental;
import com.huiyou.msfw.model.CarRentalExample;
import com.huiyou.msfw.model.Role;
import com.huiyou.msfw.model.SysUser;
import com.huiyou.msfw.service.CarRentalService;
import com.huiyou.msfw.service.UserService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/console")
public class ConsoleCarRentalController {
    @Autowired
    private CarRentalService carRentalService;


    @Autowired
    private CarRentalMapper carRentalMapper;

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

    /**
     * 添加车辆
     * @param carRental
     * @return
     */
    @ResponseBody
    @RequestMapping("/carRental/addCarRental")
    @CrossOrigin
    public BaseResultEntity addCarRental(CarRental carRental){
        carRental.setUpdateTime(new Date());
        carRental.setCreateTime(new Date());
        Integer res = carRentalService.InsertCarRental(carRental);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    /**
     * 跳转到车辆修改页面
     * @param id 角色ID
     * @param model
     * @return
     */
    @RequestMapping("/carRental/toEdit")
    public String toEdit(Long id, Model model){
        CarRental carRental = new CarRental();
        List<CarRental> carRentalList = carRentalMapper.selCarRentalById(id);
        if (carRentalList.size()>0){
            carRental = carRentalList.get(0);
        }
        model.addAttribute("carRental",carRental);
        model.addAttribute("carRentalList",carRentalList);
        return "console/CarRentalEdit";
    }

    /**
     * 修改
     * @param carRental
     */
    @RequestMapping("/carRental/editCarRental")
    @ResponseBody
    public BaseResultEntity editCarRental(CarRental carRental){
        carRental.setUpdateTime(new Date());
        if (carRental.getCityId()==null||carRental.getAreaId()==null){
            return ResultEntity.fail("请选择城市/区域");
        }
        Integer res = carRentalService.updateCarRental(carRental);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    /**
     * 逻辑删除
     * @param id
     */
    @RequestMapping("/carRental/delCarRental")
    @ResponseBody
    public void delCarRental(@RequestParam(value = "id") Integer[] id){
        carRentalMapper.delCarRental(id);
    }
}
