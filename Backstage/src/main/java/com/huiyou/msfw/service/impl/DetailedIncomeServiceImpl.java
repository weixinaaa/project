package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.DetailedIncomeMapper;
import com.huiyou.msfw.model.DetailedIncome;
import com.huiyou.msfw.model.DetailedIncomeExample;
import com.huiyou.msfw.service.DetailedIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.huiyou.msfw.service.DetailedIncomeService;

import java.util.List;

@Service
public class DetailedIncomeServiceImpl implements DetailedIncomeService {

    @Autowired
    private DetailedIncomeMapper detailedIncomeMapper;

    @Override
    public List<DetailedIncome> selectDetailedIncomeExample(DetailedIncomeExample detailedIncomeExample) {
        return detailedIncomeMapper.selectByExample(detailedIncomeExample);
    }

    @Override
    public Integer insertDetailedIncome(DetailedIncome detailedIncome) {
        return detailedIncomeMapper.insertSelective(detailedIncome);
    }

    @Override
    public Integer countDetailedIncomeByDetailedIncomeExample(DetailedIncomeExample detailedIncomeExample) {
        return detailedIncomeMapper.countByExample(detailedIncomeExample);
    }
}
