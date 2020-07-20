package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.Bank;
import com.huiyou.msfw.model.BankExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BankMapper extends BaseMapper<Bank, BankExample, Long> {

    @Select("SELECT * FROM bank ")
    /**
     * 银行查询
     * @return List
     */
    List<Bank> selBank(Bank bank);
}