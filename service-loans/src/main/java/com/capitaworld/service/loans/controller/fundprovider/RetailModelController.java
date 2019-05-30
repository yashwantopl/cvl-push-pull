package com.capitaworld.service.loans.controller.fundprovider;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.service.loans.model.HomeLoanModelRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.RetailModelRequest;
import com.capitaworld.service.loans.model.WorkflowData;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanModelService;
import com.capitaworld.service.loans.service.fundprovider.RetailModelService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/model")
public class RetailModelController {

	private static final Logger logger = LoggerFactory.getLogger(RetailModelController.class);

	private static final String USER_ID_CAN_NOT_BE_EMPTY_MSG = "userId can not be empty ==>{}";
	private static final String METHOD_CREATE = "create() ";
	private static final String METHOD_CREATE_WITH_ERROR = "create() With Error";
	private static final String METHOD_GET_TEMP_LIST = "getTempList() ";
	private static final String METHOD_GET_LIST = "getList() ";

	@Autowired
	private RetailModelService retailModelService;
	
	@Autowired
	private HomeLoanModelService homeLoanModelService;

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> create(@RequestBody RetailModelRequest  retailModelRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info(CommonUtils.ENTRY_IN + METHOD_CREATE);
		try {
			// request must not be null

			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			if (userId == null) {
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG , userId);
				logger.info(CommonUtils.EXIT_FROM + METHOD_CREATE);
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			retailModelRequest.setUserId(userId);
			retailModelRequest.setOrgId(userOrgId);
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				retailModelRequest.setClientId(clientId);
			}
			logger.info(CommonUtils.EXIT_FROM + METHOD_CREATE);
			return new ResponseEntity<>( new LoansResponse(CommonUtils.SUCCESSFULLY_SAVED, HttpStatus.OK.value(),retailModelService.saveToTemp(retailModelRequest)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saving Loan Purpose Model Details==> {} ", e);
			logger.info(CommonUtils.EXIT_FROM + METHOD_CREATE_WITH_ERROR );
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/get_temp_list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getTempList(HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info(CommonUtils.ENTRY_IN + METHOD_GET_TEMP_LIST);
		try {
			// request must not be null

			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			if (userId == null) {
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG , userId);
				logger.info(CommonUtils.EXIT_FROM + METHOD_GET_TEMP_LIST);
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			logger.info(CommonUtils.EXIT_FROM + METHOD_GET_TEMP_LIST);
			return new ResponseEntity<>( new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value(),retailModelService.getTemp(userOrgId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Getting Loan Purpose Model TempList Details==> {} ", e);
			logger.info(CommonUtils.EXIT_FROM + METHOD_GET_TEMP_LIST + " with Error");
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info(CommonUtils.ENTRY_IN + METHOD_GET_LIST);
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			if (userId == null) {
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG , userId);
				logger.info(CommonUtils.EXIT_FROM + METHOD_GET_LIST);
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			logger.info(CommonUtils.EXIT_FROM + METHOD_GET_LIST);
			return new ResponseEntity<>( new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value(),retailModelService.get(userOrgId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Getting Loan Purpose Model TempList Details==> {} ", e);
			logger.info(CommonUtils.EXIT_FROM + METHOD_GET_LIST + " with Error");
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/hl/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> hlUpdate(@RequestBody HomeLoanModelRequest homeLoanModelRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info(CommonUtils.ENTRY_IN + METHOD_CREATE);
		try {
			// request must not be null

			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			if (userId == null) {
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG , userId);
				logger.info(CommonUtils.EXIT_FROM + METHOD_CREATE);
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			homeLoanModelRequest.setUserId(userId);
			homeLoanModelRequest.setOrgId(userOrgId);
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				homeLoanModelRequest.setClientId(clientId);
			}
			logger.info(CommonUtils.EXIT_FROM + METHOD_CREATE);
			return new ResponseEntity<>( new LoansResponse(CommonUtils.SUCCESSFULLY_UPDATED, HttpStatus.OK.value(),homeLoanModelService.saveToTemp(homeLoanModelRequest)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saving Loan Purpose Model Details==> {} ", e);
			logger.info(CommonUtils.EXIT_FROM + METHOD_CREATE_WITH_ERROR );
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/hl/get_temp/{modelId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> hlGetTemp(@RequestBody List<Long> roles,@PathVariable("modelId") Long modelId, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			// request must not be null
			if (roles == null || roles.isEmpty()) {
				logger.warn("Roles Must not be Null == >{}" , roles);
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG , userId);
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			return new ResponseEntity<>( new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(),homeLoanModelService.getTemp(modelId, roles.get(0), userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saving Loan Purpose Model Details For HomeLoan==> {} ", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/hl/get/{modelId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> hlGet(@RequestBody List<Long> roles,@PathVariable("modelId") Long modelId, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			// request must not be null
			if (roles == null || roles.isEmpty()) {
				logger.warn("Roles Must not be Null == >{}" , roles);
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG , userId);
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			return new ResponseEntity<>( new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(),homeLoanModelService.get(modelId, roles.get(0), userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saving Loan Purpose Model Details For HomeLoan==> {} ", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/hl/get_client/{modelId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HomeLoanModelRequest hlGetForClient(@PathVariable("modelId") Long modelId, HttpServletRequest request) {
		try {
			return homeLoanModelService.get(modelId, null, null);
		} catch (Exception e) {
			logger.error("Error while Getting Loan Purpose Model Details For HomeLoan Client==> {} ", e);
			return null;
		}
	}

	@PostMapping(value = "/update_status/{bti}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateStatus(@RequestBody WorkflowData workflowData,@PathVariable("bti") Integer businessTypeId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info(CommonUtils.ENTRY_IN + METHOD_CREATE);
		try {
			// request must not be null

			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG , userId);
				logger.info(CommonUtils.EXIT_FROM + METHOD_CREATE);
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			workflowData.setUserId(userId);
			String msg = null;
			if (WorkflowUtils.Action.SEND_FOR_APPROVAL.equals(workflowData.getActionId())) {
				msg = "Purpose of Loan Model sent for approval";
			}if (WorkflowUtils.Action.SEND_BACK.equals(workflowData.getActionId())) {
				msg = "Scoring Model is successfully sent back";
			}
			return new ResponseEntity<>(new LoansResponse(msg, HttpStatus.OK.value(),retailModelService.processWorkflow(workflowData, businessTypeId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Updating Workflow Process==> {} ", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
}
