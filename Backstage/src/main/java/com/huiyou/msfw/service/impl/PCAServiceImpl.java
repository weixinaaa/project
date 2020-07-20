package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.PCAMapper;
import com.huiyou.msfw.model.PCA;
import com.huiyou.msfw.service.PCAService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PCAServiceImpl implements PCAService {
	@Resource
	private PCAMapper pcaMapper;
	
	//省
	@Override
	public List<PCA> selPro() {
		List<PCA> selPro = pcaMapper.selPro();
		return selPro;
	}
	//市
	@Override
	public List<PCA> selCity(Integer pid) {
		List<PCA> selCity = pcaMapper.selCity(pid);
		return selCity;
	}
	//区
	@Override
	public List<PCA> selArea(Integer pid) {
		List<PCA> selArea = pcaMapper.selArea(pid);
		return selArea;
	}

}
