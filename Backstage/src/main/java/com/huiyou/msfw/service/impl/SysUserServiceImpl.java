package com.huiyou.msfw.service.impl;

import java.util.List;

import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.ResultEntity;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiyou.msfw.mapper.SysUserMapper;
import com.huiyou.msfw.model.SysUser;
import com.huiyou.msfw.model.SysUserExample;
import com.huiyou.msfw.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	
	
	@Override
	public BaseResultEntity doLogin(String username, String password) {
		
		SysUserExample example = new SysUserExample();
		example.or().andIsDelEqualTo(0).andUsernameEqualTo(username).andPasswordEqualTo(DigestUtils.md5Hex(password));
		List<SysUser> list = sysUserMapper.selectByExample(example);
		if (list.size() == 0) {
			return ResultEntity.fail("用户名或密码错误");
		}else {
			return ResultEntity.successd(list.get(0));
		}
	}

	@Override
	public BaseResultEntity modifyPassword(SysUser sysUser) {
		
		SysUserExample example=new SysUserExample();
		example.or().andIsDelEqualTo(0).andIdEqualTo(sysUser.getId()).andPasswordEqualTo(sysUser.getOldPwd());
		int count = sysUserMapper.countByExample(example);
		if (count == 0) {
			return ResultEntity.fail("原密码错误");
		}else {
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
		}
		return BaseResultEntity.success();
	}

    @Override
    public List<SysUser> selectBySysUserExample(SysUserExample sysUserExample) {
        return sysUserMapper.selectByExample(sysUserExample);
    }

    @Override
    public Integer countBySysUserExample(SysUserExample sysUserExample) {
        return sysUserMapper.countByExample(sysUserExample);
    }

    @Override
    public SysUser getUserId(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

	@Override
	public List<SysUser> getSysUser(String address) {
		return sysUserMapper.getSysUser(address);
	}

	@Override
    public Integer updateUser(SysUser sysUser) {
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public Integer InsertUser(SysUser sysUser) {
        return sysUserMapper.insertSelective(sysUser);
    }

}
