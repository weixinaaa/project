package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：advertisement
 * 注释：
 */
@Data
public class Advertisement {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：title
     * 注释：标题
     */
    private String title;

    /**
     * 列名：type_id
     * 注释：广告类型ID
     */
    private Long typeId;

    /**
     * 列名：business_id
     * 注释：广告商家ID
     */
    private Long businessId;

    /**
     * 列名：cover_map
     * 注释：封面图
     */
    private String coverMap;

    /**
     * 列名：brief_introduction
     * 注释：简介
     */
    private String briefIntroduction;

    /**
     * 列名：content
     * 注释：内容
     */
    private String content;

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

    /**
     * 列名：is_del
     * 注释：是否删除 0未删除 1已删除
     */
    private Integer isDel;

    /**
     * 列名：sort
     * 注释：排序
     */
    private Long sort;

    /**
     * 列名：clicks
     * 注释：点击量
     */
    private Long clicks;
}