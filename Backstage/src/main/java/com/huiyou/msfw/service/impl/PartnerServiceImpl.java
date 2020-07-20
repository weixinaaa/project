package com.huiyou.msfw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huiyou.msfw.mapper.PartnerMapper;
import com.huiyou.msfw.model.Partner;
import com.huiyou.msfw.model.PartnerExample;
import com.huiyou.msfw.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerMapper partnerMapper;


    @Override
    public int insertPartner(Partner partner) {
        return partnerMapper.insertSelective(partner);
    }

    @Override
    public Integer countPartnerExample(PartnerExample partnerExample) {
        return partnerMapper.countByExample(partnerExample);
    }

    @Override
    public void delPartner(String id) {

        String[] split = id.split(",");
        for (String string : split) {
            partnerMapper.delPartner(Integer.valueOf(string));
        }
    }

    @Override
    public List<Partner> selectByPartnerExample(PartnerExample partnerExample) {
        return partnerMapper.selectByExample(partnerExample);
    }

    @Override
    public Map<String, Object> getPartner(Integer pageNum, Integer pageSize, Partner partner) {
        if(pageNum != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<Partner> partners = partnerMapper.getPartner(partner);
        PageInfo<Partner> partnerPageInfo = new PageInfo<>(partners);
        long count = partnerPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("count",count);
        map.put("data",partners);
        map.put("msg","成功");
        map.put("code",0);
        return map;
    }
}
