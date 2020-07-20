package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：user_resource
 * 注释：
 */
@Data
public class UserResource {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：user_id
     * 注释：
     */
    private Long userId;

    /**
     * 列名：resource_id
     * 注释：
     */
    private Long resourceId;

    /**
     * 列名：is_del
     * 注释：
     */
    private Integer isDel;

    /**
     * 列名：create_time
     * 注释：
     */
    private Date createTime;
}