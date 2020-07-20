package com.huiyou.msfw.controller;

import com.huiyou.msfw.model.Bank;
import com.huiyou.msfw.service.BankService;
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
@RequestMapping("bank")
public class BankController {
    @Resource
    private BankService bankService;

    /**
     * 查询银行
     */
    @CrossOrigin
    @RequestMapping("selBank")
    @ResponseBody
    public BaseResultEntity selBank(Bank bank) {
        List<Bank> selBank = bankService.selBank(bank);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Bank banks : selBank) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", banks.getId());
            map.put("bankName", banks.getBankName());
            map.put("bankNumber",banks.getBankNumber());
            mapList.add(map);
        }
        return PageResultEntity.success("成功!" , selBank.size() , mapList);

    }
}
