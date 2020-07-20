package com.huiyou.msfw.model;

import java.util.Date;

import lombok.Data;
import me.fishlord.common.result.BaseJsonView;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * 表名：device_version
 * 注释：
 */
@Data
public class DeviceVersion {
	
	public interface DetailView extends BaseJsonView{};
	
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：url
     * 注释：下载地址
     */
    @JsonView({DetailView.class})
    private String url;

    /**
     * 列名：version
     * 注释：版本号
     */
    @JsonView({DetailView.class})
    private String version;

    /**
     * 列名：inner_version
     * 注释：内部版本号
     */
    @JsonView({DetailView.class})
    private Integer innerVersion;

    /**
     * 列名：create_time
     * 注释：
     */
    private Date createTime;
}