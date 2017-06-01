package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalHomeLoanDetail;
import com.capitaworld.service.loans.model.retail.FinalHomeLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalHomeLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalHomeLoanServiceImpl implements FinalHomeLoanService {

	private static final Logger logger = LoggerFactory.getLogger(FinalHomeLoanServiceImpl.class);

	@Autowired
	private FinalHomeLoanDetailRepository finalHomeLoanDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public boolean saveOrUpdate(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest, Long userId) throws Exception {
		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailRequest.getClientId()) ? userId : finalHomeLoanDetailRequest.getClientId());
			FinalHomeLoanDetail finalHomeLoanDetail = finalHomeLoanDetailRepository
					.getByApplicationAndUserId(finalHomeLoanDetailRequest.getApplicationId(), finalUserId);
			if (finalHomeLoanDetail == null) {
				finalHomeLoanDetail = new FinalHomeLoanDetail();
				finalHomeLoanDetail.setCreatedBy(userId);
				finalHomeLoanDetail.setCreatedDate(new Date());
				finalHomeLoanDetail.setIsActive(true);
				finalHomeLoanDetail
						.setApplicationId(new LoanApplicationMaster(finalHomeLoanDetailRequest.getApplicationId()));
			} else {
				finalHomeLoanDetail.setModifiedBy(userId);
				finalHomeLoanDetail.setModifiedDate(new Date());
			}
			String[] corporate = new String[CommonUtils.IgnorableCopy.CORPORATE.length + 1];
			corporate[CommonUtils.IgnorableCopy.CORPORATE.length] = CommonUtils.IgnorableCopy.ID;
			BeanUtils.copyProperties(finalHomeLoanDetailRequest, finalHomeLoanDetail,corporate);
			finalHomeLoanDetail = finalHomeLoanDetailRepository.save(finalHomeLoanDetail);
			
			//setting Flag to DB
			if(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailRequest.getIsFinalInformationFilled())){
//				we are reusing this method and also same column in loanApplication master. it is actually using Corporate. 
				loanApplicationRepository.setIsFinalMcqMandatoryFilled(finalHomeLoanDetailRequest.getApplicationId(), finalUserId, finalHomeLoanDetailRequest.getIsFinalInformationFilled());
			}
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Final Home Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public FinalHomeLoanDetailRequest get(Long applicationId, Long userId) throws Exception {
		try {
			FinalHomeLoanDetail loanDetail = finalHomeLoanDetailRepository.getByApplicationAndUserId(applicationId,
					userId);
			if (loanDetail == null) {
				return null;
			}
			FinalHomeLoanDetailRequest finalHomeLoanDetailRequest = new FinalHomeLoanDetailRequest();
			BeanUtils.copyProperties(loanDetail, finalHomeLoanDetailRequest);
			Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
			finalHomeLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
			return finalHomeLoanDetailRequest;
		} catch (Exception e) {
			logger.error("Error while getting Final Home Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
