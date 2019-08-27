package com.capitaworld.service.loans.controller.fundprovider;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.WorkflowData;
import com.capitaworld.service.loans.model.corporate.CoLendingRequest;
import com.capitaworld.service.loans.service.fundprovider.CoLendingService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.WorkflowData;
import com.capitaworld.service.loans.model.corporate.AddProductRequest;
import com.capitaworld.service.loans.model.corporate.CoLendingRequest;
import com.capitaworld.service.loans.service.fundprovider.CoLendingService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/co_lending")
public class CoLendingRatioController {

	private static final Logger logger = LoggerFactory.getLogger(CoLendingRatioController.class);

	@Autowired
	private CoLendingService coLendingService;


	@GetMapping(value = "/getList/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getListByUserType(HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId,@PathVariable(value = "role")Long role) {
		// request must not be null
		CommonDocumentUtils.startHook(logger,"start getListByUserType");
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}		
			//get org id
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			
			if (userId == null) {
				logger.warn("User Id is mandatory", userId);
				CommonDocumentUtils.endHook(logger, "end getListByUserType");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			//List<ProductMasterRequest> response = productMasterService.getListByUserType(userId, userType);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setListData(coLendingService.listAll(userId,userOrgId,role));
			CommonDocumentUtils.endHook(logger, "end getListByUserType");
			return new ResponseEntity<>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting colending ratio", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getBankList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LoansResponse> getBankList() {
		// request must not be null
		CommonDocumentUtils.startHook(logger,"start getBankList");
		try {
			
			
			//List<ProductMasterRequest> response = productMasterService.getListByUserType(userId, userType);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setListData(coLendingService.getBankList());
			CommonDocumentUtils.endHook(logger, "end getBankList");
			return new ResponseEntity<>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while  getBankList", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> addProduct(@RequestBody CoLendingRequest coLendingRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		CommonDocumentUtils.startHook(logger, "save");
		try {
			// request must not be null

			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);

			if (userId == null) {
				logger.warn("user id can not be null" + userId);
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (coLendingRequest == null) {
				logger.warn("coLendingRequest Object can not be empty ==>" + coLendingRequest);
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			coLendingRequest.setUserId(userId);
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				coLendingRequest.setClientId(clientId);
			}

			Boolean response = coLendingService.saveOrUpdate(coLendingRequest,userOrgId);
			if (response) {
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.SUCCESSFULLY_SAVED, HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving colending Details==>", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/clickOnNbfcWorkFlow", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> clickOnWorkFlowButton(@RequestBody WorkflowData workflowData,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		CommonDocumentUtils.startHook(logger, "clickOnNbfcWorkFlow");
		try {
			// request must not be null

			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			
			workflowData.setUserId(userId);
			if(CommonUtils.isObjectListNull(workflowData.getActionId(),workflowData.getJobId(),workflowData.getNextworkflowStep(),workflowData.getWorkflowStep()))
			{
				logger.warn("workflow data can not be null" );
				CommonDocumentUtils.endHook(logger, "clickOnNbfcWorkFlow");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			if (userId == null) {
				logger.warn("user id can not be null" + userId);
				CommonDocumentUtils.endHook(logger, "clickOnNbfcWorkFlow");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			

			Boolean response = coLendingService.clickOnWorkFlowButton(workflowData);
			if (response) {
				CommonDocumentUtils.endHook(logger, "clickOnNbfcWorkFlow");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.SUCCESSFULLY_SAVED, HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				CommonDocumentUtils.endHook(logger, "clickOnNbfcWorkFlow");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while clickOnNbfcWorkFlow==>", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/removeCoLendingProposal/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> removeCoLendingProposal(HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId,@PathVariable(value = "id")Long id) {
		// request must not be null
		CommonDocumentUtils.startHook(logger,"start removeCoLendingProposal");
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}		
			//get org id
			
			if (userId == null) {
				logger.warn("User Id is mandatory", userId);
				CommonDocumentUtils.endHook(logger, "end removeCoLendingProposal");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			//List<ProductMasterRequest> response = productMasterService.getListByUserType(userId, userType);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(coLendingService.removeCoLendingProposal(id));
			CommonDocumentUtils.endHook(logger, "end removeCoLendingProposal");
			return new ResponseEntity<>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while removeCoLendingProposal", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/saveReasonForRatio", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveReasonForRatio(@RequestBody DataRequest dataRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		CommonDocumentUtils.startHook(logger, "saveReasonForRatio");
		try {
			// request must not be null

			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);

			if (userId == null) {
				logger.warn("user Id can not be " + userId);
				CommonDocumentUtils.endHook(logger, "saveReasonForRatio");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (dataRequest == null) {
				logger.warn("dataRequest Object can not be empty ==>" + dataRequest);
				CommonDocumentUtils.endHook(logger, "saveReasonForRatio");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			

			Boolean response = coLendingService.addReasonByJobId(dataRequest);
			if (response) {
				CommonDocumentUtils.endHook(logger, "saveReasonForRatio");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.SUCCESSFULLY_SAVED, HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				CommonDocumentUtils.endHook(logger, "saveReasonForRatio");
				return new ResponseEntity<>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saveReasonForRatio ==>", e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	/**
	 * get accepted bank id from NBFC organization id
	 * @param id
	 * @return LoansResponse
	 */
	@GetMapping(value = "/getBankList/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getConfirmBankList(@PathVariable(value = "id") Long id) {
		logger.info("in /getBankList/{}", id);
		// request must not be null
		try {
			if (id == null) {
				logger.warn("organization id is mandatory");
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(coLendingService.listByOrgId(id));
			CommonDocumentUtils.endHook(logger, "end getBankList");
			return new ResponseEntity<>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while removeCoLendingProposal", e);
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
