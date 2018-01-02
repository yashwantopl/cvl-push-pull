package com.capitaworld.service.loans.service.teaser.finalview.impl;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalCarLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.teaser.finalview.CarLoanFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalCarLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.teaser.finalview.CarLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.RetailFinalCommonApplicantService;
import com.capitaworld.service.loans.service.teaser.primaryview.CarLoanPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarLoanFinalViewServiceImpl implements CarLoanFinalViewService {

	private static final Logger logger = LoggerFactory.getLogger(CarLoanFinalViewServiceImpl.class);

	@Autowired
	private RetailApplicantDetailRepository applicantRepository;
    
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private CoApplicantService coApplicantService;
	
	@Autowired
	private GuarantorService guarantorService;
	
	@Autowired
	private RetailFinalCommonApplicantService finalCommonService;
	
	@Autowired
	private FinalCarLoanDetailRepository finalCLRepository;
	
	@Autowired
	private CarLoanPrimaryViewService carLoanPrimaryViewService;
	
	@Override
	public CarLoanFinalViewResponse getCarLoanFinalViewDetails(Long applicantId) throws Exception {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicantId);
		CarLoanFinalViewResponse clFinalViewResponse = new CarLoanFinalViewResponse();
		RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(applicationMaster.getUserId(), applicantId);
		if (!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
			RetailFinalViewResponse finalViewResponse = new RetailFinalViewResponse();
			//applicant final common details
			finalViewResponse.setApplicantCommonDetails(finalCommonService.getApplicantCommonInfo(applicantId, applicantDetail,applicationMaster.getProductId()));
			
			//co-applicant final common details
			try {
				finalViewResponse.setCoApplicantCommonDetails(coApplicantService.getCoApplicantFinalResponse(applicantId, applicationMaster.getUserId(),applicationMaster.getProductId()));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting CoApplicant final details");
			}
			
			//guarantor final common details
			try {
				finalViewResponse.setGuarantorCommonDetails(guarantorService.getGuarantorFinalViewResponse(applicantId, applicationMaster.getUserId(),applicationMaster.getProductId()));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting Guarantor final details");
			}
			clFinalViewResponse.setFinalViewResponse(finalViewResponse);
			
			//Car Loan primary details
			try { 
				clFinalViewResponse.setCarLoanPrimaryViewResponse(carLoanPrimaryViewService.getCarLoanPrimaryViewDetails(applicantId));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting CL primary details");
			}
			
			//Car Loan final details
			FinalCarLoanDetail finalCLDetails = finalCLRepository.getByApplicationID(applicantId, applicationMaster.getUserId());
			try {
				clFinalViewResponse.setColor(!CommonUtils.isObjectNullOrEmpty(finalCLDetails.getCarColour()) ? finalCLDetails.getCarColour() : "-" );
				clFinalViewResponse.setSupplier(!CommonUtils.isObjectNullOrEmpty(finalCLDetails.getCarSupplier()) ? finalCLDetails.getCarSupplier() : "-");
				clFinalViewResponse.setRegistrationNumber(!CommonUtils.isObjectNullOrEmpty(finalCLDetails.getCarRegistrationNumber()) ? finalCLDetails.getCarRegistrationNumber() : "-");
				clFinalViewResponse.setVehicleCost(!CommonUtils.isObjectNullOrEmpty(finalCLDetails.getVehicleCost()) ? CommonUtils.CurrencyFormat(finalCLDetails.getVehicleCost().toString()) : "-");
				clFinalViewResponse.setInsuaranceCost(!CommonUtils.isObjectNullOrEmpty(finalCLDetails.getInsuranceCost()) ? CommonUtils.CurrencyFormat(finalCLDetails.getInsuranceCost().toString()) : "-");
				clFinalViewResponse.setAccessoriesCost(!CommonUtils.isObjectNullOrEmpty(finalCLDetails.getAccessoriesCost()) ? CommonUtils.CurrencyFormat(finalCLDetails.getAccessoriesCost().toString()) : "-");
				clFinalViewResponse.setRoadTaxCost(!CommonUtils.isObjectNullOrEmpty(finalCLDetails.getRoadTax()) ? CommonUtils.CurrencyFormat(finalCLDetails.getRoadTax().toString()) : "-");
				clFinalViewResponse.setOtherCost(!CommonUtils.isObjectNullOrEmpty(finalCLDetails.getOthersCost()) ? CommonUtils.CurrencyFormat(finalCLDetails.getOthersCost().toString()) : "-");
				clFinalViewResponse.setTotalCost(!CommonUtils.isObjectNullOrEmpty(finalCLDetails.getLoanTotalCost()) ? CommonUtils.CurrencyFormat(finalCLDetails.getLoanTotalCost().toString()) : "-");
				
           
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting CL final details");
			}
		}
		return clFinalViewResponse;
	}

	
}
