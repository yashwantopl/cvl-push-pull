package com.capitaworld.service.loans.controller.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.retail.RetailApplicantIncomeRequest;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantIncomeService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/retail_applicant_income")
public class RetailApplicantIncomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(RetailApplicantIncomeController.class);
	
	@Autowired
	private RetailApplicantIncomeService applicantIncomeService;

	@Autowired
	private LoanApplicationService loanApplicationService;

	@RequestMapping(value = "/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getIncomeDetails(@PathVariable("applicationId") Long applicationId){
		logger.info("Enter in Get Retail Income Details By App Id :- " + applicationId);
		try {
			List<RetailApplicantIncomeRequest> list = applicantIncomeService.getAll(applicationId);
			logger.info("Get Total Record For Retails Applicant Details ----->" + list.size());
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully get data", HttpStatus.OK.value(), list),HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Throw Exception while Get Retail Income Details : ",e);
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
			logger.error("Throw Exception while Save Retail Income Details : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/final_frame/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody FrameRequest frameRequest, HttpServletRequest request,
											  @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

		if (frameRequest == null) {
			logger.warn("frameRequest can not be empty ==>" + frameRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		// application id and user id must not be null
		if (frameRequest.getApplicationId() == null) {
			logger.warn("application id, user id and applicant Type must not be null ==>" + frameRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

		try {
			frameRequest.setUserId(userId);
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				frameRequest.setClientId(clientId);
			}
			//Checking Profile is Locked
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(frameRequest.getClientId()) ? userId
					: frameRequest.getClientId());
			Long applicationId = frameRequest.getApplicationId();

			Boolean finalLocked = loanApplicationService.isFinalLocked(applicationId, finalUserId);
			if(!CommonUtils.isObjectNullOrEmpty(finalLocked) && finalLocked.booleanValue()){
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			applicantIncomeService.saveOrUpdateIncomeDetailForGrossIncome(frameRequest);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Gross Income Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/final_frame/getList/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(@PathVariable Long id,
												 @RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (id == null) {
				logger.warn("ID Require to get Gross Income Details==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			List<RetailApplicantIncomeRequest> response = applicantIncomeService.getAll(id);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			Integer currencyId = null;

			loansResponse.setData(CommonDocumentUtils.getCurrency(currencyId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Gross Income Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
