package com.capitaworld.service.loans.controller.fundseeker.corporate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
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
import com.capitaworld.service.loans.model.retail.PastFinancialEstimatesDetailRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PastFinancialEstiamateDetailsService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

/**
 * @author Sanket
 *
 */
@RestController
@RequestMapping("/past_financial_estimate_details")
public class PastFinancialEstimateDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(PastFinancialEstimateDetailsController.class);

	@Autowired
	private PastFinancialEstiamateDetailsService pastFinancialEstiamateDetailsService;
	
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
		Long userId =null;
		
		//==============
		
				if((!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) && (CommonUtils.UserType.SERVICE_PROVIDER  == Integer.parseInt(request.getAttribute(CommonUtils.USER_TYPE).toString()) || 
						 CommonUtils.UserType.NETWORK_PARTNER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
							.intValue())){
					frameRequest.setClientId(clientId);
					userId = (Long) request.getAttribute(CommonUtils.USER_ID);
				}else{
					   if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))){ 
						   userId = (Long) request.getAttribute(CommonUtils.USER_ID);
					   }else if(!CommonUtils.isObjectNullOrEmpty( frameRequest.getUserId())){
						   userId= frameRequest.getUserId();
					   }else{
					    logger.warn("Invalid request.");
					    return new ResponseEntity<LoansResponse>(
					      new LoansResponse("Invalid request.", HttpStatus.BAD_REQUEST.value()),
					      HttpStatus.OK);
					   }
				}
				
		//==============
				
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
//			if(CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue()){
//				frameRequest.setClientId(clientId);
//			}
			pastFinancialEstiamateDetailsService.saveOrUpdate(frameRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Past Financial Estimate Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/getList/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(@PathVariable Long id,HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getList");
			Long userId = null;
			if (id == null) {
				logger.warn("ID Require to get Past Financial Estimate Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if(CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue() || 
					 CommonUtils.UserType.NETWORK_PARTNER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
						.intValue()){
				userId = clientId;
			}else{
				userId = (Long)request.getAttribute(CommonUtils.USER_ID);
			}
			List<PastFinancialEstimatesDetailRequest> response = pastFinancialEstiamateDetailsService
					.getFinancialListData(userId,id);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			JSONObject result = loanApplicationService.getCurrencyAndDenomination(id,userId);
			String data = result.get("currency").toString();
			data = data.concat(" In "+ result.get("denomination").toString());
			loansResponse.setData(data);
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getList");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Past Financial Estimate Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
//	@RequestMapping(value = "/getList/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<LoansResponse> getYearList(@PathVariable("applicationId") Long applicationId) {
//		// request must not be null
//		try {
//			if (applicationId == null) {
//				logger.warn("ApplicationId Require to get Past Financial Estimate Details ==>" + applicationId);
//				return new ResponseEntity<LoansResponse>(
//						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
//			}
//			return new ResponseEntity<LoansResponse>(new LoansResponse(), HttpStatus.OK);
//		} catch (Exception e) {
//			logger.error("Error while getting Past Financial Estimate Details==>", e);
//			return new ResponseEntity<LoansResponse>(
//					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
//					HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}

}
