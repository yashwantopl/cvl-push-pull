package com.opl.service.loans.controller.fundseeker.corporate;

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

import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.MonthlyTurnoverDetailRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.opl.service.loans.service.fundseeker.corporate.MonthlyTurnoverDetailService;
import com.opl.service.loans.utils.CommonDocumentUtils;

/**
 * @author Sanket
 *
 */
@RestController
@RequestMapping("/monthly_turnover_details")
public class MonthlyTurnoverDetailController {

	private static final Logger logger = LoggerFactory.getLogger(MonthlyTurnoverDetailController.class);

	@Autowired
	private MonthlyTurnoverDetailService monthlyTurnoverDetailService;
	
	@Autowired
	private LoanApplicationService loanApplicationService;

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
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

		if (frameRequest.getProposalMappingId() == null) {
			logger.warn("proposal id and user id must not be null ==>" + frameRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

		try {
			frameRequest.setUserId(userId);
			if(CommonDocumentUtils.isThisClientApplication(request)){
				frameRequest.setClientId(clientId);
			}
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(frameRequest.getClientId()) ? userId
					: frameRequest.getClientId());
			Boolean finalLocked = loanApplicationService.isFinalLockedByProposalId(frameRequest.getProposalMappingId(), finalUserId);
			if (!CommonUtils.isObjectNullOrEmpty(finalLocked) && finalLocked.booleanValue()) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			monthlyTurnoverDetailService.saveOrUpdate(frameRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Monthly Turnover Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/getList/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(@PathVariable Long id, HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getList");
		Long userId = null;
		   if(CommonDocumentUtils.isThisClientApplication(request)){
		    userId = clientId;
		   } else {
		    userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		   }
		try {
			if (id == null) {
				logger.warn("ID Require to get Monthly Turnover Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			List<MonthlyTurnoverDetailRequest> response = monthlyTurnoverDetailService.getMonthlyTurnoverDetailList(id,userId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getList");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Monthly Turnover Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getListByProposalId/{applicationId}/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getListByProposalId(@PathVariable Long applicationId,@PathVariable Long proposalId, HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getList");
		Long userId = null;
		if(CommonDocumentUtils.isThisClientApplication(request)){
			userId = clientId;
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		}
		try {
			if (applicationId == null) {
				logger.warn("ID Require to get Monthly Turnover Details ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			if (proposalId == null) {
				logger.warn("proposal id Require to get Monthly Turnover Details ==>" + proposalId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}


			List<MonthlyTurnoverDetailRequest> response = monthlyTurnoverDetailService.getMonthlyTurnoverDetailListByProposalId(applicationId,userId,proposalId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getList");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Monthly Turnover Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
