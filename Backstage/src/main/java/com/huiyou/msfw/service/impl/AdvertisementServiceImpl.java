package com.huiyou.msfw.service.impl;

import com.huiyou.msfw.mapper.AdvertisementBusinessMapper;
import com.huiyou.msfw.mapper.AdvertisementMapper;
import com.huiyou.msfw.mapper.AdvertisementTypeMapper;
import com.huiyou.msfw.model.*;
import com.huiyou.msfw.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Autowired
    private AdvertisementBusinessMapper advertisementBusinessMapper;

    @Autowired
    private AdvertisementTypeMapper advertisementTypeMapper;

    @Override
    public List<Advertisement> selectAdvertisment(AdvertisementExample advertisementExample) {
        return advertisementMapper.selectByExample(advertisementExample);
    }

    @Override
    public List<AdvertisementType> selectAdvertismentType(AdvertisementTypeExample advertisementTypeExample) {
        return advertisementTypeMapper.selectByExample(advertisementTypeExample);
    }

    @Override
    public Integer countAdverTismentType(AdvertisementTypeExample advertisementTypeExample) {
        return advertisementTypeMapper.countByExample(advertisementTypeExample);
    }

    @Override
    public AdvertisementType selectAdvertismentTypeByTypeId(Long id) {
        return advertisementTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateAdverTisementTypeByAdvertisementType(AdvertisementType advertisementType) {
        return advertisementTypeMapper.updateByPrimaryKeySelective(advertisementType);
    }

    @Override
    public int insertAdvertisementTypeByAdvertisementType(AdvertisementType advertisementType) {
        return advertisementTypeMapper.insertSelective(advertisementType);
    }

    @Override
    public AdvertisementBusiness selectAdvertismentBusinessById(Long businessId) {
        return advertisementBusinessMapper.selectByPrimaryKey(businessId);
    }

    @Override
    public Integer countAdverTisment(AdvertisementExample advertisementExample) {
        return advertisementMapper.countByExample(advertisementExample);
    }

    @Override
    public Advertisement selectAdvertismentByid(Long id) {
        return advertisementMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AdvertisementBusiness> selectAdvertismentBusinessByExample(AdvertisementBusinessExample advertisementBusinessExample) {
        return advertisementBusinessMapper.selectByExample(advertisementBusinessExample);
    }

    @Override
    public Integer updateAdvertisementByAdvertisement(Advertisement advertisement) {
        return advertisementMapper.updateByPrimaryKeySelective(advertisement);
    }

    @Override
    public Integer insertAdvertisement(Advertisement advertisement) {
        return advertisementMapper.insertSelective(advertisement);
    }

    @Override
    public Integer countAdverTismentBusinesses(AdvertisementBusinessExample advertisementBusinessExample) {
        return advertisementBusinessMapper.countByExample(advertisementBusinessExample);
    }

    @Override
    public Integer updateAdvertisementBusinessByAdvertisementBusiness(AdvertisementBusiness advertisementBusiness) {
        return advertisementBusinessMapper.updateByPrimaryKeySelective(advertisementBusiness);
    }

    @Override
    public Integer insertAdvertisementBusiness(AdvertisementBusiness advertisementBusiness) {
        return advertisementBusinessMapper.insertSelective(advertisementBusiness);
    }
}
