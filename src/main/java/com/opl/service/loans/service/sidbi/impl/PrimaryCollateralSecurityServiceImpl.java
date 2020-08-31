/**
 * 
 */
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
import com.opl.mudra.api.loans.model.sidbi.PrimaryCollateralSecurityRequest;
import com.opl.mudra.api.sidbi.enums.SidbiCurrencyRate;
import com.opl.service.loans.domain.sidbi.PrimaryCollateralSecurity;
import com.opl.service.loans.repository.sidbi.PrimaryCollateralSecurityRepository;
import com.opl.service.loans.service.sidbi.PrimaryCollateralSecurityService;
import com.opl.service.loans.service.sidbi.SidbiSpecificService;

/**
 * @author vijay.chauhan
 *
 */

@Service
@Transactional
public class PrimaryCollateralSecurityServiceImpl implements PrimaryCollateralSecurityService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryCollateralSecurityServiceImpl.class.getName());
	
	@Autowired
	private PrimaryCollateralSecurityRepository primaryCollateralSecuRepository;
	
	@Autowired
	SidbiSpecificService sidbiSpecificService;
	
	@Override 
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				PrimaryCollateralSecurityRequest primaryCollateralSecurityRequest = MultipleJSONObjectHelper.getObjectFromMap(obj, PrimaryCollateralSecurityRequest.class);
				PrimaryCollateralSecurity primaryCollateralSecurity = null;
				if (primaryCollateralSecurityRequest.getId() != null) {
					primaryCollateralSecurity = primaryCollateralSecuRepository.findOne(primaryCollateralSecurityRequest.getId());
				} else {
					primaryCollateralSecurity = new PrimaryCollateralSecurity();
					primaryCollateralSecurity.setCreatedBy(frameRequest.getUserId());
					primaryCollateralSecurity.setCreatedDate(new Date());
				}
				this.convertAbsoluteValues(primaryCollateralSecurityRequest, frameRequest.getApplicationId());
				BeanUtils.copyProperties(primaryCollateralSecurityRequest, primaryCollateralSecurity);
				primaryCollateralSecurity.setApplicationId(frameRequest.getApplicationId());
				primaryCollateralSecurity.setModifiedBy(frameRequest.getUserId());
				primaryCollateralSecurity.setModifiedDate(new Date());
				primaryCollateralSecuRepository.save(primaryCollateralSecurity);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception in save primaryCollateralSecurity :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<PrimaryCollateralSecurityRequest> getPrimaryCollateralSecurityListAppId(Long applicationId) throws LoansException {
		List<PrimaryCollateralSecurityRequest> primaryCollateralSecurityList = null;
		try {
			List<PrimaryCollateralSecurity> primaryCollateralSecurity = primaryCollateralSecuRepository.getPrimaryCollateralSecurityListAppId(applicationId);
			primaryCollateralSecurityList = new ArrayList<>(primaryCollateralSecurity.size());

			for (PrimaryCollateralSecurity detail : primaryCollateralSecurity) {
				PrimaryCollateralSecurityRequest primaryCollateralSecurityRequest = new PrimaryCollateralSecurityRequest();				
				BeanUtils.copyProperties(detail, primaryCollateralSecurityRequest);
//				this.convertValuesIn(primaryCollateralSecurityRequest, applicationId);
				primaryCollateralSecurityList.add(primaryCollateralSecurityRequest);
			}
			return primaryCollateralSecurityList;
		}

		catch (Exception e) { 
			logger.error("Exception in get primaryCollateralSecurity :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}
		
	private void convertAbsoluteValues(PrimaryCollateralSecurityRequest primaryCollateralSecurityRequest,Long applicationId) throws LoansException {
		SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);

		primaryCollateralSecurityRequest.setMarketValue(CommonUtils.convertTwoDecimalAbsoluteValues(primaryCollateralSecurityRequest.getMarketValue(), sidbiCurrencyRateObj.getRate()));
		
	}
	
	private void convertValuesIn(PrimaryCollateralSecurityRequest primaryCollateralSecurityRequest,Long applicationId) throws LoansException {
		SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);
		primaryCollateralSecurityRequest.setMarketValue(CommonUtils.convertTwoDecimalValuesIn(primaryCollateralSecurityRequest.getMarketValue(), sidbiCurrencyRateObj.getRate()));
		
		
	}
}
