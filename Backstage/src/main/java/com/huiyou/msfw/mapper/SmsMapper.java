package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.Sms;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**

 * @Author: xf

 * @Date: 2020年6月12日  下午5:55

 * @Description:

 */
public interface SmsMapper {
    /**
     * 查询message
     * @param tel
     * @return
     */
    List<Sms> selSms(@Param("tel")String tel,@Param("message")String message);

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
