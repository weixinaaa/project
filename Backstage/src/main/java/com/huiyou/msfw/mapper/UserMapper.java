package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.SysUser;
import com.huiyou.msfw.model.User;
import com.huiyou.msfw.model.UserExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User, UserExample, Long> {
    /**
     * 根据地址查
     * @param address
     * @return
     */
    @Select("SELECT * FROM user WHERE address = #{address}")
    List<User> getUser(@Param("address") String address);

    /**
     * 修改佣金
     * @param user
     */
    @Update("UPDATE user SET commission_balance = #{commissionBalance} WHERE id = #{id}")
    Integer addCommissionBalance(User user);
}