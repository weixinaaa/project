package com.huiyou.msfw.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 表名：sys_user
 * 注释：
 */
@Data
public class SysUser {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：username
     * 注释：用户名
     */
    private String username;

    /**
     * 列名：password
     * 注释：密码
     */
    private String password;

    /**
     * 列名：address
     * 注释：所属地址
     */
    private String address;

    /**
     * 列名：status
     * 注释：状态 0：不可用 1：可用
     */
    private Integer status;

    /**
     * 列名：commission_balance
     * 注释：佣金余额
     */
    private BigDecimal commissionBalance;

    /**
     * 列名：tel
     * 注释：
     */
    private String tel;

    /**
     * 列名：is_del
     * 注释：是否删除  0：未删除 1：删除
     */
    private Integer isDel;

    /**
     * 列名：create_time
     * 注释：
     */
    private Date createTime;

    /**
     * 列名：update_time
     * 注释：
     */
    private Date updateTime;

    /**
     * 列名：create_user_id
     * 注释：
     */
    private Long createUserId;

    /**
     * 列名：update_user_id
     * 注释：
     */
    private Long updateUserId;

    /**
     * 列名：role_id
     * 注释：权限的id
     */
    private Long roleId;

    private String oldPwd;
}