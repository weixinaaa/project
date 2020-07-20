package com.huiyou.msfw.controller.console;

import com.alibaba.druid.sql.PagerUtils;
import com.huiyou.msfw.model.AdvertisementExample;
import com.huiyou.msfw.model.SysUser;
import com.huiyou.msfw.model.User;
import com.huiyou.msfw.model.UserExample;
import com.huiyou.msfw.service.SysUserService;
import com.huiyou.msfw.service.UserService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/console")
public class ConsoleUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SysUserService sysUserService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ResponseBody
    @RequestMapping("/user/getUserAll")
    public BaseResultEntity getUserAll(HttpSession session,Integer pageNo,Integer pageSize,String userName,String tel,String createTime,String endTime) throws ParseException {
        UserExample userExample = new UserExample();
        userExample.setIsPage(1);
        userExample.setPageNo(pageNo);
        userExample.setPageSize(pageSize);

        UserExample.Criteria criteria = userExample.or().andIsDelEqualTo(0);
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

        Long sysUserId = (Long) session.getAttribute("sysUserId");
        SysUser sysUser = sysUserService.getUserId(sysUserId);
        Long roleId = sysUser.getRoleId();

        List<User> users = userService.selectByUserExample(userExample);
        Integer count = userService.countByUserExample(userExample);
            List<Map<String,Object>> mapList = new ArrayList<>();
            for(User user:users){
                Map<String,Object> map = new HashMap<>();
                map.put("id",user.getId());
                map.put("userName",user.getUsername());
                map.put("img",user.getImg());
                if (roleId==1){
                    map.put("tel",user.getTel());
                }else {
                    map.put("tel",user.getTel().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
                }
                map.put("invitationCode",user.getInvitationCode());
                map.put("type",user.getType());
                User inviterUser = userService.getUserId(user.getInviterId());
                if(user.getInviterId()!=0 && inviterUser.getUsername()!=null){
                    map.put("inviterName",inviterUser.getUsername());
                }else{
                    map.put("inviterName","无邀请人");
                }
                map.put("commissionBalance",user.getCommissionBalance());
                map.put("gender",user.getGender());
                map.put("mailbox",user.getMailbox());
                map.put("createTime",sdf.format(user.getCreateTime()));
                map.put("updateTime",sdf.format(user.getUpdateTime()));
                mapList.add(map);
            }
            return PageResultEntity.success("成功！",count,mapList);
    }

    @RequestMapping("/user/toEdit")
    public String toEdit(Long id, HttpSession session){
        User user = userService.getUserId(id);
        UserExample userExample = new UserExample();
        userExample.or().andIdNotEqualTo(id).andIsDelEqualTo(0);
        List<User> users = userService.selectByUserExample(userExample);
        session.setAttribute("user",user);
        session.setAttribute("userList",users);
        return "console/userEdit";
    }

    @ResponseBody
    @RequestMapping("/user/editUser")
    public BaseResultEntity editUser(User user){
        user.setUpdateTime(new Date());
        if(user.getImg()==""){
            user.setImg(null);
        }
        Integer res = userService.updateUser(user);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    @RequestMapping("/user/toAdd")
    public String toAdd(HttpSession session){
        UserExample userExample = new UserExample();
        userExample.or().andIsDelEqualTo(0);
        List<User> users = userService.selectByUserExample(userExample);
        session.setAttribute("userList",users);
        return "console/userAdd";
    }

    @ResponseBody
    @RequestMapping("/user/addUser")
    public BaseResultEntity addUser(User user){
        user.setUpdateTime(new Date());
        if(user.getImg()==""){
            user.setImg(null);
        }
        user.setCreateTime(new Date());
        String InvitationCode = String.valueOf((int)((Math.random()*9+1)*100000));
        user.setInvitationCode(InvitationCode);
        Integer res = userService.register(user);

        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    @ResponseBody
    @RequestMapping("/user/deleteUser")
    public BaseResultEntity deleteUser(Long id){
        User user = new User();
        user.setIsDel(1);
        user.setId(id);
        user.setUpdateTime(new Date());
        int res = userService.updateUser(user);
        if(res>0){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }
}
