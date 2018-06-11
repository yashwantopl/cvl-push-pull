package com.capitaworld.service.loans.controller.common;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.common.UserLoanAmountMappingRequest;
import com.capitaworld.service.loans.service.common.UserLoanAmountMappingService;
import com.capitaworld.service.loans.utils.CommonUtils;

/**
 * @author harshit
 * Date : 11-Jun-2018
 * THIS IS FOR SECOND FLOW OF FP (MAKER AND CHECKER)
 * USER LOAN AMOUNT MAPPING STOARE MIN AND MAX AMOUNT BASED ON USERID AND PRODUCT ID
 */
@RestController
@RequestMapping("/user_amount_mapping")
public class UserLoanAmountMappingController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoanAmountMappingController.class);
	
	@Autowired
	private UserLoanAmountMappingService amountMappingService; 
	
	@RequestMapping(value="/check_amount_by_user_and_product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> checkAmountByUserIdAndProductId(@RequestBody UserLoanAmountMappingRequest amountMappingRequest,HttpServletRequest httpServletRequest){

		logger.info("Enter in check amount by user Id -------------------->" + amountMappingRequest.toString());
		if(CommonUtils.isObjectNullOrEmpty(amountMappingRequest.getUserId())) {
			if(!CommonUtils.isObjectNullOrEmpty(httpServletRequest.getAttribute(CommonUtils.USER_ID))) {
				amountMappingRequest.setUserId((Long)(httpServletRequest.getAttribute(CommonUtils.USER_ID)));
			}
		}
		
		if(CommonUtils.isObjectNullOrEmpty(amountMappingRequest.getUserId())) {
			logger.info("UserId is null or Empty");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Request Data Null Or Empty !!", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}
		
		if(CommonUtils.isObjectNullOrEmpty(amountMappingRequest.getProductId())) {
			logger.info("Product Id is null or Empty");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Request Data Null Or Empty !!", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}
		
		try {
			Boolean result = amountMappingService.checkAmountByUserIdAndProductId(amountMappingRequest.getUserId(), amountMappingRequest.getAmount(), amountMappingRequest.getProductId());
			logger.info("Response from service layer ===============>" + result);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Checked !!", HttpStatus.OK.value(),result),HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while check amount by userid and product id==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/get_by_user_and_product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getByUserIdAndProductId(@RequestBody UserLoanAmountMappingRequest amountMappingRequest,HttpServletRequest httpServletRequest){

		logger.info("Enter in get by user id and product id-------------------->" + amountMappingRequest.toString());
		if(CommonUtils.isObjectNullOrEmpty(amountMappingRequest.getUserId())) {
			if(!CommonUtils.isObjectNullOrEmpty(httpServletRequest.getAttribute(CommonUtils.USER_ID))) {
				amountMappingRequest.setUserId((Long)(httpServletRequest.getAttribute(CommonUtils.USER_ID)));
			}
		}
		
		if(CommonUtils.isObjectNullOrEmpty(amountMappingRequest.getUserId())) {
			logger.info("UserId is null or Empty");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Request Data Null Or Empty !!", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}
		
		if(CommonUtils.isObjectNullOrEmpty(amountMappingRequest.getProductId())) {
			logger.info("Product Id is null or Empty");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Request Data Null Or Empty !!", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}
		
		try {
			UserLoanAmountMappingRequest response = amountMappingService.getByUserIdAndProductId(amountMappingRequest.getUserId(), amountMappingRequest.getProductId());
			
			if(!CommonUtils.isObjectNullOrEmpty(response)) {
				logger.info("Response from service layer ===============>" + response.toString());
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully get data !!", HttpStatus.OK.value(),response),HttpStatus.OK);
			}
			logger.info("Response from service layer ===============>"  + response);
			return new ResponseEntity<LoansResponse>(new LoansResponse("No data found !!", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while get by user id and product id==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
		
	}
	

}
