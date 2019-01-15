package com.capitaworld.service.loans.controller.fundprovider;


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
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.fundprovider.OfflineProcessedAppService;
import com.capitaworld.service.loans.utils.CommonUtils;


@RestController
@RequestMapping("/offlineApplication")
public class OfflineProcessedAppController {
	
	private static final Logger logger = LoggerFactory.getLogger(OfflineProcessedAppController.class);
	
	
	@Autowired
	private OfflineProcessedAppService offlineProcessedApplicationService;
	
	@RequestMapping(value="/applicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getApplicationList(HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId)  {
		//GET USER ID AND USER TYPE
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully fetched Ineligible Loans List", HttpStatus.OK.value(),offlineProcessedApplicationService.getApplicationList(userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while get pending offline proposal list : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/uniformApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUniformApplicationList(HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId)  {
		//GET USER ID AND USER TYPE
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully fetched Ineligible Loans List", HttpStatus.OK.value(),offlineProcessedApplicationService.getUniformApplicationList(userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while get pending offline proposal list : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/sanctionedApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getSanctionedApplicationList(HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId)  {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully fetched Sanctioned Loans List", HttpStatus.OK.value(),offlineProcessedApplicationService.getSanctionedApplicationList(userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while get sanctioned offline proposal list : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/uniformSanctionedApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUniformSanctionedApplicationList(HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId)  {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully fetched Sanctioned Loans List", HttpStatus.OK.value(),offlineProcessedApplicationService.getUniformSanctionedApplicationList(userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while get sanctioned offline proposal list : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/disbursedApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getDisbursedApplicationList(HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId)  {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully fetched Disbursed Loans List", HttpStatus.OK.value(),offlineProcessedApplicationService.getDisbursedApplicationList(userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while get disbursed offline proposal list : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/uniformDisbursedApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUniformDisbursedApplicationList(HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId)  {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully fetched Disbursed Loans List", HttpStatus.OK.value(),offlineProcessedApplicationService.getUniformDisbursedApplicationList(userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while get disbursed offline proposal list : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/rejectApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getRejectApplicationList(HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId)  {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully fetched Rejected Loans List", HttpStatus.OK.value(),offlineProcessedApplicationService.getRejectProposalList(userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while get reject offline proposal list : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/uniformRejectApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUniformRejectApplicationList(HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId)  {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully fetched Rejected Loans List", HttpStatus.OK.value(),offlineProcessedApplicationService.getUniformRejectProposalList(userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while get reject offline proposal list : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/otherApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getOtherApplicationList(HttpServletRequest request)  {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully fetched Other Loans List", HttpStatus.OK.value(),offlineProcessedApplicationService.getOtherProposalList(userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while get Other offline proposal list : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/uniformOtherApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUniformOtherApplicationList(HttpServletRequest request)  {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully fetched Other Loans List", HttpStatus.OK.value(),offlineProcessedApplicationService.getUniformOtherProposalList(userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while get Other offline proposal list : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
		
	}
	/*		Long userId = null;
	Integer userType = null;
	Long orgId = null;
	if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
		 userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
	}
	if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ORG_ID))) {
		 orgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
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
					logger.warn(WARN_MSG_USER_VERIFICATION_INVALID_REQUEST_CLIENT_ID_IS_NOT_VALID);
					return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.CLIENT_ID_IS_NOT_VALID,HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
				}
			}catch(Exception e) {
				logger.error(ERROR_MSG_USER_VERIFICATION_INVALID_REQUEST_SOMETHING_WENT_WRONG,e);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
			}
		}else {
			if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
				userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
			}
			if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
				userId = ((Long) request.getAttribute(CommonUtils.USER_ID));		
			}
		}

	} else {
		userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
	}*/
	
	
}
