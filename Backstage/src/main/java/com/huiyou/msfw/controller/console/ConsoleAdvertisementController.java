package com.huiyou.msfw.controller.console;

import com.alibaba.druid.sql.PagerUtils;
import com.huiyou.msfw.model.*;
import com.huiyou.msfw.service.AdvertisementService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.collections.map.HashedMap;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.handler.HandlerMethodMappingNamingStrategy;

import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.net.URLDecoder;
import java.security.Signature;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/console")
@Controller
public class ConsoleAdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 广告类型全查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/advertisement/getTypeAll")
    public BaseResultEntity getAll(Integer pageNo, Integer pageSize,String typeName){
        AdvertisementTypeExample advertisementExample = new AdvertisementTypeExample();
        advertisementExample.setPageSize(pageSize);
        advertisementExample.setPageNo(pageNo);
        advertisementExample.setIsPage(1);
        if(typeName!=null){
            advertisementExample.or().andIsDelEqualTo(0).andTypeNameLike("%"+typeName+"%");
        }else{
            advertisementExample.or().andIsDelEqualTo(0);
        }

        List<AdvertisementType> advertisements = advertisementService.selectAdvertismentType(advertisementExample);
        Integer count = advertisementService.countAdverTismentType(advertisementExample);
            return PageResultEntity.success("成功！",count,advertisements);
    }

    /**
     * 前往广告修改页面
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/advertisement/toEditAdvertisementType")
    public String toEditAdvertisementType(Long id,HttpSession session){
        AdvertisementType advertisementType = advertisementService.selectAdvertismentTypeByTypeId(id);
        if(advertisementType!=null){
            session.setAttribute("advertisementType",advertisementType);
        }
        return "console/AdvertisementTypeEdit";
    }

    /**
     * 修改广告类型
     * @param id
     * @param typeName
     * @return
     */
    @ResponseBody
    @RequestMapping("/advertisement/editType")
    public BaseResultEntity editType(Long id,String typeName){
        AdvertisementType advertisementType = new AdvertisementType();
        advertisementType.setId(id);
        advertisementType.setTypeName(typeName);
        Integer res = advertisementService.updateAdverTisementTypeByAdvertisementType(advertisementType);
        if(res>0){
            return ResultEntity.success();
        }
        return ResultEntity.fail("修改失败！");
    }

    /**
     * 删除广告类型
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/advertisement/deleteAdvertisementType")
    public BaseResultEntity deleteAdvertisementType(Long id){
        AdvertisementType advertisementType = new AdvertisementType();
        advertisementType.setId(id);
        advertisementType.setIsDel(1);
        int res = advertisementService.updateAdverTisementTypeByAdvertisementType(advertisementType);
        if (res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    /**
     * 添加广告类型
     * @param typeName
     * @return
     */
    @ResponseBody
    @RequestMapping("/advertisement/addAdvertisementType")
    public BaseResultEntity addAdvertisementType(String typeName){
        AdvertisementType advertisementType = new AdvertisementType();
        advertisementType.setTypeName(typeName);
        advertisementType.setIsDel(0);
        int res = advertisementService.insertAdvertisementTypeByAdvertisementType(advertisementType);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail("添加失败");
        }
    }


    /**
     * 获取广告列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/advertisement/getAdvertisementAll")
    public BaseResultEntity getAdvertisementAll(Integer pageNo,Integer pageSize,String title,String createTime,String endTime) throws ParseException {
        AdvertisementExample advertisementExample = new AdvertisementExample();
        advertisementExample.setIsPage(1);
        advertisementExample.setPageNo(pageNo);
        advertisementExample.setPageSize(pageSize);
        AdvertisementExample.Criteria criteria = advertisementExample.or().andIsDelEqualTo(0);
        if(title!=null && title!=""){
            criteria.andTitleLike("%"+title+"%");
        }
        if(createTime!=null && createTime!=""){
            criteria.andCreateTimeGreaterThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime));
        }

        if(endTime!=null && endTime!=""){
            criteria.andCreateTimeLessThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime));
        }

        try {
            Integer count = advertisementService.countAdverTisment(advertisementExample);
            List<Advertisement> advertisements = advertisementService.selectAdvertisment(advertisementExample);
            List<Map<String,Object>> mapList = new ArrayList<>();
                for(Advertisement advertisement:advertisements){
                    Map<String,Object> map = new HashMap<>();
                    map.put("id",advertisement.getId());
                    map.put("title",advertisement.getTitle());
                    AdvertisementType advertisementType = advertisementService.selectAdvertismentTypeByTypeId(advertisement.getTypeId());
                    if(advertisementType.getTypeName()!=null){
                        map.put("typeName",advertisementType.getTypeName());
                    }else{
                        map.put("typeName","数据已丢失！");
                    }
                    AdvertisementBusiness advertisementBusiness = advertisementService.selectAdvertismentBusinessById(advertisement.getBusinessId());
                    if(advertisementBusiness.getName()!=null){
                        map.put("businessName",advertisementBusiness.getName());
                    }else{
                        map.put("businessName","数据已丢失！");
                    }
                    map.put("briefIntroduction",advertisement.getBriefIntroduction());
                    map.put("coverMap",advertisement.getCoverMap());
                    map.put("createTime",sdf.format(advertisement.getCreateTime()));
                    map.put("updateTime",sdf.format(advertisement.getUpdateTime()));
                    map.put("sort",advertisement.getSort());
                    map.put("clicks",advertisement.getClicks());
                    mapList.add(map);
                }
                return PageResultEntity.success("成功",count,mapList);
        }catch (Exception e){
            return PageResultEntity.fail();
        }
    }

    /**
     * 前往广告编辑页面
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/advertisement/toEditAdvertisement")
    public String toEditAdvertisement(Long id,HttpSession session){
        Advertisement advertisement = advertisementService.selectAdvertismentByid(id);
        session.setAttribute("advertisement",advertisement);
        AdvertisementTypeExample advertisementTypeExample = new AdvertisementTypeExample();
        advertisementTypeExample.or().andIsDelEqualTo(0);
        List<AdvertisementType> advertisementTypes = advertisementService.selectAdvertismentType(advertisementTypeExample);
        session.setAttribute("advertisementType",advertisementTypes);
        AdvertisementBusinessExample advertisementBusinessExample = new AdvertisementBusinessExample();
        advertisementBusinessExample.or().andIsDelEqualTo(0);
        List<AdvertisementBusiness> advertisementBusinesses = advertisementService.selectAdvertismentBusinessByExample(advertisementBusinessExample);
        session.setAttribute("advertisementBusiness",advertisementBusinesses);
        return "console/AdvertisementEdit";
    }

    /**
     * 修改广告
     * @param advertisement
     * @return
     */
    @ResponseBody
    @RequestMapping("/advertisement/editAdvertisement")
    public BaseResultEntity editAdvertisement(Advertisement advertisement){
        advertisement.setUpdateTime(new Date());
        Integer res = advertisementService.updateAdvertisementByAdvertisement(advertisement);
        if(res >0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    /**
     * 前往广告编辑页面
     * @param session
     * @return
     */
    @RequestMapping("/advertisement/toAddAdvertisement")
    public String toEditAdvertisement(HttpSession session){
        AdvertisementTypeExample advertisementTypeExample = new AdvertisementTypeExample();
        advertisementTypeExample.or().andIsDelEqualTo(0);
        List<AdvertisementType> advertisementTypes = advertisementService.selectAdvertismentType(advertisementTypeExample);
        session.setAttribute("advertisementType",advertisementTypes);

        AdvertisementBusinessExample advertisementBusinessExample = new AdvertisementBusinessExample();
        advertisementBusinessExample.or().andIsDelEqualTo(0);
        List<AdvertisementBusiness> advertisementBusinesses = advertisementService.selectAdvertismentBusinessByExample(advertisementBusinessExample);
        session.setAttribute("advertisementBusiness",advertisementBusinesses);
        return "console/AdvertisementAdd";
    }

    /**
     * 修改广告
     * @param advertisement
     * @return
     */
    @ResponseBody
    @RequestMapping("/advertisement/addAdvertisement")
    public BaseResultEntity addAdvertisement(Advertisement advertisement){
        advertisement.setCreateTime(new Date());
        advertisement.setIsDel(0);
        advertisement.setUpdateTime(new Date());
        if(advertisement.getSort() == null){
            advertisement.setSort((long) 0);
        }
        Integer res = advertisementService.insertAdvertisement(advertisement);
        if(res >0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    /**
     * 删除广告类型
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/advertisement/deleteAdvertisement")
    public BaseResultEntity deleteAdvertisement(Long id){
        Advertisement advertisement = new Advertisement();
        advertisement.setId(id);
        advertisement.setIsDel(1);
        int res = advertisementService.updateAdvertisementByAdvertisement(advertisement);
        if (res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    @ResponseBody
    @RequestMapping("/advertisement/getAdvertisementBusinessAll")
    public BaseResultEntity getAdvertisementBusinessAll(Integer pageNo,Integer pageSize,String name){
        AdvertisementBusinessExample advertisementBusinessExample = new AdvertisementBusinessExample();
        advertisementBusinessExample.setIsPage(1);
        advertisementBusinessExample.setPageNo(pageNo);
        advertisementBusinessExample.setPageSize(pageSize);

        AdvertisementBusinessExample.Criteria criteria = advertisementBusinessExample.or().andIsDelEqualTo(0);
        if(name!=null && name!=""){
            criteria.andNameLike("%"+name+"%");
        }

        List<AdvertisementBusiness> advertisementBusinesses = advertisementService.selectAdvertismentBusinessByExample(advertisementBusinessExample);
        Integer count = advertisementService.countAdverTismentBusinesses(advertisementBusinessExample);
        if(count>0){
            List<Map<String,Object>> mapList = new ArrayList<>();
            for (AdvertisementBusiness advertisementBusiness:advertisementBusinesses){
                Map<String,Object> map = new HashMap<>();
                map.put("id",advertisementBusiness.getId());
                map.put("name",advertisementBusiness.getName());
                map.put("tel",advertisementBusiness.getTel());
                map.put("createTime",sdf.format(advertisementBusiness.getCreateTime()));
                map.put("updateTime",sdf.format(advertisementBusiness.getUpdateTime()));
                mapList.add(map);
            }
            return PageResultEntity.success("成功！",count,mapList);
        }else{
            return PageResultEntity.fail();
        }
    }

    @RequestMapping("/advertisement/toEditAdvertisementBusiness")
    public String toEditAdvertisementBusiness(Long id,HttpSession session){
        AdvertisementBusiness advertisementBusiness = advertisementService.selectAdvertismentBusinessById(id);
        session.setAttribute("advertisementBusiness",advertisementBusiness);
        return "/console/AdvertisementBusinessEdit";
    }


    @ResponseBody
    @RequestMapping("/advertisement/editAdvertisementBusiness")
    public BaseResultEntity editAdvertisementBusiness(AdvertisementBusiness advertisementBusiness){
        advertisementBusiness.setUpdateTime(new Date());
        Integer res = advertisementService.updateAdvertisementBusinessByAdvertisementBusiness(advertisementBusiness);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    @ResponseBody
    @RequestMapping("/advertisement/addAdvertisementBusiness")
    public BaseResultEntity addAdvertisementBusiness(AdvertisementBusiness advertisementBusiness){
        advertisementBusiness.setUpdateTime(new Date());
        advertisementBusiness.setCreateTime(new Date());
        advertisementBusiness.setIsDel(0);
        Integer res = advertisementService.insertAdvertisementBusiness(advertisementBusiness);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }


    @ResponseBody
    @RequestMapping("/advertisement/deleteAdvertisementBusiness")
    public BaseResultEntity addAdvertisementBusiness(Long id){
        AdvertisementBusiness advertisementBusiness = new AdvertisementBusiness();
        advertisementBusiness.setId(id);
        advertisementBusiness.setIsDel(1);
        Integer res = advertisementService.updateAdvertisementBusinessByAdvertisementBusiness(advertisementBusiness);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

}
