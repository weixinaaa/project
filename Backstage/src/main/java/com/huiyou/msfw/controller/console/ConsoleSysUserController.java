package com.huiyou.msfw.controller.console;

import javax.servlet.http.HttpSession;

import com.huiyou.msfw.mapper.RoleMapper;
import com.huiyou.msfw.model.Role;
import com.huiyou.msfw.model.SysUserExample;
import com.huiyou.msfw.service.SysUserService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.huiyou.msfw.model.SysUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/console")
public class ConsoleSysUserController {
	
	@Autowired
	private SysUserService sysUserService;
    @Autowired
    private RoleMapper roleMapper;
    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("toLogin")
	public String toLogin() {
		return "console/login";
	}
	/**
	 * 管理员登录
	 * @param session
	 * @param username
	 * @param password
	 * @return
	 */
	
	@RequestMapping("/sysUser/login")
    @ResponseBody
	public BaseResultEntity login(HttpSession session,@RequestParam(required = true) String username,@RequestParam(required = true) String password){
		
    	BaseResultEntity resultEntity = sysUserService.doLogin(username, password);
    	if (resultEntity.getCode()==0) {
    		ResultEntity resultEntity2 = (ResultEntity) resultEntity;
    		SysUser sysUser = (SysUser) resultEntity2.getData();
    		session.setAttribute("sysUser", sysUser);
			session.setAttribute("sysUserId", sysUser.getId());
			return BaseResultEntity.success();
		}else {
			return resultEntity;
			
		}
	}
	
	/**
	 * 登出
	 * @param session
	 * @return
	 */
	@RequestMapping("/sysUser/logout")
    @ResponseBody
	public BaseResultEntity logout(HttpSession session){
    	session.invalidate();
		return BaseResultEntity.success();
	}

	/**
	 * 修改密码
	 * @return
	 */
    @RequestMapping("/sysUser/modifyPassword")
    @ResponseBody
    public BaseResultEntity modifyPwd(@SessionAttribute Long sysUserId,String oldPwd,String newPwd){
		
    	SysUser sysUser = new SysUser();
    	sysUser.setId(sysUserId);
    	sysUser.setPassword(DigestUtils.md5Hex(newPwd));
    	sysUser.setOldPwd(DigestUtils.md5Hex(oldPwd));
    	
    	return sysUserService.modifyPassword(sysUser);		
	}

    @ResponseBody
    @RequestMapping("/sysUser/getSysUserAll")
    public BaseResultEntity getUserAll(Integer pageNo,Integer pageSize,String userName,String tel,String createTime,String endTime) throws ParseException {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.setIsPage(1);
        sysUserExample.setPageNo(pageNo);
        sysUserExample.setPageSize(pageSize);

        SysUserExample.Criteria criteria = sysUserExample.or().andIsDelEqualTo(0);
        if(userName!=null && userName!=""){
            criteria.andUsernameLike("%"+userName+"%");
        }

        if(tel!=null && tel!=""){
            criteria.andTelLike("%"+tel+"%");
        }

        if(createTime!=null && createTime!=""){
            criteria.andCreateTimeGreaterThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime));
        }

        if(endTime!=null && endTime!=""){
            criteria.andCreateTimeLessThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime));
        }

        List<SysUser> sysUsers = sysUserService.selectBySysUserExample(sysUserExample);
        Integer count = sysUserService.countBySysUserExample(sysUserExample);
        if(count>0){
            List<Map<String,Object>> mapList = new ArrayList<>();
            for(SysUser sysUser:sysUsers){
                Map<String,Object> map = new HashMap<>();
                map.put("id",sysUser.getId());
                map.put("userName",sysUser.getUsername());
                System.out.println("-------------------"+sysUser.getRoleId());
                List<Role> roles = roleMapper.selRole(null, sysUser.getRoleId());
                String roleName = "暂无";
                if(roles.size()!=0){
                    roleName = roles.get(0).getRoleName();
                }
                map.put("roleName",roleName);
                if (sysUser.getRoleId()==1){
                    map.put("tel",sysUser.getTel());
                }else {
                    map.put("tel",sysUser.getTel().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
                }
                map.put("status",sysUser.getStatus());
                map.put("createTime",sdf.format(sysUser.getCreateTime()));
                map.put("updateTime",sdf.format(sysUser.getUpdateTime()));
                mapList.add(map);
            }
            return PageResultEntity.success("成功！",count,mapList);
        }else{
            return PageResultEntity.fail();
        }
    }

    @RequestMapping("/sysUser/toEdit")
    public String toEdit(Long id, HttpSession session){
        SysUser user = sysUserService.getUserId(id);
        user.setPassword(user.getPassword());
        if (user.getRoleId() == null){
            user.setRoleId((long) 0);
        }
        session.setAttribute("sysUser",user);
        return "console/SysUserEdit";
    }

    @ResponseBody
    @RequestMapping("/sysUser/editSysUser")
    public BaseResultEntity editUser(SysUser sysUser){
        sysUser.setUpdateTime(new Date());
        if(sysUser.getStatus()!=null){
            switch (sysUser.getStatus()){
                case 0:
                    sysUser.setStatus(1);
                    break;
                case 1:
                    sysUser.setStatus(0);
                    break;
            }
        }
        Integer res = sysUserService.updateUser(sysUser);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    @ResponseBody
    @RequestMapping("/sysUser/addSysUser")
    public BaseResultEntity addSysUser(SysUser sysUser){
        sysUser.setUpdateTime(new Date());
        sysUser.setCreateTime(new Date());
        sysUser.setPassword(DigestUtils.md5Hex(sysUser.getPassword()));
        Integer res = sysUserService.InsertUser(sysUser);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }
}
