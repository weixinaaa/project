package com.huiyou.msfw.controller.console;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huiyou.msfw.mapper.RoleMapper;
import com.huiyou.msfw.model.Role;
import com.huiyou.msfw.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ConsoleRoleController
 * @Description: TODO
 * @Author zy
 * @Date 2020/6/10
 * @Version V1.0
 **/
@Controller
@RequestMapping("console/role")
public class ConsoleRoleController {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleService roleService;

    /**
     * 查询Role 全查，分页，模糊
     * @param page 页数
     * @param limit 条数
     * @param roleName 角色名
     * @return
     */
    @RequestMapping("selRole")
    @ResponseBody
    public Map<String,Object> selRole(Integer page, Integer limit, String roleName){
        return roleService.selRole(page, limit, roleName);
    }

    /**
     * 添加角色
     * @param roleName
     */
    @RequestMapping("addRole")
    @ResponseBody
    public Map<String,Object> addRole(String roleName){
        return roleService.addRole(roleName);
    }

    /**
     * 跳转到角色修改页面
     * @param id 角色ID
     * @param model
     * @return
     */
    @RequestMapping("toEdit")
    public String toEdit(Long id, Model model){
        Role role = roleMapper.selRole(null, id).get(0);
        model.addAttribute("role",role);
        return "console/roleEdit";
    }
    /**
     * 修改角色
     * @param role
     */
    @RequestMapping("editRole")
    @ResponseBody
    public void editRole(Role role){
        roleMapper.editRole(role);
    }

    /**
     * 逻辑删除角色
     * @param id
     */
    @RequestMapping("delRole")
    @ResponseBody
    public void delRole(@RequestParam(value = "id") Integer[] id){
        roleMapper.delRole(id);
    }
}
