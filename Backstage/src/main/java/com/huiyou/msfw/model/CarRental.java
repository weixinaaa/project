package com.huiyou.msfw.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 表名：car_rental
 * 注释：租车
 * @Description: TODO
 * @Author xuf
 * @Date 2020/6/16
 * @Version V1.0
 */
@Data
public class CarRental {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：car_name
     * 注释：车名
     */
    private String carName;

    /**
     * 列名：car_type_id
     * 注释：车型id
     */
    private Integer carTypeId;

    /**
     * 列名：engine_type
     * 注释：发动机机型
     */
    private String engineType;

    /**
     * 列名：img
     * 注释：图片
     */
    private String img;

    /**
     * 列名：pro_id
     * 注释：省
     */
    private Integer proId;

    /**
     * 列名：city_id
     * 注释：市
     */
    private Integer cityId;

    /**
     * 列名：area_id
     * 注释：区
     */
    private Integer areaId;

    /**
     * 补充字段
     * 注释：车型名
     */
    private String carTypeName;

    /**
     * 补充字段
     * 注释：省名
     */
    private String proName;

    /**
     * 补充字段
     * 注释：市名
     */
    private String cityName;

    /**
     * 补充字段
     * 注释：区域名
     */
    private String areaName;

    /**
     * 列名：gear_type
     * 注释：1为自动挡，0为手动挡
     */
    private Long gearType;

    /**
     * 列名：seat_num
     * 注释：车座数量
     */
    private Long seatNum;

    /**
     * 列名：daily_rent_price
     * 注释：日租价格
     */
    private Float dailyRentPrice;

    /**
     * 列名：is_del
     * 注释：是否删除
     */
    private Integer isDel;

    /**
     * 列名：create_time
     * 注释：创建时间
     */
    private Date createTime;

    @ApiParam(value="开始时间",required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @ApiParam(value="结束时间",required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    private String timeCreate;

    /**
     * 列名：update_time
     * 注释：更新时间
     */
    private Date updateTime;

    private String timeUpdate;

    public void setCreateTime(Date createTime) {
        setTimeCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime));
        this.createTime = createTime;
    }
    public void setUpdateTime(Date updateTime) {
        setTimeUpdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime));
        this.updateTime = updateTime;
    }
}