package com.huiyou.msfw.mapper;


import com.huiyou.msfw.model.PCA;

import java.util.List;

public interface PCAMapper {
	/**
	 * 省查询
	 * @return List
	 */
	List<PCA> selPro();

	/**
	 * 市
	 * @param pid
	 * @return List
	 */
	List<PCA> selCity(Integer pid);

	/**
	 * 区
	 * @param pid
	 * @return List
	 */
	List<PCA> selArea(Integer pid);
	
}
