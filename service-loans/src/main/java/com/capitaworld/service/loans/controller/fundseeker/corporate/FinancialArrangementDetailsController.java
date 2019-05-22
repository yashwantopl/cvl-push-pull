package com.capitaworld.service.loans.controller.fundseeker.corporate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.loans.model.NTBRequest;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.cibil.api.utility.CibilUtils;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.NTBRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

/**
 * @author Sanket
 *
 */
@RestController
@RequestMapping("/financial_arrangement_details")
public class FinancialArrangementDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(SecurityCorporateDetailsController.class);

	private static final String APPLICATION_ID_MSG = "applicationId == >";
	private static final String ERROR_WHILE_GETTING_TOTAL_EMI_BY_APPLICATION_ID_MSG = "Error while Getting total EMI by Application Id==>";

	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;
	
	@Autowired
	private LoanApplicationService loanApplicationService;

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody FrameRequest frameRequest, HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "save");
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

		if (frameRequest == null) {
			logger.warn("frameRequest can not be empty ==>" + frameRequest);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		// application id and user id must not be null
		if (frameRequest.getApplicationId() == null) {
			logger.warn("application id and user id must not be null ==>" + frameRequest);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

		try {
			frameRequest.setUserId(userId);
			if(CommonDocumentUtils.isThisClientApplication(request)){
				frameRequest.setClientId(clientId);
			}
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(frameRequest.getClientId()) ? userId
					: frameRequest.getClientId());
			Boolean primaryLocked = loanApplicationService.isPrimaryLocked(frameRequest.getApplicationId(),
					finalUserId);
			if (!CommonUtils.isObjectNullOrEmpty(primaryLocked) && primaryLocked.booleanValue()) {
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			financialArrangementDetailsService.saveOrUpdate(frameRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SUCCESSFULLY_SAVED, HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Financial Arrangement Details==>", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/getList/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(@PathVariable Long id , HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getList");
		Long userId = null;
		if(CommonDocumentUtils.isThisClientApplication(request)){
			userId = clientId;
		}else{
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		}
		try {
			if (id == null) {
				logger.warn("ID Require to get Financial Arrangement Details ==>" + id);
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			List<FinancialArrangementsDetailRequest> response = financialArrangementDetailsService
					.getFinancialArrangementDetailsList(id,userId);
			LoansResponse loansResponse = new LoansResponse("Success", HttpStatus.OK.value());
			JSONObject result = loanApplicationService.getCurrencyAndDenomination(id,userId);
			String data = result.get("currency").toString();
			data = data.concat(" In "+ result.get("denomination").toString());
			loansResponse.setData(data);
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getList");
			return new ResponseEntity<>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Financial Arrangement Details==>", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/save_from_cibil/{applicationId}/{userId}/{clientId}/{directorId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFromCibil(@RequestBody List<FinancialArrangementsDetailRequest> detailRequests,
			@PathVariable("applicationId") Long applicationId, @PathVariable("userId") Long userId,
			@PathVariable("clientId") Long clientId,@PathVariable("directorId") Long directorId) {
		// request must not be null
		if (CommonUtils.isListNullOrEmpty(detailRequests)) {
			logger.warn("frameRequest can not be empty ==>" + detailRequests);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		// application id and user id must not be null
		if (applicationId == null || userId == null) {
			logger.warn("application id, user id must not be null ==>");
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}

		// When calling client for this method if one of this path variable is null it
		// takes "null" so can not cast to Long
		if (clientId != null && clientId == -1) {
			clientId = null;
		}

		logger.warn(APPLICATION_ID_MSG + applicationId + "and userId == >" + userId + " and ClienId ==> " + clientId);
		try {
			// Checking Profile is Locked
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(clientId) ? userId : clientId);
			Boolean primaryLocked = loanApplicationService.isFinalLocked(applicationId, finalUserId);
			if (!CommonUtils.isObjectNullOrEmpty(primaryLocked) && primaryLocked.booleanValue()) {
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			if(CibilUtils.isObjectNullOrEmpty(directorId) || directorId <= 0) {
				logger.info("Going to Save Company Financial Information");
				financialArrangementDetailsService.saveOrUpdate(detailRequests, applicationId, finalUserId);	
			}else {
				logger.info("Going to Save Director or Partner Financial Information");
				financialArrangementDetailsService.saveOrUpdate(detailRequests, applicationId, finalUserId,directorId);
			}
			
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SUCCESSFULLY_SAVED, HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Existing Loan Details==>", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/save_for_one_pager_eligibility", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveForOnePagerEligibility(@RequestBody FundSeekerInputRequestResponse fundSeekerInputRequestResponse,HttpServletRequest httpServletRequest ) {

		// application id and user id must not be null
		if (fundSeekerInputRequestResponse.getApplicationId() == null) {
			logger.warn("application id must not be null ==>");
			return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		fundSeekerInputRequestResponse.setUserId(userId);

		logger.info(APPLICATION_ID_MSG + fundSeekerInputRequestResponse.getApplicationId() + "and userId == >" + userId);
		try {
			// Checking Profile is Locked
			Boolean primaryLocked = loanApplicationService.isFinalLocked(fundSeekerInputRequestResponse.getApplicationId(), fundSeekerInputRequestResponse.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(primaryLocked) && primaryLocked.booleanValue()) {
				return new ResponseEntity<>(new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
			}
			financialArrangementDetailsService.saveOrUpdate(fundSeekerInputRequestResponse.getFinancialArrangementsDetailRequestsList(), fundSeekerInputRequestResponse.getApplicationId(), fundSeekerInputRequestResponse.getUserId());
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SUCCESSFULLY_SAVED, HttpStatus.OK.value()),HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Existing Loan Details==>", e);
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/get_total_emi_sanction_amount/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public FinancialArrangementsDetailRequest getTotalEmiAndSanctionedAmount(@PathVariable("applicationId") Long applicationId) {
		if (applicationId == null) {
			logger.warn("application id, must not be null ==>");
			return null;
		}
		logger.warn(APPLICATION_ID_MSG + applicationId);
		try {
			return financialArrangementDetailsService.getTotalEmiAndSanctionAmountByApplicationId(applicationId);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_TOTAL_EMI_BY_APPLICATION_ID_MSG, e);
			return null;
		}
	}

	@RequestMapping(value = "/get_total_emi_sanction_amount_uniform/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public FinancialArrangementsDetailRequest getTotalEmiAndSanctionedAmountUniform(@PathVariable("applicationId") Long applicationId) {
		if (applicationId == null) {
			logger.warn("application id, must not be null ==>");
			return null;
		}
		logger.warn(APPLICATION_ID_MSG + applicationId);
		try {
			return financialArrangementDetailsService.getTotalEmiAndSanctionAmountByApplicationIdForUniforProduct(applicationId);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_TOTAL_EMI_BY_APPLICATION_ID_MSG, e);
			return null;
		}
	}

	@PostMapping(value = "/getTotalEmiFromDirectorId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getTotalEmi(@RequestBody NTBRequest ntbRequest) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(ntbRequest) && !CommonUtils.isObjectNullOrEmpty(ntbRequest.getApplicationId()) && !CommonUtils.isObjectNullOrEmpty(ntbRequest.getDirectorId())) {
				Double emi = financialArrangementDetailsService.getTotalOfEmiByApplicationIdAndDirectorId(ntbRequest.getApplicationId(), ntbRequest.getDirectorId());
				LoansResponse loansResponse = new LoansResponse();
				loansResponse.setMessage("Successfully get Data!");
				loansResponse.setData(emi);
				return new ResponseEntity<>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_TOTAL_EMI_BY_APPLICATION_ID_MSG, e);
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
	@PostMapping(value = "/getTotalEmiFromForAllDir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getTotalEmiFromForAllDir(@RequestBody Long applicationId) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(applicationId)) {
				Double emi = financialArrangementDetailsService.getTotalEmiOfAllDirByApplicationId(applicationId);
				LoansResponse loansResponse = new LoansResponse();
				loansResponse.setMessage("Successfully get Data!");
				loansResponse.setData(emi);
				return new ResponseEntity<>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_TOTAL_EMI_BY_APPLICATION_ID_MSG, e);
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/getTotalEmiForSoftPing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getTotalEmiForSoftPing(@RequestBody Long applicationId) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(applicationId)) {
				Double emi = financialArrangementDetailsService.getTotalEmiByApplicationIdSoftPing(applicationId);
				LoansResponse loansResponse = new LoansResponse();
				loansResponse.setMessage("Successfully get Data!");
				loansResponse.setData(emi);
				return new ResponseEntity<>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_TOTAL_EMI_BY_APPLICATION_ID_MSG, e);
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/getTotalEmiForSoftPingForCoApplicant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getTotalEmiForSoftPingForCoApplicant(@RequestBody Long coApplicantId) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(coApplicantId)) {
				Double emi = financialArrangementDetailsService.getTotalEmiByApplicationIdSoftPingForCoApplicant(coApplicantId);
				LoansResponse loansResponse = new LoansResponse();
				loansResponse.setMessage("Successfully get Data!");
				loansResponse.setData(emi);
				return new ResponseEntity<>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_TOTAL_EMI_BY_APPLICATION_ID_MSG, e);
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}

}
