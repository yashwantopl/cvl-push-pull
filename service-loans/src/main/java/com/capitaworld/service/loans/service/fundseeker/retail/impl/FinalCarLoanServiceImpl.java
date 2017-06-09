package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalCarLoanDetail;
import com.capitaworld.service.loans.model.retail.FinalCarLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalCarLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalCarLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalCarLoanServiceImpl implements FinalCarLoanService {

	private static final Logger logger = LoggerFactory.getLogger(FinalCarLoanServiceImpl.class);

	@Autowired
	private FinalCarLoanDetailRepository finalCarLoanDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public boolean saveOrUpdate(FinalCarLoanDetailRequest finalCarLoanDetailRequest, Long userId) throws Exception {
		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(finalCarLoanDetailRequest.getClientId()) ? userId : finalCarLoanDetailRequest.getClientId());
			FinalCarLoanDetail finalCarLoanDetail = finalCarLoanDetailRepository
					.getByApplicationID(finalCarLoanDetailRequest.getApplicationId(), (CommonUtils.isObjectNullOrEmpty(finalCarLoanDetailRequest.getClientId()) ? userId : finalCarLoanDetailRequest.getClientId()));
			if (finalCarLoanDetail == null) {
				finalCarLoanDetail = new FinalCarLoanDetail();
				finalCarLoanDetail.setCreatedBy(userId);
				finalCarLoanDetail.setCreatedDate(new Date());
				finalCarLoanDetail.setIsActive(true);
				finalCarLoanDetail
						.setApplicationId(new LoanApplicationMaster(finalCarLoanDetailRequest.getApplicationId()));
			} else {
				finalCarLoanDetail.setModifiedBy(userId);
				finalCarLoanDetail.setModifiedDate(new Date());
			}
			String[] corporate = new String[CommonUtils.IgnorableCopy.CORPORATE.length + 1];
			corporate[CommonUtils.IgnorableCopy.CORPORATE.length] = CommonUtils.IgnorableCopy.ID; 
			BeanUtils.copyProperties(finalCarLoanDetailRequest, finalCarLoanDetail,corporate);
			finalCarLoanDetail = finalCarLoanDetailRepository.save(finalCarLoanDetail);
			
			//setting Flag to DB
			if(!CommonUtils.isObjectNullOrEmpty(finalCarLoanDetailRequest.getIsFinalInformationFilled())){
//				we are reusing this method and also same column in loanApplication master. it is actually using Corporate. 
				loanApplicationRepository.setIsFinalMcqMandatoryFilled(finalCarLoanDetailRequest.getApplicationId(), finalUserId, finalCarLoanDetailRequest.getIsFinalInformationFilled());
			}
			
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Final Car Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public FinalCarLoanDetailRequest get(Long applicationId, Long userId) throws Exception {
		try {
			FinalCarLoanDetail loanDetail = finalCarLoanDetailRepository.getByApplicationID(applicationId, userId);
			FinalCarLoanDetailRequest finalCarLoanDetailRequest = new FinalCarLoanDetailRequest();
			if (loanDetail == null) {
				Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
				finalCarLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
				return finalCarLoanDetailRequest;
			}
			BeanUtils.copyProperties(loanDetail, finalCarLoanDetailRequest);
			Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
			finalCarLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
			return finalCarLoanDetailRequest;
		} catch (Exception e) {
			logger.error("Error while getting Final Car Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
