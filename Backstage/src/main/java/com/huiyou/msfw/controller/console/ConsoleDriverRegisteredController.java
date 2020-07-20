package com.huiyou.msfw.controller.console;

import com.huiyou.msfw.mapper.CarRentalMapper;
import com.huiyou.msfw.model.CarRental;
import com.huiyou.msfw.model.DriverRegistered;
import com.huiyou.msfw.model.SysUser;
import com.huiyou.msfw.service.CarRentalService;
import com.huiyou.msfw.service.DriverRegisteredService;
import com.huiyou.msfw.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
@RequestMapping("/console")
public class ConsoleDriverRegisteredController {
    @Resource
    private DriverRegisteredService driverRegisteredService;
    @Resource
    private SysUserService sysUserService;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    /**
     * 司机查询
     * @param driverRegistered
     * @return
     * @throws ParseException
     */
    @RequestMapping("/driverRegistered/selDriver")
    @ResponseBody
    public Map<String,Object> getDriver(Model model, HttpSession session, DriverRegistered driverRegistered, Integer page, Integer limit){
        Long sysUserId = (Long) session.getAttribute("sysUserId");
        Long roleId = sysUserService.getUserId(sysUserId).getRoleId();
        model.addAttribute("roleId",roleId);
        return driverRegisteredService.selDriver(page, limit, driverRegistered);
    }
}
