package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class FinancialArrangementDetailsServiceImpl implements FinancialArrangementDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(SecurityCorporateDetailsServiceImpl.class);

	private static final String EXCEPTION_IN_SAVE_FINANCIAL_ARRANGEMENTS_DETAIL_MSG = "Exception in save financialArrangementsDetail :-";
	private static final String FOR_APPLICATION_ID_MSG = " For Application Id=====>{}";

	@Autowired
	private FinancialArrangementDetailsRepository financialArrangementDetailsRepository;
	
	@Autowired
	private PrimaryCorporateDetailRepository  primaryCorporateDetailRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				FinancialArrangementsDetailRequest financialArrangementsDetailRequest = (FinancialArrangementsDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, FinancialArrangementsDetailRequest.class);
				FinancialArrangementsDetail financialArrangementsDetail = null;
				if (financialArrangementsDetailRequest.getId() != null) {
					financialArrangementsDetail = financialArrangementDetailsRepository
							.findOne(financialArrangementsDetailRequest.getId());
				} else {
					financialArrangementsDetail = new FinancialArrangementsDetail();
					financialArrangementsDetail.setCreatedBy(frameRequest.getUserId());
					financialArrangementsDetail.setCreatedDate(new Date());
				}
				BeanUtils.copyProperties(financialArrangementsDetailRequest, financialArrangementsDetail);
				financialArrangementsDetail
						.setApplicationId(new LoanApplicationMaster(frameRequest.getApplicationId()));
				financialArrangementsDetail.setModifiedBy(frameRequest.getUserId());
				financialArrangementsDetail.setModifiedDate(new Date());
				financialArrangementDetailsRepository.save(financialArrangementsDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.error(EXCEPTION_IN_SAVE_FINANCIAL_ARRANGEMENTS_DETAIL_MSG,e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<FinancialArrangementsDetailRequest> getFinancialArrangementDetailsList(Long id, Long userId)
			throws LoansException {
		try {
			return prepareObject(financialArrangementDetailsRepository.listSecurityCorporateDetailFromAppId(id));
		}

		catch (Exception e) {
			logger.error(EXCEPTION_IN_SAVE_FINANCIAL_ARRANGEMENTS_DETAIL_MSG,e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}


	@Override
	public List<FinancialArrangementsDetailRequest> getManuallyAddedFinancialArrangementDetailsList(Long applicationId) {
		try {
			return prepareObject(financialArrangementDetailsRepository.getManuallyAddedFinancialDetail(applicationId));
		}
		catch (Exception e) {
			logger.error(EXCEPTION_IN_SAVE_FINANCIAL_ARRANGEMENTS_DETAIL_MSG,e);
			return Collections.emptyList();
		}
	}

	@Override
	public Boolean saveOrUpdate(List<FinancialArrangementsDetailRequest> finArrDetailRequest,
			Long applicationId, Long userId) {
		financialArrangementDetailsRepository.inActive(userId, applicationId);
		for (FinancialArrangementsDetailRequest req : finArrDetailRequest) {
			FinancialArrangementsDetail arrangementsDetail = new FinancialArrangementsDetail();
			BeanUtils.copyProperties(req, arrangementsDetail,"id");
			arrangementsDetail.setApplicationId(new LoanApplicationMaster(applicationId));
			arrangementsDetail.setCreatedBy(userId);
			arrangementsDetail.setCreatedDate(new Date());
			arrangementsDetail.setIsActive(true);
			financialArrangementDetailsRepository.save(arrangementsDetail);
		}
		return true;
	}

	@Override
	public Boolean saveOrUpdateManuallyAddedLoans(List<FinancialArrangementsDetailRequest> finArrDetailRequest,Long applicationId,Long userId) {
		for (FinancialArrangementsDetailRequest req : finArrDetailRequest) {
			FinancialArrangementsDetail arrangementsDetail = new FinancialArrangementsDetail();
			BeanUtils.copyProperties(req, arrangementsDetail,"id");
			arrangementsDetail.setApplicationId(new LoanApplicationMaster(applicationId));
			arrangementsDetail.setCreatedBy(userId);
			arrangementsDetail.setCreatedDate(new Date());
			arrangementsDetail.setIsActive(true);
			financialArrangementDetailsRepository.save(arrangementsDetail);
		}
		return true;
	}
	
	@Override
	public Boolean saveOrUpdate(List<FinancialArrangementsDetailRequest> existingLoanDetailRequest, Long applicationId,
			Long userId, Long directorId) {
		financialArrangementDetailsRepository.inActive(userId, applicationId,directorId);
		for (FinancialArrangementsDetailRequest req : existingLoanDetailRequest) {
			FinancialArrangementsDetail arrangementsDetail = new FinancialArrangementsDetail();
			BeanUtils.copyProperties(req, arrangementsDetail);
			arrangementsDetail.setApplicationId(new LoanApplicationMaster(applicationId));
			arrangementsDetail.setCreatedBy(userId);
			arrangementsDetail.setCreatedDate(new Date());
			arrangementsDetail.setIsActive(true);
			arrangementsDetail.setDirectorBackgroundDetail(new DirectorBackgroundDetail(directorId));
			financialArrangementDetailsRepository.save(arrangementsDetail);
		}
		return true;
	}

	@Override
	public FinancialArrangementsDetailRequest getTotalEmiAndSanctionAmountByApplicationId(Long applicationId) {
	    Double totalEmi = financialArrangementDetailsRepository.getTotalEmiByApplicationId(applicationId);
	    logger.info("getTotalOfEmiByApplicationId=====>" + totalEmi + FOR_APPLICATION_ID_MSG, applicationId);
	    Integer loanType = primaryCorporateDetailRepository.getPurposeLoanId(applicationId);
	    Double existingLimits = 0.0d;
	    List<String> loanTypes = null;
	    if(loanType == 2) {// Working Capital
	    	loanTypes = Arrays.asList(new String[]{"cash credit","overdraft","loan - commercial cash credit"});
	    	 existingLimits = financialArrangementDetailsRepository.getExistingLimits(applicationId, loanTypes);
	    }else if(loanType == 1) { //Term Loan
	    	loanTypes = Arrays.asList(new String[]{
	    			"demand loan",
	    			"medium term loan (period above 1 year and up to 3 years)",
	    			"long term loan (period above 3 years)",
	    			"lease finance",
	    			"hire purchase",
	    			"commercial vehicle loan",
	    			"equipment financing (construction office medical)",
	    			"property loan","others"
	    	});
			 existingLimits = financialArrangementDetailsRepository.getOutStandingAmount(applicationId, loanTypes);
	    }
	//	Double existingLimits = financialArrangementDetailsRepository.getExistingLimits(applicationId, loanTypes);
		logger.info("existingLimits=====>" + existingLimits + FOR_APPLICATION_ID_MSG, applicationId);
		FinancialArrangementsDetailRequest arrangementsDetailRequest = new FinancialArrangementsDetailRequest();
		arrangementsDetailRequest.setAmount(existingLimits);
		arrangementsDetailRequest.setEmi(totalEmi);
		return arrangementsDetailRequest;		
		
	}

	@Override
	public FinancialArrangementsDetailRequest getTotalEmiAndSanctionAmountByApplicationIdForUniforProduct(Long applicationId) {
		Double totalEmi = financialArrangementDetailsRepository.getTotalEmiByApplicationIdForUniformProduct(applicationId);
		logger.info("getTotalOfEmiByApplicationId for Uniform Product=====>" + totalEmi + FOR_APPLICATION_ID_MSG, applicationId);
		Double existingLimits = financialArrangementDetailsRepository.getExistingLimitsForUniformProduct(applicationId);
		logger.info("existingLimits for Uniform Product=====>" + existingLimits + FOR_APPLICATION_ID_MSG, applicationId);
		FinancialArrangementsDetailRequest arrangementsDetailRequest = new FinancialArrangementsDetailRequest();
		arrangementsDetailRequest.setAmount(existingLimits);
		arrangementsDetailRequest.setEmi(totalEmi);
		return arrangementsDetailRequest;
	}

	@Override
	public Double getTotalOfEmiByApplicationIdAndDirectorId(Long applicationId, Long directorId) {
		Double totalEmi = financialArrangementDetailsRepository.getTotalEmiByApplicationIdAndDirectorId(applicationId,directorId);
		logger.info("getTotalOfEmiByApplicationIdAndDirectorId {} For Application Id = {} DirectorId = {}", totalEmi ,applicationId,directorId);
		return totalEmi;
	}

	@Override
	public Double getTotalEmiOfAllDirByApplicationId(Long applicationId) {
		String [] creditCards = {"credit card","secured credit card","kisan credit card","corporate credit card","credit merchant card","credit premium card","credit retail card",
				"credit secured card","credit single - limited purpose card","corporate credit card","credit card - fleet",
				"credit stored-value smart card","credit co-branded credit card","credit affinity credit card","credit charge card","credit commercial card","credit line - open"};
		Double totalEmi = financialArrangementDetailsRepository.getTotalEmiOfAllDirByApplicationId(applicationId,Arrays.asList(creditCards));
		logger.info("getTotalEmiOfAllDirByApplicationId {} For Application Id = {}", totalEmi ,applicationId);
		return totalEmi;
	}

	/* (non-Javadoc)
	 * @see com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService#getFinancialArrangementDetailsListDirId(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<FinancialArrangementsDetailRequest> getFinancialArrangementDetailsListDirId(Long dirId, Long id) throws LoansException {
		try {
			return prepareObject(financialArrangementDetailsRepository.findByDirectorBackgroundDetailIdAndApplicationIdIdAndIsActive(dirId,id,true));
		}
		catch (Exception e) {
			logger.error(EXCEPTION_IN_SAVE_FINANCIAL_ARRANGEMENTS_DETAIL_MSG,e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	private List<FinancialArrangementsDetailRequest> prepareObject(List<FinancialArrangementsDetail> financialArrangementDetails){
		List<FinancialArrangementsDetailRequest> financialArrangementDetailRequests = new ArrayList<FinancialArrangementsDetailRequest>(financialArrangementDetails.size());

		for (FinancialArrangementsDetail detail : financialArrangementDetails) {
			FinancialArrangementsDetailRequest financialArrangementDetailsRequest = new FinancialArrangementsDetailRequest();
			BeanUtils.copyProperties(detail, financialArrangementDetailsRequest);
			if(!CommonUtils.isObjectNullOrEmpty(detail.getDirectorBackgroundDetail())) {
				financialArrangementDetailsRequest.setDirectorId(detail.getDirectorBackgroundDetail().getId());
			}
			financialArrangementDetailRequests.add(financialArrangementDetailsRequest);
		}
		return financialArrangementDetailRequests;
	}
}
