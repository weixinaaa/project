package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：resource
 * 注释：
 */
@Data
public class Resource {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：title
     * 注释：标题名
     */
    private String title;

    /**
     * 列名：href
     * 注释：链接名
     */
    private String href;

    /**
     * 列名：p_id
     * 注释：父级ID
     */
    private Long pId;

    /**
     * 列名：sort
     * 注释：排序
     */
    private boolean sort;

    /**
     * 列名：spread
     * 注释：是否为菜单 0否 1是
     */
    private Boolean spread;

    /**
     * 列名：is_del
     * 注释：是否删除 0未删除 1已删除
     */
    private Integer isDel;

    /**
     * 列名：create_time
     * 注释：创建时间
     */
    private Date createTime;

    /**
     * 列名：update_time
     * 注释：
     */
    private Date updateTime;
}