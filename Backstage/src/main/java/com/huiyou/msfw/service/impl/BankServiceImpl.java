package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.BankMapper;
import com.huiyou.msfw.model.Bank;
import com.huiyou.msfw.service.BankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    @Resource
    private BankMapper bankMapper;

    @Override
    public List<Bank> selBank(Bank bank) {
        List<Bank> banks = bankMapper.selBank(bank);
        return banks;
    }
}
