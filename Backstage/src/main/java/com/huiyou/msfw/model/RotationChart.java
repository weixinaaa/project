package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：rotation_chart
 * 注释：
 */
@Data
public class RotationChart {
    /**
     * 列名：id
     * 注释：ID
     */
    private Long id;

    /**
     * 列名：title
     * 注释：轮播图标题
     */
    private String title;

    /**
     * 列名：img
     * 注释：轮播图图片地址
     */
    private String img;

    /**
     * 列名：sort
     * 注释：排序
     */
    private Long sort;

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