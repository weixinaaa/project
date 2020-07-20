package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：chartered_car
 * 注释：
 */
@Data
public class CharteredCar {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：vehicle_type_id
     * 注释：车型ID
     */
    private Long vehicleTypeId;

    /**
     * 列名：end_time
     * 注释：结束时间
     */
    private Date endTime;

    /**
     * 列名：time_of_appointment
     * 注释：预约时间
     */
    private Date timeOfAppointment;

    /**
     * 列名：remarks
     * 注释：备注
     */
    private String remarks;
}