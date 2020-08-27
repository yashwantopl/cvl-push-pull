package com.opl.service.loans.controller.payment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.payment.model.GatewayRequest;
import com.opl.mudra.api.payment.model.GatewayResponse;
import com.opl.mudra.api.payment.model.LoansResponse;
import com.opl.service.loans.config.ClientInfo;
import com.opl.service.loans.service.fundseeker.corporate.PaymentServiceLoans;

@RestController
public class PaymentControllerLoans {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentControllerLoans.class.getName());
	

	@Autowired
	PaymentServiceLoans paymentService;
	
	/**
	 * @param gatewayRequest
	 * @param request
	 * Api excution order 1
	 * 
	 *  USE ON
	 * on branch selection
	 * payment page on load
	 * on paynow button clicked 
	 * @return
	 */
	@PostMapping(value = "/checkSkipPayment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> checkSkipPayment(@RequestBody GatewayRequest gatewayRequest,HttpServletRequest request) {
		
		logger.info("start checkSkipPayment()");
		if (CommonUtils.isObjectNullOrEmpty(gatewayRequest) 
				|| CommonUtils.isObjectNullOrEmpty(gatewayRequest.getBusinessTypeId()) 
				||  CommonUtils.isObjectNullOrEmpty(gatewayRequest.getApplicationId()) ){
			return new ResponseEntity<LoansResponse>( new LoansResponse("required values can not be null ",HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			LoansResponse response = new LoansResponse();
			response.setData(paymentService.checkTypeOfSkipPayment(gatewayRequest));
			response.setStatus(HttpStatus.OK.value());
			
			logger.info("end checkSkipPayment()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Saving Payment info==>{}", e);
			return new ResponseEntity<LoansResponse>( new LoansResponse( CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
	/**
	 * @param gatewayRequest
	 * @param request
	 * @param clientId
	 * Api excution order 2
	 * 
	 * USE ON
	 * on paynow button clicked 
	 * 
	 * @return
	 */
	@PostMapping(value = "/save_payment_info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> savePaymentDetail(@RequestBody GatewayRequest gatewayRequest,
														   HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		
		logger.info("start save_payment_info()");
		Long userId = null;
						
		if (!CommonUtils.isObjectNullOrEmpty(request) && !CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))){	
			logger.info("User id from front end===>{}" , request.getAttribute(CommonUtils.USER_ID));
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		} else{
			userId = gatewayRequest.getUserId();
			logger.error("User id from browser==>{}" , userId);
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(request)) {
			try {
				ClientInfo clientInfo=new ClientInfo();
				Map<String, Object> clientDetail=clientInfo.printClientInfo(request);
				gatewayRequest.setClientInfo(clientDetail);
			}
			catch (Exception e) {
				logger.error("Error/Exception while getting ClientInfo Detail",e);
			}
		}

		if (CommonUtils.isObjectNullOrEmpty(gatewayRequest.getApplicationId())) {
			logger.error("applicationId is null");
			return new ResponseEntity<LoansResponse>( new LoansResponse("ApplicationId can not be null ",HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
			
		try {
			Map<String,Object> applicationMaster = paymentService.savePaymentDetail(gatewayRequest);
			logger.info("Payment Request========>{}", applicationMaster);

			LoansResponse response = new LoansResponse(CommonUtils.PaymentStatus.SUCCESS, HttpStatus.OK.value());
			response.setData(applicationMaster);
			if(applicationMaster.get(CommonUtils.IS_PAYMENT_DONE) != null) {
				response.setMessage("Payment Already Succeeded");
			}else if(applicationMaster.get(CommonUtils.PRODUCT_INACTIVE) != null) {
				response.setMessage(applicationMaster.get(CommonUtils.PRODUCT_INACTIVE).toString());
			}else if(applicationMaster.get(CommonUtils.BRANCH_INACTIVE) != null) {
//				response.setData(applicationMaster);
				response.setMessage(applicationMaster.get(CommonUtils.BRANCH_INACTIVE).toString());
			}else if(applicationMaster.get(CommonUtils.DELAY_STATUS) != null) {
//				response.setData(applicationMaster);
				response.setMessage(applicationMaster.get(CommonUtils.DELAY_STATUS).toString());
			}else if(applicationMaster.get(CommonUtils.PaymentStatus.BYPASS) != null) {
//				response.setData(applicationMaster);
				response.setMessage(applicationMaster.get(CommonUtils.PaymentStatus.BYPASS).toString());
			}
			
			logger.info("end save_payment_info()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Saving Payment info==>{}", e);
			return new ResponseEntity<LoansResponse>( new LoansResponse( CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
	
	/**
	 * @param gatewayRequest
	 * @param request
	 * Api excution order 3 (On Conditional - not on msme)
	 * 
	 * USE ON
	 * on branch selected all type of loan except MSME (Market Place)
	 * 
	 * on option set skippayment true
	 * @return
	 */
	@PostMapping(value = "/skipPayment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> skipPayment(@RequestBody GatewayRequest gatewayRequest,HttpServletRequest request) {
		
		logger.info("start skipPayment()");
		if ( (CommonUtils.isObjectNullOrEmpty(gatewayRequest) ||  CommonUtils.isObjectNullOrEmpty(gatewayRequest.getApplicationId()) 
				|| CommonUtils.isObjectNullOrEmpty(gatewayRequest.getSkipPayment()) )  
				&&  (gatewayRequest.getSkipPayment() && CommonUtils.isObjectNullOrEmpty(gatewayRequest.getPaymentTypeId())) ){
			return new ResponseEntity<LoansResponse>( new LoansResponse("required values can not be null ",HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		if(gatewayRequest.getSkipPayment() != null && Boolean.TRUE.equals(gatewayRequest.getSkipPayment())) {
			gatewayRequest.setSpecificSkipType(gatewayRequest.getSkipType());
		}
		
		try {
			LoansResponse response = new LoansResponse();
			String status = paymentService.skipPayment(gatewayRequest);
			response.setData(status);
			if(status.equals(CommonUtils.PaymentStatus.SUCCESS)) {
				response.setStatus(HttpStatus.OK.value());
			}else {			
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			}
			logger.info("end skipPayment()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Saving Payment info==>{}", e);
			return new ResponseEntity<LoansResponse>( new LoansResponse( CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
	/**
	 * @param gatewayRequest
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/getInprincipleDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getInprincipleDetail(@RequestBody GatewayRequest gatewayRequest,HttpServletRequest request) {
		
		logger.info("start getInprincipleDetail()");
		if( CommonUtils.isObjectNullOrEmpty(gatewayRequest) ||  CommonUtils.isObjectNullOrEmpty(gatewayRequest.getApplicationId()) ){
			return new ResponseEntity<LoansResponse>( new LoansResponse("required values can not be null ",HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		try {
			LoansResponse response = new LoansResponse();
			Map<String, Object> inPrincipleDetail = paymentService.getInPrincipleDetail(gatewayRequest.getApplicationId(), null, gatewayRequest.getSkipType());
			if(inPrincipleDetail.isEmpty() || inPrincipleDetail.containsKey("Error")) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setErrors(inPrincipleDetail);
			}else {
				response.setData(inPrincipleDetail);
				response.setStatus(HttpStatus.OK.value());
			}
			
			logger.info("end getInprincipleDetail()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Saving Payment info==>{}", e);
			return new ResponseEntity<LoansResponse>( new LoansResponse( CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
	/**
	 * @param gatewayRequest
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/afterPaymentActiveAndSendInprinciple", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> afterPaymentActiveAndSendInprinciple(@RequestBody GatewayRequest gatewayRequest,HttpServletRequest request) {
		
		logger.info("start getInprincipleDetail()");
		if( CommonUtils.isObjectNullOrEmpty(gatewayRequest) ||  CommonUtils.isObjectNullOrEmpty(gatewayRequest.getApplicationId())  ){
			return new ResponseEntity<LoansResponse>( new LoansResponse("required values can not be null ",HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		try {
			LoansResponse response = new LoansResponse();
			Map<String, Object> inPrincipleDetail = paymentService.updatePaymentAfterSuccess(gatewayRequest);
			if(inPrincipleDetail == null || inPrincipleDetail.isEmpty() || inPrincipleDetail.containsKey("Error") || inPrincipleDetail.containsKey("PaymentError")) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setErrors(inPrincipleDetail);
			}else {
				response.setData(inPrincipleDetail);
				response.setStatus(HttpStatus.OK.value());
			}
			
			logger.info("end getInprincipleDetail()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Saving Payment info==>{}", e);
			return new ResponseEntity<LoansResponse>( new LoansResponse( CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
	
	
	/** Send inprinciple to user when required (Used from postman)
	 * @param applicationId
	 * @param proposalId
	 * @return
	 */
	@PostMapping(value= {"/sendInPrincipleByApplicationId/{applicationId}","/sendInPrincipleByApplicationId/{applicationId}/{forFs}/{forFp}" , "/sendInPrincipleByApplicationId/{applicationId}/{proposalId}"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GatewayResponse> sendInPrincipleByApplicationId(@PathVariable("applicationId") Long applicationId, @PathVariable(name= "proposalId" , required = false) Long proposalId,
			@PathVariable(name= "forFs" , required = false) Boolean forFs,@PathVariable(name= "forFp" , required = false) Boolean forFp){
		
		logger.info("Enter in sendInPrincipleByApplicationId() ====================> " );
	  
		GatewayResponse gatewayResponse=null;
		if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
			gatewayResponse = new GatewayResponse(CommonUtils.PARAMETER_IS_NULL_OR_EMPTY, HttpStatus.BAD_REQUEST.value());
			logger.error(CommonUtils.PARAMETER_IS_NULL_OR_EMPTY);
			return new ResponseEntity<GatewayResponse>(gatewayResponse, HttpStatus.OK);
		} else {
			try {
				GatewayRequest gatewayRequest= new GatewayRequest();
				gatewayRequest.setApplicationId(applicationId);
				gatewayRequest.setStatus(CommonUtils.PaymentStatus.SUCCESS);
				
				
				gatewayResponse.setData(paymentService.sendAllMailToFSAndFP(gatewayRequest, proposalId, forFs, forFp) );
				gatewayResponse.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<GatewayResponse>(gatewayResponse, HttpStatus.OK);

			} catch (Exception e) {
				logger.trace(e.getMessage(), e);
				gatewayResponse = new GatewayResponse(CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR.value());
				logger.error("Error while getInPricipleDetailByApplicationId()");
				logger.trace(e.getMessage(), e);
				return new ResponseEntity<GatewayResponse>(gatewayResponse, HttpStatus.OK);
			}
		}
	}
	
	
	
}
