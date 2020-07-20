package com.huiyou.msfw.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName Role
 * @Description: TODO
 * @Author zy
 * @Date 2020/6/10
 * @Version V1.0
 **/
@Data
public class Role {
    private Long id;
    private String roleName;
    private Integer isDel;
    private Date createTime;
    private String time;

    public void setCreateTime(Date createTime) {
        setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime));
        this.createTime = createTime;
    }
}
