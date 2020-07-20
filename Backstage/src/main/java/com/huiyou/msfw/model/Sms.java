package com.huiyou.msfw.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName Sms
 * @Description: TODO
 * @Author xf
 * @Date 2020/6/12
 * @Version V1.0
 **/
@Data
public class Sms {
    private Long id;
    private String tel;
    private String message;
    private Date createTime;
    private String time;

    public void setCreateTime(Date createTime) {
        setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime));
        this.createTime = createTime;
    }
}
