package com.huiyou.msfw.model;

import lombok.Data;

/**
 * 表名：bank
 * 注释：
 */
@Data
public class Bank {
    /**
     * 列名：id
     * 注释：
     */
    private Integer id;

    /**
     * 列名：bank_name
     * 注释：
     */
    private String bankName;

    /**
     * 列名：bank_number
     * 注释：
     */
    private String bankNumber;
}