package com.huiyou.msfw.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 表名：detailed_income
 * 注释：
 */
@Data
public class DetailedIncome {
    /**
     * 列名：id
     * 注释：ID
     */
    private Long id;

    /**
     * 列名：title
     * 注释：标题
     */
    private String title;

    /**
     * 列名：create_time
     * 注释：创建时间
     */
    private Date createTime;

    /**
     * 列名：profit
     * 注释：收益
     */
    private BigDecimal profit;

    /**
     * 列名：user_id
     * 注释：用户ID
     */
    private Long userId;

    /**
     * 列名：type
     * 注释：收益类型 0用户佣金 1点击广告
     */
    private Integer type;
}