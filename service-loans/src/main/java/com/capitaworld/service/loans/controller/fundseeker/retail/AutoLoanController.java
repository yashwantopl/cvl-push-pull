package com.capitaworld.service.loans.controller.fundseeker.retail;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.retail.ALOneformPrimaryRes;
import com.capitaworld.service.loans.model.retail.FinalCarLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryAutoLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryCarLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalCarLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryAutoLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/auto")
public class AutoLoanController {

	private static final Logger logger = LoggerFactory.getLogger(AutoLoanController.class);

	@Autowired
	private PrimaryAutoLoanService primaryAutoLoanService;

	@Autowired
	private FinalCarLoanService finalCarLoanService;

	@RequestMapping(value = "${primary}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFinal(@RequestBody PrimaryAutoLoanDetailRequest autoLoanDetailRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			// request must not be null
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (autoLoanDetailRequest == null) {
				logger.warn("autoLoanDetailRequest Object can not be empty ==>" + autoLoanDetailRequest);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (autoLoanDetailRequest.getApplicationId() == null) {
				logger.warn("Application ID must not be empty ==>" + autoLoanDetailRequest.getApplicationId());
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				autoLoanDetailRequest.setClientId(clientId);
			}
			primaryAutoLoanService.saveOrUpdate(autoLoanDetailRequest, userId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saving auto Loan Detail==>", e);
			return new ResponseEntity<LoansResponse>( new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "${final}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFinal(@RequestBody FinalCarLoanDetailRequest finalCarLoanDetailRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			// request must not be null
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				finalCarLoanDetailRequest.setClientId(clientId);
			}

			if (finalCarLoanDetailRequest == null) {
				logger.warn("finalCarLoanDetailRequest Object can not be empty ==>" + finalCarLoanDetailRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			if (finalCarLoanDetailRequest.getApplicationId() == null) {
				logger.warn("Application ID must not be empty ==>" + finalCarLoanDetailRequest.getId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Application ID can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			finalCarLoanService.saveOrUpdate(finalCarLoanDetailRequest, userId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saving Final Car  Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "${final}/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinal(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {

		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (applicationId == null) {
				logger.warn("ID Require to get Final Car Details ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			FinalCarLoanDetailRequest response = finalCarLoanService.get(applicationId, userId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Final car Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = "oneForm/loanReqDetails/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getLoanReqDetails(@PathVariable("applicationId") Long applicationId, HttpServletRequest request) {
		logger.info("Enter in Onefrom getLoanReqDetails");
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			
			if (CommonUtils.isObjectNullOrEmpty(applicationId) || CommonUtils.isObjectNullOrEmpty(userId)) {
				logger.warn("Application Id or UserId can not be empty ==>");
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SUCCESSFULLY_SAVED, HttpStatus.OK.value(),primaryAutoLoanService.getOneformPrimaryDetails(applicationId)),HttpStatus.OK);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "${primary}/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimary(@PathVariable("applicationId") Long applicationId, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			
			if (applicationId == null) {
				logger.warn("ID Require to get Primary Auto loan Details ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			PrimaryAutoLoanDetailRequest response = primaryAutoLoanService.get(applicationId, userId);
			if (response != null) {
				LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
				loansResponse.setData(response);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while getting Primary auto loan Details==>", e);
			return new ResponseEntity<LoansResponse>( new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = "oneForm/loanReqDetails/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveLoanReqDetails(@RequestBody ALOneformPrimaryRes alOneformPrimaryRes, HttpServletRequest request) {
		logger.info("Enter in save auto loanReqDetails oneform details");
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (CommonUtils.isObjectNullOrEmpty(alOneformPrimaryRes.getApplicationId()) || CommonUtils.isObjectNullOrEmpty(userId)) {
				logger.warn("Application Id or UserId can not be empty ==>");
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			alOneformPrimaryRes.setUserId(userId);
			return new ResponseEntity<>(new LoansResponse("Data Saved Successfully !!", HttpStatus.OK.value(), primaryAutoLoanService.saveOneformPrimaryDetails(alOneformPrimaryRes, userId)),HttpStatus.OK);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return new ResponseEntity<>(new LoansResponse("The application has encountered an error, please try again after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
