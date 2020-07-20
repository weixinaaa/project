package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.SysUser;
import com.huiyou.msfw.model.SysUserExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysUserMapper extends BaseMapper<SysUser, SysUserExample, Long> {
    @Select("SELECT * FROM sys_user WHERE address = #{address}")
    List<SysUser> getSysUser(@Param("address") String address);
}