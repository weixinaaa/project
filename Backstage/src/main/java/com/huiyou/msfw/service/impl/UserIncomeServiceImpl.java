package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.UserIncomeMapper;
import com.huiyou.msfw.model.UserIncome;
import com.huiyou.msfw.model.UserIncomeExample;
import com.huiyou.msfw.service.UserIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserIncomeServiceImpl implements UserIncomeService {

    @Autowired
    private UserIncomeMapper userIncomeMapper;

    @Override
    public List<UserIncome> selectUserIncomeAllByUserIncomeExample(UserIncomeExample userIncomeExample) {
        return userIncomeMapper.selectByExample(userIncomeExample);
    }

    @Override
    public Integer countUserIncomeAllByUserIncomeExample(UserIncomeExample userIncomeExample) {
        return userIncomeMapper.countByExample(userIncomeExample);
    }

    @Override
    public Integer updateaUserIncomeByUserIncome(UserIncome userIncome) {
        return userIncomeMapper.updateByPrimaryKeySelective(userIncome);
    }
}
