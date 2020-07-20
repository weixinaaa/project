package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.UserResource;
import com.huiyou.msfw.model.UserResourceExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserResourceMapper extends BaseMapper<UserResource, UserResourceExample, Long> {
}