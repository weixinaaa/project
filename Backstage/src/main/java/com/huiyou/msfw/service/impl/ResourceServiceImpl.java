package com.huiyou.msfw.service.impl;


import com.huiyou.msfw.mapper.ResourceMapper;
import com.huiyou.msfw.model.Resource;
import com.huiyou.msfw.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> selectResource(Long sysRoleId, Long pId) {
        return resourceMapper.selectResource(sysRoleId,pId);
    }
}
