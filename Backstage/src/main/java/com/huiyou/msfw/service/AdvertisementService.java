package com.huiyou.msfw.service;

import com.huiyou.msfw.model.*;

import java.util.List;

public interface AdvertisementService {
    List<Advertisement> selectAdvertisment(AdvertisementExample advertisementExample);

    List<AdvertisementType> selectAdvertismentType(AdvertisementTypeExample advertisementExample);

    Integer countAdverTismentType(AdvertisementTypeExample advertisementExample);

    AdvertisementType selectAdvertismentTypeByTypeId(Long id);

    Integer updateAdverTisementTypeByAdvertisementType(AdvertisementType advertisementType);

    int insertAdvertisementTypeByAdvertisementType(AdvertisementType advertisementType);

    AdvertisementBusiness selectAdvertismentBusinessById(Long businessId);

    Integer countAdverTisment(AdvertisementExample advertisementExample);

    Advertisement selectAdvertismentByid(Long id);

    List<AdvertisementBusiness> selectAdvertismentBusinessByExample(AdvertisementBusinessExample advertisementBusinessExample);

    Integer updateAdvertisementByAdvertisement(Advertisement advertisement);

    Integer insertAdvertisement(Advertisement advertisement);

    Integer countAdverTismentBusinesses(AdvertisementBusinessExample advertisementBusinessExample);

    Integer updateAdvertisementBusinessByAdvertisementBusiness(AdvertisementBusiness advertisementBusiness);

    Integer insertAdvertisementBusiness(AdvertisementBusiness advertisementBusiness);
}
