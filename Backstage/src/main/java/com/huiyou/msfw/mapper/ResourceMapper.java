package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.Resource;
import com.huiyou.msfw.model.ResourceExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper extends BaseMapper<Resource, ResourceExample, Long> {
    List<Resource> selectResource(@Param("sysRoleId") Long sysRoleId,@Param("pId") Long pId);
}