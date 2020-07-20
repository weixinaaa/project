package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.UserMapper;
import com.huiyou.msfw.model.User;
import com.huiyou.msfw.model.UserExample;
import com.huiyou.msfw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> selectByUserExample(UserExample userExample) {
        return userMapper.selectByExample(userExample);
    }

    @Override
    public Integer countByUserExample(UserExample userExample) {
        return userMapper.countByExample(userExample);
    }

    @Override
    public int register(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public List<User> getUser(String address) {
        return userMapper.getUser(address);
    }

    @Override
    public Integer addCommissionBalance(User user) {
        return userMapper.addCommissionBalance(user);
    }
}
