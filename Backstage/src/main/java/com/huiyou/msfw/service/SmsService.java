package com.huiyou.msfw.service;

import com.huiyou.msfw.model.Sms;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SmsService
 * @Description: TODO
 * @Author xf
 * @Date 2020/6/12
 * @Version V1.0
 **/
public interface SmsService {

    /**
     * 查询message
     * @param tel
     * @return
     */
    List<Sms> selSms(String tel, String message);

    /**
     * 删除
     * @param tel
     */
    void delSms(String tel);

    /**
     * 添加
     * @param sms
     */
    void addSms(Sms sms);
}
