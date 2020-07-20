package com.huiyou.msfw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huiyou.msfw.mapper.RoleMapper;
import com.huiyou.msfw.model.Role;
import com.huiyou.msfw.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleServiceImpl
 * @Description: TODO
 * @Author zy
 * @Date 2020/6/10
 * @Version V1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 查询角色
     *
     * @param pageNum  页数
     * @param pageSize 条数
     * @param roleName 角色名
     * @return
     */
    @Override
    public Map<String, Object> selRole(Integer pageNum, Integer pageSize, String roleName) {
        if(pageNum != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<Role> roles = roleMapper.selRole(roleName, null);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        long count = rolePageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("count",count);
        map.put("data",roles);
        map.put("msg","成功");
        map.put("code",0);
        return map;
    }

    /**
     * 添加角色（做了重名处理）
     *
     * @param roleName 角色名
     * @return
     */
    @Override
    public Map<String, Object> addRole(String roleName) {
        Integer byName = roleMapper.selRoleByName(roleName);
        String msg = "角色名已存在,请重新输入...";
        Boolean flag = false;
        if(byName == null || byName == 0){
            roleMapper.addRole(roleName);
            msg = "添加成功";
            flag = true;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("flag",flag);
        return map;
    }
}
