package com.huiyou.msfw.service;

import com.huiyou.msfw.model.PCA;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PCAService {
	@Autowired
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
