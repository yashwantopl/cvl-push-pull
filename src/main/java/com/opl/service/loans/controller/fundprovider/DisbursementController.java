package com.opl.service.loans.controller.fundprovider;

import javax.servlet.http.HttpServletRequest;

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

import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.sanction.DisbursementHandOffDetailsReqRes;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.service.loans.service.fundprovider.DisbursementHandOffDetailsService;
@RestController
@RequestMapping("/disbursement")
public class DisbursementController {
	
	private static final Logger logger = LoggerFactory.getLogger(DisbursementController.class);
	
	@Autowired
	DisbursementHandOffDetailsService disbursementHandOffDetailsService;
	
	@RequestMapping(value = "/saveHandOff", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveHandOff(@RequestBody DisbursementHandOffDetailsReqRes detailsReqRes, HttpServletRequest request) {
		try {
			logger.info("Enter in saving co-origination disbursment hand off details ");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null || detailsReqRes.getApplicationId() == null) {
				logger.info("exit application id or user is null or empty !! ");
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			detailsReqRes.setCreatedBy(userId);
			detailsReqRes = disbursementHandOffDetailsService.save(detailsReqRes);
			logger.info("Saved successfully co-origination disbursment hand off details !! ");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value(),detailsReqRes),HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saving co-origination disbursment hand off details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/getHandOff/{applicationId}/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getHandOff(@PathVariable("applicationId") Long applicationId,@PathVariable("proposalId") Long proposalId,HttpServletRequest request) {
		try {
			logger.info("Enter in get co-origination disbursment hand off details ");
			DisbursementHandOffDetailsReqRes res = disbursementHandOffDetailsService.get(applicationId, proposalId);
			logger.info("Get successfully co-origination disbursment hand off details !! ");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Get data Successfully", HttpStatus.OK.value(),res),HttpStatus.OK);
		} catch (Exception e) {
			logger.error("No data foung while get co-origination disbursment hand off details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	
/*
    private static final Logger logger = LoggerFactory.getLogger(DisbursementController.class.getName());

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody CarLoanParameterRequest  carLoanParameterRequest,HttpServletRequest request) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "save");
		if (carLoanParameterRequest == null) {
			logger.warn("carLoanParameterRequest Object can not be empty ==>", carLoanParameterRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}

		if(carLoanParameterRequest.getId()==null)
		{
			logger.warn("carLoanParameterRequest id can not be empty ==>", carLoanParameterRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}
		
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		//Long userId=1755l;
		if(userId==null)
		{
			logger.warn("userId  id can not be empty ==>", userId);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}
		carLoanParameterRequest.setUserId(userId);
		boolean response = carLoanParameterService.saveOrUpdate(carLoanParameterRequest);
		if (response) {
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);
		} else {
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}*/
}
