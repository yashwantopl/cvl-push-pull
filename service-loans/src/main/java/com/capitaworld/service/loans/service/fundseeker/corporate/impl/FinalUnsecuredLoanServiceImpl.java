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
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalUnsecureLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OverseasNetworkMappingDetail;
import com.capitaworld.service.loans.model.corporate.FinalUnsecuredLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinalUnsecuredLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OverseasNetworkRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinalUnsecuredLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalUnsecuredLoanServiceImpl implements FinalUnsecuredLoanService {

	private static final Logger logger = LoggerFactory.getLogger(FinalUnsecuredLoanServiceImpl.class.getName());

	@Autowired
	private FinalUnsecuredLoanDetailRepository unsecuredLoanDetailRepository;

	@Autowired
	private OverseasNetworkRepository networkRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public boolean saveOrUpdate(FinalUnsecuredLoanRequest unsecuredLoanRequest, Long userId) throws Exception {
		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(unsecuredLoanRequest.getClientId()) ? userId : unsecuredLoanRequest.getClientId());
			FinalUnsecureLoanDetail unsecuredLoanDetail = unsecuredLoanDetailRepository
					.getByApplicationAndUserId(unsecuredLoanRequest.getApplicationId(), (CommonUtils.isObjectNullOrEmpty(unsecuredLoanRequest.getClientId()) ? userId : unsecuredLoanRequest.getClientId()));
			if (unsecuredLoanDetail != null) {
				// Inactive Previous Mapping
				networkRepository.inActiveMappingByApplicationId(unsecuredLoanRequest.getApplicationId());
				unsecuredLoanDetail.setModifiedBy(userId);
				unsecuredLoanDetail.setModifiedDate(new Date());
			} else {
				unsecuredLoanDetail = new FinalUnsecureLoanDetail();
				unsecuredLoanDetail.setCreatedBy(userId);
				unsecuredLoanDetail.setCreatedDate(new Date());
				unsecuredLoanDetail.setIsActive(true);
				unsecuredLoanDetail.setApplicationId(new LoanApplicationMaster(unsecuredLoanRequest.getApplicationId()));
			}
			BeanUtils.copyProperties(unsecuredLoanRequest, unsecuredLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
			unsecuredLoanDetail = unsecuredLoanDetailRepository.save(unsecuredLoanDetail);

			// saving Data
			saveOverseasNetworkMapping(unsecuredLoanRequest.getApplicationId(), userId,
					unsecuredLoanRequest.getOverseasNetworkIds());
			
			//setting flag 
			loanApplicationRepository.setIsFinalMcqMandatoryFilled(unsecuredLoanRequest.getApplicationId(), finalUserId, CommonUtils.isObjectNullOrEmpty(unsecuredLoanRequest.getIsFinalMcqFilled()) ? false : unsecuredLoanRequest.getIsFinalMcqFilled());
			loanApplicationRepository.setFinalFilledCount(unsecuredLoanRequest.getApplicationId(), finalUserId,unsecuredLoanRequest.getFinalFilledCount());
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Final Unsecured Loan Details:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public FinalUnsecuredLoanRequest get(Long userId, Long applicationId) throws Exception {
		try {
			FinalUnsecureLoanDetail loanDetail = unsecuredLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
			if (loanDetail == null) {
				throw new NullPointerException(
						"FinalUnsecuredLoanDetail not exist in DB with ID=>" + userId + " applicationId==>" + applicationId);
			}
			FinalUnsecuredLoanRequest unsecuredLoanRequest = new FinalUnsecuredLoanRequest();
			BeanUtils.copyProperties(loanDetail, unsecuredLoanRequest);
			unsecuredLoanRequest.setOverseasNetworkIds(networkRepository.getOverseasNetworkIds(applicationId));
			return unsecuredLoanRequest;
		} catch (Exception e) {
			logger.error("Error while getting Final Unsecured Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
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
