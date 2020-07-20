package com.huiyou.msfw.controller;

import com.huiyou.msfw.mapper.AdvertisementMapper;
import com.huiyou.msfw.model.Advertisement;
import com.huiyou.msfw.model.AdvertisementExample;
import com.huiyou.msfw.service.AdvertisementService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 查询广告详细
     * @param id
     * @return
     */
    @CrossOrigin
    @RequestMapping("AdverTisement/getAdvertisementById")
    @ResponseBody
    public Map<String,Object> getAdvertisementById(Long id){
        Map<String,Object> returnMap = new HashMap<>();
        AdvertisementExample advertisementExample =new AdvertisementExample();
        advertisementExample.or().andIsDelEqualTo(0).andIdEqualTo(id);
        List<Advertisement> advertisements= advertisementService.selectAdvertisment(advertisementExample);
        Map<String,Object> dataMap = new HashMap<>();
        if(advertisements.size()>0){
            dataMap.put("id",advertisements.get(0).getId());//ID
            dataMap.put("content",advertisements.get(0).getContent());//内容
            dataMap.put("title",advertisements.get(0).getTitle());//标题
            dataMap.put("createTime",sdf.format(advertisements.get(0).getCreateTime()));//创建时间
            dataMap.put("clicks",advertisements.get(0).getClicks());//点击量
            advertisements.get(0).setClicks(advertisements.get(0).getClicks()+1);
            advertisementService.updateAdvertisementByAdvertisement(advertisements.get(0));
        }else{
            returnMap.put("code",-1);
            returnMap.put("msg","查询详细信息失败！");
            return returnMap;
        }
        returnMap.put("code",-1);
        returnMap.put("msg","成功！");
        returnMap.put("data",dataMap);
        return returnMap;
    }
}
