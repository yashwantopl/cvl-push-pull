package com.capitaworld.service.loans.service.sidbi.impl;

import com.capitaworld.service.loans.domain.sidbi.RawMaterialDetails;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.sidbi.RawMaterialDetailsRequest;
import com.capitaworld.service.loans.repository.sidbi.RawMaterialDetailsRepository;
import com.capitaworld.service.loans.service.sidbi.RawMaterialDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * Created by pooja.patel on 19-06-2019.
 */

@Service
@Transactional
public class RawMaterialDetailsServiceImpl implements RawMaterialDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialDetailsServiceImpl.class.getName());

    @Autowired
    RawMaterialDetailsRepository rawMaterialDetailsRepository;

    @Override
    public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
        try {
            for (Map<String, Object> obj : frameRequest.getDataList()) {
                RawMaterialDetailsRequest RawMaterialDetailsRequest= (RawMaterialDetailsRequest) MultipleJSONObjectHelper.getObjectFromMap(obj, RawMaterialDetailsRequest.class);
                RawMaterialDetails rawMaterialDetails= null;
                if (RawMaterialDetailsRequest.getId() != null) {
                    rawMaterialDetails = rawMaterialDetailsRepository.findOne(RawMaterialDetailsRequest.getId());
                } else {
                    rawMaterialDetails = new RawMaterialDetails();
                    rawMaterialDetails.setCreatedBy(frameRequest.getUserId());
                    rawMaterialDetails.setCreatedDate(new Date());
                }

                BeanUtils.copyProperties(RawMaterialDetailsRequest, rawMaterialDetails);
                rawMaterialDetails.setApplicationId(frameRequest.getApplicationId());
                rawMaterialDetails.setModifiedBy(frameRequest.getUserId());
                rawMaterialDetails.setModifiedDate(new Date());
                rawMaterialDetails.setActive(true);
                rawMaterialDetailsRepository.save(rawMaterialDetails);
            }
            return true;
        }

        catch (Exception e) {
            logger.error("Exception in save facilityDetails :-",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }

    }

}
