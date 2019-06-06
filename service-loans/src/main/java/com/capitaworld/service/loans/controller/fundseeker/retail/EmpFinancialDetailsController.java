package com.capitaworld.service.loans.controller.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSelfEmployedType;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.retail.EmpAgriculturistTypeRequest;
import com.capitaworld.service.loans.model.retail.EmpSalariedTypeRequest;
import com.capitaworld.service.loans.model.retail.EmpSelfEmployedTypeRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
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
@RequestMapping("/emp_financial_details")
public class EmpFinancialDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(EmpFinancialDetailsController.class);

	@Autowired
	private ReferenceRetailDetailsService referenceRetailDetailsService;

	@Autowired
	private EmpFinancialDetailsService empFinancialDetailsService;
	
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

	@RequestMapping(value = "/salaried/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveSalaried(@RequestBody FrameRequest frameRequest, HttpServletRequest request,
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
			boolean response = empFinancialDetailsService.saveOrUpdateSalariedEmpDetails(frameRequest);
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

	@RequestMapping(value = "/self/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveSelf(@RequestBody FrameRequest frameRequest, HttpServletRequest request,
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
			boolean response = empFinancialDetailsService.saveOrUpdateSelfEmpDetails(frameRequest);
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

	@RequestMapping(value = "/agriculturist/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveAgriculturist(@RequestBody FrameRequest frameRequest, HttpServletRequest request,
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
			boolean response = empFinancialDetailsService.saveOrUpdateAgriculturistEmpDetails(frameRequest);
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

	@RequestMapping(value = "/getList/{applicationType}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(@PathVariable Long id, @PathVariable int applicationType,
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
			List<ReferenceRetailDetailsRequest> response = referenceRetailDetailsService
					.getReferenceRetailDetailList(id, applicationType);
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
	
	@RequestMapping(value = "/salaried/getList/{applicationType}/{id}/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getSalariedList(@PathVariable Long id, @PathVariable int applicationType, @PathVariable Long proposalId,
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
			List<EmpSalariedTypeRequest> response = empFinancialDetailsService
					.getSalariedEmpFinDetailListByProposalId(proposalId, applicationType);
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

	@RequestMapping(value = "/self/getList/{applicationType}/{id}/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getSelfList(@PathVariable Long id, @PathVariable int applicationType, @PathVariable Long proposalId,
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
			List<EmpSelfEmployedTypeRequest> response = empFinancialDetailsService
					.getSelfEmpFinDetailListByProposalId(proposalId, applicationType);
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

	@RequestMapping(value = "/agriculturist/getList/{applicationType}/{id}/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getAgriculturistList(@PathVariable Long id, @PathVariable int applicationType, @PathVariable Long proposalId,
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
			List<EmpAgriculturistTypeRequest> response = empFinancialDetailsService
					.getAgriculturistEmpFinDetailListByProposalId(proposalId, applicationType);
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
