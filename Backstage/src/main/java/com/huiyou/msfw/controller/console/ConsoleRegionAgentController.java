package com.huiyou.msfw.controller.console;


import com.huiyou.msfw.model.*;
import com.huiyou.msfw.service.SysUserService;
import com.huiyou.msfw.service.UserService;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/console")
public class ConsoleRegionAgentController {

    @Autowired
    private UserService userService;

    @Autowired
    private SysUserService sysUserService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 区域用户搜索
     * @param //userId 用户ID
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/agent/selRegion")
    @ResponseBody
    public BaseResultEntity selRegion(HttpSession session,Integer pageNo,Integer pageSize,String createTime,String endTime) throws ParseException {
        Long sysUserId = (Long) session.getAttribute("sysUserId");
        SysUser sysUser = sysUserService.getUserId(sysUserId);
        if (sysUser==null){
            return ResultEntity.fail();
        }
        String address="";
        if (!sysUser.getAddress().isEmpty()) {
            address = sysUser.getAddress();
        }

        UserExample userExample = new UserExample();
        userExample.setIsPage(1);
        userExample.setPageNo(pageNo);
        userExample.setPageSize(pageSize);

        UserExample.Criteria criteria = userExample.or().andIsDelEqualTo(0);
        criteria.andAddressLike(address);
        if(createTime!=null && createTime!=""){
            criteria.andCreateTimeGreaterThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime));
        }

        if(endTime!=null && endTime!=""){
            criteria.andCreateTimeLessThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime));
        }

        List<User> userList = userService.selectByUserExample(userExample);
        Integer count = userService.countByUserExample(userExample);
            List<Map<String,Object>> mapList = new ArrayList<>();
            for (User users : userList) {
                Map<String,Object> map = new HashMap<>();
                map.put("id",users.getId());
                map.put("userName",users.getUsername());
                map.put("passwrod",users.getPasswrod());
                map.put("gender",users.getGender());
                if (sysUser.getRoleId()==1){
                    map.put("tel",users.getTel());
                }else {
                    map.put("tel",users.getTel().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
                }
                map.put("mailbox",users.getMailbox());
                map.put("address",users.getAddress());
                map.put("type",users.getType());
                map.put("commissionBalance",users.getCommissionBalance());
                User inviterUser = userService.getUserId(users.getInviterId());
                if(users.getInviterId()!=0 && inviterUser.getUsername()!=null){
                    map.put("inviterName",inviterUser.getUsername());
                }else{
                    map.put("inviterName","无邀请人");
                }
                map.put("invitationCode",users.getInvitationCode());
                map.put("createTime",sdf.format(users.getCreateTime()));
                map.put("updateTime",sdf.format(users.getUpdateTime()));
                mapList.add(map);
            }
            return PageResultEntity.success("成功！",count,mapList);
    }

    /**
     * 跳转到添加佣金
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/agent/toAddRegionCommission")
    public String toEdit(Long id, HttpSession session){
        User user= userService.getUserId(id);
        Long inviterId = user.getInviterId();

        UserExample userExample = new UserExample();
        userExample.or().andIdEqualTo(inviterId).andIsDelEqualTo(0);
        Map<String,String> map = new HashMap<>();

        List<User> users = userService.selectByUserExample(userExample);
        session.setAttribute("user",user);
        session.setAttribute("userList",users);
        return "console/AgentCommissionAdd";
    }

    /**
     * 添加佣金
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/agent/addRegionCommission")
    public BaseResultEntity addRegionCommission(User user) {
        user.setUpdateTime(new Date());
        if (user.getImg() == "") {
            user.setImg(null);
        }
        BigDecimal commissionBalance1 = userService.getUserId(user.getId()).getCommissionBalance();
        BigDecimal commissionBalance2 = user.getCommissionBalance();
        user.setCommissionBalance(commissionBalance1.add(commissionBalance2));
        Integer res = userService.addCommissionBalance(user);
        if (res > 0) {
            return ResultEntity.success();
        } else {
            return ResultEntity.fail();
        }
    }

    /**
     * 区域分佣
     * @param userId 用户ID
     * @param profit 金额
     * @return
     */
    @CrossOrigin
    @RequestMapping("/agent/addCommission")
    @ResponseBody
    public BaseResultEntity addCommission(Long userId, BigDecimal profit){

        User user = userService.getUserId(userId);

        String address = user.getAddress();

        List<SysUser> sysUserList = sysUserService.getSysUser(address);

        if (sysUserList.size() !=0 ){
            SysUser sysUser2 = sysUserList.get(0);
            BigDecimal sharing1 = profit.multiply(BigDecimal.valueOf(0.65));//分区代理人获取65%的分佣
            BigDecimal result1 = sysUser2.getCommissionBalance().add(sharing1);

            sysUser2.setCommissionBalance(result1);
            Integer sysu = sysUserService.updateUser(sysUser2);
            if(sysu>0){
                if (user.getInviterId()!=null&&user.getInviterId()!=0){
                    User user1 = userService.getUserId(user.getInviterId());
                    BigDecimal sharing2 = profit.multiply(BigDecimal.valueOf(0.35));//用户邀请人获取35%的分佣
                    BigDecimal result2 = user1.getCommissionBalance().add(sharing2);

                    user1.setCommissionBalance(result2);
                    Integer userUpdate = userService.addCommissionBalance(user1);
                    if (userUpdate>0){
                        return ResultEntity.success();
                    }else {
                        return ResultEntity.fail();
                    }
                }else{
                    return ResultEntity.fail();
                }

            }else{
                return ResultEntity.fail();
            }

        }else{
            return ResultEntity.fail();
        }

    }

}
