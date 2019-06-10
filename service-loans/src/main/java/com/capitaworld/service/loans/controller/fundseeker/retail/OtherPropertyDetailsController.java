package com.capitaworld.service.loans.controller.fundseeker.retail;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.retail.OtherPropertyDetailsRequest;
import com.capitaworld.service.loans.model.retail.PurchasePropertyDetailsRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.ApplicationProposalMappingService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.retail.*;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Sanket
 *
 */
@RestController
@RequestMapping("/other_property_details")
public class OtherPropertyDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(OtherPropertyDetailsController.class);

	@Autowired
	private ReferenceRetailDetailsService referenceRetailDetailsService;

	@Autowired
	private OtherPropertyDetailsService otherPropertyDetailsService;
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private ApplicationProposalMappingService applicationProposalMappingService;
	
	@Autowired
	private RetailApplicantService retailApplicantService;
	
	@Autowired
	private CoApplicantService coApplicantService;
	
	@Autowired
	private GuarantorService guarantorService;

	@RequestMapping(value = "/renovation/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saverenovation(@RequestBody FrameRequest frameRequest, HttpServletRequest request,
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
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				frameRequest.setClientId(clientId);
			}
			//Checking Profile is Locked
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(frameRequest.getClientId()) ? userId
					: frameRequest.getClientId());
			Long applicationId = null;
			if(CommonUtils.ApplicantType.APPLICANT == frameRequest.getApplicantType()){
				applicationId = frameRequest.getApplicationId();
			}else if(CommonUtils.ApplicantType.COAPPLICANT == frameRequest.getApplicantType()){
				applicationId = coApplicantService.getApplicantIdById(frameRequest.getApplicationId());
			}else if(CommonUtils.ApplicantType.GARRANTOR == frameRequest.getApplicantType()){
				applicationId = guarantorService.getApplicantIdById(frameRequest.getApplicationId());
			}
			
			Boolean primaryLocked = null;
			if(frameRequest.getProposalMappingId() != null) {
				primaryLocked = applicationProposalMappingService.isFinalLocked(frameRequest.getProposalMappingId());
			}else {
				primaryLocked = loanApplicationService.isFinalLocked(applicationId, finalUserId);
			}
			
			if(!CommonUtils.isObjectNullOrEmpty(primaryLocked) && primaryLocked.booleanValue()){
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			boolean response = otherPropertyDetailsService.saveOrUpdate(frameRequest,1);
			if (response) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving Reference Retail Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/construction/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveConstruction(@RequestBody FrameRequest frameRequest, HttpServletRequest request,
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
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				frameRequest.setClientId(clientId);
			}
			//Checking Profile is Locked
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(frameRequest.getClientId()) ? userId
					: frameRequest.getClientId());
			Long applicationId = null;
			if(CommonUtils.ApplicantType.APPLICANT == frameRequest.getApplicantType()){
				applicationId = frameRequest.getApplicationId();
			}else if(CommonUtils.ApplicantType.COAPPLICANT == frameRequest.getApplicantType()){
				applicationId = coApplicantService.getApplicantIdById(frameRequest.getApplicationId());
			}else if(CommonUtils.ApplicantType.GARRANTOR == frameRequest.getApplicantType()){
				applicationId = guarantorService.getApplicantIdById(frameRequest.getApplicationId());
			}

			Boolean primaryLocked = null;
			if(frameRequest.getProposalMappingId() != null) {
				primaryLocked = applicationProposalMappingService.isFinalLocked(frameRequest.getProposalMappingId());
			}else {
				primaryLocked = loanApplicationService.isFinalLocked(applicationId, finalUserId);
			}

			if(!CommonUtils.isObjectNullOrEmpty(primaryLocked) && primaryLocked.booleanValue()){
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			boolean response = otherPropertyDetailsService.saveOrUpdate(frameRequest,2);
			if (response) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving Reference Retail Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/construction/getList/{applicationType}/{id}/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getConstructionProperty(@PathVariable Long id, @PathVariable int applicationType, @PathVariable Long proposalId,
														 @RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (id == null) {
				logger.warn("ID Require to get Reference Retail Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			List<OtherPropertyDetailsRequest> response = otherPropertyDetailsService
					.getPropertyDetailListByProposalId(proposalId, applicationType,2);
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
				default : break;
			}
			loansResponse.setData(CommonDocumentUtils.getCurrency(currencyId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Reference Retail Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/renovation/getList/{applicationType}/{id}/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getRenovationPropertyList(@PathVariable Long id, @PathVariable int applicationType, @PathVariable Long proposalId,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			
			if (id == null) {
				logger.warn("ID Require to get Reference Retail Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			List<OtherPropertyDetailsRequest> response = otherPropertyDetailsService
					.getPropertyDetailListByProposalId(proposalId, applicationType,1);
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
			default : break;
			}
			loansResponse.setData(CommonDocumentUtils.getCurrency(currencyId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Reference Retail Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
