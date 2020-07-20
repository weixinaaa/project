package com.huiyou.msfw.controller.console;

import java.util.List;

import javax.annotation.Resource;

import com.huiyou.msfw.model.TreeView;
import com.huiyou.msfw.service.RootService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**   

* @Author: 曾源  

* @Date: 2020年5月12日  下午1:35:31

* @Description:  角色权限

*/
@Controller
@RequestMapping("menu")
public class ConsoleRootController {
	@Resource
	private RootService menuService;
	/**
	 * 打开权限页面
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping("toroot.action")
	public String toRoot(Integer roleId,Model model) {
		model.addAttribute("roleid", roleId);
		return "console/selRoot";
	}
	/**
	 * 查询权限
	 * @param roleId
	 * @return
	 */
	@RequestMapping("selroot.action")
	@ResponseBody
	public List<TreeView> selRoot(Integer roleId){
		return menuService.selRoot(roleId);
	}
	/**
	 * 修改权限
	 * @param roleId
	 * @param menuIds
	 */
	@RequestMapping("editroot.action")
	@ResponseBody
	public void editRoot(Integer roleId,@RequestParam(value = "menuIds")Integer[] menuIds) {
		menuService.editRoot(roleId, menuIds);
	}

}
