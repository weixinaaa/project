package com.huiyou.msfw.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.huiyou.msfw.mapper.RootMapper;
import com.huiyou.msfw.model.Resource;
import com.huiyou.msfw.model.TreeView;
import com.huiyou.msfw.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**   

* @Author: 曾源  

* @Date: 2020年5月12日  下午1:47:47

* @Description: 

*/
@Service
public class RootServiceImpl implements RootService {
	@Autowired
	private RootMapper rootMapper;

	@Override
	public List<TreeView> selRoot(Integer roleId) {
		// 返回给树形组件的数据
		List<TreeView> list = new ArrayList<TreeView>();
		// 查询全部菜单
		List<Resource> selResource = rootMapper.selResource();
		// 查询角色下的菜单
		List<Resource> selResourceByRole = rootMapper.selResourceByRole(roleId);
		for (Resource Resource : selResource) {
			if (Resource.getPId() == 0) {
				TreeView t1 = new TreeView();
				t1.setId(Resource.getId());
				t1.setTitle(Resource.getTitle());
				for (Resource Resource2 : selResourceByRole) {
					if (Resource2.getId() == Resource.getId()) {
						t1.setChecked(true);
					}
				}
				// 返回给树形组件的数据
				List<TreeView> list1 = new ArrayList<TreeView>();
				for (Resource Resource2 : selResource) {
					// 判断是否属于外层循环的下级节点
					if (Resource2.getPId() == Resource.getId()) {
						TreeView t2 = new TreeView();
						t2.setId(Resource2.getId());
						t2.setTitle(Resource2.getTitle());
						for (Resource Resource3 : selResourceByRole) {
							if (Resource3.getId() == Resource2.getId()) {
								t2.setChecked(true);
								t1.setChecked(false);
							}
						}
						list1.add(t2);
					}
				}
				t1.setChildren(list1);
				list.add(t1);
			}
		}
		return list;
	}

	@Override
	public void editRoot(Integer roleId, Integer[] resourceIds) {
		// 先删除再添加
		rootMapper.delRoot(roleId);
		System.out.println(Arrays.toString(resourceIds));
		rootMapper.addRoot(resourceIds,roleId);
		
	}

}
