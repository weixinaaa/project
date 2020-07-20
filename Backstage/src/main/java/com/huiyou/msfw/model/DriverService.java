package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：driver_service
 * 注释：
 */
@Data
public class DriverService {
    /**
     * 列名：id
     * 注释：ID
     */
    private Long id;

    /**
     * 列名：name
     * 注释：名称
     */
    private String name;

    /**
     * 列名：tel
     * 注释：联系方式
     */
    private String tel;

    /**
     * 列名：license_plate
     * 注释：车牌号
     */
    private String licensePlate;

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
     * 注释：
     */
    private Integer isDel;

    /**
     * 列名：type
     * 注释：0乘客1司机
     */
    private Integer type;
}