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
import com.capitaworld.service.loans.model.retail.FinalHomeLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalHomeLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryHomeLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/home")
public class HomeLoanController {

	private static final Logger logger = LoggerFactory.getLogger(HomeLoanController.class);

	@Autowired
	private PrimaryHomeLoanService primaryHomeLoanService;

	@Autowired
	private FinalHomeLoanService finalHomeLoanService;

	@RequestMapping(value = "${primary}/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "${primary}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFinal(
			@RequestBody PrimaryHomeLoanDetailRequest primaryHomeLoanDetailRequest, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			// request must not be null
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
				primaryHomeLoanDetailRequest.setClientId(clientId);
			}

			if (primaryHomeLoanDetailRequest == null) {
				logger.warn("primaryHomeLoanDetailRequest Object can not be empty ==>" + primaryHomeLoanDetailRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			if (primaryHomeLoanDetailRequest.getId() == null) {
				logger.warn("Application ID must not be empty ==>" + primaryHomeLoanDetailRequest.getId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Application ID can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			boolean response = primaryHomeLoanService.saveOrUpdate(primaryHomeLoanDetailRequest, userId);
			if (response) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving Home==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "${primary}/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimary(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			
			if (applicationId == null) {
				logger.warn("ID Require to get Primary Home loan Details ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			PrimaryHomeLoanDetailRequest response = primaryHomeLoanService.get(applicationId, userId);
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
			logger.error("Error while getting Primary home loan Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "${final}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFinal(@RequestBody FinalHomeLoanDetailRequest finalHomeLoanDetailRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			// request must not be null
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
				finalHomeLoanDetailRequest.setClientId(clientId);
			}

			if (finalHomeLoanDetailRequest == null) {
				logger.warn("finalHomeLoanDetailRequest Object can not be empty ==>" + finalHomeLoanDetailRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			if (finalHomeLoanDetailRequest.getApplicationId() == null) {
				logger.warn("Application ID must not be empty ==>" + finalHomeLoanDetailRequest.getApplicationId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			boolean response = finalHomeLoanService.saveOrUpdate(finalHomeLoanDetailRequest, userId);
			if (response) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving Final Home Loan Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "${final}/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinal(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (applicationId == null) {
				logger.warn("ID Require to get Final Home Details ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			FinalHomeLoanDetailRequest response = finalHomeLoanService.get(applicationId, userId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Final home Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
