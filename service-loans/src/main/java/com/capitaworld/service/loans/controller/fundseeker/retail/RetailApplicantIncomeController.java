package com.capitaworld.service.loans.controller.fundseeker.retail;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.retail.RetailApplicantIncomeRequest;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantIncomeService;

@RestController
@RequestMapping(value = "/retail_applicant_income")
public class RetailApplicantIncomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(RetailApplicantIncomeController.class);
	
	@Autowired
	private RetailApplicantIncomeService applicantIncomeService;
	
	@RequestMapping(value = "/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getIncomeDetails(@PathVariable("applicationId") Long applicationId){
		logger.info("Enter in Get Retail Income Details By App Id :- " + applicationId);
		try {
			List<RetailApplicantIncomeRequest> list = applicantIncomeService.getAll(applicationId);
			logger.info("Get Total Record For Retails Applicant Details ----->" + list.size());
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully get data", HttpStatus.OK.value(), list),HttpStatus.OK);	
		} catch (Exception e) {
			logger.info("Throw Exception while Get Retail Income Details");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/saveAll", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveAll(@RequestBody List<RetailApplicantIncomeRequest> applicantReqList){
		logger.info("Enter in SaveAll Retail Income Details");
		try {
			applicantIncomeService.saveAll(applicantReqList);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully save data", HttpStatus.OK.value()),HttpStatus.OK);	
		} catch (Exception e) {
			logger.info("Throw Exception while Save Retail Income Details");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
