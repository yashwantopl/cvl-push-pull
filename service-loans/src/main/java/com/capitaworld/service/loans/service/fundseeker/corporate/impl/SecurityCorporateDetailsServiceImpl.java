package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

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

import com.capitaworld.service.loans.domain.fundseeker.corporate.SecurityCorporateDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.SecurityCorporateDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SecurityCorporateDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.SecurityCorporateDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class SecurityCorporateDetailsServiceImpl implements SecurityCorporateDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(SecurityCorporateDetailsServiceImpl.class);

	@Autowired
	private SecurityCorporateDetailsRepository securityCorporateDetailsRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				SecurityCorporateDetailRequest securityCorporateDetailRequest = (SecurityCorporateDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, SecurityCorporateDetailRequest.class);
				SecurityCorporateDetail securityCorporateDetail = new SecurityCorporateDetail();
				BeanUtils.copyProperties(securityCorporateDetailRequest, securityCorporateDetail);
				if (securityCorporateDetailRequest.getId() == null) {
					securityCorporateDetail.setCreatedBy(frameRequest.getUserId());
					securityCorporateDetail.setCreatedDate(new Date());
				}
				securityCorporateDetail
						.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				securityCorporateDetail.setModifiedBy(frameRequest.getUserId());
				securityCorporateDetail.setModifiedDate(new Date());
				securityCorporateDetailsRepository.save(securityCorporateDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save securityCorporateDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<SecurityCorporateDetailRequest> getsecurityCorporateDetailsList(Long id) {
		List<SecurityCorporateDetail> securityCorporateDetails = securityCorporateDetailsRepository
				.listSecurityCorporateDetailFromAppId(id);
		List<SecurityCorporateDetailRequest> securityCorporateDetailRequests = new ArrayList<SecurityCorporateDetailRequest>();

		for (SecurityCorporateDetail detail : securityCorporateDetails) {
			SecurityCorporateDetailRequest securityCorporateDetailsRequest = new SecurityCorporateDetailRequest();
			BeanUtils.copyProperties(detail, securityCorporateDetailsRequest);
			securityCorporateDetailRequests.add(securityCorporateDetailsRequest);
		}
		return securityCorporateDetailRequests;
	}
}
