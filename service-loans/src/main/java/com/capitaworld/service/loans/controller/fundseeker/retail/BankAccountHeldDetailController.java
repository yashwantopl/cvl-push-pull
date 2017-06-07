package com.capitaworld.service.loans.controller.fundseeker.retail;

import java.util.List;

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

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.service.fundseeker.retail.BankAccountHeldDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;

/**
 * @author Sanket
 *
 */
@RestController
@RequestMapping("bank_account_held_details")
public class BankAccountHeldDetailController {

	private static final Logger logger = LoggerFactory.getLogger(BankAccountHeldDetailController.class);

	@Autowired
	private BankAccountHeldDetailService bankAccountHeldDetailService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
		if (frameRequest.getApplicationId() == null || frameRequest.getApplicantType() == 0) {
			logger.warn("application id, user id and applicant Type must not be null ==>" + frameRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}

		try {
			frameRequest.setUserId(userId);
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
				frameRequest.setClientId(clientId);
			}
			bankAccountHeldDetailService.saveOrUpdate(frameRequest);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Bank Account Held Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/getList/{applicationType}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(@PathVariable Long id, @PathVariable int applicationType) {
		// request must not be null
		try {
			if (id == null) {
				logger.warn("ID Require to get Bank Account Held Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			List<BankAccountHeldDetailsRequest> response = bankAccountHeldDetailService.getExistingLoanDetailList(id,
					applicationType);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Bank Account Held Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
