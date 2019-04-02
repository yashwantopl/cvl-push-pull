package com.capitaworld.service.loans.controller.fundprovider;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.retail.HomeLoanParameterRequest;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/home_parameter")
public class HomeLoanParameterController {

	private static final Logger logger = LoggerFactory.getLogger(HomeLoanParameterController.class.getName());
	@Autowired
	private HomeLoanParameterService homeLoanParameterService;

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody HomeLoanParameterRequest  homeLoanParameterRequest,HttpServletRequest request) {
		CommonDocumentUtils.startHook(logger, "save");
		// request must not be null
		if (homeLoanParameterRequest == null) {
			logger.warn("homeLoanParameterRequest Object can not be empty ==>{}", homeLoanParameterRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.REQUESTED_DATA_CAN_NOT_BE_EMPTY, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}

		if(homeLoanParameterRequest.getId()==null)
		{
			logger.warn("homeLoanParameterRequest id can not be empty ==>{}", homeLoanParameterRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.REQUESTED_DATA_CAN_NOT_BE_EMPTY, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}
		
		if(homeLoanParameterRequest.getId()==null)
		{
			logger.warn("user id can not be empty  ==>{}", homeLoanParameterRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.REQUESTED_DATA_CAN_NOT_BE_EMPTY, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if(userId==null)
		{
			logger.warn("userId  id can not be empty ==>{}", userId);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.REQUESTED_DATA_CAN_NOT_BE_EMPTY, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}
		homeLoanParameterRequest.setUserId(userId);
		
		boolean response = homeLoanParameterService.saveOrUpdate(homeLoanParameterRequest);
		if (response) {
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);
		} else {
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> get(@PathVariable("id") Long id) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "get");
		try {
			if (id == null) {
				logger.warn("ID Require to get home loan parameter ==>{}" , id);
				CommonDocumentUtils.endHook(logger, "end");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			HomeLoanParameterRequest parameterRequest= homeLoanParameterService.getHomeLoanParameterRequest(id);
			if (parameterRequest != null) {
				LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
				loansResponse.setData(parameterRequest);
				CommonDocumentUtils.endHook(logger, "end");
				return new ResponseEntity<>(loansResponse, HttpStatus.OK);
			} else {
				CommonDocumentUtils.endHook(logger, "end");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while getting home Loan Details==>", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

}
