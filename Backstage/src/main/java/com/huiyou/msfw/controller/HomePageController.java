package com.huiyou.msfw.controller;

import com.huiyou.msfw.mapper.AdvertisementMapper;
import com.huiyou.msfw.model.*;
import com.huiyou.msfw.service.AdvertisementService;
import com.huiyou.msfw.service.NavigtionBarService;
import com.huiyou.msfw.service.RotationChartService;
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
public class HomePageController {

    @Autowired
    private NavigtionBarService navigtionBarService;

    @Autowired
    private RotationChartService rotationChartService;

    @Autowired
    private AdvertisementService advertisementService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @CrossOrigin
    @RequestMapping("/HomePage/getAll")
    @ResponseBody
    public Map<String,Object> getAll(Integer pageNo,Integer pageSize){
        Map<String,Object> returnMap = new HashMap<>();
        Map<String,Object> dataMap = new HashMap<>();

        //导航栏信息
        if(getNavigtionBar(pageNo,pageSize)!=null){
            dataMap.put("navigtionBar",getNavigtionBar(pageNo,pageSize));
        }else{
            returnMap.put("code",-1);
            returnMap.put("msg","查询导航栏信息失败！");
            return returnMap;
        }

        //轮播图信息
        if(getRotationChart()!=null){
            dataMap.put("rotationChart",getRotationChart());
        }else{
            returnMap.put("code",-1);
            returnMap.put("msg","查询轮播图信息失败！");
            return returnMap;
        }

        //广告信息
        if(getAdvertisment(pageNo,pageSize)!=null){
            dataMap.put("advertisment",getAdvertisment(pageNo,pageSize));
        }else{
            dataMap.put("advertisment",null);
        }


        returnMap.put("code",0);
        returnMap.put("msg","查询成功！");
        returnMap.put("data",dataMap);
        return returnMap;
    }


    //导航栏信息查询
    public List<Map<String,Object>> getNavigtionBar(Integer pageNo,Integer pageSize){
        NavigationBarTypeExample navigationBarTypeExample = new NavigationBarTypeExample();
        navigationBarTypeExample.or().andIsDelEqualTo(0);
        List<NavigationBarType> navigationBarTypes = navigtionBarService.selectNavigationBarType(navigationBarTypeExample);//导航栏类别查询
        if(navigationBarTypes.size()<1 ){
            return null;
        }
        List<Map<String,Object>> dataList  = new ArrayList<>();
        for(NavigationBarType navigationBarType : navigationBarTypes){

            Map<String,Object> data = new HashMap<>();
            data.put("title",navigationBarType.getName());//标题
            List<Map<String,Object>> mapList = new ArrayList<>();
            NavigtionBarExample navigtionBarExample = new NavigtionBarExample();
            navigtionBarExample.setIsPage(1);
            navigtionBarExample.setPageNo(pageNo);
            navigtionBarExample.setPageSize(pageSize);
            navigtionBarExample.or().andIsDelEqualTo(0).andTypeIdEqualTo(navigationBarType.getId());
            List<NavigtionBar> navigtionBars = navigtionBarService.selectnavigtionBar(navigtionBarExample);//导航栏查询
            for(NavigtionBar navigtionBar :navigtionBars){
                Map<String,Object> navigtionBarMap = new HashMap<>();
                navigtionBarMap.put("id",navigtionBar.getId());//导航栏ID
                //导航栏名称
                if(navigtionBar.getName()!=null){
                    navigtionBarMap.put("name",navigtionBar.getName());
                }else{
                    navigtionBarMap.put("name","");
                }
                //导航栏图标
                if(navigtionBar.getImg()!=null){
                    navigtionBarMap.put("img",navigtionBar.getImg());
                }else{
                    navigtionBarMap.put("img","");
                }
                //导航栏链接网页名称
                if(navigtionBar.getUrlName()!=null){
                    navigtionBarMap.put("urlName",navigtionBar.getUrlName());
                }else{
                    navigtionBarMap.put("urlName","");
                }
                mapList.add(navigtionBarMap);
            }
            data.put("navigtionBarList",mapList);
            dataList.add(data);
        }
        return dataList;
    }


    //轮播图信息获取
    public List<Map<String,Object>> getRotationChart(){
        List<Map<String,Object>> dataList  = new ArrayList<>();
        RotationChartExample rotationChartExample = new RotationChartExample();
        RotationChartExample rotationChartExample1 = new RotationChartExample();
        rotationChartExample.or().andIsDelEqualTo(0);
        List<RotationChart> rotationCharts = rotationChartService.selectRotationCharts(rotationChartExample1);
        if(rotationCharts.size()>0){
            Map<String,Object> data = new HashMap<>();
            for(RotationChart rotationChart:rotationCharts){
                data.put("id",rotationChart.getId());//轮播图ID
                data.put("img",rotationChart.getImg());//轮播图图片地址
                data.put("title",rotationChart.getTitle());//轮播图标题
                dataList.add(data);
            }
        }else{
            return null;
        }
        return dataList;
    }

    /**
     * 广告列表查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Map<String,Object>> getAdvertisment(Integer pageNo,Integer pageSize){
        List<Map<String,Object>> dataList  = new ArrayList<>();
        AdvertisementExample advertisementExample = new AdvertisementExample();
        advertisementExample.setIsPage(1);
        advertisementExample.setPageNo(pageNo);
        advertisementExample.setPageSize(pageSize);
        advertisementExample.or().andIsDelEqualTo(0);
        List<Advertisement> advertisements = advertisementService.selectAdvertisment(advertisementExample);
        if(advertisements.size()>0){
            for(Advertisement advertisement:advertisements){
                Map<String,Object> data = new HashMap<>();
                data.put("id",advertisement.getId());//ID
                data.put("title",advertisement.getTitle());//标题
                data.put("img",advertisement.getCoverMap());//封面图
                data.put("briefIntroduction",advertisement.getBriefIntroduction());//简介
                data.put("createTime",sdf.format(advertisement.getCreateTime()));//创建时间
                data.put("clicks",advertisement.getClicks());//点击量
                dataList.add(data);
            }
        }else{
            return null;
        }
        return dataList;
    }
}
