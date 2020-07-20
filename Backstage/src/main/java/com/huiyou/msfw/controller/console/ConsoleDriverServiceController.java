package com.huiyou.msfw.controller.console;

import com.huiyou.msfw.model.*;
import com.huiyou.msfw.service.DriverServiceService;
import com.huiyou.msfw.service.SysUserService;
import com.huiyou.msfw.service.UserService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/console")
public class ConsoleDriverServiceController {

    @Autowired
    private DriverServiceService driverServiceService;

    @Autowired
    private SysUserService sysUserService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/DriverService/getDriverServiceAll")
    @ResponseBody
    public BaseResultEntity getDriverServiceAll(HttpSession session,Integer pageNo, Integer pageSize,Integer type,String userName,String tel){
        Long sysUserId = (Long) session.getAttribute("sysUserId");
        SysUser sysUser = sysUserService.getUserId(sysUserId);

        DriverServiceExample driverServiceExample = new DriverServiceExample();
        driverServiceExample.setIsPage(1);
        driverServiceExample.setPageNo(pageNo);
        driverServiceExample.setPageSize(pageSize);

        DriverServiceExample.Criteria criteria = driverServiceExample.or().andIsDelEqualTo(0).andTypeEqualTo(type);
        if(userName!=null && userName!=""){
            criteria.andNameLike("%"+userName+"%");
        }

        if(tel!=null && tel!=""){
            criteria.andTelLike("%"+tel+"%");
        }

        List<DriverService> driverServices = driverServiceService.selectDriverServiceAllByDriverServiceExample(driverServiceExample);
        Integer count = driverServiceService.countDriverServiceByDriverServiceExample(driverServiceExample);
        List<Map<String,Object>> mapList = new ArrayList<>();
        for(DriverService driverService:driverServices){
            Map<String,Object> map = new HashMap<>();
            map.put("id",driverService.getId());
            map.put("licensePlate",driverService.getLicensePlate());
            map.put("createTime",sdf.format(driverService.getCreateTime()));
            map.put("updateTime",sdf.format(driverService.getUpdateTime()));
            map.put("name",driverService.getName());
            if (sysUser.getRoleId()==1){
                map.put("tel",driverService.getTel());
            }else {
                map.put("tel",driverService.getTel().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
            }
            mapList.add(map);
        }
        return PageResultEntity.success("成功！",count,mapList);
    }

    @RequestMapping("/DriverService/toEdit")
    public String toEdit(Long id,HttpSession session){
        DriverService driverService = driverServiceService.getDriverServiceById(id);
        session.setAttribute("driverService",driverService);
        return "/console/DriverServiceEdit";
    }

    @RequestMapping("/DriverService/editDriverService")
    @ResponseBody
    public BaseResultEntity editDriverServiceAll(DriverService driverService){
        driverService.setUpdateTime(new Date());
        Integer res = driverServiceService.updateDriverServiceByDriverService(driverService);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    @RequestMapping("/DriverService/toAddDriverService")
    public String toAddDriverService(int type){
        if(type == 0){
            return "/console/UserServiceAdd";
        }else{
            return "/console/DriverServiceAdd";
        }
    }

    @RequestMapping("/DriverService/addDriverService")
    @ResponseBody
    public BaseResultEntity addDriverService(DriverService driverService){
        driverService.setUpdateTime(new Date());
        driverService.setCreateTime(new Date());
        driverService.setIsDel(0);
        Integer res = driverServiceService.insertDriverServiceByDriverService(driverService);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }
}
