package com.huiyou.msfw.controller.console;

import com.huiyou.msfw.model.Partner;
import com.huiyou.msfw.model.PartnerExample;
import com.huiyou.msfw.service.PartnerService;
import com.huiyou.msfw.service.UserService;
import lombok.SneakyThrows;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/console")
public class ConsolePartnerController {
    @Resource
    private PartnerService partnerService;
    @Resource
    private UserService userService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ResponseBody
    @RequestMapping("/partner/selPartner")
    public BaseResultEntity selPartner(Integer pageNo, Integer pageSize,String contact,String createTime,String endTime) throws ParseException {
        PartnerExample partnerExample = new PartnerExample();
        partnerExample.setPageSize(pageSize);
        partnerExample.setPageNo(pageNo);
        partnerExample.setIsPage(1);

        PartnerExample.Criteria criteria = partnerExample.or();
        if(contact!=null && contact!=""){
            criteria.andContactEqualTo("%"+contact+"%");
        }

        if(createTime!=null && createTime!=""){
            criteria.andCreateTimeGreaterThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime));
        }

        if(endTime!=null && endTime!=""){
            criteria.andCreateTimeLessThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime));
        }

        List<Partner> partners = partnerService.selectByPartnerExample(partnerExample);
        Integer count = partnerService.countPartnerExample(partnerExample);

        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Partner partner:partners){
            Map<String,Object> map = new HashMap<>();
            map.put("id",partner.getId());
            map.put("contact",partner.getContact());
            map.put("name",partner.getName());
            map.put("note",partner.getNote());
            String username = userService.getUserId(Long.valueOf(partner.getId())).getUsername();
            map.put("userName",username);
            map.put("createTime",sdf.format(partner.getCreateTime()));
            map.put("updateTime",sdf.format(partner.getUpdateTime()));
            mapList.add(map);
        }
        return PageResultEntity.success("成功！",count,mapList);
    }

    @SneakyThrows
    @ResponseBody
    @RequestMapping("/partner/getPartner")
    public Map<String,Object> getPartner(Integer page, Integer limit,Partner partner) {
        System.out.println(partner.getStartTime());
        Map<String, Object> partners = partnerService.getPartner(page, limit, partner);

        return partners;
    }

    // 删除
    @RequestMapping("/partner/delPartner")
    @ResponseBody
    public BaseResultEntity delPartner(String id) {
        partnerService.delPartner(id);
        return PageResultEntity.success("成功");
    }
}
