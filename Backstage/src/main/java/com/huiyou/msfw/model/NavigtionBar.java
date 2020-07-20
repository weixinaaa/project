package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：navigtion_bar
 * 注释：
 */
@Data
public class NavigtionBar {
    /**
     * 列名：id
     * 注释：ID
     */
    private Long id;

    /**
     * 列名：type_id
     * 注释：导航栏类型ID
     */
    private Long typeId;

    /**
     * 列名：name
     * 注释：导航栏名称
     */
    private String name;

    /**
     * 列名：url_name
     * 注释：网页链接名称
     */
    private String urlName;

    /**
     * 列名：img
     * 注释：导航栏图片
     */
    private String img;

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
     * 注释：更新时间
     */
    private Date updateTime;
}