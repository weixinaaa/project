package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：user_income
 * 注释：
 */
@Data
public class UserIncome {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：userId
     * 注释：用户ID
     */
    private Long userid;

    /**
     * 列名：vehicle_type
     * 注释：车型
     */
    private Integer vehicleType;

    /**
     * 列名：detailed
     * 注释：详细
     */
    private String detailed;

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
     * 注释：是否删除
     */
    private Integer isDel;
}