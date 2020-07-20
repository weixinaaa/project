package com.huiyou.msfw.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * 表名：partner
 * 注释：
 */
@Data
public class Partner {
    /**
     * 列名：id
     * 注释：
     */
    private Integer id;

    /**
     * 列名：user_id
     * 注释：用户id
     */
    private Integer userId;

    /**
     * 列名：name
     * 注释：合伙人姓名
     */
    private String name;

    /**
     * 列名：contact
     * 注释：联系方式
     */
    private String contact;

    /**
     * 列名：note
     * 注释：备注留言
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

    /**
     * 列名：update_time
     * 注释：更新时间
     */
    private Date updateTime;
    @ApiParam(value="结束时间",required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    //补充字段
    private String userName;

    private String timeCreate;
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