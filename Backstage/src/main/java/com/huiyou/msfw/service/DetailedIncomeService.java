package com.huiyou.msfw.service;

import com.huiyou.msfw.model.DetailedIncome;
import com.huiyou.msfw.model.DetailedIncomeExample;

import java.util.List;

public interface DetailedIncomeService {
    List<DetailedIncome> selectDetailedIncomeExample(DetailedIncomeExample detailedIncomeExample);

    Integer insertDetailedIncome(DetailedIncome detailedIncome);

    Integer countDetailedIncomeByDetailedIncomeExample(DetailedIncomeExample detailedIncomeExample);
}
