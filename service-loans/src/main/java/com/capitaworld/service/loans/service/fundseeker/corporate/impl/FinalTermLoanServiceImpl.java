package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OverseasNetworkMappingDetail;
import com.capitaworld.service.loans.model.corporate.FinalTermLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinalTermLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OverseasNetworkRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinalTermLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalTermLoanServiceImpl implements FinalTermLoanService {

	private static final Logger logger = LoggerFactory.getLogger(FinalTermLoanServiceImpl.class.getName());

	@Autowired
	private FinalTermLoanDetailRepository termLoanDetailRepository;

	@Autowired
	private OverseasNetworkRepository networkRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public boolean saveOrUpdate(FinalTermLoanRequest termLoanRequest, Long userId) throws Exception {
		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(termLoanRequest.getClientId()) ? userId : termLoanRequest.getClientId());
			FinalTermLoanDetail termLoanDetail = termLoanDetailRepository
					.getByApplicationAndUserId(termLoanRequest.getApplicationId(), (CommonUtils.isObjectNullOrEmpty(termLoanRequest.getClientId()) ? userId : termLoanRequest.getClientId()));
			if (termLoanDetail != null) {
				// Inactive Previous Mapping
				networkRepository.inActiveMappingByApplicationId(termLoanRequest.getApplicationId());
				termLoanDetail.setModifiedBy(userId);
				termLoanDetail.setModifiedDate(new Date());
			} else {
				termLoanDetail = new FinalTermLoanDetail();
				termLoanDetail.setCreatedBy(userId);
				termLoanDetail.setCreatedDate(new Date());
				termLoanDetail.setIsActive(true);
				termLoanDetail.setApplicationId(new LoanApplicationMaster(termLoanRequest.getApplicationId()));
			}
			BeanUtils.copyProperties(termLoanRequest, termLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
			termLoanDetail = termLoanDetailRepository.save(termLoanDetail);

			// saving Data
			saveOverseasNetworkMapping(termLoanRequest.getApplicationId(), userId,
					termLoanRequest.getOverseasNetworkIds());
			
			//setting flag 
			loanApplicationRepository.setIsFinalMcqMandatoryFilled(termLoanRequest.getApplicationId(), finalUserId, CommonUtils.isObjectNullOrEmpty(termLoanRequest.getIsFinalMcqFilled()) ? false : termLoanRequest.getIsFinalMcqFilled());
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Final Term Loan Details:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public FinalTermLoanRequest get(Long userId, Long applicationId) throws Exception {
		try {
			FinalTermLoanDetail loanDetail = termLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
			if (loanDetail == null) {
				throw new NullPointerException(
						"FinalTermLoanDetail not exist in DB with ID=>" + userId + " applicationId==>" + applicationId);
			}
			FinalTermLoanRequest termLoanRequest = new FinalTermLoanRequest();
			BeanUtils.copyProperties(loanDetail, termLoanRequest);
			termLoanRequest.setOverseasNetworkIds(networkRepository.getOverseasNetworkIds(applicationId));
			return termLoanRequest;
		} catch (Exception e) {
			logger.error("Error while getting Final Term Loan Details:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	private void saveOverseasNetworkMapping(Long applicationId, Long userId, List<Integer> overseasNetworkIds) {
		for (Integer networkId : overseasNetworkIds) {
			OverseasNetworkMappingDetail mappingDetail = new OverseasNetworkMappingDetail();
			mappingDetail.setApplicationId(applicationId);
			mappingDetail.setOverseasNetworkId(networkId);
			mappingDetail.setActive(true);
			mappingDetail.setCreatedDate(new Date());
			mappingDetail.setCreatedBy(userId);
			networkRepository.save(mappingDetail);
		}
	}
}
