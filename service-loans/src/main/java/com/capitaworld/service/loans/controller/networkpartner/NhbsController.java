package com.capitaworld.service.loans.controller.networkpartner;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.capitaworld.service.loans.model.FpNpMappingRequest;
import com.capitaworld.service.loans.service.fundprovider.FpNpMappingService;
import org.json.simple.JSONObject;
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
import com.capitaworld.service.loans.model.NhbsApplicationRequest;
import com.capitaworld.service.loans.model.NhbsApplicationsResponse;
import com.capitaworld.service.loans.service.networkpartner.NetworkPartnerService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
public class NhbsController {

	private static final Logger logger = LoggerFactory.getLogger(NhbsController.class);
	
	@Autowired
	private NetworkPartnerService networkPartnerService;

	@Autowired
	private FpNpMappingService fpNpMappingService;
	
	@RequestMapping(value = "/get/proposals", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getProposals(@RequestBody NhbsApplicationRequest applicationRequest,HttpServletRequest request) {
		try {
			
			if(CommonUtils.isObjectNullOrEmpty(applicationRequest) ||
					CommonUtils.isObjectNullOrEmpty(applicationRequest.getUserRoleIdString()) ||
					(CommonUtils.isObjectNullOrEmpty(applicationRequest.getApplicationStatusId()) && CommonUtils.isObjectNullOrEmpty(applicationRequest.getDdrStatusId()))){
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			Long orgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			
			applicationRequest.setUserRoleId(Long.parseLong(CommonUtils.decode(applicationRequest.getUserRoleIdString())));
			List<NhbsApplicationsResponse> applicationsResponseList = networkPartnerService.getListOfProposals(applicationRequest,orgId,userId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(applicationsResponseList);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);			
		} catch (Exception e) {
			logger.error("Error while getting proposals based on application or ddr status.", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/get/assigned/proposals", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getAssignedProposals(@RequestBody NhbsApplicationRequest nhbsApplicationRequest,HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.isObjectNullOrEmpty(userId) || 
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getApplicationStatusId()) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getUserRoleIdString())){
				logger.warn("userId  can not be empty ==>" + userId);
				logger.warn("applicationStatusId  can not be empty ==>" + nhbsApplicationRequest.getApplicationStatusId());
				logger.warn("userRoleId  can not be empty ==>" + nhbsApplicationRequest.getUserRoleIdString());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			nhbsApplicationRequest.setUserRoleId(Long.parseLong(CommonUtils.decode(nhbsApplicationRequest.getUserRoleIdString())));
			List<NhbsApplicationsResponse> applicationsResponseList = networkPartnerService.getListOfAssignedProposals(nhbsApplicationRequest);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(applicationsResponseList);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Error while getting assigned proposals ", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/set/maker", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> setMaker(@RequestBody NhbsApplicationRequest nhbsApplicationRequest,HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.isObjectNullOrEmpty(userId) || 
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getApplicationId()) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getAssignedUserId())){
				logger.warn("userId  can not be empty ==>" + userId);
				logger.warn("applicationId  can not be empty ==>" + nhbsApplicationRequest.getApplicationId());
				logger.warn("assignedUserId  can not be empty ==>" + nhbsApplicationRequest.getAssignedUserId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse();
			boolean isDataUpdated = networkPartnerService.setMaker(nhbsApplicationRequest);
			
				if(isDataUpdated){
				logger.info("Data Updated.");
				
				loansResponse.setMessage("Data Updated.");
				
				boolean status = networkPartnerService.sendSMSNotificationWhenCheckerAssignMaker(nhbsApplicationRequest.getApplicationId(),nhbsApplicationRequest.getAssignedUserId());
				logger.info("SMS notification status is:-"+status);
				
			} else {
				logger.info("Data Not Updated.");
				loansResponse.setMessage("Data Not Updated.");
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(isDataUpdated);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Error while setting maker to proposals ", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/set/checker", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> setChecker(@RequestBody FpNpMappingRequest fpNpMappingRequest, HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.isObjectNullOrEmpty(userId) ||
					CommonUtils.isObjectNullOrEmpty(fpNpMappingRequest) ||
					CommonUtils.isObjectNullOrEmpty(fpNpMappingRequest.getApplicationId()) ||
					CommonUtils.isObjectNullOrEmpty(fpNpMappingRequest.getFpProductId()) ||
					CommonUtils.isObjectNullOrEmpty(fpNpMappingRequest.getNpUserId())){
				logger.warn("userId  can not be empty ==>" + userId);
				logger.warn("applicationId  can not be empty ==>" + fpNpMappingRequest.getApplicationId());
				logger.warn("fpProductId  can not be empty ==>" + fpNpMappingRequest.getFpProductId());
				logger.warn("npUserId  can not be empty ==>" + fpNpMappingRequest.getNpUserId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse();
			boolean isDataUpdated = fpNpMappingService.fpNpBoLevelMapping(fpNpMappingRequest);

			if(isDataUpdated){
				logger.info("Data Updated.");
				loansResponse.setMessage("Data Updated.");
				//boolean status = networkPartnerService.sendSMSNotificationWhenCheckerAssignMaker(nhbsApplicationRequest.getApplicationId(),nhbsApplicationRequest.getAssignedUserId());
				//logger.info("SMS notification status is:-"+status);

			} else {
				logger.info("Data Not Updated.");
				loansResponse.setMessage("Data Not Updated.");
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(isDataUpdated);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while mapping checker with fp to proposals ", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get/checker", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getChecker(@RequestBody FpNpMappingRequest fpNpMappingRequest, HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.isObjectNullOrEmpty(userId) ||
					CommonUtils.isObjectNullOrEmpty(fpNpMappingRequest) ||
					CommonUtils.isObjectNullOrEmpty(fpNpMappingRequest.getApplicationId()) ||
					CommonUtils.isObjectNullOrEmpty(fpNpMappingRequest.getFpProductId())){
				logger.warn("userId  can not be empty ==>" + userId);
				logger.warn("applicationId  can not be empty ==>" + fpNpMappingRequest.getApplicationId());
				logger.warn("fpProductId  can not be empty ==>" + fpNpMappingRequest.getFpProductId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse();
			String checkerName = networkPartnerService.getCheckerName(fpNpMappingRequest);

			if(!CommonUtils.isObjectNullOrEmpty(checkerName)){
				loansResponse.setMessage("Data fetched.");
				//boolean status = networkPartnerService.sendSMSNotificationWhenCheckerAssignMaker(nhbsApplicationRequest.getApplicationId(),nhbsApplicationRequest.getAssignedUserId());
				//logger.info("SMS notification status is:-"+status);

			} else {
				logger.info("Checker name not found.");
				loansResponse.setMessage("Checker name not found.");
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(checkerName);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting checker name");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get/nhbsProposalCount", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getNhbsProposalCount(@RequestBody NhbsApplicationRequest nhbsApplicationRequest,HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.isObjectNullOrEmpty(userId) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getUserRoleIdString())){
				logger.warn("userId  can not be empty ==>" + userId);
				logger.warn("userRoleId  can not be empty ==>" + nhbsApplicationRequest.getUserRoleIdString());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse();
			Long orgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			nhbsApplicationRequest.setUserRoleId(Long.parseLong(CommonUtils.decode(nhbsApplicationRequest.getUserRoleIdString())));
			JSONObject jsonCountObj = networkPartnerService.getNhbsProposalCount(nhbsApplicationRequest,orgId);
			if(!CommonUtils.isObjectNullOrEmpty(jsonCountObj)){
				logger.info("Data Found.");
				loansResponse.setMessage("Data Found.");
			}else{
				logger.info("Data Not Found.");
				loansResponse.setMessage("Data Not Found.");
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(jsonCountObj);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Error while getting count of proposals ", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get/proposalsList/fp", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getProposalsListFP(@RequestBody NhbsApplicationRequest applicationRequest,HttpServletRequest request) {
		try {

			if(CommonUtils.isObjectNullOrEmpty(applicationRequest) ||
					CommonUtils.isObjectNullOrEmpty(applicationRequest.getUserRoleIdString()) ||
					(CommonUtils.isObjectNullOrEmpty(applicationRequest.getApplicationStatusId()) && CommonUtils.isObjectNullOrEmpty(applicationRequest.getDdrStatusId()))){
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			Long orgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			applicationRequest.setUserRoleId(Long.parseLong(CommonUtils.decode(applicationRequest.getUserRoleIdString())));
			List<NhbsApplicationsResponse> applicationsResponseList = networkPartnerService.getListOfProposalsFP(applicationRequest,orgId,userId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(applicationsResponseList);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting proposals based on application or ddr status.", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get/fp/assigned/proposals", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFPAssignedProposals(@RequestBody NhbsApplicationRequest nhbsApplicationRequest,HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.isObjectNullOrEmpty(userId) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getApplicationStatusId()) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getUserRoleIdString())){
				logger.warn("userId  can not be empty ==>" + userId);
				logger.warn("applicationStatusId  can not be empty ==>" + nhbsApplicationRequest.getApplicationStatusId());
				logger.warn("userRoleId  can not be empty ==>" + nhbsApplicationRequest.getUserRoleIdString());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			nhbsApplicationRequest.setUserRoleId(Long.parseLong(CommonUtils.decode(nhbsApplicationRequest.getUserRoleIdString())));
			List<NhbsApplicationsResponse> applicationsResponseList = networkPartnerService.getListOfAssignedProposalsFP(nhbsApplicationRequest);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(applicationsResponseList);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting assigned proposals ", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get/fpProposalCount", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFPProposalCount(@RequestBody NhbsApplicationRequest nhbsApplicationRequest,HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.isObjectNullOrEmpty(userId) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getUserRoleIdString())){
				logger.warn("userId  can not be empty ==>" + userId);
				logger.warn("userRoleId  can not be empty ==>" + nhbsApplicationRequest.getUserRoleIdString());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse();
			Long orgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			nhbsApplicationRequest.setUserRoleId(Long.parseLong(CommonUtils.decode(nhbsApplicationRequest.getUserRoleIdString())));
			JSONObject jsonCountObj = networkPartnerService.getNhbsProposalCount(nhbsApplicationRequest,orgId);
			if(!CommonUtils.isObjectNullOrEmpty(jsonCountObj)){
				logger.info("Data Found.");
				loansResponse.setMessage("Data Found.");
			}else{
				logger.info("Data Not Found.");
				loansResponse.setMessage("Data Not Found.");
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(jsonCountObj);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting count of proposals ", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/set/fp/maker", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> setFpMaker(@RequestBody NhbsApplicationRequest nhbsApplicationRequest,HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.isObjectNullOrEmpty(userId) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getApplicationId())){
				logger.warn("userId  can not be empty ==>" + userId);
				logger.warn("applicationId  can not be empty ==>" + nhbsApplicationRequest.getApplicationId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse();
			boolean isDataUpdated = networkPartnerService.setFPMaker(nhbsApplicationRequest);
			if(isDataUpdated){
				logger.info("Data Updated.");
				loansResponse.setMessage("Data Updated.");
				/*boolean status = networkPartnerService.sendSMSNotificationWhenCheckerAssignMaker(nhbsApplicationRequest.getApplicationId(),nhbsApplicationRequest.getAssignedUserId());
				logger.info("SMS notification status is:-"+status);*/

			} else {
				logger.info("Data Not Updated.");
				loansResponse.setMessage("Data Not Updated.");
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(isDataUpdated);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while setting maker to proposals ", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/set/fp/checker", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> setFPChecker(@RequestBody FpNpMappingRequest fpNpMappingRequest, HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.isObjectNullOrEmpty(userId) ||
					CommonUtils.isObjectNullOrEmpty(fpNpMappingRequest) ||
					CommonUtils.isObjectNullOrEmpty(fpNpMappingRequest.getApplicationId()) ||
					CommonUtils.isObjectNullOrEmpty(fpNpMappingRequest.getNpUserId())){
				logger.warn("userId  can not be empty ==>" + userId);
				logger.warn("applicationId  can not be empty ==>" + fpNpMappingRequest.getApplicationId());
				logger.warn("npUserId  can not be empty ==>" + fpNpMappingRequest.getNpUserId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse();
			boolean isDataUpdated = fpNpMappingService.fpNpBoLevelMapping(fpNpMappingRequest);

			if(isDataUpdated){
				logger.info("Data Updated.");
				loansResponse.setMessage("Data Updated.");
				//boolean status = networkPartnerService.sendSMSNotificationWhenCheckerAssignMaker(nhbsApplicationRequest.getApplicationId(),nhbsApplicationRequest.getAssignedUserId());
				//logger.info("SMS notification status is:-"+status);

			} else {
				logger.info("Data Not Updated.");
				loansResponse.setMessage("Data Not Updated.");
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(isDataUpdated);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while mapping checker with fp to proposals ", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
