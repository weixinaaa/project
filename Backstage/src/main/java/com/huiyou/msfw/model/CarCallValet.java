package com.huiyou.msfw.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 表名：car_call_valet
 * 注释：
 */
@Data
public class CarCallValet {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：type
     * 注释：1 机场-火车东站 2机场-乌镇 3火车东站 -机场 4火车东站-乌镇 5乌镇-火车东站 6乌镇-机场
     */
    private String type;

    /**
     * 列名：departure_time
     * 注释：发车时间
     */
    private Date departureTime;

    /**
     * 列名：use_time
     * 注释：用时
     */
    private Integer useTime;

    /**
     * 列名：amountOfMoney
     * 注释：金额
     */
    private BigDecimal amountofmoney;
}