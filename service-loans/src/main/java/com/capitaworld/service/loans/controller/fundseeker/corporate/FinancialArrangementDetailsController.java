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
import org.springframework.web.bind.annotation.*;

import com.capitaworld.cibil.api.utility.CibilUtils;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoansResponse;
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

	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;
	
	@Autowired
	private LoanApplicationService loanApplicationService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody FrameRequest frameRequest, HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "save");
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

		if (frameRequest == null) {
			logger.warn("frameRequest can not be empty ==>" + frameRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		// application id and user id must not be null
		if (frameRequest.getApplicationId() == null) {
			logger.warn("application id and user id must not be null ==>" + frameRequest);
			return new ResponseEntity<LoansResponse>(
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
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			financialArrangementDetailsService.saveOrUpdate(frameRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Financial Arrangement Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
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
				return new ResponseEntity<LoansResponse>(
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
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Financial Arrangement Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
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
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		// application id and user id must not be null
		if (applicationId == null || userId == null) {
			logger.warn("application id, user id must not be null ==>");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}

		// When calling client for this method if one of this path variable is null it
		// takes "null" so can not cast to Long
		if (clientId != null && clientId == -1) {
			clientId = null;
		}

		logger.warn("applicationId == >" + applicationId + "and userId == >" + userId + " and ClienId ==> " + clientId);
		try {
			// Checking Profile is Locked
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(clientId) ? userId : clientId);
			Boolean primaryLocked = loanApplicationService.isFinalLocked(applicationId, finalUserId);
			if (!CommonUtils.isObjectNullOrEmpty(primaryLocked) && primaryLocked.booleanValue()) {
				return new ResponseEntity<LoansResponse>(
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
			
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Existing Loan Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/get_total_emi/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getTotalEmi(
			@PathVariable("applicationId") Long applicationId) {
		// application id must not be null
		if (applicationId == null) {
			logger.warn("application id, must not be null ==>");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		logger.warn("applicationId == >" + applicationId);
		try {
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value(),financialArrangementDetailsService.getTotalOfEmiByApplicationId(applicationId)),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Getting total EMI by Application Id==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
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
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while Getting total EMI by Application Id==>", e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
}
