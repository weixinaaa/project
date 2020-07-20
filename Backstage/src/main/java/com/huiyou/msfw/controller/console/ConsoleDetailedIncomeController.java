package com.huiyou.msfw.controller.console;


import com.huiyou.msfw.mapper.UserMapper;
import com.huiyou.msfw.model.DetailedIncome;
import com.huiyou.msfw.model.DetailedIncomeExample;
import com.huiyou.msfw.model.User;
import com.huiyou.msfw.model.UserExample;
import com.huiyou.msfw.service.DetailedIncomeService;
import com.huiyou.msfw.service.UserService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/console")
public class ConsoleDetailedIncomeController {

    @Autowired
    private DetailedIncomeService detailedIncomeService;
    @Autowired
    private UserService userService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取用户收益明细
     * @param pageNo 页数
     * @param pageSize 条数
     * @param createTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("/detailedIncome/getDetailedIncomeAll")
    public BaseResultEntity getUserAll(Integer pageNo, Integer pageSize,String createTime,String endTime) throws ParseException {
        DetailedIncomeExample detailedIncomeExample = new DetailedIncomeExample();
        detailedIncomeExample.setIsPage(1);
        detailedIncomeExample.setPageNo(pageNo);
        detailedIncomeExample.setPageSize(pageSize);

        DetailedIncomeExample.Criteria criteria = detailedIncomeExample.createCriteria();

        if(createTime!=null && createTime!=""){
            criteria.andCreateTimeGreaterThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime));
        }

        if(endTime!=null && endTime!=""){
            criteria.andCreateTimeLessThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime));
        }

        List<DetailedIncome> detailedIncomes = detailedIncomeService.selectDetailedIncomeExample(detailedIncomeExample);
        Integer count = detailedIncomeService.countDetailedIncomeByDetailedIncomeExample(detailedIncomeExample);
            List<Map<String,Object>> mapList = new ArrayList<>();
            for(DetailedIncome detailedIncome:detailedIncomes){
                Map<String,Object> map = new HashMap<>();
                map.put("id",detailedIncome.getId());
                map.put("profit",detailedIncome.getProfit());
                map.put("title",detailedIncome.getTitle());
                User user = userService.getUserId(detailedIncome.getUserId());
                map.put("userName",user.getUsername());
                map.put("createTime",sdf.format(detailedIncome.getCreateTime()));
                mapList.add(map);
            }
            return PageResultEntity.success("成功！",count,mapList);
    }
}
