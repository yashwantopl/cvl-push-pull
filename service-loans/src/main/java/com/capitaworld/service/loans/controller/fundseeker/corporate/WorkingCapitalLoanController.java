package com.capitaworld.service.loans.controller.fundseeker.corporate;

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
import com.capitaworld.service.loans.model.corporate.FinalWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinalWorkingCapitalLoanService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryWorkingCapitalLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/working_capital")
public class WorkingCapitalLoanController {

	private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalLoanController.class);

	@Autowired
	private FinalWorkingCapitalLoanService finalWCService;

	@Autowired
	private PrimaryWorkingCapitalLoanService primaryWCService;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "${final}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFinal(@RequestBody FinalWorkingCapitalLoanRequest capitalLoanRequest,
			HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		try {
			CommonDocumentUtils.startHook(logger, "saveFinal");
			// request must not be null
			
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			
			if(CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue() || 
					 CommonUtils.UserType.NETWORK_PARTNER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
						.intValue()){
				capitalLoanRequest.setClientId(clientId);
			}
			if (userId == null) {
				logger.warn("userId can not be empty ==>" + capitalLoanRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			if (capitalLoanRequest.getApplicationId() == null) {
				logger.warn("Application ID must not be empty ==>" + capitalLoanRequest.getApplicationId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Application ID can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			finalWCService.saveOrUpdate(capitalLoanRequest, userId);
			CommonDocumentUtils.endHook(logger, "saveFinal");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saving Final Working Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "${final}/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinal(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getFinal");
			Long userId = null;
			if(CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue() || 
					 CommonUtils.UserType.NETWORK_PARTNER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
						.intValue()){
				userId = clientId;
			}else{
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (userId == null || applicationId == null) {
				logger.warn("ID and ApplicationId Require to get Final Working Details. ID==>" + userId
						+ " Applcation ID ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			FinalWorkingCapitalLoanRequest response = finalWCService.get(userId, applicationId);
			CommonDocumentUtils.endHook(logger, "getFinal");
			if (response != null) {
				LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
				loansResponse.setData(response);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while getting Final Working Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "${primary}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> savePrimary(@RequestBody PrimaryWorkingCapitalLoanRequest capitalLoanRequest,
			HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		try {
			// request must not be null
			CommonDocumentUtils.startHook(logger, "savePrimary");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue() || 
					 CommonUtils.UserType.NETWORK_PARTNER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
						.intValue()){
				capitalLoanRequest.setClientId(clientId);
			}
			else
			
			if (userId == null) {
				logger.warn("userId can not be empty ==>" + capitalLoanRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			primaryWCService.saveOrUpdate(capitalLoanRequest,userId);
			CommonDocumentUtils.endHook(logger, "savePrimary");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while saving Primary Working Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "${primary}/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimary(@PathVariable("applicationId") Long applicationId, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getPrimary");
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue() || 
					 CommonUtils.UserType.NETWORK_PARTNER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
						.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (applicationId == null || userId == null) {
				logger.warn(
						"ID And UserId Require to get Primary Working Details ==>" + applicationId + " and UserId ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			PrimaryWorkingCapitalLoanRequest response = primaryWCService.get(applicationId,userId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(response);
			CommonDocumentUtils.endHook(logger, "getPrimary");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Primary Working Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@RequestMapping(value = "${primary}/get_client/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimaryClient(@PathVariable("applicationId") Long applicationId, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			if (applicationId == null) {
				logger.warn(
						"Application ID Require to get Primary Working Details ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			PrimaryWorkingCapitalLoanRequest response = primaryWCService.get(applicationId,null);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(response);
			CommonDocumentUtils.endHook(logger, "getPrimary");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Primary Working Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
