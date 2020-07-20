package com.huiyou.msfw.controller;

import com.huiyou.msfw.model.Partner;
import com.huiyou.msfw.model.PartnerExample;
import com.huiyou.msfw.service.PartnerService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("partner")
public class PartnerController {
    @Resource
    private PartnerService partnerService;



    @CrossOrigin
    @RequestMapping("addPartner")
    @ResponseBody
    public BaseResultEntity addPartner(Integer userId,String name,String contact,String note) {
        PartnerExample partnerExample = new PartnerExample();
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        partnerExample.or().andContactEqualTo(contact);
        List<Partner> partners = partnerService.selectByPartnerExample(partnerExample);
        if (partners.size()>3){
            return BaseResultEntity.fail("您已提交申请了");
        }
        Partner partner = new Partner();
        partner.setContact(contact);
        partner.setName(name);
        partner.setCreateTime(new Date());
        partner.setUpdateTime(new Date());
        partner.setUserId(userId);
        if (note!=null){
            partner.setNote(note);
        }

        int insertPartner = partnerService.insertPartner(partner);
        if (insertPartner==0){
            PageResultEntity.fail("失败");
        }


        return PageResultEntity.success("成功");
    }


}
