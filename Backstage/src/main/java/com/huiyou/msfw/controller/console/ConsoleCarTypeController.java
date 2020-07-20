package com.huiyou.msfw.controller.console;

import com.huiyou.msfw.model.CarType;
import com.huiyou.msfw.model.CarTypeExample;
import com.huiyou.msfw.model.PCA;
import com.huiyou.msfw.service.CarTypeService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/console/carType")
public class ConsoleCarTypeController {
    @Resource
    private CarTypeService carTypeService;

    /**
     * 查询车型
     * @return
     */
    @RequestMapping("selCarType")
    @ResponseBody
    public BaseResultEntity selCarType(){
        CarTypeExample carTypeExample = new CarTypeExample();

        List<CarType> carTypes = carTypeService.selectByExample(carTypeExample);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (carTypes.size()>0){
            for (CarType carType : carTypes) {
                Map<String, Object> map = new HashMap<>();
                map.put("id",carType.getId());
                map.put("carType", carType.getName());
                mapList.add(map);
            }
            return PageResultEntity.success("成功!" , carTypes.size() , mapList);
        }
        return PageResultEntity.fail("查询无果");
    }
}
