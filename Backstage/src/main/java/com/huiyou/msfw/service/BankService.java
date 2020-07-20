package com.huiyou.msfw.service;

import com.huiyou.msfw.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface BankService {
    @Autowired
    /**
     * 银行查询
     * @return List
     */
    List<Bank> selBank(Bank bank);
}
