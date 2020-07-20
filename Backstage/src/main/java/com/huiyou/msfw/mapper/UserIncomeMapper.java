package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.UserIncome;
import com.huiyou.msfw.model.UserIncomeExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserIncomeMapper extends BaseMapper<UserIncome, UserIncomeExample, Long> {
}