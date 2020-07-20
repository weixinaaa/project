package com.huiyou.msfw.controller;

import com.huiyou.msfw.model.*;
import com.huiyou.msfw.service.DriverRegisteredService;
import com.huiyou.msfw.service.SysUserService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class DriverRegisteredController {
    @Resource
    private DriverRegisteredService driverRegisteredService;

    @Autowired
    private SysUserService sysUserService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    /**
     * 司机查询
     * @param pageNo
     * @param pageSize
     * @param driverRegistered
     * @return
     * @throws ParseException
     */
    @RequestMapping("/driverRegistered/selDriver")
    @ResponseBody
    public BaseResultEntity selDriver(HttpSession session, Integer pageNo, Integer pageSize, DriverRegistered driverRegistered) throws ParseException {
        Long sysUserId = (Long) session.getAttribute("sysUserId");
        SysUser sysUser = sysUserService.getUserId(sysUserId);

        DriverRegisteredExample driverRegisteredExample = new DriverRegisteredExample();
        driverRegisteredExample.setIsPage(1);
        driverRegisteredExample.setPageNo(pageNo);
        driverRegisteredExample.setPageSize(pageSize);

        List<DriverRegistered> driverRegisteredList = driverRegisteredService.selDriverRegistered(driverRegistered);
        Integer count = driverRegisteredService.countByDriverRegisteredExample(driverRegisteredExample);
        if(count>0){
            List<Map<String,Object>> mapList = new ArrayList<>();
            for(DriverRegistered selDriver:driverRegisteredList) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", selDriver.getId());
                map.put("driverName", selDriver.getDriverName());
                if (sysUser.getRoleId()==1){
                    map.put("contactTel",selDriver.getContactTel());
                }else {
                    map.put("contactTel",selDriver.getContactTel().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
                }
                map.put("isHave", selDriver.getIsHave());
                map.put("isDel", selDriver.getIsDel());
                map.put("note", selDriver.getNote());
                map.put("address", selDriver.getProName()+selDriver.getCityName()+selDriver.getAreaName());

                map.put("createTime", sdf.format(selDriver.getCreateTime()));
                map.put("updateTime", sdf.format(selDriver.getUpdateTime()));
                mapList.add(map);
            }
            return PageResultEntity.success("成功！",count,mapList);
        }
        return PageResultEntity.fail();
    }

    /**
     * 司机注册
     * @param driverRegistered
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/driverRegistered/register",method = POST)
    @ResponseBody
    public BaseResultEntity register(DriverRegistered driverRegistered){
        System.out.println(driverRegistered.getDriverName());
        String contactTel = driverRegistered.getContactTel();
        if (contactTel==null){
            return ResultEntity.fail("请输入手机号");
        }
        DriverRegisteredExample driverRegisteredExample = new DriverRegisteredExample();
        driverRegisteredExample.or().andContactTelLike(contactTel);
        List<DriverRegistered> driverRegisteredList = driverRegisteredService.selectByDriverRegisteredExample(driverRegisteredExample);
        if(driverRegisteredList.size()>0){
            return ResultEntity.fail("该手机号已被注册！");
        }

        driverRegistered.setUpdateTime(new Date());
        driverRegistered.setCreateTime(new Date());
        Integer res = driverRegisteredService.InsertDriverRegistered(driverRegistered);

        List<Map<String,Object>> mapList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", driverRegistered.getId());
        map.put("driverName", driverRegistered.getDriverName());
        map.put("contactTel", driverRegistered.getContactTel());
        map.put("isHave", driverRegistered.getIsHave());
        map.put("isDel", driverRegistered.getIsDel());
        map.put("note", driverRegistered.getNote());
        map.put("proId", driverRegistered.getProId());
        map.put("cityId", driverRegistered.getCityId());
        map.put("areaId", driverRegistered.getAreaId());

        map.put("createTime", sdf.format(driverRegistered.getCreateTime()));
        map.put("updateTime", sdf.format(driverRegistered.getUpdateTime()));
        mapList.add(map);


        if(res >0){
            return PageResultEntity.success("成功！",res,mapList );
        }else{
            return ResultEntity.fail();
        }
    }

    /**
     * 司机注销
     * @param id
     * @return
     */
    @CrossOrigin
    @RequestMapping("/driverRegistered/delDriver")
    @ResponseBody
    public BaseResultEntity delDriver(Integer id){
        Integer isDel = driverRegisteredService.delDriver(id);
        if (isDel>0){
            return ResultEntity.success();
        }
        return  ResultEntity.fail();
    }
}
