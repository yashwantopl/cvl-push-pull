package com.capitaworld.service.loans.service.common.impl;

import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.mobile.MRetailApplicantResponse;
import com.capitaworld.service.loans.model.mobile.MobileUserRequest;
import com.capitaworld.service.loans.model.retail.PrimaryCarLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryLapLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryPersonalLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.MobileService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryCarLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryHomeLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryLapLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryPersonalLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class MobileLoanServiceImpl implements MobileService {

	private static final Logger logger = LoggerFactory.getLogger(MobileLoanServiceImpl.class.getName());
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	private PrimaryHomeLoanService primaryHomeLoanService;
	
	@Autowired
	private PrimaryCarLoanService primaryCarLoanService;
	
	@Autowired
	private PrimaryPersonalLoanService primaryPersonalLoanService;
	
	@Autowired
	private PrimaryLapLoanService primaryLapLoanService;
	
	@Override
	public MRetailApplicantResponse getApplicantDetails(MobileUserRequest mobileUserRequest) throws Exception {
		logger.info("Get Applicant Details From RetailApplicantDetail");
		RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.getByApplicationAndUserId(mobileUserRequest.getUserId(), mobileUserRequest.getApplicationId());
		if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
			MRetailApplicantResponse response = new MRetailApplicantResponse();
			BeanUtils.copyProperties(retailApplicantDetail, response);
			response.setApplicationId(mobileUserRequest.getApplicationId());
			if(!CommonUtils.isObjectNullOrEmpty(mobileUserRequest.getProductId())) {
				response.setProductId(mobileUserRequest.getProductId());
				LoanType loantype = CommonUtils.LoanType.getType(mobileUserRequest.getProductId());
				if(loantype.getValue() == LoanType.HOME_LOAN.getValue()) {
					logger.info("Get Applicant Home Loan Primary Details");
					response.setData(primaryHomeLoanService.get(mobileUserRequest.getApplicationId(), mobileUserRequest.getUserId()));
				
				} else if(loantype.getValue() == LoanType.PERSONAL_LOAN.getValue()) {
					logger.info("Get Applicant Personal Loan Primary Details");
					response.setData(primaryPersonalLoanService.get(mobileUserRequest.getApplicationId(), mobileUserRequest.getUserId()));
				
				} else if(loantype.getValue() == LoanType.CAR_LOAN.getValue()) {
					logger.info("Get Applicant Car Loan Primary Details");
					response.setData(primaryCarLoanService.get(mobileUserRequest.getApplicationId(), mobileUserRequest.getUserId()));
				
				} else if(loantype.getValue() == LoanType.LAP_LOAN.getValue()) {
					logger.info("Get Applicant LAP Loan Primary Details");
					response.setData(primaryLapLoanService.get(mobileUserRequest.getApplicationId(), mobileUserRequest.getUserId()));
				}
			}
			logger.info("Get Successfully Profile and Primary Applicant Details");
			return response;
		}
		return null;
	}
	
	@Override
	public Long saveApplicantDetails(MRetailApplicantResponse mRetailApplicantResponse) throws Exception {
		logger.info("Save Applicant Details...");
		RetailApplicantDetail retailApplicantDetail = new RetailApplicantDetail();
		if(!CommonUtils.isObjectNullOrEmpty(mRetailApplicantResponse.getId())) {
			retailApplicantDetail = retailApplicantDetailRepository.findOne(mRetailApplicantResponse.getId());
		}
		BeanUtils.copyProperties(mRetailApplicantResponse, retailApplicantDetail,"applicationId","userId","data");
		retailApplicantDetail.setModifiedDate(new Date());
		retailApplicantDetail.setModifiedBy(mRetailApplicantResponse.getUserId());
		retailApplicantDetail = retailApplicantDetailRepository.save(retailApplicantDetail);
		if(!CommonUtils.isObjectNullOrEmpty(mRetailApplicantResponse.getProductId()) && !CommonUtils.isObjectNullOrEmpty(mRetailApplicantResponse.getData())) {
			LoanType loantype = CommonUtils.LoanType.getType(mRetailApplicantResponse.getProductId());
			if(loantype.getValue() == LoanType.HOME_LOAN.getValue()) {
				logger.info("Start Save Applicant Home Loan Primary Details...");
				PrimaryHomeLoanDetailRequest homeLoanRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) mRetailApplicantResponse.getData(), PrimaryHomeLoanDetailRequest.class);
				primaryHomeLoanService.saveOrUpdate(homeLoanRequest, mRetailApplicantResponse.getUserId());
				
			} else if(loantype.getValue() == LoanType.PERSONAL_LOAN.getValue()) {
				logger.info("Start Save Applicant Personal Loan Primary Details...");
				PrimaryPersonalLoanRequest personalLoanRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) mRetailApplicantResponse.getData(),PrimaryPersonalLoanRequest.class);
				primaryPersonalLoanService.saveOrUpdate(personalLoanRequest, mRetailApplicantResponse.getUserId());
				
			} else if(loantype.getValue() == LoanType.CAR_LOAN.getValue()) {
				logger.info("Start Save Applicant Car Loan Primary Details...");
				PrimaryCarLoanDetailRequest carLoanRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) mRetailApplicantResponse.getData(),PrimaryCarLoanDetailRequest.class);
				primaryCarLoanService.saveOrUpdate(carLoanRequest, mRetailApplicantResponse.getUserId());
				
			} else if(loantype.getValue() == LoanType.LAP_LOAN.getValue()) {
				logger.info("Start Save Applicant LAP Loan Primary Details...");
				PrimaryLapLoanDetailRequest lapLoanDetailRequest =  MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) mRetailApplicantResponse.getData(),PrimaryLapLoanDetailRequest.class);;
				primaryLapLoanService.saveOrUpdate(lapLoanDetailRequest, mRetailApplicantResponse.getUserId());
			}
		}
		logger.info("Saved Successfully All Profile And Primary Data For Mobile App...");
		return retailApplicantDetail.getId();
	}
	
}
