package com.capitaworld.service.loans.service.sidbi.impl;

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

import com.capitaworld.service.loans.domain.sidbi.FacilityDetails;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.sidbi.FacilityDetailsRequest;
import com.capitaworld.service.loans.repository.sidbi.FacilityDetailsRepository;
import com.capitaworld.service.loans.service.sidbi.FacilityDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * Created by pooja.patel on 19-06-2019.
 */

@Service
@Transactional
public class FacilityDetailsServiceImpl implements FacilityDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(FacilityDetailsServiceImpl.class.getName());

    @Autowired
    FacilityDetailsRepository facilityDetailsRepository;

    @Override
    public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
        try {
            for (Map<String, Object> obj : frameRequest.getDataList()) {
                FacilityDetailsRequest facilityDetailsRequest= (FacilityDetailsRequest) MultipleJSONObjectHelper.getObjectFromMap(obj, FacilityDetailsRequest.class);
                FacilityDetails facilityDetails= null;
                if (facilityDetailsRequest.getId() != null) {
                    facilityDetails = facilityDetailsRepository.findOne(facilityDetailsRequest.getId());
                } else {
                    facilityDetails = new FacilityDetails();
                    facilityDetails.setCreatedBy(frameRequest.getUserId());
                    facilityDetails.setCreatedDate(new Date());
                }

                BeanUtils.copyProperties(facilityDetailsRequest, facilityDetails);
                facilityDetails.setApplicationId(frameRequest.getApplicationId());
                facilityDetails.setModifiedBy(frameRequest.getUserId());
                facilityDetails.setModifiedDate(new Date());
                facilityDetails.setActive(true);
                facilityDetailsRepository.save(facilityDetails);
            }
            return true;
        }

        catch (Exception e) {
            logger.error("Exception in save facilityDetails :-",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }

    }

	@Override
	public List<FacilityDetailsRequest> getFacilityDetailsListAppId(Long applicationId) throws LoansException {
		List<FacilityDetailsRequest> facilityDetailsRequestList = null;
		try {
			List<FacilityDetails> facilityDetailsList = facilityDetailsRepository.getFacilityDetailsListAppId(applicationId);
			facilityDetailsRequestList = new ArrayList<>(facilityDetailsList.size());

			for (FacilityDetails detail : facilityDetailsList) {
				FacilityDetailsRequest facilityDetailsRequest = new FacilityDetailsRequest();
				BeanUtils.copyProperties(detail, facilityDetailsRequest);
				
				facilityDetailsRequestList.add(facilityDetailsRequest);
			}
			return facilityDetailsRequestList;
		}

		catch (Exception e) { 
			logger.error("Exception in get facilityDetailsRequestList :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
