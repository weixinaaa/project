package com.huiyou.msfw.service;

import com.huiyou.msfw.model.UserIncome;
import com.huiyou.msfw.model.UserIncomeExample;

import java.util.List;

public interface UserIncomeService {
    List<UserIncome> selectUserIncomeAllByUserIncomeExample(UserIncomeExample userIncomeExample);

    Integer countUserIncomeAllByUserIncomeExample(UserIncomeExample userIncomeExample);

    Integer updateaUserIncomeByUserIncome(UserIncome userIncome);
}
