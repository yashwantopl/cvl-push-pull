package com.capitaworld.service.loans.service.sidbi.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.domain.sidbi.SidbiBasicDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.sidbi.SidbiBasicDetailRequest;
import com.capitaworld.service.loans.repository.sidbi.BasicDetailRepository;
import com.capitaworld.service.loans.service.sidbi.SidbiSpecificService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class SidbiSpecificServiceImpl implements SidbiSpecificService{

	private static final Logger logger = LoggerFactory.getLogger(SidbiSpecificServiceImpl.class);
	
	@Autowired
	BasicDetailRepository basicDetailRepository;
	
	@Override
	public boolean saveOrUpdateAdditionalData(SidbiBasicDetailRequest corporateAdditionalRequest, Long userId) throws LoansException {

		try {
			SidbiBasicDetail sidbiBasicDetail = null;
//					primaryCorporateRepository.getByApplicationAndUserId(corporateAdditionalRequest.getApplicationId(),
//					(CommonUtils.isObjectNullOrEmpty(corporateAdditionalRequest.getClientId()) ? userId : corporateAdditionalRequest.getClientId()));
			
			if (sidbiBasicDetail == null) {
				throw new NullPointerException("Application ID : "+ corporateAdditionalRequest.getApplicationId() 
					+ " and UserId==>" + userId);
			}
			
			BeanUtils.copyProperties(corporateAdditionalRequest, sidbiBasicDetail);

			sidbiBasicDetail.setModifiedBy(userId);
			sidbiBasicDetail.setModifiedDate(new Date());
			basicDetailRepository.save(sidbiBasicDetail);
			return true;
		} catch (Exception e) {
			logger.error("Exception : ", e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
}
