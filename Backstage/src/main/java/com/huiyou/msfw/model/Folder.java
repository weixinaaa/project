package com.huiyou.msfw.model;

import lombok.Data;

/**
 * 表名：folder
 * 注释：
 */
@Data
public class Folder {
    /**
     * 列名：id
     * 注释：
     */
    private Long id;

    /**
     * 列名：folder
     * 注释：
     */
    private String folder;

    /**
     * 列名：child_folder
     * 注释：
     */
    private String childFolder;

    /**
     * 列名：is_folder
     * 注释：
     */
    private Integer isFolder;

    /**
     * 列名：sn
     * 注释：
     */
    private String sn;
}