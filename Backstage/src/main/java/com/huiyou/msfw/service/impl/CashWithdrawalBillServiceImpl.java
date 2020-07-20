package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.CashWithdrawalBillMapper;
import com.huiyou.msfw.model.CashWithdrawalBill;
import com.huiyou.msfw.model.CashWithdrawalBillExample;
import com.huiyou.msfw.service.CashWithdrawalBillService;


import com.huiyou.msfw.service.CashWithdrawalBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashWithdrawalBillServiceImpl implements CashWithdrawalBillService {

    @Autowired
    private CashWithdrawalBillMapper cashWithdrawalBillMapper;

    @Override
    public List<CashWithdrawalBill> selectCashWithdrawalBill(CashWithdrawalBillExample cashWithdrawalBillExample) {
        return cashWithdrawalBillMapper.selectByExample(cashWithdrawalBillExample);
    }

    @Override
    public Integer countCashwithdrawalBill(CashWithdrawalBillExample cashWithdrawalBillExample) {
        return cashWithdrawalBillMapper.countByExample(cashWithdrawalBillExample);
    }

    @Override
    public Integer insertCashwithdrawalBill(CashWithdrawalBill cashWithdrawalBill) {
        return cashWithdrawalBillMapper.insertSelective(cashWithdrawalBill);
    }

    @Override
    public int updateCashWithdrawalBill(CashWithdrawalBill cashWithdrawalBill) {
        return cashWithdrawalBillMapper.updateByPrimaryKeySelective(cashWithdrawalBill);
    }
}
