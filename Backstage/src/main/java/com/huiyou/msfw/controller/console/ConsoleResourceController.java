package com.huiyou.msfw.controller.console;


import com.huiyou.msfw.model.Resource;
import com.huiyou.msfw.model.SysUser;
import com.huiyou.msfw.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/console/resource")
public class ConsoleResourceController {

    @Autowired
    private ResourceService resourceService;

    @ResponseBody
    @RequestMapping("/getList")
    private List<Map<String,Object>>  getList(HttpSession session){
        SysUser user = (SysUser) session.getAttribute("sysUser");
        System.out.println("角色ID"+user.getRoleId());
        List<Resource> resources = resourceService.selectResource(user.getRoleId(), (long) 0);
        resources.forEach(System.out::println);
        List<Map<String,Object>> resourcesMap = new ArrayList<>();
        for(Resource resource:resources){
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("title",resource.getTitle());
            dataMap.put("spread",resource.getSpread());
            if(resource.getSpread()){
                List<Resource> resourceList = resourceService.selectResource(user.getRoleId(),resource.getId());
                dataMap.put("children",resourceList);
            }
            resourcesMap.add(dataMap);
        }
        return resourcesMap;
    }


}
