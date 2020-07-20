package com.huiyou.msfw.service;

import com.huiyou.msfw.model.TreeView;

import java.util.List;


/**   

* @Author: 曾源  

* @Date: 2020年5月12日  下午1:46:13

* @Description: 

*/
public interface RootService {
	/**
	 * 查询role下的权限并处理树形组件所需要的数据
	 * @param roleId
	 * @return
	 */
	List<TreeView> selRoot(Integer roleId);
	/**
	 * 修改权限
	 * @param roleId
	 * @param resourceId
	 */
	void editRoot(Integer roleId,Integer[] resourceId);

}
