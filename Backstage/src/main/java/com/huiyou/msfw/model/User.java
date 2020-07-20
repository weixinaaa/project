package com.huiyou.msfw.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 表名：user
 * 注释：
 */
@Data
public class User {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：userName
     * 注释：用户姓名
     */
    private String username;

    /**
     * 列名：passwrod
     * 注释：密码
     */
    private String passwrod;

    /**
     * 列名：address
     * 注释：所属地址
     */
    private String address;

    /**
     * 列名：img
     * 注释：头像
     */
    private String img;

    /**
     * 列名：tel
     * 注释：手机号
     */
    private String tel;

    /**
     * 列名：type
     * 注释：用户类型 1乘客 2司机
     */
    private Integer type;

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
     * 列名：invitation_code
     * 注释：邀请码
     */
    private String invitationCode;

    /**
     * 列名：inviter_id
     * 注释：邀请人ID
     */
    private Long inviterId;

    /**
     * 列名：commission_balance
     * 注释：佣金余额
     */
    private BigDecimal commissionBalance;

    /**
     * 列名：gender
     * 注释：性别0男1女2其他
     */
    private Integer gender;

    /**
     * 列名：mailbox
     * 注释：邮箱
     */
    private String mailbox;

    /**
     * 列名：is_del
     * 注释：是否删除
     */
    private Integer isDel;
}