package com.huiyou.msfw.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 表名：vehicle_type
 * 注释：
 */
@Data
public class VehicleType {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：type
     * 注释：车型 1中巴 2商务 3轿车
     */
    private String type;

    /**
     * 列名：price
     * 注释：价格
     */
    private BigDecimal price;

    /**
     * 列名：charter_price
     * 注释：
     */
    private BigDecimal charterPrice;

    /**
     * 列名：img
     * 注释：图片
     */
    private String img;

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
     * 注释：0未删除1已删除
     */
    private Integer isDel;
}