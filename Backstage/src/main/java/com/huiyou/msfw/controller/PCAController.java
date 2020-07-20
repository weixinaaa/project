package com.huiyou.msfw.controller;

import com.huiyou.msfw.model.PCA;
import com.huiyou.msfw.service.PCAService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("pca")
public class PCAController {
    @Resource
    private PCAService pcaService;

    /**
     * 查询省
     */
    @CrossOrigin
    @RequestMapping("selPro")
    @ResponseBody
    public BaseResultEntity selPro() {
        List<PCA> selPro = pcaService.selPro();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (PCA pro : selPro) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", pro.getId());
            map.put("proName", pro.getName());
            mapList.add(map);
        }
        return PageResultEntity.success("成功!" , selPro.size() , mapList);

    }
    /**
     * 查询市
     * @param pid
     * @return
     */
    @CrossOrigin
    @RequestMapping("selCity")
    @ResponseBody
    public BaseResultEntity selCity(Integer pid) {
        List<PCA> selCity = pcaService.selCity(pid);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (selCity.size()>0){
            for (PCA city : selCity) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", city.getId());
                map.put("pid", city.getPid());
                map.put("cityName", city.getName());
                mapList.add(map);
            }
            return PageResultEntity.success("成功!" , selCity.size() , mapList);

        }
        return PageResultEntity.fail();
    }
    /**
     * 查询区域
     * @param pid
     * @return
     */
    @CrossOrigin
    @RequestMapping("selArea")
    @ResponseBody
    public BaseResultEntity selArea(Integer pid) {
        List<PCA> selArea = pcaService.selArea(pid);
        List<Map<String, Object>> mapList = new ArrayList<>();
            for (PCA area : selArea) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", area.getId());
                map.put("pid", area.getPid());
                map.put("areaName", area.getName());
                mapList.add(map);
            }
            return PageResultEntity.success("成功!", selArea.size(), mapList);

    }
}
