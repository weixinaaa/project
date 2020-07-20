package com.huiyou.msfw.model;

import lombok.Data;

/**
 * 表名：car_type
 * 注释：车型
 * @Description: TODO
 * @Author xuf
 * @Date 2020/6/17
 * @Version V1.0
 */
@Data
public class CarType {
    /**
     * 列名：id
     * 注释：
     */
    private Integer id;

    /**
     * 列名：name
     * 注释：车型名
     */
    private String name;
}