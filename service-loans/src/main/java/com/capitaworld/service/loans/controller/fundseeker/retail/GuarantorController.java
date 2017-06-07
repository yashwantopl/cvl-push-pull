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
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/guarantor")
public class GuarantorController {

	private static final Logger logger = LoggerFactory.getLogger(GuarantorController.class.getName());

	@Autowired
	private GuarantorService guarantorService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	// Primary Portion
	@RequestMapping(value = "${profile}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody GuarantorRequest applicantRequest,
			HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (applicantRequest == null) {
				logger.warn("applicantRequest can not be empty ==>", applicantRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			if (applicantRequest.getApplicationId() == null || applicantRequest.getId() == null) {
				logger.warn("Application Id and ID (PK) can not be empty ==>", applicantRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);

			}
			if(CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()){
				applicantRequest.setClientId(clientId);
			}
			guarantorService.save(applicantRequest, applicantRequest.getApplicationId(), userId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "${profile}/get/{applicationId}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> get(@PathVariable("applicationId") Long applicationId,
			@PathVariable("id") Long id, HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()){
				userId = clientId;
			}else{
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (applicationId == null || id == null) {
				logger.warn("applicationId and ID Require to get CoApplicant Profile Details. Application ID==>"
						+ applicationId + " and ID==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			GuarantorRequest response = guarantorService.get(userId, applicationId, id);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Guarantor Profile Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Final Portion
	@RequestMapping(value = "${final}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFinal(@RequestBody FinalCommonRetailRequest applicantRequest,
			HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()){
				applicantRequest.setClientId(clientId);
			}
			if (applicantRequest == null) {
				logger.warn("applicantRequest can not be empty ==>", applicantRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			if (applicantRequest.getApplicationId() == null) {
				logger.warn("Application Id can not be empty ==>", applicantRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);

			}
			guarantorService.saveFinal(applicantRequest, userId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "${final}/get/{applicationId}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinal(@PathVariable("applicationId") Long applicationId,
			@PathVariable("id") Long id, HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()){
				userId = clientId;
			}else{
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (applicationId == null || id == null) {
				logger.warn("applicationId and ID Require to get CoApplicant Profile Details ==>" + applicationId
						+ " and ID==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			FinalCommonRetailRequest response = guarantorService.getFinal(userId, applicationId, id);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Guarantor Profile Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
