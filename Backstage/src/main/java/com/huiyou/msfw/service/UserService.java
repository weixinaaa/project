package com.huiyou.msfw.service;

import com.huiyou.msfw.model.User;
import com.huiyou.msfw.model.UserExample;

import java.util.List;

public interface UserService {
    User getUserId(Long userId);

    Integer updateUser(User user);

    List<User> selectByUserExample(UserExample userExample);

    Integer countByUserExample(UserExample userExample);

    int register(User user);

    List<User> getUser(String address);

    /**
     * 添加佣金
     * @param user
     */
    Integer addCommissionBalance(User user);

}
