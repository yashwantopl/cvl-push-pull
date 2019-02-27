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

	private static final String USER_ID_CAN_NOT_BE_EMPTY_MSG = "userId  can not be empty ==>";
	private static final String NP_USER_ID_CAN_NOT_BE_EMPTY_MSG = "npUserId  can not be empty ==>";
	private static final String USER_ROLE_ID_CAN_NOT_BE_EMPTY_MSG = "userRoleId  can not be empty ==>";
	private static final String APPLICATION_ID_CAN_NOT_BE_EMPTY_MSG = "applicationId  can not be empty ==>";
	private static final String ERROR_WHILE_SETTING_MAKER_TO_PROPOSALS_MSG = "Error while setting maker to proposals : ";
	private static final String DATA_UPDATED = "Data Updated.";
	private static final String DATA_NOT_UPDATED = "Data Not Updated.";


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
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setListData(applicationsResponseList);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);			
		} catch (Exception e) {
			logger.error("Error while getting proposals based on application or ddr status : ", e);
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
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
				logger.warn("applicationStatusId  can not be empty ==>" + nhbsApplicationRequest.getApplicationStatusId());
				logger.warn(USER_ROLE_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getUserRoleIdString());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			nhbsApplicationRequest.setUserRoleId(Long.parseLong(CommonUtils.decode(nhbsApplicationRequest.getUserRoleIdString())));
			List<NhbsApplicationsResponse> applicationsResponseList = networkPartnerService.getListOfAssignedProposals(nhbsApplicationRequest);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setListData(applicationsResponseList);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Error while getting assigned proposals : ", e);
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
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
				logger.warn(APPLICATION_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getApplicationId());
				logger.warn("assignedUserId  can not be empty ==>" + nhbsApplicationRequest.getAssignedUserId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse();
			boolean isDataUpdated = networkPartnerService.setMaker(nhbsApplicationRequest);
			
				if(isDataUpdated){
				logger.info(DATA_UPDATED);
				
				loansResponse.setMessage(DATA_UPDATED);
				
				boolean status = networkPartnerService.sendSMSNotificationWhenCheckerAssignMaker(nhbsApplicationRequest.getApplicationId(),nhbsApplicationRequest.getAssignedUserId());
				logger.info("SMS notification status is:-"+status);
				
			} else {
				logger.info(DATA_NOT_UPDATED);
				loansResponse.setMessage(DATA_NOT_UPDATED);
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(isDataUpdated);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error(ERROR_WHILE_SETTING_MAKER_TO_PROPOSALS_MSG, e);
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
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
				logger.warn(APPLICATION_ID_CAN_NOT_BE_EMPTY_MSG + fpNpMappingRequest.getApplicationId());
				logger.warn("fpProductId  can not be empty ==>" + fpNpMappingRequest.getFpProductId());
				logger.warn(NP_USER_ID_CAN_NOT_BE_EMPTY_MSG + fpNpMappingRequest.getNpUserId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse();
			boolean isDataUpdated = fpNpMappingService.fpNpBoLevelMapping(fpNpMappingRequest);

			if(isDataUpdated){
				logger.info(DATA_UPDATED);
				loansResponse.setMessage(DATA_UPDATED);
				//boolean status = networkPartnerService.sendSMSNotificationWhenCheckerAssignMaker(nhbsApplicationRequest.getApplicationId(),nhbsApplicationRequest.getAssignedUserId());
				//logger.info("SMS notification status is:-"+status);

			} else {
				logger.info(DATA_NOT_UPDATED);
				loansResponse.setMessage(DATA_NOT_UPDATED);
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(isDataUpdated);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while mapping checker with fp to proposals : ", e);
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
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
				logger.warn(APPLICATION_ID_CAN_NOT_BE_EMPTY_MSG + fpNpMappingRequest.getApplicationId());
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
			logger.error("Error while getting checker name : ",e);
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
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
				logger.warn(USER_ROLE_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getUserRoleIdString());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse();
			Long orgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			nhbsApplicationRequest.setUserRoleId(Long.parseLong(CommonUtils.decode(nhbsApplicationRequest.getUserRoleIdString())));
			JSONObject jsonCountObj = networkPartnerService.getNhbsProposalCount(nhbsApplicationRequest,orgId);
			if(!CommonUtils.isObjectNullOrEmpty(jsonCountObj)){
				logger.info(CommonUtils.DATA_FOUND);
				loansResponse.setMessage(CommonUtils.DATA_FOUND);
			}else{
				logger.info(CommonUtils.DATA_NOT_FOUND);
				loansResponse.setMessage(CommonUtils.DATA_NOT_FOUND);
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(jsonCountObj);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Error while getting count of proposals : ", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get/proposalsList/fpMaker", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getProposalsListFPMaker(@RequestBody NhbsApplicationRequest applicationRequest,HttpServletRequest request) {
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
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setListData(applicationsResponseList);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting proposals based on application or ddr status : ", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*@RequestMapping(value = "/get/fp/assigned/proposals", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFPAssignedProposals(@RequestBody NhbsApplicationRequest nhbsApplicationRequest,HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if(CommonUtils.isObjectNullOrEmpty(userId) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getApplicationStatusId()) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getUserRoleIdString())){
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
				logger.warn("applicationStatusId  can not be empty ==>" + nhbsApplicationRequest.getApplicationStatusId());
				logger.warn(USER_ROLE_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getUserRoleIdString());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			nhbsApplicationRequest.setUserRoleId(Long.parseLong(CommonUtils.decode(nhbsApplicationRequest.getUserRoleIdString())));
			List<NhbsApplicationsResponse> applicationsResponseList = networkPartnerService.getListOfAssignedProposalsFP(nhbsApplicationRequest);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setListData(applicationsResponseList);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting assigned proposals : ", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

    @RequestMapping(value = "/get/fp/checker/proposals", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getFPCheckerProposals(@RequestBody NhbsApplicationRequest nhbsApplicationRequest,HttpServletRequest request) {
        try {
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            if(CommonUtils.isObjectNullOrEmpty(userId) ||
					(CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getApplicationStatusId()) && CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getDdrStatusId()))||
                    CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getUserRoleIdString()) ||
                    CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getFpProductId())){
                logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
                logger.warn("applicationStatusId or ddrStatusId can not be empty ==>" + nhbsApplicationRequest.getApplicationStatusId());
                logger.warn(USER_ROLE_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getUserRoleIdString());
                logger.warn("fpProduct  can not be empty ==>" + nhbsApplicationRequest.getFpProductId());
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            nhbsApplicationRequest.setUserId(userId);
            nhbsApplicationRequest.setUserRoleId(Long.parseLong(CommonUtils.decode(nhbsApplicationRequest.getUserRoleIdString())));
            List<NhbsApplicationsResponse> applicationsResponseList = networkPartnerService.getListOfCheckerProposalsFP(nhbsApplicationRequest);
            LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
            loansResponse.setListData(applicationsResponseList);
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while getting assigned proposals : ", e);
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
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
				logger.warn(USER_ROLE_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getUserRoleIdString());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse();
			Long orgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			nhbsApplicationRequest.setUserRoleId(Long.parseLong(CommonUtils.decode(nhbsApplicationRequest.getUserRoleIdString())));
			JSONObject jsonCountObj = networkPartnerService.getFPProposalCount(nhbsApplicationRequest,orgId);
			if(!CommonUtils.isObjectNullOrEmpty(jsonCountObj)){
				logger.info(CommonUtils.DATA_FOUND);
				loansResponse.setMessage(CommonUtils.DATA_FOUND);
			}else{
				logger.info(CommonUtils.DATA_NOT_FOUND);
				loansResponse.setMessage(CommonUtils.DATA_NOT_FOUND);
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(jsonCountObj);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting count of proposals : ", e);
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
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
				logger.warn(APPLICATION_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getApplicationId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse();
			boolean isDataUpdated = networkPartnerService.setFPMaker(nhbsApplicationRequest);
			if(isDataUpdated){
				logger.info(DATA_UPDATED);
				loansResponse.setMessage(DATA_UPDATED);
				/*boolean status = networkPartnerService.sendSMSNotificationWhenCheckerAssignMaker(nhbsApplicationRequest.getApplicationId(),nhbsApplicationRequest.getAssignedUserId());
				logger.info("SMS notification status is:-"+status);*/

			} else {
				logger.info(DATA_NOT_UPDATED);
				loansResponse.setMessage(DATA_NOT_UPDATED);
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(isDataUpdated);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_SETTING_MAKER_TO_PROPOSALS_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/set/fp/checker/byProposalId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> setFPCheckerByProposalId(@RequestBody NhbsApplicationRequest nhbsApplicationRequest, HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (CommonUtils.isObjectNullOrEmpty(userId) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getApplicationId()) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getProposalMappingId()) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getNpUserId())) {
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
				logger.warn(APPLICATION_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getApplicationId());
				logger.warn("proposalId  can not be empty ==>" + nhbsApplicationRequest.getProposalMappingId());
				logger.warn("npUserId  can not be empty ==>" + nhbsApplicationRequest.getNpUserId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse();
			boolean isDataUpdated = networkPartnerService.setFPCheckerByProposalId(nhbsApplicationRequest);
			if (isDataUpdated) {
				logger.info(DATA_UPDATED);
				loansResponse.setMessage(DATA_UPDATED);
				/*boolean status = networkPartnerService.sendSMSNotificationWhenCheckerAssignMaker(nhbsApplicationRequest.getApplicationId(),nhbsApplicationRequest.getAssignedUserId());
				logger.info("SMS notification status is:-"+status);*/

			} else {
				logger.info(DATA_NOT_UPDATED);
				loansResponse.setMessage(DATA_NOT_UPDATED);
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(isDataUpdated);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_SETTING_MAKER_TO_PROPOSALS_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/set/fp/checker", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> setFPChecker(@RequestBody NhbsApplicationRequest nhbsApplicationRequest, HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (CommonUtils.isObjectNullOrEmpty(userId) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getApplicationId()) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getNpUserId())) {
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
				logger.warn(APPLICATION_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getApplicationId());
				logger.warn(NP_USER_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getNpUserId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse();
			boolean isDataUpdated = networkPartnerService.setFPChecker(nhbsApplicationRequest);
			if (isDataUpdated) {
				logger.info(DATA_UPDATED);
				loansResponse.setMessage(DATA_UPDATED);
				/*boolean status = networkPartnerService.sendSMSNotificationWhenCheckerAssignMaker(nhbsApplicationRequest.getApplicationId(),nhbsApplicationRequest.getAssignedUserId());
				logger.info("SMS notification status is:-"+status);*/

			} else {
				logger.info(DATA_NOT_UPDATED);
				loansResponse.setMessage(DATA_NOT_UPDATED);
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(isDataUpdated);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_SETTING_MAKER_TO_PROPOSALS_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/set/fp/revert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> revertApplication(@RequestBody NhbsApplicationRequest nhbsApplicationRequest, HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (CommonUtils.isObjectNullOrEmpty(userId) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getApplicationId()) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getNpUserId())) {
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
				logger.warn(APPLICATION_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getApplicationId());
				logger.warn(NP_USER_ID_CAN_NOT_BE_EMPTY_MSG + nhbsApplicationRequest.getNpUserId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse();
			boolean isDataUpdated = networkPartnerService.revertApplication(nhbsApplicationRequest);
			if (isDataUpdated) {
				logger.info(DATA_UPDATED);
				loansResponse.setMessage(DATA_UPDATED);
				/*boolean status = networkPartnerService.sendSMSNotificationWhenCheckerAssignMaker(nhbsApplicationRequest.getApplicationId(),nhbsApplicationRequest.getAssignedUserId());
				logger.info("SMS notification status is:-"+status);*/

			} else {
				logger.info(DATA_NOT_UPDATED);
				loansResponse.setMessage(DATA_NOT_UPDATED);
			}
			loansResponse.setStatus(HttpStatus.OK.value());
			loansResponse.setData(isDataUpdated);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_SETTING_MAKER_TO_PROPOSALS_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
