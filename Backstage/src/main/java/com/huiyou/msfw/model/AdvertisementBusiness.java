package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：advertisement_business
 * 注释：
 */
@Data
public class AdvertisementBusiness {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：name
     * 注释：商家名称
     */
    private String name;

    /**
     * 列名：tel
     * 注释：商家联系电话
     */
    private String tel;

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
     * 注释：是否启用 0启用 1未启用
     */
    private Integer isDel;
}