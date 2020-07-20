package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：position
 * 注释：
 */
@Data
public class Position {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：name
     * 注释：地点名称
     */
    private String name;

    /**
     * 列名：parent_level_id
     * 注释：父级ID
     */
    private Long parentLevelId;

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