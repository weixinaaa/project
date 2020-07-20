package com.huiyou.msfw.mapper;

import java.util.List;

import com.huiyou.msfw.model.Resource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**   

* @Author: 曾源  

* @Date: 2020年5月12日  下午1:43:22

* @Description: 

*/
public interface RootMapper {
	/**
	 * 查询全部菜单
	 * @return
	 */
	@Select("SELECT * FROM resource")
	List<Resource> selResource();
	/**
	 * 根据role查询菜单
	 * @return
	 */
	@Select("SELECT * FROM resource WHERE id IN (SELECT resource_id FROM role_resource WHERE role_id = #{roleId})")
	List<Resource> selResourceByRole(Integer roleId);
	/**
	 * 添加权限
	 * @param roleId
	 * @param resourceIds
	 */
	void addRoot(@Param("resourceIds") Integer[] resourceIds, @Param("roleId") Integer roleId);
	/**
	 * 删除权限
	 * @param roleId
	 */
	@Delete("DELETE FROM role_resource WHERE role_id = #{roleId}")
	void delRoot(Integer roleId);
}
