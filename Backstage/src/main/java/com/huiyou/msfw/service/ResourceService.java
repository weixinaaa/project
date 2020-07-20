package com.huiyou.msfw.service;

import com.huiyou.msfw.model.Resource;

import java.util.List;

public interface ResourceService {
    List<Resource> selectResource(Long sysRoleId, Long pId);
}
