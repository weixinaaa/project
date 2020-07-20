package com.huiyou.msfw.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * 表名：driver_registered
 * 注释：司机注册
 * @Description: TODO
 * @Author xuf
 * @Date 2020/6/17
 * @Version V1.0
 */
@Data
public class DriverRegistered {
    /**
     * 列名：id
     * 注释：
     */
    private Integer id;

    /**
     * 列名：driver_name
     * 注释：姓名
     */
    private String driverName;

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
     * 列名：contact_tel
     * 注释：联系电话
     */
    private String contactTel;

    /**
     * 列名：is_have
     * 注释：有无网约资格证，1是有，0是无
     */
    private Integer isHave;

    /**
     * 列名：is_del
     * 注释：是否删除
     */
    private Integer isDel;

    /**
     * 列名：note
     * 注释：备注
     */
    private String note;

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