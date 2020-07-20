package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName RoleMapper
 * @Description: TODO
 * @Author zy
 * @Date 2020/6/10
 * @Version V1.0
 **/
public interface RoleMapper {
    /**
     * 查询Role
     * @param roleName 角色名
     * @param id 角色id
     * @return
     */
    List<Role> selRole(@Param("roleName") String roleName , @Param("id") Long id);

    /**
     * 添加角色
     * @param roleName 角色名
     */
    @Insert("INSERT INTO role(role_name,create_time) VALUES(#{roleName},NOW())")
    void addRole(String roleName);

    /**
     * 修改角色名
     * @param role
     */
    @Update("UPDATE role SET role_name = #{roleName} WHERE id = #{id}")
    void editRole(Role role);

    /**
     * 逻辑删除角色(可单，可多)
     * @param id
     */
    void delRole(Integer[] id);

    /**
     * 用于重名处理
     * @param roleName
     * @return
     */
    @Select("SELECT COUNT(*) FROM role WHERE role_name = #{roleName}")
    Integer selRoleByName(String roleName);
}
