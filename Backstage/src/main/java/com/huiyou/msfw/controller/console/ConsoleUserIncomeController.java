package com.huiyou.msfw.controller.console;

import com.huiyou.msfw.model.SysUser;
import com.huiyou.msfw.model.User;
import com.huiyou.msfw.model.UserIncome;
import com.huiyou.msfw.model.UserIncomeExample;
import com.huiyou.msfw.service.SysUserService;
import com.huiyou.msfw.service.UserIncomeService;
import com.huiyou.msfw.service.UserService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/console")
public class ConsoleUserIncomeController {


    @Autowired
    private UserIncomeService userIncomeService;

    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private UserService userService;
    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @RequestMapping("/userIncome/getUserIncomeAll")
    @ResponseBody
    public BaseResultEntity getUserIncomeAll(HttpSession session, Integer pageNo, Integer pageSize){
        Long sysUserId = (Long) session.getAttribute("sysUserId");
        SysUser sysUser = sysUserService.getUserId(sysUserId);

        UserIncomeExample userIncomeExample = new UserIncomeExample();
        userIncomeExample.setIsPage(1);
        userIncomeExample.setPageNo(pageNo);
        userIncomeExample.setPageSize(pageSize);
        userIncomeExample.or().andIsDelEqualTo(0);
        List<UserIncome> userIncomes = userIncomeService.selectUserIncomeAllByUserIncomeExample(userIncomeExample);
        Integer count = userIncomeService.countUserIncomeAllByUserIncomeExample(userIncomeExample);
        List<Map<String,Object>> mapList = new ArrayList<>();
            for(UserIncome userIncome:userIncomes){
                Map<String,Object> map = new HashMap<>();
                map.put("id",userIncome.getId());
                map.put("vehicleType",userIncome.getVehicleType());
                map.put("detailed",userIncome.getDetailed());
                map.put("createTime",sdf.format(userIncome.getCreateTime()));
                map.put("updateTime",sdf.format(userIncome.getUpdateTime()));
                User user = userService.getUserId(userIncome.getUserid());
                map.put("userName",user.getUsername());
                if (sysUser.getRoleId()==1){
                    map.put("tel",user.getTel());
                }else {
                    map.put("tel",user.getTel().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
                }

                map.put("gender",user.getGender());
                mapList.add(map);
            }
            return PageResultEntity.success("成功！",count,mapList);
    }

    @RequestMapping("/userIncome/deleteUserIncome")
    @ResponseBody
    public BaseResultEntity getUserIncomeAll(Long id){
        UserIncome userIncome = new UserIncome();
        userIncome.setId(id);
        userIncome.setIsDel(1);
        Integer res = userIncomeService.updateaUserIncomeByUserIncome(userIncome);
        if(res>0){
            return ResultEntity.success();
        }else {
            return ResultEntity.fail();
        }

    }
}
