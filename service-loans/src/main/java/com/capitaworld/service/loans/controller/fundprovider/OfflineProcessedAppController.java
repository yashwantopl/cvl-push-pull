package com.capitaworld.service.loans.controller.fundprovider;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.OfflineProcessedApplicationRequest;
import com.capitaworld.service.loans.service.fundprovider.OfflineProcessedAppService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UserTypeRequest;
import com.capitaworld.service.users.model.UsersRequest;


@RestController
@RequestMapping("/offlineApplication")
public class OfflineProcessedAppController {
	
	private static final Logger logger = LoggerFactory.getLogger(OfflineProcessedAppController.class);
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private OfflineProcessedAppService offlineProcessedApplicationService;
	
	@RequestMapping(value="/applicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getApplicationList(HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId)  {
		//GET USER ID AND USER TYPE
		Long userId = null;
		Integer userType = null;
		Long orgId = null;
		if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
			 userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();		
		}
		if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ORG_ID))) {
			 orgId = ((Long) request.getAttribute(CommonUtils.USER_ORG_ID)).longValue();		
		}
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if(!CommonUtils.isObjectNullOrEmpty(clientId) && userType != CommonUtils.UserType.FUND_PROVIDER){
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if(response != null && response.getData() != null){
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					}else{
						logger.warn("user_verification, Invalid Request... Client Id is not valid");
						return new ResponseEntity<LoansResponse>(new LoansResponse("Client Id is not valid",HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
					}
				}catch(Exception e) {
					logger.warn("user_verification, Invalid Request... Something went wrong");
					e.printStackTrace();
					return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			}else {
				if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
					userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();		
				}
				if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
					userId = ((Long) request.getAttribute(CommonUtils.USER_ID));		
				}
			}

		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}
		     List<OfflineProcessedApplicationRequest> lst = offlineProcessedApplicationService.getApplicationList(orgId);
		     LoansResponse loansResponse = new LoansResponse();
			try {
				if(!CommonUtils.isObjectNullOrEmpty(lst)){
					loansResponse.setData(lst);
					loansResponse.setMessage("Successfully fetched Ineligible Loans List");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found Ineligible List");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse,HttpStatus.OK);
			}catch (Exception e){
				loansResponse.setData(lst);
				loansResponse.setMessage("Something went wrong..!");
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/sanctionedApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getSanctionedApplicationList(HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId)  {
		//GET USER ID AND USER TYPE
		Long userId = null;
		Integer userType = null;
		Long orgId = null;
		if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
			 userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();		
		}
		if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ORG_ID))) {
			 orgId = ((Long) request.getAttribute(CommonUtils.USER_ORG_ID)).longValue();		
		}
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if(!CommonUtils.isObjectNullOrEmpty(clientId) && userType != CommonUtils.UserType.FUND_PROVIDER){
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if(response != null && response.getData() != null){
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					}else{
						logger.warn("user_verification, Invalid Request... Client Id is not valid");
						return new ResponseEntity<LoansResponse>(new LoansResponse("Client Id is not valid",HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
					}
				}catch(Exception e) {
					logger.warn("user_verification, Invalid Request... Something went wrong");
					e.printStackTrace();
					return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			}else {
				if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
					userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();		
				}
				if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
					userId = ((Long) request.getAttribute(CommonUtils.USER_ID));		
				}
			}

		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}
		     List<OfflineProcessedApplicationRequest> lst = offlineProcessedApplicationService.getSanctionedApplicationList(orgId);
		     LoansResponse loansResponse = new LoansResponse();
			try {
				if(!CommonUtils.isObjectNullOrEmpty(lst)){
					loansResponse.setData(lst);
					loansResponse.setMessage("Successfully fetched Sanctioned Loans List");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found Sanctioned Loan List");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse,HttpStatus.OK);
			}catch (Exception e){
				loansResponse.setData(lst);
				loansResponse.setMessage("Something went wrong..!");
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/disbursedApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getDisbursedApplicationList(HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId)  {
		//GET USER ID AND USER TYPE
		Long userId = null;
		Integer userType = null;
		Long orgId = null;
		if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
			 userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();		
		}
		if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ORG_ID))) {
			 orgId = ((Long) request.getAttribute(CommonUtils.USER_ORG_ID)).longValue();		
		}
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if(!CommonUtils.isObjectNullOrEmpty(clientId) && userType != CommonUtils.UserType.FUND_PROVIDER){
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if(response != null && response.getData() != null){
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					}else{
						logger.warn("user_verification, Invalid Request... Client Id is not valid");
						return new ResponseEntity<LoansResponse>(new LoansResponse("Client Id is not valid",HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
					}
				}catch(Exception e) {
					logger.warn("user_verification, Invalid Request... Something went wrong");
					e.printStackTrace();
					return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			}else {
				if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
					userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();		
				}
				if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
					userId = ((Long) request.getAttribute(CommonUtils.USER_ID));		
				}
			}

		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}
		     List<OfflineProcessedApplicationRequest> lst = offlineProcessedApplicationService.getDisbursedApplicationList(orgId);
		     LoansResponse loansResponse = new LoansResponse();
			try {
				if(!CommonUtils.isObjectNullOrEmpty(lst)){
					loansResponse.setData(lst);
					loansResponse.setMessage("Successfully fetched Disbursed Loans List");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found Disbursed Loan List");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse,HttpStatus.OK);
			}catch (Exception e){
				loansResponse.setData(lst);
				loansResponse.setMessage("Something went wrong..!");
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		}
		
	}
	
	
	
}
