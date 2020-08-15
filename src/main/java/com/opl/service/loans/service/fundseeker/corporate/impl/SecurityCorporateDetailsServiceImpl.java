package com.opl.service.loans.service.fundseeker.corporate.impl;

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

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.SecurityCorporateDetailRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.domain.fundseeker.corporate.SecurityCorporateDetail;
import com.opl.service.loans.repository.fundseeker.corporate.SecurityCorporateDetailsRepository;
import com.opl.service.loans.service.fundseeker.corporate.SecurityCorporateDetailsService;

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

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				SecurityCorporateDetailRequest securityCorporateDetailRequest = (SecurityCorporateDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, SecurityCorporateDetailRequest.class);
				SecurityCorporateDetail securityCorporateDetail = null;
				if (securityCorporateDetailRequest.getId() != null) {
					securityCorporateDetail = securityCorporateDetailsRepository
							.findOne(securityCorporateDetailRequest.getId());
				} else {
					securityCorporateDetail = new SecurityCorporateDetail();
					securityCorporateDetail.setCreatedBy(frameRequest.getUserId());
					securityCorporateDetail.setCreatedDate(new Date());
				}
				BeanUtils.copyProperties(securityCorporateDetailRequest, securityCorporateDetail);
				securityCorporateDetail.setApplicationId(new LoanApplicationMaster(frameRequest.getApplicationId()));
				securityCorporateDetail.setProposalId(new ApplicationProposalMapping(frameRequest.getProposalMappingId()));
				securityCorporateDetail.setModifiedBy(frameRequest.getUserId());
				securityCorporateDetail.setModifiedDate(new Date());
				securityCorporateDetailsRepository.save(securityCorporateDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save securityCorporateDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailsListFromProposalId(Long proposalId,Long userId) throws Exception {
		try {
			List<SecurityCorporateDetail> securityCorporateDetails = securityCorporateDetailsRepository
					.getSecurityCorporateDetailFromProposalId(proposalId);
			List<SecurityCorporateDetailRequest> securityCorporateDetailRequests = new ArrayList<SecurityCorporateDetailRequest>();

			for (SecurityCorporateDetail detail : securityCorporateDetails) {
				SecurityCorporateDetailRequest securityCorporateDetailsRequest = new SecurityCorporateDetailRequest();
				BeanUtils.copyProperties(detail, securityCorporateDetailsRequest);
				securityCorporateDetailsRequest.setAmountString(CommonUtils.checkString(detail.getAmount()));
				SecurityCorporateDetailRequest.printFields(securityCorporateDetailsRequest);
				securityCorporateDetailRequests.add(securityCorporateDetailsRequest);
			}
			return securityCorporateDetailRequests;
		}

		catch (Exception e) {
			logger.error("Exception  in save securityCorporateDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<SecurityCorporateDetailRequest> getsecurityCorporateDetailsList(Long id,Long userId) throws LoansException {
		try {
			List<SecurityCorporateDetail> securityCorporateDetails = securityCorporateDetailsRepository
					.getSecurityCorporateDetailFromAppId(id);
			List<SecurityCorporateDetailRequest> securityCorporateDetailRequests = new ArrayList<SecurityCorporateDetailRequest>();

			for (SecurityCorporateDetail detail : securityCorporateDetails) {
				SecurityCorporateDetailRequest securityCorporateDetailsRequest = new SecurityCorporateDetailRequest();
				BeanUtils.copyProperties(detail, securityCorporateDetailsRequest);
				securityCorporateDetailsRequest.setAmountString(CommonUtils.checkString(detail.getAmount()));
				SecurityCorporateDetailRequest.printFields(securityCorporateDetailsRequest);
				securityCorporateDetailRequests.add(securityCorporateDetailsRequest);
			}
			return securityCorporateDetailRequests;
		}

		catch (Exception e) {
			logger.error("Exception  in save securityCorporateDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
}
