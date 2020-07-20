package com.huiyou.msfw.model;

import java.util.Date;
import lombok.Data;

/**
 * 表名：device
 * 注释：
 */
@Data
public class Device {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：sn
     * 注释：
     */
    private String sn;

    /**
     * 列名：psssword
     * 注释：设备密码
     */
    private String psssword;

    /**
     * 列名：status
     * 注释：状态  1：在线 2：故障 3：离线
     */
    private Integer status;

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
     * 列名：login_seconds
     * 注释：登陆时长,秒为单位
     */
    private Integer loginSeconds;

    /**
     * 列名：login_time
     * 注释：上次登陆时间
     */
    private Date loginTime;

    /**
     * 列名：experiment_times
     * 注释：实验次数
     */
    private Integer experimentTimes;

    /**
     * 列名：general_log
     * 注释：机器的普通日志
     */
    private String generalLog;

    /**
     * 列名：warning_log
     * 注释：机器的警报日志
     */
    private String warningLog;

    /**
     * 列名：service_life
     * 注释：使用期限（天）
     */
    private Integer serviceLife;

    /**
     * 列名：fileName
     * 注释：文件名
     */
    private String filename;

    /**
     * 列名：file
     * 注释：文件地址
     */
    private String file;

    /**
     * 列名：service_status
     * 注释：使用状态 1：过期 2：没过期
     */
    private Integer serviceStatus;

    /**
     * 列名：size
     * 注释：设备空间
     */
    private Double size;

    /**
     * 列名：user_size
     * 注释：设备使用空间
     */
    private Double userSize;

    /**
     * 列名：dayhave
     * 注释：剩余天数
     */
    private Integer dayhave;

    /**
     * 列名：isdongjie
     * 注释：0未冻结1冻结
     */
    private Integer isdongjie;

    /**
     * 列名：dongdays
     * 注释：累计冻结天数
     */
    private Integer dongdays;

    /**
     * 列名：dongtime
     * 注释：最后冻结日期
     */
    private Date dongtime;

    /**
     * 列名：file_size
     * 注释：文件大小
     */
    private String fileSize;
    private String oldPwd;
    private String newPwd;
}