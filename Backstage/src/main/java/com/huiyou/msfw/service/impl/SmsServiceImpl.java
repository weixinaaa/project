package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.SmsMapper;
import com.huiyou.msfw.model.Sms;
import com.huiyou.msfw.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsMapper smsMapper;


    @Override
    public List<Sms> selSms(String tel, String message) {
        List<Sms> sms = smsMapper.selSms(tel,message);
        return sms;
    }

    @Override
    public void delSms(String tel) {
        smsMapper.delSms(tel);
    }

    @Override
    public void addSms(Sms sms) {
        smsMapper.addSms(sms);
    }
}
