package com.huiyou.msfw.service;

import java.util.Map;

/**
 * @ClassName RoleService
 * @Description: TODO
 * @Author zy
 * @Date 2020/6/10
 * @Version V1.0
 **/
public interface RoleService {
    /**
     * 查询角色
     * @param pageNum 页数
     * @param pageSize 条数
     * @param roleName 角色名
     * @return
     */
    Map<String,Object> selRole(Integer pageNum,Integer pageSize,String roleName);

    /**
     * 添加角色（做了重名处理）
     * @param roleName 角色名
     * @return
     */
    Map<String, Object> addRole(String roleName);
}
