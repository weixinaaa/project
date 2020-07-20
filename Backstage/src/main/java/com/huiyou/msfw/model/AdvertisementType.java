package com.huiyou.msfw.model;

import lombok.Data;

/**
 * 表名：advertisement_type
 * 注释：
 */
@Data
public class AdvertisementType {
    /**
     * 列名：id
     * 注释：ID
     */
    private Long id;

    /**
     * 列名：type_name
     * 注释：广告类型名称
     */
    private String typeName;

    /**
     * 列名：is_del
     * 注释：是否启用 0启用 1未启用
     */
    private Integer isDel;
}