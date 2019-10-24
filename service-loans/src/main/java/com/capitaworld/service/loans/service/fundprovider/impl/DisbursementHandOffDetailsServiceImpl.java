/**
 * Date :- 24 OCT 2019
 * Pr :- Co-Origination
 * Ds :- Post Disbursment Functionality
 */

package com.capitaworld.service.loans.service.fundprovider.impl;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundprovider.DisbursementHandOffDetails;
import com.capitaworld.service.loans.model.sanction.DisbursementHandOffDetailsReqRes;
import com.capitaworld.service.loans.repository.fundprovider.DisbursementHandOffDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.fundprovider.DisbursementHandOffDetailsService;

@Service
@Transactional
public class DisbursementHandOffDetailsServiceImpl implements DisbursementHandOffDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(DisbursementHandOffDetailsServiceImpl.class);
	
	@Autowired
	private LoanSanctionRepository loanSanctionRepository;
	
	@Autowired
	DisbursementHandOffDetailsRepository detailsRepository;
	
	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;
	
	@Override
	public DisbursementHandOffDetailsReqRes get(Long applicationId, Long proposalId) throws Exception {
		
		DisbursementHandOffDetailsReqRes res = new DisbursementHandOffDetailsReqRes();
		DisbursementHandOffDetails disHandOffDetails = detailsRepository.findByApplicationIdAndIsActive(applicationId, true);
		if(disHandOffDetails != null) {
			BeanUtils.copyProperties(disHandOffDetails, res);
			res.setApplicationCode(applicationProposalMappingRepository.getAppCodeByProposalIdAndApplicationId(proposalId, applicationId));
			return res;
		}

		Object[] sanctionDetails = loanSanctionRepository.getTenureAndRoiByAppIdAndNbfcFlow(applicationId,2);
		if(sanctionDetails == null) {
			logger.info("Throw Exception while not data dound from sanctioned details");
			throw new Exception("Sanctioned Details not found while get Co-Origination Disbursment HandOfff Details By ApplicationId " + applicationId);
		}
		
		res.setTenure(sanctionDetails[0] != null ? Integer.valueOf(sanctionDetails[0].toString()) : null);
		res.setInterestRate(sanctionDetails[1] != null ? Double.valueOf(sanctionDetails[1].toString()) : null);
		res.setApplicationCode(applicationProposalMappingRepository.getAppCodeByProposalIdAndApplicationId(proposalId, applicationId));
		return res;

	}
	
	
	@Override
	public DisbursementHandOffDetailsReqRes save(DisbursementHandOffDetailsReqRes request) {
		
		DisbursementHandOffDetails disHandOffDetails = null;
		if(request.getId() != null) {
			disHandOffDetails = detailsRepository.findOne(request.getId());
		} else if (request.getApplicationId() != null) {
			disHandOffDetails = detailsRepository.findByApplicationIdAndIsActive(request.getApplicationId(), true);
		}
		
		if(disHandOffDetails != null) {
			disHandOffDetails.setModifiedBy(request.getModifiedBy());
			disHandOffDetails.setModifiedDate(new Date());
		} else {
			disHandOffDetails = new DisbursementHandOffDetails();
			disHandOffDetails.setApplicationId(request.getApplicationId());
			disHandOffDetails.setCreatedBy(request.getCreatedBy());
			disHandOffDetails.setCreatedDate(new Date());
			disHandOffDetails.setIsActive(true);
		}
		
		BeanUtils.copyProperties(request, disHandOffDetails,"id","applicationId","createdBy","createdDate","modifiedBy","modifiedDate","isActive");
		disHandOffDetails = detailsRepository.save(disHandOffDetails);
		request.setId(disHandOffDetails.getId());
		return request;
	}
	

}
