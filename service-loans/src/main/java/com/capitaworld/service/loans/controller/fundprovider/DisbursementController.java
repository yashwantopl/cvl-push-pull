package com.capitaworld.service.loans.controller.fundprovider;

import javax.servlet.http.HttpServletRequest;

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
import com.capitaworld.service.loans.model.retail.CarLoanParameterRequest;
import com.capitaworld.service.loans.service.fundprovider.CarLoanParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
@RestController
@RequestMapping("/disbursement")
public class DisbursementController {
private static final Logger logger = LoggerFactory.getLogger(DisbursementController.class.getName());
	/*
	

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
