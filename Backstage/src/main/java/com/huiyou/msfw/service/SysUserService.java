package com.huiyou.msfw.service;

import com.huiyou.msfw.model.SysUser;

import com.huiyou.msfw.model.SysUserExample;
import me.fishlord.common.result.BaseResultEntity;

import java.util.List;

public interface SysUserService {
	
	BaseResultEntity doLogin(String username,String password);
	
	BaseResultEntity modifyPassword(SysUser sysUser);

    List<SysUser> selectBySysUserExample(SysUserExample sysUserExample);

    Integer countBySysUserExample(SysUserExample sysUserExample);

    SysUser getUserId(Long id);

    List<SysUser> getSysUser(String address);

    Integer updateUser(SysUser sysUser);

    Integer InsertUser(SysUser sysUser);
}
