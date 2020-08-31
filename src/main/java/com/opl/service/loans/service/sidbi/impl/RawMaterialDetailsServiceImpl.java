package com.opl.service.loans.service.sidbi.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.common.MultipleJSONObjectHelper;
import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.sidbi.RawMaterialDetailsRequest;
import com.opl.service.loans.domain.sidbi.RawMaterialDetails;
import com.opl.service.loans.repository.sidbi.RawMaterialDetailsRepository;
import com.opl.service.loans.service.sidbi.RawMaterialDetailsService;

/**
 * Created by pooja.patel on 19-06-2019.
 */

@Service
@Transactional
public class RawMaterialDetailsServiceImpl implements RawMaterialDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialDetailsServiceImpl.class.getName());

    @Autowired
    RawMaterialDetailsRepository rawMaterialDetailsRepository;

    @Override
    public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
        try {
            for (Map<String, Object> obj : frameRequest.getDataList()) {
                RawMaterialDetailsRequest rawMaterialDetailsRequest= MultipleJSONObjectHelper.getObjectFromMap(obj, RawMaterialDetailsRequest.class);
                RawMaterialDetails rawMaterialDetails= null;
                if (rawMaterialDetailsRequest.getId() != null) {
                    rawMaterialDetails = rawMaterialDetailsRepository.findOne(rawMaterialDetailsRequest.getId());
                } else {
                    rawMaterialDetails = new RawMaterialDetails();
                    rawMaterialDetails.setCreatedBy(frameRequest.getUserId());
                    rawMaterialDetails.setCreatedDate(new Date());
                }

                BeanUtils.copyProperties(rawMaterialDetailsRequest, rawMaterialDetails);
                rawMaterialDetails.setApplicationId(frameRequest.getApplicationId());
                rawMaterialDetails.setModifiedBy(frameRequest.getUserId());
                rawMaterialDetails.setModifiedDate(new Date());
                rawMaterialDetailsRepository.save(rawMaterialDetails);
            }
            return true;
        }

        catch (Exception e) {
            logger.error("Exception in save facilityDetails :-",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }

    }

	@Override
	public List<RawMaterialDetailsRequest> getRawMaterialDetailsListAppId(Long applicationId) throws LoansException {
		List<RawMaterialDetailsRequest> rawMaterialDetailsRequestList = null;
		try {
			List<RawMaterialDetails> rawMaterialDetailsList = rawMaterialDetailsRepository.getRawMaterialDetailsListAppId(applicationId);
			rawMaterialDetailsRequestList = new ArrayList<>(rawMaterialDetailsList.size());

			for (RawMaterialDetails detail : rawMaterialDetailsList) {
				RawMaterialDetailsRequest rawMaterialDetailsRequest = new RawMaterialDetailsRequest();
				BeanUtils.copyProperties(detail, rawMaterialDetailsRequest);
				
				rawMaterialDetailsRequestList.add(rawMaterialDetailsRequest);
			}
			return rawMaterialDetailsRequestList;
		}

		catch (Exception e) { 
			logger.error("Exception in get rawMaterialDetailsRequestList :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
