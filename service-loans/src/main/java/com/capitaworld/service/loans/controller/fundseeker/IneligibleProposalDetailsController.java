package com.capitaworld.service.loans.controller.fundseeker;

import com.capitaworld.service.loans.model.InEligibleProposalDetailsRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ProposalDetailsAdminRequest;
import com.capitaworld.service.loans.service.common.IneligibleProposalDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by KushalCW on 22-09-2018.
 */

@RestController
public class IneligibleProposalDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(IneligibleProposalDetailsController.class);

	@Autowired
	private IneligibleProposalDetailsService ineligibleProposalDetailsService;

	@RequestMapping(value = "/save/ineligible/proposal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(
			@RequestBody InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest,
			HttpServletRequest request) {
		if (CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest)
				|| CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getApplicationId())
				|| CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getUserOrgId())
				|| CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getBranchId())) {
			logger.warn("Requested data can not be empty.Invalid Request. ");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			inEligibleProposalDetailsRequest.setUserId(userId);
		}
		
		Integer isDetailsSaved = ineligibleProposalDetailsService.save(inEligibleProposalDetailsRequest);
		if (isDetailsSaved == 2) {

//        	Trigger mail  to fs and bank branch
			Boolean isSent = ineligibleProposalDetailsService.sendMailToFsAndBankBranch(
					inEligibleProposalDetailsRequest.getApplicationId(),
					inEligibleProposalDetailsRequest.getBranchId(),inEligibleProposalDetailsRequest.getUserOrgId());
			if (isSent) {
				logger.info("Email sent to fs and branch");
			} else {
				logger.info("Error in sending email to fs and branch");
			}

			return new ResponseEntity<LoansResponse>(new LoansResponse("Data saved", HttpStatus.OK.value()),
					HttpStatus.OK);
		} else  if (isDetailsSaved == 1) {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("It seems your proposal is already sanctioned by one of our bank partner. If you did not receive any communication from bank please mail your details at support@psbloansin59minutes.com", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}  else {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Data not saved", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/update/ineligible/status", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> update( @RequestBody InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest, HttpServletRequest request) {
		if (CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest) || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getApplicationId())
				|| CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getStatus()) ||
				CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getReason())) {
			logger.warn("Requested data can not be empty.Invalid Request. ");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			inEligibleProposalDetailsRequest.setUserId(userId);
			inEligibleProposalDetailsRequest.setUserOrgId(userOrgId);
		}
		
		Boolean isDetailsSaved = ineligibleProposalDetailsService.updateStatus(inEligibleProposalDetailsRequest);
		if (isDetailsSaved) {
			return new ResponseEntity<LoansResponse>(new LoansResponse("Data saved", HttpStatus.OK.value()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("The application has encountered an error, please try again after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/getOfflineProposalByOrgId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getOfflineProposalByOrgId(@RequestBody ProposalDetailsAdminRequest request, HttpServletRequest httpServletRequest) {
		
		Long userOrgId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ORG_ID);
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		
		if(CommonUtils.isObjectNullOrEmpty(userOrgId) || CommonUtils.isObjectNullOrEmpty(request.getFromDate()) || CommonUtils.isObjectNullOrEmpty(request.getToDate()) || CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("Bad Request !!");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Request parameter null or empty !!", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		List<ProposalDetailsAdminRequest> dataList = ineligibleProposalDetailsService.getOfflineProposals(userOrgId, userId, request);
		
		LoansResponse response = new LoansResponse("Data Found.", HttpStatus.OK.value());
		response.setData(dataList);
		
		return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
	}
	
	/**
	 * Transfer branch and reason 
	 * @param inEligibleProposalDetailsRequest
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/update/ineligible/transferBranch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateTransferBranch( @RequestBody InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest, HttpServletRequest request) {
		if (CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest) || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getBranchId())
				|| CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getIneligibleProposalId()) ||
				CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getReason())) {
			logger.warn("Requested data can not be empty.Invalid Request. ");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			inEligibleProposalDetailsRequest.setUserId(userId);
			inEligibleProposalDetailsRequest.setUserOrgId(userOrgId);
		}
		
		Boolean isDetailsSaved = ineligibleProposalDetailsService.updateTransferBranchDetail(inEligibleProposalDetailsRequest);
		if (isDetailsSaved) {
			return new ResponseEntity<LoansResponse>(new LoansResponse("Data saved", HttpStatus.OK.value()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("The application has encountered an error, please try again after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
	
	/**
	 * Re open proposal
	 * @param inEligibleProposalDetailsRequest
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/update/ineligible/reOpenProposalDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateReOpenProposalDetail(@RequestBody InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest, HttpServletRequest request) {
		if (CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest) || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getReOpenReason())
				|| CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getIneligibleProposalId())) {
			logger.warn("Requested data can not be empty.Invalid Request. ");
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			inEligibleProposalDetailsRequest.setUserId(userId);
			inEligibleProposalDetailsRequest.setUserOrgId(userOrgId);
		}
		
		Boolean isDetailsSaved = ineligibleProposalDetailsService.updateReOpenProposalDetail(inEligibleProposalDetailsRequest);
		if (isDetailsSaved) {
			return new ResponseEntity<LoansResponse>(new LoansResponse("Data updated", HttpStatus.OK.value()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("The application has encountered an error, please try again after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
}
