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
import com.capitaworld.service.loans.model.retail.CreditCardsDetailRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.CreditCardsDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

/**
 * @author Sanket
 *
 */
@RestController
@RequestMapping("/credit_cards_detail")
public class CreditCardsDetailController {

	private static final Logger logger = LoggerFactory.getLogger(CreditCardsDetailController.class);

	@Autowired
	private CreditCardsDetailService creditCardsDetailService;

	@Autowired
	private RetailApplicantService retailApplicantService;

	@Autowired
	private CoApplicantService coApplicantService;

	@Autowired
	private GuarantorService guarantorService;

	@Autowired
	private LoanApplicationService loanApplicationService;

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
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

		try {
			frameRequest.setUserId(userId);
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				frameRequest.setClientId(clientId);
			}
			// Checking Profile is Locked
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(frameRequest.getClientId()) ? userId
					: frameRequest.getClientId());
			Long applicationId = null;
			if (CommonUtils.ApplicantType.APPLICANT == frameRequest.getApplicantType()) {
				applicationId = frameRequest.getApplicationId();
			} else if (CommonUtils.ApplicantType.COAPPLICANT == frameRequest.getApplicantType()) {
				applicationId = coApplicantService.getApplicantIdById(frameRequest.getApplicationId());
			} else if (CommonUtils.ApplicantType.GARRANTOR == frameRequest.getApplicantType()) {
				applicationId = guarantorService.getApplicantIdById(frameRequest.getApplicationId());
			}
			Boolean primaryLocked = loanApplicationService.isFinalLocked(applicationId, finalUserId);
			if (!CommonUtils.isObjectNullOrEmpty(primaryLocked) && primaryLocked.booleanValue()) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			creditCardsDetailService.saveOrUpdate(frameRequest);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Credit Card Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/getList/{applicationType}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(@PathVariable Long id, @PathVariable int applicationType,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (id == null) {
				logger.warn("ID Require to get Credit Card Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			List<CreditCardsDetailRequest> response = creditCardsDetailService.getCreditCardDetailList(id,
					applicationType);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			Integer currencyId = null;
			Long applicantIdById = null;
			switch (applicationType) {
			case CommonUtils.ApplicantType.APPLICANT:
				currencyId = retailApplicantService.getCurrency(id, userId);
				break;
			case CommonUtils.ApplicantType.COAPPLICANT:
				applicantIdById = coApplicantService.getApplicantIdById(id);
				currencyId = retailApplicantService.getCurrency(applicantIdById, userId);
				break;
			case CommonUtils.ApplicantType.GARRANTOR:
				applicantIdById = guarantorService.getApplicantIdById(id);
				currencyId = retailApplicantService.getCurrency(applicantIdById, userId);
				break;
			}
			loansResponse.setData(CommonDocumentUtils.getCurrency(currencyId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Creit Card Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/save_from_cibil/{applicationId}/{userId}/{clientId}/{applicantType}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFromCibil(@RequestBody List<CreditCardsDetailRequest> detailRequests,
			@PathVariable("applicationId") Long applicationId, @PathVariable("userId") Long userId,
			@PathVariable("clientId") Long clientId, @PathVariable("applicantType") Integer applicantType) {
		// request must not be null
		if (CommonUtils.isListNullOrEmpty(detailRequests)) {
			logger.warn("CreditCardsDetailRequest can not be empty ==>" + detailRequests);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		// application id and user id must not be null
		if (applicationId == null || applicantType == null || applicantType == 0 || userId == null) {
			logger.warn("application id, user id and applicant Type must not be null ==>");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
		// When calling client for this method if one of this path variable is null it
		// takes "null" so can not cast to Long
		if (clientId != null && clientId == -1) {
			clientId = null;
		}

		logger.warn("applicationId == >" + applicationId + "and userId == >" + userId + " and ClienId ==> " + clientId
				+ " and applicantType ==>" + applicantType);
		try {
			// Checking Profile is Locked
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(clientId) ? userId : clientId);
			Boolean primaryLocked = loanApplicationService.isFinalLocked(applicationId, finalUserId);
			if (!CommonUtils.isObjectNullOrEmpty(primaryLocked) && primaryLocked.booleanValue()) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			creditCardsDetailService.saveOrUpdateFromCibil(detailRequests, applicationId, finalUserId, applicantType);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Existing Loan Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}
}
