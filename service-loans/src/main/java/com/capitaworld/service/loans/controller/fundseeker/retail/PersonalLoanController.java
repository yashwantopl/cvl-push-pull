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
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryPersonalLoanRequest;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryPersonalLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/personal")
public class PersonalLoanController {

	private static final Logger logger = LoggerFactory.getLogger(PersonalLoanController.class);

	@Autowired
	private PrimaryPersonalLoanService primaryPersonalLoanService;

	@RequestMapping(value = "${primary}/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "${primary}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFinal(@RequestBody PrimaryPersonalLoanRequest personalLoanRequest,
			HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			// request must not be null
			if (personalLoanRequest == null) {
				logger.warn("personalLoanRequest Object can not be empty ==>" + personalLoanRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			if (personalLoanRequest.getId() == null) {
				logger.warn("Application ID must not be empty ==>" + personalLoanRequest.getId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if(CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()){
				personalLoanRequest.setClientId(clientId);
			}
			primaryPersonalLoanService.saveOrUpdate(personalLoanRequest, userId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving personal==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "${primary}/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimary(@PathVariable("id") Long id, HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()){
				userId = clientId;
			}else{
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (id == null) {
				logger.warn("ID Require to get Primary Personal loan Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			PrimaryPersonalLoanRequest response = primaryPersonalLoanService.get(id, userId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Primary Personalloan Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	//For Client
			@RequestMapping(value = "${primary}/get_primary_info/{applicationId}/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<LoansResponse> getPrimary(@PathVariable("applicationId") Long applicationId,@PathVariable("userId") Long userId) {
				// request must not be null
				try {
					PrimaryPersonalLoanRequest response = primaryPersonalLoanService.get(applicationId, userId);
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
					logger.error("Error while getting Primary home loan Details from Client==>", e);
					return new ResponseEntity<LoansResponse>(
							new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
}
