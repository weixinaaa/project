package com.huiyou.msfw.service;

import com.huiyou.msfw.model.Partner;
import com.huiyou.msfw.model.PartnerExample;

import java.util.List;
import java.util.Map;

public interface PartnerService {

    int insertPartner(Partner partner);

    Integer countPartnerExample(PartnerExample partnerExample);

    void delPartner(String id);

    List<Partner> selectByPartnerExample(PartnerExample partnerExample);

    Map<String,Object> getPartner(Integer pageNum, Integer pageSize, Partner partner);
}
