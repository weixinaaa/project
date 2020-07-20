package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：navigation_bar_type
 * 注释：
 */
@Data
public class NavigationBarType {
    /**
     * 列名：id
     * 注释：ID
     */
    private Long id;

    /**
     * 列名：name
     * 注释：导航栏标题
     */
    private String name;

    /**
     * 列名：is_del
     * 注释：是否删除 0未删除1已删除
     */
    private Integer isDel;

    /**
     * 列名：create_time
     * 注释：创建时间
     */
    private Date createTime;

    /**
     * 列名：update_time
     * 注释：更新时间
     */
    private Date updateTime;
}