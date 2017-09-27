package com.capitaworld.service.loans.controller.mobile;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.mobile.MLoanDetailsResponse;
import com.capitaworld.service.loans.model.mobile.MRetailApplicantResponse;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;
import com.capitaworld.service.loans.service.common.MobileService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;


@RestController
@RequestMapping("/mobile")
public class MobileLoanController {

	private static final Logger logger = LoggerFactory.getLogger(MobileLoanController.class.getName());
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private MobileService mobileService;
	
	@RequestMapping(value="/loanList",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getLoanList(@RequestBody MobileLoanRequest mobileUserRequest){
		logger.info("Enter in get loan list for mobile app");
		try {
			List<MLoanDetailsResponse> response = loanApplicationService.getLoanListForMobile(mobileUserRequest.getUserId());
			logger.info("Successfully get loan lost for mobile app");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully get data", HttpStatus.OK.value(),response),HttpStatus.OK);
		} catch(Exception e) {
			logger.warn("Error While Get Loan List For Mobile App");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getApplicantDetails",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getApplicantDetails(@RequestBody MobileLoanRequest mobileUserRequest){
		logger.info("Enter in get applicant details for mobile app");
		try {
			MRetailApplicantResponse response = mobileService.getApplicantDetails(mobileUserRequest);
			logger.info("Successfully get applicant details for mobile app");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully get data", HttpStatus.OK.value(),response),HttpStatus.OK);
		} catch(Exception e) {
			logger.warn("Error While get applicant details for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/saveApplicantDetails",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveApplicantDetails(@RequestBody MRetailApplicantResponse mRetailApplicantResponse){
		logger.info("Enter in save applicant details for mobile app");
		try {
			Long id = mobileService.saveApplicantDetails(mRetailApplicantResponse);
			logger.info("Successfully save applicant details for mobile app");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully save data", HttpStatus.OK.value(),id),HttpStatus.OK);
		} catch(Exception e) {
			logger.warn("Error While save applicant details for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/lockPrimary",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> lockPrimary(@RequestBody MobileLoanRequest mobileUserRequest){
		logger.info("Enter in lock profile and primary details for mobile app");
		try {
			loanApplicationService.lockPrimary(mobileUserRequest.getApplicationId(),mobileUserRequest.getUserId(), true);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Your profile & primary details are locked", HttpStatus.OK.value()),HttpStatus.OK);
		} catch(Exception e) {
			logger.warn("Error While lock profile and primary details for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
}
