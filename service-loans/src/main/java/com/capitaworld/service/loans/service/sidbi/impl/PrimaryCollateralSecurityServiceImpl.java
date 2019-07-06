/**
 * 
 */
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

import com.capitaworld.service.loans.domain.sidbi.PrimaryCollateralSecurity;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.sidbi.PrimaryCollateralSecurityRequest;
import com.capitaworld.service.loans.repository.sidbi.PrimaryCollateralSecurityRepository;
import com.capitaworld.service.loans.service.sidbi.PrimaryCollateralSecurityService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

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
				primaryCollateralSecurityList.add(primaryCollateralSecurityRequest);
			}
			return primaryCollateralSecurityList;
		}

		catch (Exception e) { 
			logger.error("Exception in get primaryCollateralSecurity :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}
		
}
