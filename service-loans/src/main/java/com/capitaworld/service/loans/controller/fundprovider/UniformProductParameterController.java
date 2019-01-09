package com.capitaworld.service.loans.controller.fundprovider;

import java.util.List;

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
import com.capitaworld.service.loans.model.corporate.UniformProductParamterRequest;
import com.capitaworld.service.loans.service.fundprovider.UniformProductParameterAuditService;
import com.capitaworld.service.loans.service.fundprovider.UniformProductParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/uniform/product")
public class UniformProductParameterController {

	private static final Logger logger = LoggerFactory.getLogger(UniformProductParameterController.class.getName());

	private static final String USER_ID_AND_ORG_ID_CAN_NOT_BE_EMPTY_MSG = "userId and OrgId can not be empty ==> Org Id ==>{}";
	private static final String UNAUTHORIZED_PLEASE_RE_LOGIN_MSG = "Unauthorized!. Please Re-login.";

	@Autowired
	private UniformProductParameterService uniformProductParameterService; 
	
	@Autowired
	private UniformProductParameterAuditService uniformProductParameterAuditService; 

	/*@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}*/

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody UniformProductParamterRequest paramterRequest,HttpServletRequest httpServletRequest) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "save");
		try {

			Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			Long orgId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ORG_ID);
			//Long userId=1755l;
			if(userId == null || orgId == null)
			{
				logger.warn(USER_ID_AND_ORG_ID_CAN_NOT_BE_EMPTY_MSG, userId,orgId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(UNAUTHORIZED_PLEASE_RE_LOGIN_MSG, HttpStatus.UNAUTHORIZED.value()),
						HttpStatus.OK);
			}
			paramterRequest.setUserId(userId);
			paramterRequest.setUserOrgId(orgId);
			Boolean saveOrUpdate = uniformProductParameterService.saveOrUpdateTemp(paramterRequest);
			if(saveOrUpdate){
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESS_RESULT, HttpStatus.OK.value()),HttpStatus.OK);				
			}else{
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INTERNAL_SERVER_ERROR, HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving Uniform Product Parameter==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> get(@RequestBody List<Long> roleIds, HttpServletRequest httpServletRequest) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "get");
		try {
			Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			Long orgId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ORG_ID);
			//Long userId=1755l;
			if(userId == null || orgId == null)
			{
				logger.warn(USER_ID_AND_ORG_ID_CAN_NOT_BE_EMPTY_MSG, userId,orgId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(UNAUTHORIZED_PLEASE_RE_LOGIN_MSG, HttpStatus.UNAUTHORIZED.value()),
						HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(),uniformProductParameterService.getTempObj(userId, orgId,roleIds)),HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Getting Uniform Product parameter Details==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
//	@RequestMapping(value = "/save_master", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<LoansResponse> saveMaster(@RequestBody UniformProductParamterRequest paramterRequest,HttpServletRequest httpServletRequest) {
//		// request must not be null
//		CommonDocumentUtils.startHook(logger, "save");
//		try {
//
//			Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
//			Long orgId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ORG_ID);
//			//Long userId=1755l;
//			if(userId == null || orgId == null)
//			{
//				logger.warn(USER_ID_AND_ORG_ID_CAN_NOT_BE_EMPTY_MSG, userId,orgId);
//				return new ResponseEntity<LoansResponse>(
//						new LoansResponse(UNAUTHORIZED_PLEASE_RE_LOGIN_MSG, HttpStatus.UNAUTHORIZED.value()),
//						HttpStatus.OK);
//			}
//			paramterRequest.setUserId(userId);
//			paramterRequest.setUserOrgId(orgId);
//			Boolean saveOrUpdate = uniformProductParameterService.saveOrUpdateTemp(paramterRequest);
//			if(saveOrUpdate){
//				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESS_RESULT, HttpStatus.OK.value()),HttpStatus.OK);				
//			}else{
//				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INTERNAL_SERVER_ERROR, HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			logger.error("Error while saving Uniform Product Parameter==>{}", e);
//			return new ResponseEntity<LoansResponse>(
//					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
//					HttpStatus.OK);
//		}
//
//	}

	@RequestMapping(value = "/get_master", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getMaster(@RequestBody List<Long> roleIds, HttpServletRequest httpServletRequest) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "get");
		try {
			Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			Long orgId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ORG_ID);
			//Long userId=1755l;
			if(userId == null || orgId == null)
			{
				logger.warn(USER_ID_AND_ORG_ID_CAN_NOT_BE_EMPTY_MSG, userId,orgId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(UNAUTHORIZED_PLEASE_RE_LOGIN_MSG, HttpStatus.UNAUTHORIZED.value()),
						HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(),uniformProductParameterService.get(userId, orgId)),HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Getting Uniform Product parameter Details==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/get_audit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getAudit(HttpServletRequest httpServletRequest) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getAudit");
		try {
			Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			Long orgId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ORG_ID);
			//Long userId=1755l;
			if(userId == null || orgId == null)
			{
				logger.warn(USER_ID_AND_ORG_ID_CAN_NOT_BE_EMPTY_MSG, userId,orgId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(UNAUTHORIZED_PLEASE_RE_LOGIN_MSG, HttpStatus.UNAUTHORIZED.value()),
						HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(),uniformProductParameterAuditService.get(userId, orgId)),HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Getting Uniform Product parameter Audit Details==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

}
