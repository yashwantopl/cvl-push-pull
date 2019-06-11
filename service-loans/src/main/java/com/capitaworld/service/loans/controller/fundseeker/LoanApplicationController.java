
package com.capitaworld.service.loans.controller.fundseeker;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.capitaworld.service.loans.model.retail.RetailFinalInfoRequest;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

import com.capitaworld.api.payment.gateway.model.GatewayResponse;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.model.AdminPanelLoanDetailsResponse;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.LoanDisbursementRequest;
import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.PaymentRequest;
import com.capitaworld.service.loans.model.common.AutoFillOneFormDetailRequest;
import com.capitaworld.service.loans.model.common.ChatDetails;
import com.capitaworld.service.loans.model.common.DisbursementRequest;
import com.capitaworld.service.loans.model.common.EkycRequest;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;
import com.capitaworld.service.loans.service.common.AutoFillOneFormDetailService;
import com.capitaworld.service.loans.service.common.FsDetailsForPdfService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ApplicationProposalMappingService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.sanction.LoanDisbursementService;
import com.capitaworld.service.loans.service.sanction.LoanSanctionService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtility;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.FpProfileBasicDetailRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@RestController
@RequestMapping("/loan_application")
public class LoanApplicationController {

	private static final Logger logger = LoggerFactory.getLogger(LoanApplicationController.class);

	private static final String ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS = "Error while getting Loan Application Details==>";
	private static final String SUCCESSFULLY_UPDATED = "Successfully updated";
	private static final String FAILED_LITERAL = "Failed";
	private static final String INACTIVATED = "Inactivated";
	private static final String INVALID_CREDENTIALS = "Invalid Credentials";
	private static final String TOKEN_IS_NULL = "Token is null";
	private static final String TOKEN_IS_EXPIRED = "Token is Expired ";
	private static final String SET_LAST_APPLICATION_ACCESS = "setLastApplicationAccess";
	private static final String USER_ID_MUST_NOT_BE_NULL_MSG = "User Id must not be null==>";
	private static final String ERROR_WHILE_DECRYPT_ENCRYPTED_OBJECT_MSG = "ERROR WHILE DECRYPT ENCRYPTED OBJECT   ====> Msg ===> ";
	private static final String ERROR_WHILE_GETTING_PRODUCT_ID_BY_APPLICATION_ID = "Error while Getting Product Id by Application Id==>";
	private static final String APPLICATION_ID_MUST_NOT_BE_NULL = "Application id must not be null.";
	private static final String INVALID_USER_PLEASE_RELOGIN_AND_TRY_AGAIN_MSG = "Invalid User. Please relogin and try again.";
	private static final String ALL_PARAMETER_MUST_NOT_BE_NULL_MSG = "All parameter must not be null";
	private static final String CHECK_USER_HAS_ANY_APPLICATION = "check User Has Any Application";
	private static final String ERROR_WHILE_GET_UBI_REPORT1_FOR_ADMIN_PANEL_MSG = "Error while getUbiReport1ForAdminPanel==>";
	private static final String INFORMATION_SUCCESSFULLY_STORED_MSG = "Information Successfully Stored ";
	private static final String ERROR_WHILE_UPDATING_PAYMENT_STATUS_MSG = "Error while updating Payment Status==>{}";
	private static final String SAVING_REQUEST_TO_DB_MSG = "Saving Request to DB ===> ";
	private static final String AND_USER_ID_MSG = " and UserId ==>";
	private static final String TOKEN_NULL_MSG = "Token null ==>";
	private static final String RESPONSE_MSG = "Response ==>{}";
	private static final String REASON_MSG = " reason  ";
	private static final String TOKEN_LITERAL = "token";
	private static final String SUCCESS_LITERAL = "SUCCESS";
	private static final String IS_PRIMARY_LOCKED = "isPrimaryLocked";
	private static final String IS_FINAL_LOCKED = "isFinalLocked";
	private static final String PRODUCT_INFO = "productinfo";
	private static final String GET_UBI_REPORT1_FOR_ADMIN_PANEL = "getUbiReport1ForAdminPanel";


	@Autowired
	private LoanApplicationService loanApplicationService;

//	@Autowired
//	private AsyncComponent asyncComponent;

	@Autowired
	private UsersClient usersClient;

	@Autowired
	private FsDetailsForPdfService fsDetailPdfService;

	@Autowired
	private AutoFillOneFormDetailService autoFillOneFormDetailService;

	@Autowired
	private LoanSanctionService loanSanctionService;

	@Autowired
	private LoanDisbursementService loanDisbursementService;

	@Autowired
	private ProposalDetailsClient proposalDetailsClient;

	@Autowired
	private ApplicationProposalMappingService appPropMappService;

	/*@Autowired
	private GatewayClient gatewayClient;*/

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody FrameRequest commonRequest, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			// request must not be null
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			commonRequest.setUserId(userId);
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				commonRequest.setClientId(clientId);
			}

			// ==============

			loanApplicationService.saveOrUpdate(commonRequest, userId);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving applicationRequest Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/saveFromLoanEligibility", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFromLoanEligibility(@RequestBody FrameRequest commonRequest,
			HttpServletRequest request) {
		try {
			// request must not be null
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			commonRequest.setUserId(userId);

			loanApplicationService.saveOrUpdateFromLoanEligibilty(commonRequest, userId);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving applicationRequest Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> get(@PathVariable("id") Long id, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "get");
			Long userId;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (id == null || userId == null) {
				logger.warn("ID And UserId Require to get Loan Application Details. ID==>" + id + " and UserId==>"
						+ userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			Long userOrdId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			LoanApplicationRequest response = loanApplicationService.get(id, userId,userOrdId);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(response);
			CommonDocumentUtils.endHook(logger, "get");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getIrrByApplicationId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getIrrByApplicationId(@PathVariable("id") Long id, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getIrrByApplicationId");
			Long userId;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (id == null || userId == null) {
				logger.warn("ID And UserId Require to get Loan Application Details. ID==>" + id + " and UserId==>"
						+ userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			Long response = loanApplicationService.getIrrByApplicationId(id);
			LoansResponse loansResponse = null;
			if (response != null) {
				loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
				loansResponse.setData(response);
				CommonDocumentUtils.endHook(logger, "get");
			}
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getList");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (userId == null) {
				logger.warn("UserId Require to get Loan Applications Details ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			List<LoanApplicationRequest> response = loanApplicationService.getList(userId);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getList");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getApplicationList(HttpServletRequest request,
												 @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getList");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (userId == null) {
				logger.warn("UserId Require to get Loan Applications Details ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			List<LoanApplicationRequest> response = loanApplicationService.getApplicationList(userId);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getList");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> inActive(@PathVariable("id") Long id, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "inActive");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (userId == null) {
				logger.warn("UserId Require to Inactive Loan Application Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse(INACTIVATED, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.inActive(id, userId));
			CommonDocumentUtils.endHook(logger, "inActive");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getListByUserIdList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getListByUseIdList(@RequestBody Long userId, HttpServletRequest request) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getListByUseIdList");
			if (userId == null) {
				logger.warn("UserId Require to get Loan Applications Details ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			List<LoanApplicationDetailsForSp> response = loanApplicationService.getLoanDetailsByUserIdList(userId);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getListByUseIdList");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/set_last_application_access/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> setLastApplicationAccess(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, SET_LAST_APPLICATION_ACCESS);
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (applicationId == null) {
				logger.warn("applicationId Require to Set last Access Profile ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			UserResponse userResponse = loanApplicationService.setLastAccessApplication(applicationId, userId);
			if (userResponse != null && userResponse.getStatus() == 200) {
				LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
				loansResponse.setData(userResponse);
				CommonDocumentUtils.endHook(logger, SET_LAST_APPLICATION_ACCESS);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
				CommonDocumentUtils.endHook(logger, SET_LAST_APPLICATION_ACCESS);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while Setting Last Access profile==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get_product_by_application/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getProductByApplication(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getProductByApplication");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (applicationId == null) {
				logger.warn("applicationId Require to get Product Id ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			Integer productId = loanApplicationService.getProductIdByApplicationId(applicationId, userId);
			if (!CommonUtils.isObjectNullOrEmpty(productId)) {
				LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
				loansResponse.setId(productId.longValue());
				logger.info("End getProductByApplication() method");
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
				logger.info("End getProductByApplication() method");
				logger.warn("ProductId not found");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_PRODUCT_ID_BY_APPLICATION_ID, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUserIdByApplicationId", method = RequestMethod.POST)
	public ResponseEntity<LoansResponse> getUserIdByApplicationId(HttpServletRequest request,
			@RequestBody Long applicationId) {

		try {
			CommonDocumentUtils.startHook(logger, "getUserIdByApplicationId");
			if (applicationId == null) {
				logger.warn("applicationId Require to get Loan Applications Details ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse();

			Object[] response = loanApplicationService.getApplicationDetailsById(applicationId);
			if (!CommonUtils.isObjectNullOrEmpty(response[0])) {
				loansResponse.setData(response[0]);
			}
			CommonDocumentUtils.endHook(logger, "getUserIdByApplicationId");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_PRODUCT_ID_BY_APPLICATION_ID, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUserNameByApplicationId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUserNameByApplicationId(HttpServletRequest request,
			@RequestBody Long applicationId) {

		try {
			CommonDocumentUtils.startHook(logger, "getUserNameByApplicationId");
			if (applicationId == null) {
				logger.warn("applicationId Require to get Loan Applications Details ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse();

			String nameResponse = loanApplicationService.getFsApplicantName(applicationId);
			loansResponse.setData(nameResponse);
			CommonDocumentUtils.endHook(logger, "getUserNameByApplicationId");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_PRODUCT_ID_BY_APPLICATION_ID, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/lock_primary/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> lockPrimary(@PathVariable("applicationId") Long applicationId,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "lockPrimary");
			Long userId = null;
			Integer userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn(APPLICATION_ID_MUST_NOT_BE_NULL);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			loanApplicationService.lockPrimary(applicationId, userId, true);
			if (CommonUtils.UserType.FUND_SEEKER == userType) {
//				asyncComponent.sentMailWhenUserLogoutWithoutFillingFinalData(userId, applicationId);
			}
			CommonDocumentUtils.endHook(logger, "lockPrimary");
			return new ResponseEntity<LoansResponse>(new LoansResponse(SUCCESSFULLY_UPDATED, HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while locking primary information==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/lock_final/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> lockFinal(@PathVariable("applicationId") Long applicationId,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "lockFinal");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn(APPLICATION_ID_MUST_NOT_BE_NULL);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoanApplicationRequest loanApplicationRequest = loanApplicationService.lockFinal(applicationId, userId,
					true);
			if (loanApplicationRequest.getIsMailSent()) {
//				asyncComponent.sendEmailWhenMakerLockFinalDetails(loanApplicationRequest.getNpAssigneeId(),
//						loanApplicationRequest.getNpUserId(), loanApplicationRequest.getApplicationCode(),
//						loanApplicationRequest.getProductId(), loanApplicationRequest.getName(),
//						loanApplicationRequest.getId());
			}
			CommonDocumentUtils.endHook(logger, "lockFinal");
			return new ResponseEntity<LoansResponse>(new LoansResponse(SUCCESSFULLY_UPDATED, HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Locking final information==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/lock_final_by_proposalId/{applicationId}/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> lockFinal(@PathVariable("applicationId") Long applicationId,
                                                   @PathVariable("proposalId") Long proposalId,
                                                   @RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
        try {
            CommonDocumentUtils.startHook(logger, "lockFinal");
            Long userId = null;
            if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
                userId = clientId;
            } else {
                userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            }
            if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
                logger.warn(APPLICATION_ID_MUST_NOT_BE_NULL);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            LoanApplicationRequest loanApplicationRequest = loanApplicationService.lockFinalByProposalId(applicationId, proposalId,userId,
                    true);
            if (loanApplicationRequest.getIsMailSent()) {
//                asyncComponent.sendEmailWhenMakerLockFinalDetails(loanApplicationRequest.getNpAssigneeId(),
//                        loanApplicationRequest.getNpUserId(), loanApplicationRequest.getApplicationCode(),
//                        loanApplicationRequest.getProductId(), loanApplicationRequest.getName(),
//                        loanApplicationRequest.getId());
            }
            CommonDocumentUtils.endHook(logger, "lockFinal");
            return new ResponseEntity<LoansResponse>(new LoansResponse(SUCCESSFULLY_UPDATED, HttpStatus.OK.value()),
                    HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Locking final information==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/update_final_information_flag/{applicationId}/{flag}/{finalFilledCount}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateFinalInformationFlag(@PathVariable("applicationId") Long applicationId,
			@PathVariable("flag") Boolean flag, @PathVariable("finalFilledCount") String finalFilledCount,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "updateFinalInformationFlag");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error(APPLICATION_ID_MUST_NOT_BE_NULL);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			loanApplicationService.updateFinalCommonInformation(applicationId, userId, flag, finalFilledCount);
			CommonDocumentUtils.endHook(logger, "updateFinalInformationFlag");
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/primary_profile_filled/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isProfileAndPrimaryFilled(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "isProfileAndPrimaryFilled");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error(APPLICATION_ID_MUST_NOT_BE_NULL);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse(CommonUtils.SUCCESS_RESULT, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isProfileAndPrimaryDetailFilled(applicationId, userId));
			CommonDocumentUtils.endHook(logger, "isProfileAndPrimaryFilled");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/primary_locked/{proposalId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isPrimaryLocked(@PathVariable("proposalId") Long proposalId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, IS_PRIMARY_LOCKED);
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(proposalId)) {
				logger.error(APPLICATION_ID_MUST_NOT_BE_NULL);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse(CommonUtils.SUCCESS_RESULT, HttpStatus.OK.value());

			loansResponse.setData(true);
			//if (!loanApplicationService.isApplicationIdActive(applicationId)) {
			if (!loanApplicationService.getByProposalId(proposalId)) {
				loansResponse.setData(false);
				loansResponse.setMessage("Requested user is Inactive");
				CommonDocumentUtils.endHook(logger, IS_PRIMARY_LOCKED);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
			//loanApplicationService.isPrimaryLocked(applicationId, userId)
			if (!loanApplicationService.isPrimaryLockedByProposalId(proposalId, userId)) {
				loansResponse.setData(false);
				loansResponse.setMessage("Requested User has not filled Primary Details");
				CommonDocumentUtils.endHook(logger, IS_PRIMARY_LOCKED);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}

			CommonDocumentUtils.endHook(logger, IS_PRIMARY_LOCKED);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/final_filled/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isFinalFilled(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "isFinalFilled");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error(APPLICATION_ID_MUST_NOT_BE_NULL);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse(CommonUtils.SUCCESS_RESULT, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isFinalDetailFilled(applicationId, userId));
			CommonDocumentUtils.endHook(logger, "isFinalFilled");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/final_locked/{applicationId}/{proposalId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isFinalLocked(@PathVariable("applicationId") Long applicationId,
			@PathVariable("proposalId") Long proposalId, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, IS_FINAL_LOCKED);
			Long userId = null;
			Integer userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
			if ((CommonUtils.UserType.SERVICE_PROVIDER == userType || CommonUtils.UserType.NETWORK_PARTNER == userType
					|| CommonUtils.UserType.FUND_PROVIDER == userType) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error(APPLICATION_ID_MUST_NOT_BE_NULL);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse(CommonUtils.SUCCESS_RESULT, HttpStatus.OK.value());
			loansResponse.setData(true);
			//
			if(!loanApplicationService.getByProposalId(proposalId)) {
			//if (!loanApplicationService.isApplicationIdActive(applicationId)) {
				loansResponse.setData(false);
				loansResponse.setMessage("Requested user is Inactive");
				CommonDocumentUtils.endHook(logger, IS_FINAL_LOCKED);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
			if (!loanApplicationService.isFinalLockedByProposalId(proposalId, userId)) {
			//if (!loanApplicationService.isFinalLocked(applicationId, userId)) {
				loansResponse.setData(loanApplicationService.isFinalLockedByProposalId(applicationId, userId));
				//loansResponse.setData(loanApplicationService.isFinalLocked(applicationId, userId));
				loansResponse.setMessage("Requested User has not filled Final Details");
				if (CommonUtils.UserType.FUND_PROVIDER == userType) {
					logger.info("Start Sending Mail To Fs for Fill Final Details When FP Click View More Details");
//					asyncComponent.sendMailWhenUserNotCompleteFinalDetails(userId, applicationId);
				}
				CommonDocumentUtils.endHook(logger, IS_FINAL_LOCKED);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
			CommonDocumentUtils.endHook(logger, IS_FINAL_LOCKED);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/primary_final_locked_proposalId/{proposalId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isPrimaryAndFinalLockedByProposalId(@PathVariable("proposalId") Long proposalId,
																 HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "isPrimaryAndFinalLocked");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(proposalId)) {
				logger.error("Application id must not be null.");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			JSONObject json = null;
			LoanApplicationRequest applicationRequest = loanApplicationService.getAllFlagByProposalId(proposalId, userId);
			if(applicationRequest != null) {
				json = new JSONObject();
				json.put("isPrimaryLock", applicationRequest.getProfilePrimaryLocked());
				json.put("isFinalLock", applicationRequest.getFinalLocked());
				json.put("isMcqSkipped", applicationRequest.getIsMcqSkipped());
				//json.put("isSkipMcq", applicationRequest.getIsMcqSkipped());
				json.put("ddrStatusId", applicationRequest.getDdrStatusId());
			}
			LoansResponse loansResponse = new LoansResponse("Success Result", HttpStatus.OK.value());
			loansResponse.setData(json);
			CommonDocumentUtils.endHook(logger, "isPrimaryAndFinalLocked");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/primary_final_locked/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isPrimaryAndFinalLocked(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "isPrimaryAndFinalLocked");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error(APPLICATION_ID_MUST_NOT_BE_NULL);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			LoanApplicationRequest applicationRequest = loanApplicationService.getAllFlag(applicationId, userId);
			JSONObject json = null;
			if(applicationRequest != null) {
				json = new JSONObject();
				json.put("isPrimaryLock", applicationRequest.getProfilePrimaryLocked());
				json.put("isFinalLock", applicationRequest.getFinalLocked());
				json.put("isMcqSkipped", applicationRequest.getIsMcqSkipped());
				json.put("ddrStatusId", applicationRequest.getDdrStatusId());
			}
			LoansResponse loansResponse = new LoansResponse(CommonUtils.SUCCESS_RESULT, HttpStatus.OK.value());
			loansResponse.setData(json);
			CommonDocumentUtils.endHook(logger, "isPrimaryAndFinalLocked");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get_selfview_primary_locked/{applicationId}/{proposalId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getSelfViewAndPrimaryLocked(@PathVariable("applicationId") Long applicationId,
			@PathVariable("proposalId") Long proposalId,HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("proposalId===================>"+proposalId +"applicationID=====================>>>"+applicationId);
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getSelfViewAndPrimaryLocked");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			/*
			 * if (CommonDocumentUtils.isThisClientApplication(request) &&
			 * !CommonUtils.isObjectNullOrEmpty(clientId)) { userId = clientId; } else {
			 * userId = (Long) request.getAttribute(CommonUtils.USER_ID); }
			 */

			if (CommonUtils.isObjectNullOrEmpty(applicationId) ||CommonUtils.isObjectNullOrEmpty(proposalId)) {
				logger.error(APPLICATION_ID_MUST_NOT_BE_NULL);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse(CommonUtils.SUCCESS_RESULT, HttpStatus.OK.value());
			if(proposalId != null) {
				loansResponse.setData(appPropMappService.getSelfViewAndPrimaryLocked(proposalId, userId));
			}else {
				loansResponse.setData(loanApplicationService.getSelfViewAndPrimaryLocked(applicationId, userId));
			}
			
			CommonDocumentUtils.endHook(logger, "getSelfViewAndPrimaryLocked");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/currency_denomination/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getCurrencyAndDenomination(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getCurrencyAndDenomination");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (applicationId == null || userId == null) {
				logger.warn("ID And UserId Require to get Primary Working Details ==>" + applicationId
						+ AND_USER_ID_MSG + userId);
				CommonDocumentUtils.endHook(logger, "getCurrencyAndDenomination");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.getCurrencyAndDenomination(applicationId, userId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Primary Working Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/is_allow_to_move_ahead_by_proposal_id/{applicationId}/{proposalId}/{tabType}/{coAppllicantOrGuarantorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isAllowToMoveAhead(@PathVariable("applicationId") Long applicationId,
															@PathVariable("proposalId") Long proposalId,
															@PathVariable("tabType") Integer tabType,
															@PathVariable("coAppllicantOrGuarantorId") Long coAppllicantOrGuarantorId, HttpServletRequest request,
															@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "isAllowToMoveAhead");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (proposalId == null || userId == null) {
				logger.warn("proposal ID And UserId Require to get Primary Working Details ==>" + proposalId
						+ " and UserId ==>" + userId);
				CommonDocumentUtils.endHook(logger, "isAllowToMoveAhead");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isAllowToMoveAheadForMultiProposal(applicationId,proposalId, userId, tabType,
					coAppllicantOrGuarantorId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Primary Working Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/is_allow_to_move_ahead/{applicationId}/{tabType}/{coAppllicantOrGuarantorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isAllowToMoveAhead(@PathVariable("applicationId") Long applicationId,
			@PathVariable("tabType") Integer tabType,
			@PathVariable("coAppllicantOrGuarantorId") Long coAppllicantOrGuarantorId, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "isAllowToMoveAhead");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (applicationId == null || userId == null) {
				logger.warn("ID And UserId Require to get Primary Working Details ==>" + applicationId
						+ AND_USER_ID_MSG + userId);
				CommonDocumentUtils.endHook(logger, "isAllowToMoveAhead");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isAllowToMoveAhead(applicationId, userId, tabType,
					coAppllicantOrGuarantorId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Primary Working Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getBowlCountByProposalId/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getBowlCountByProposalId(@PathVariable("proposalId") Long proposalId,
													  HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getBowlCount");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (proposalId == null || userId == null) {
				logger.warn("ID And UserId Require to get Bowl Count Details ==>" + proposalId + " and UserId ==>"
						+ userId);
				CommonDocumentUtils.endHook(logger, "getBowlCount");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.getBowlCountByProposalId(proposalId, userId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getBowlCount Details==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getBowlCount/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getBowlCount(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getBowlCount");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (applicationId == null || userId == null) {
				logger.warn("ID And UserId Require to get Bowl Count Details ==>" + applicationId + AND_USER_ID_MSG
						+ userId);
				CommonDocumentUtils.endHook(logger, "getBowlCount");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.getBowlCount(applicationId, userId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getBowlCount Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUsersRegisteredLoanDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUsersRegisteredLoanDetails(@RequestBody MobileLoanRequest loanRequest) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getLoanDetailsForSignUpUserList");
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setListData(loanApplicationService.getUsersRegisteredLoanDetails(loanRequest));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getLoanDetailsForSignUpUserList==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getLoanDetailsForAdminPanel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getLoanDetailsForAdminPanel(@RequestBody MobileLoanRequest loanRequest) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getLoanDetailsForAdminPanel");
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			List<AdminPanelLoanDetailsResponse> adminPanelLoanDetailsResponses = loanApplicationService.getLoanDetailsForAdminPanel(1, loanRequest);
			if (adminPanelLoanDetailsResponses != null && !adminPanelLoanDetailsResponses.isEmpty()) {
				loansResponse.setListData(adminPanelLoanDetailsResponses);
			}

			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getLoanDetailsForAdminPanel==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getFilledLoanDetailsForAdminPanel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFilledLoanDetailsForAdminPanel(@RequestBody MobileLoanRequest loanRequest) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getFilledLoanDetailsForAdminPanel");
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			List<AdminPanelLoanDetailsResponse> adminPanelLoanDetailsResponses = loanApplicationService.getLoanDetailsForAdminPanel(2, loanRequest);
			if (adminPanelLoanDetailsResponses != null && !adminPanelLoanDetailsResponses.isEmpty()) {
				loansResponse.setListData(adminPanelLoanDetailsResponses);
			}

			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getFilledLoanDetailsForAdminPanel==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUbiReport1ForAdminPanel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUbiReport1ForAdminPanel(@RequestBody MobileLoanRequest loanRequest) {
		try {
			CommonDocumentUtils.startHook(logger, GET_UBI_REPORT1_FOR_ADMIN_PANEL);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			List<AdminPanelLoanDetailsResponse> adminPanelLoanDetailsResponses = loanApplicationService.getPostLoginForAdminPanel(loanRequest);
			if (adminPanelLoanDetailsResponses != null && !adminPanelLoanDetailsResponses.isEmpty()) {
				loansResponse.setListData(adminPanelLoanDetailsResponses);
			}
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GET_UBI_REPORT1_FOR_ADMIN_PANEL_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUbiReport2ForAdminPanel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUbiReport2ForAdminPanel(@RequestBody MobileLoanRequest loanRequest) {
		try {
			CommonDocumentUtils.startHook(logger, GET_UBI_REPORT1_FOR_ADMIN_PANEL);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			List<AdminPanelLoanDetailsResponse> adminPanelLoanDetailsResponses = loanApplicationService.getPostLoginForAdminPanelOfNotEligibility(loanRequest);
			if (adminPanelLoanDetailsResponses != null && !adminPanelLoanDetailsResponses.isEmpty()) {
				loansResponse.setListData(adminPanelLoanDetailsResponses);
			}
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GET_UBI_REPORT1_FOR_ADMIN_PANEL_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUbiReport3ForAdminPanel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUbiReport3ForAdminPanel(@RequestBody MobileLoanRequest loanRequest) {
		try {
			CommonDocumentUtils.startHook(logger, GET_UBI_REPORT1_FOR_ADMIN_PANEL);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			List<AdminPanelLoanDetailsResponse> adminPanelLoanDetailsResponses = loanApplicationService.getPostLoginForAdminPanelOfEligibility(loanRequest);
			if (adminPanelLoanDetailsResponses != null && !adminPanelLoanDetailsResponses.isEmpty()) {
				loansResponse.setListData(adminPanelLoanDetailsResponses);
			}
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GET_UBI_REPORT1_FOR_ADMIN_PANEL_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUbiReport4ForAdminPanel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUbiReport4ForAdminPanel(@RequestBody MobileLoanRequest loanRequest) {
		try {
			CommonDocumentUtils.startHook(logger, GET_UBI_REPORT1_FOR_ADMIN_PANEL);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			List<AdminPanelLoanDetailsResponse> adminPanelLoanDetailsResponses = loanApplicationService.getPostLoginForAdminPanelOfFinalLockedRejectedByUbi(loanRequest);
			if (adminPanelLoanDetailsResponses != null && !adminPanelLoanDetailsResponses.isEmpty()) {
				loansResponse.setListData(adminPanelLoanDetailsResponses);
			}
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GET_UBI_REPORT1_FOR_ADMIN_PANEL_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUbiReport5ForAdminPanel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUbiReport5ForAdminPanel(@RequestBody MobileLoanRequest loanRequest) {
		try {
			CommonDocumentUtils.startHook(logger, GET_UBI_REPORT1_FOR_ADMIN_PANEL);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
            List<AdminPanelLoanDetailsResponse> adminPanelLoanDetailsResponses = loanApplicationService.getPostLoginForAdminPanelOfApprovedByUbi(loanRequest);
			if (adminPanelLoanDetailsResponses != null && !adminPanelLoanDetailsResponses.isEmpty()) {
                loansResponse.setListData(adminPanelLoanDetailsResponses);
            }
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GET_UBI_REPORT1_FOR_ADMIN_PANEL_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getChatListByFpMappingId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getChatListByFpMappingId(HttpServletRequest request,
			@RequestBody Long applicationId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getChatListByFpMappingId");
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			List<ChatDetails> chatDetailsList = loanApplicationService.getChatListByApplicationId(applicationId);
			if (chatDetailsList != null && !chatDetailsList.isEmpty()) {
				loansResponse.setListData(chatDetailsList);
			}
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getChatListByFpMappingId==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getFpNegativeList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFpNegativeList(HttpServletRequest request,
			@RequestBody Long applicationId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getFpNegativeList");
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			List<FpProfileBasicDetailRequest> fpProfileBasicDetailRequests = loanApplicationService.getFpNegativeList(applicationId);
			if (fpProfileBasicDetailRequests != null && !fpProfileBasicDetailRequests.isEmpty()) {
				loansResponse.setListData(fpProfileBasicDetailRequests);
			}
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getFpNegativeList==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getFpNegativeListByProposalId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFpNegativeListByProposalId(HttpServletRequest request,
			@RequestBody Long proposalId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getFpNegativeList");
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			List<FpProfileBasicDetailRequest> fpProfileBasicDetailRequests = loanApplicationService.getFpNegativeListByProposalId(proposalId);
			if (fpProfileBasicDetailRequests != null && !fpProfileBasicDetailRequests.isEmpty()) {
				loansResponse.setListData(fpProfileBasicDetailRequests);
			}
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getFpNegativeList==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getDetailsForEkycAuthentication", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getDetailsForEkycAuthentication(HttpServletRequest request,
			@RequestBody EkycRequest ekycRequest) {

		// request must not be null
		try {
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.getDetailsForEkycAuthentication(ekycRequest));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Something went wrong", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getMcaCompanyId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getMcaCompanyId(HttpServletRequest request, @RequestBody Long applicationId,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			CommonDocumentUtils.startHook(logger, "getMcaCompanyId");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.getMcaCompanyId(applicationId, userId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getMcaCompanyId==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updateLoanApplication", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateLoanApplication(@RequestBody LoanApplicationRequest loanRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			CommonDocumentUtils.startHook(logger, "updateLoanApplication");
			Long userId = null;
			if ((!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE)))
					&& (CommonUtils.UserType.SERVICE_PROVIDER == Integer
							.parseInt(request.getAttribute(CommonUtils.USER_TYPE).toString())
							|| CommonUtils.UserType.NETWORK_PARTNER == ((Integer) request
									.getAttribute(CommonUtils.USER_TYPE)).intValue()
							|| CommonUtils.UserType.FUND_PROVIDER == ((Integer) request
									.getAttribute(CommonUtils.USER_TYPE)).intValue())) {
				loanRequest.setClientId(clientId);
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			} else {
				if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
					userId = (Long) request.getAttribute(CommonUtils.USER_ID);
				} else if (!CommonUtils.isObjectNullOrEmpty(loanRequest.getUserId())) {
					userId = loanRequest.getClientId();
				} else {
					logger.warn("Invalid request.");
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Invalid request.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
				}
			}
			loanRequest.setUserId(userId);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loanApplicationService.updateLoanApplication(loanRequest);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while updateLoanApplication==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/isMca", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isMca(HttpServletRequest request, @RequestBody Long applicationId,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			CommonDocumentUtils.startHook(logger, "getMcaCompanyId");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isMca(applicationId, userId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getMcaCompanyId==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getLoanBasicDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getLoanBasicDetails(@RequestBody LoanApplicationRequest loanRequest) {
		try {
			CommonDocumentUtils.startHook(logger, "getLoanBasicDetails");
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse
					.setData(loanApplicationService.getLoanBasicDetails(loanRequest.getId(), loanRequest.getUserId()));
			CommonDocumentUtils.endHook(logger, "getLoanBasicDetails");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getLoanBasicDetails==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/create_loan_from_campaign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> createLoanFromCampaign(HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId,
			@RequestParam(value = "isActive", required = false) Boolean isActive,
			@RequestParam(value = "businessType", required = false) Integer busineeTypeId,
			@RequestBody List<String> campaignCodes) {
		try {
			logger.info("start createLoanFromCampaign()");
			logger.info("Business Type Id====>{}", busineeTypeId);
			logger.info("isActive====>{}", isActive);
			logger.info("clientId====>{}", clientId);
			logger.info("campaignCodes====>{}", campaignCodes.toString());

			boolean isMsmeUserFromGeneric = false;
			try {
				logger.info("Campaign Code=====>{}", campaignCodes);
				if (!CommonUtils.isListNullOrEmpty(campaignCodes)) {
					isMsmeUserFromGeneric = campaignCodes.contains(CommonUtils.CampaignCodes.ALL1MSME.getValue());
					logger.info("codeExist====>{}", isMsmeUserFromGeneric);
					if (isMsmeUserFromGeneric) {
						// In this case
						Long createdId =null;
						if(CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId() == busineeTypeId || CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId() == busineeTypeId){
							createdId = loanApplicationService.createRetailLoan(clientId, isActive, busineeTypeId);
						}else{
							createdId = loanApplicationService.createMsmeLoan(clientId, isActive, busineeTypeId);
						}

						return new ResponseEntity<LoansResponse>(
								new LoansResponse(createdId, "Successfully New Loan Created", HttpStatus.OK.value()),
								HttpStatus.OK);

					}
					/* Integer index = campaignCodes
					   .indexOf(CommonUtils.CampaignCodes.ALL1MSME.getValue());
					   logger.info("index==={}=of Code====>{}", index,
					   CommonUtils.CampaignCodes.ALL1MSME.getValue());
					   if (index > -1) {
					   } */
				}
			} catch (Exception e) {
				logger.error("Error while Set Campaign Code to LoanApplication Master : ",e);
			}

			JSONObject json = new JSONObject();
			CommonDocumentUtils.startHook(logger, "createLoanFromCampaign");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long proposalMappingId = (Long) request.getAttribute(CommonUtils.PROPOSAL_MAPPING_ID);
			LoansResponse loansResponse = new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value());
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(clientId) ? userId : clientId;
			for (String campaignCode : campaignCodes) {
				if (!CommonUtils.CampaignCodes.ALL1MSME.getValue().equals(campaignCode)) {
					boolean campaignCodeExist = loanApplicationService.isCampaignCodeExist(userId, clientId,
							campaignCode);
					if (campaignCodeExist) {
						logger.info("Campaign code is already Exists==>" + campaignCode);
					} else {
						LoanApplicationRequest request2 = loanApplicationService.saveFromCampaign(userId, clientId,
								campaignCode);
						json.put("id", request2.getId());
						json.put("productId", request2.getProductId());
						json.put("hasAlreadyApplied", loanApplicationService.hasAlreadyApplied(finalUserId,
								request2.getId(), request2.getProductId()));
						json.put("isNew", true);
					}
				}
			}

			if (json.isEmpty()) {
				UsersRequest usersRequest = new UsersRequest();
				usersRequest.setId(userId);
				UserResponse response = usersClient.getLastAccessApplicant(usersRequest);
				if (!CommonUtils.isObjectNullOrEmpty(response) && !CommonUtils.isObjectNullOrEmpty(response.getId())) {
					Integer productId = loanApplicationService.getProductIdByApplicationId(response.getId(), userId);
					json.put("id", response.getId());
					json.put("productId", productId);
					json.put("hasAlreadyApplied",
							loanApplicationService.hasAlreadyApplied(finalUserId, response.getId(), productId));
					json.put("isNew", false);
				}
			}
			json.put("msmeGeneric", isMsmeUserFromGeneric);
			loansResponse.setData(json);

			logger.info("end createLoanFromCampaign()");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while creating Loan from Campaign==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/checkUserHasAnyApplication", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> checkUserHasAnyApplication(HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, CHECK_USER_HAS_ANY_APPLICATION);
			if (CommonUtils.UserType.FUND_SEEKER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
//				asyncComponent.sendMailWhenUserHasNoApplication(userId);
			}
			LoansResponse loansResponse = new LoansResponse("Successfully recieved", HttpStatus.OK.value());
			CommonDocumentUtils.endHook(logger, CHECK_USER_HAS_ANY_APPLICATION);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while check User Has Any Application==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This is method call when fund seeker submit primary details and then go to
	 * matches This called from matchesPopupCtrl.js controller
	 * 
	 * @param applicationId
	 * @param request
	 * @param clientId
	 * @author harshit
	 * @return
	 */
	@RequestMapping(value = "/sendMailForFirstTimeUserViewMatches/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> sendMailForFirstTimeUserViewMatches(
			@PathVariable("applicationId") Long applicationId, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "sendMailForFirstTimeUserViewMatches");
			Long userId = null;
			if (CommonUtils.UserType.FUND_SEEKER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			} else {
				logger.info("Logged user not fundseeker in sendMailForFirstTimeUserViewMatches method");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested user is not fundseeker", HttpStatus.OK.value()), HttpStatus.OK);
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationId) || CommonUtils.isObjectNullOrEmpty(userId)) {
				logger.warn("ID And UserId Require to send mail for first time user view matches==>" + applicationId
						+ AND_USER_ID_MSG + userId);
				CommonDocumentUtils.endHook(logger, "sendMailForFirstTimeUserViewMatches");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

//			asyncComponent.sendMailForFirstTimeUserViewMatches(applicationId, userId);
			logger.info("Mail sent successfully while fs go in matches pages first time");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Sent Mail Successfully", HttpStatus.OK.value()),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while sendMailForFirstTimeUserViewMatches Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/isTermLoanLessThanLimit/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isTermLoanLessThanLimit(HttpServletRequest request,
			@PathVariable Long applicationId, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			CommonDocumentUtils.startHook(logger, "isTermLoanLessThanLimit");

			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isTermLoanLessThanLimit(applicationId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while isTermLoanLessThanLimit==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/isApplicationEligibleForIrr/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isApplicationEligibleForIrr(HttpServletRequest request,
			@PathVariable Long applicationId, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			CommonDocumentUtils.startHook(logger, "isApplicationEligibleForIrr");

			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isApplicationEligibleForIrr(applicationId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while isApplicationEligibleForIrr==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getIndustryIrrByApplication/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getIndustryIrrByApplication(HttpServletRequest request,
			@PathVariable Long applicationId, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			CommonDocumentUtils.startHook(logger, "getIndustryIrrByApplication");

			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.getIndustryIrrByApplication(applicationId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getIndustryIrrByApplication==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getFsDetailsInMapResponse/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFsDetailsInMapResponse(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			CommonDocumentUtils.startHook(logger, "getFsDetailsInMapResponse");
			LoansResponse loansResponse = new LoansResponse("Successfully recieved", HttpStatus.OK.value());
			loansResponse.setData(fsDetailPdfService.getHomeLoanDetails(applicationId));
			CommonDocumentUtils.endHook(logger, CHECK_USER_HAS_ANY_APPLICATION);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error whilegetFsDetailsInMapResponse==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/set_eligibility_amount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer setEligibilityAmount(@RequestBody LoanApplicationRequest loanRequest) {
		try {
			CommonDocumentUtils.startHook(logger, "setEligibilityAmount");
			return loanApplicationService.setEligibleLoanAmount(loanRequest);
		} catch (Exception e) {
			logger.error("Error while updating Eligibility Amount==>", e);
			return null;
		}
	}

	@RequestMapping(value = "/getLoanDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getLoanDetails(@RequestBody Long applicationId, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			CommonDocumentUtils.startHook(logger, "getLoanDetails");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.getLoanBasicDetails(applicationId, userId));
			CommonDocumentUtils.endHook(logger, "getLoanDetails");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getLoanDetails==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/update_flow/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateFlow(HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId,
			@PathVariable("applicationId") Long applicationId) {
		try {
			logger.info("start updateFlow()");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			loanApplicationService.updateFlow(applicationId, clientId, userId);
			logger.info("end updateFlow()");
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value()),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while updating Flow from UBI to Normal==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**@RequestMapping(value = "/save_payment_info", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> savePaymentInfor(@RequestBody PaymentRequest paymentRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			logger.info("start save_payment_info()");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				logger.info("User id from front end===>" + request.getAttribute(CommonUtils.USER_ID));
				if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
					userId = (Long) request.getAttribute(CommonUtils.USER_ID);
					logger.info("User id from browser===>" + userId);

				} else {
					userId = paymentRequest.getUserId();

					logger.info("User id from mobile===>" + userId);
				}
			}

			if (CommonUtils.isObjectNullOrEmpty(paymentRequest.getApplicationId())) {
				logger.info("Application id is null or empty");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request, Application Id Null Or Empty",
								HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			Object applicationMaster = loanApplicationService.updateLoanApplicationMaster(paymentRequest, userId);
			logger.info(RESPONSE_MSG, applicationMaster);

			try {
				if (CommonUtils.PaymentMode.ONLINE.equalsIgnoreCase(paymentRequest.getTypeOfPayment())
						&& paymentRequest.getPurposeCode().equals("NHBS_FEES")) {
					logger.info("Start Sent Mail When FS select Online Payment");
//					asyncComponent.sendMailWhenFSSelectOnlinePayment(userId, paymentRequest,
//							NotificationTemplate.EMAIL_FS_PAYMENT_ONLINE, NotificationAlias.SYS_FS_PAYMENT_ONLINE);
					logger.info("End Sent Mail When FS select Online Payment");
				} else if (CommonUtils.PaymentMode.CASH.equalsIgnoreCase(paymentRequest.getTypeOfPayment())) {
					logger.info("Start Sent Mail When FS select CASH Payment");
//					asyncComponent.sendMailWhenFSSelectOnlinePayment(userId, paymentRequest,
//							NotificationTemplate.EMAIL_FS_PAYMENT_CASH_CHEQUE,
//							NotificationAlias.SYS_FS_PAYMENT_CASH_CHEQUE);
					logger.info("End Sent Mail When FS select CASH Payment");
				} else if (CommonUtils.PaymentMode.CHEQUE.equalsIgnoreCase(paymentRequest.getTypeOfPayment())) {
					logger.info("Start Sent Mail When FS select CHEQUE Payment");
//					asyncComponent.sendMailWhenFSSelectOnlinePayment(userId, paymentRequest,
//							NotificationTemplate.EMAIL_FS_PAYMENT_CASH_CHEQUE,
//							NotificationAlias.SYS_FS_PAYMENT_CASH_CHEQUE);
					logger.info("End Sent Mail When FS select CHEQUE Payment");
				}
			} catch (Exception e) {
				logger.error("Throw Exception while send mail when save payment : ",e);
			}

			LoansResponse response = new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value());
			response.setData(applicationMaster);
			logger.info("end save_payment_info()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Saving Payment info==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/save_payment_info_for_mobile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> savePaymentInforForMobile(@RequestBody PaymentRequest paymentRequest) {
		try {
			logger.info("start save_payment_info()");
			Long userId = paymentRequest.getUserId();

			if (CommonUtils.isObjectNullOrEmpty(paymentRequest.getApplicationId())) {
				logger.info("Application id is null or empty");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request, Application Id Null Or Empty",
								HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			Object applicationMaster = loanApplicationService.updateLoanApplicationMaster(paymentRequest, userId);
			logger.info(RESPONSE_MSG, applicationMaster);

			try {
				if (CommonUtils.PaymentMode.ONLINE.equalsIgnoreCase(paymentRequest.getTypeOfPayment())
						&& paymentRequest.getPurposeCode().equals("NHBS_FEES")) {
					logger.info("Start Sent Mail When FS select Online Payment");
//					asyncComponent.sendMailWhenFSSelectOnlinePayment(userId, paymentRequest,
//							NotificationTemplate.EMAIL_FS_PAYMENT_ONLINE, NotificationAlias.SYS_FS_PAYMENT_ONLINE);
					logger.info("End Sent Mail When FS select Online Payment");
				} else if (CommonUtils.PaymentMode.CASH.equalsIgnoreCase(paymentRequest.getTypeOfPayment())) {
					logger.info("Start Sent Mail When FS select CASH Payment");
//					asyncComponent.sendMailWhenFSSelectOnlinePayment(userId, paymentRequest,
//							NotificationTemplate.EMAIL_FS_PAYMENT_CASH_CHEQUE,
//							NotificationAlias.SYS_FS_PAYMENT_CASH_CHEQUE);
					logger.info("End Sent Mail When FS select CASH Payment");
				} else if (CommonUtils.PaymentMode.CHEQUE.equalsIgnoreCase(paymentRequest.getTypeOfPayment())) {
					logger.info("Start Sent Mail When FS select CHEQUE Payment");
//					asyncComponent.sendMailWhenFSSelectOnlinePayment(userId, paymentRequest,
//							NotificationTemplate.EMAIL_FS_PAYMENT_CASH_CHEQUE,
//							NotificationAlias.SYS_FS_PAYMENT_CASH_CHEQUE);
					logger.info("End Sent Mail When FS select CHEQUE Payment");
				}
			} catch (Exception e) {
				logger.error("Throw Exception while send mail when save payment : ",e);
			}

			LoansResponse response = new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value());
			response.setData(applicationMaster);
			logger.info("end save_payment_info()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Saving Payment info==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/update_payment_status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updatePaymentStatus(@RequestBody PaymentRequest paymentRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			logger.info("start updatePaymentStatus()");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			LoansResponse response = new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value());
			response.setData(loanApplicationService.updateLoanApplicationMasterPaymentStatus(paymentRequest, userId));
			logger.info("end updatePaymentStatus()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_UPDATING_PAYMENT_STATUS_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/update_payment_status_sidbi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updatePaymentStatusForSidbi(@RequestBody PaymentRequest paymentRequest) {
		try {
			logger.info("start updatePaymentStatus from SIDBI");

			LoansResponse response = new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value());
			response.setData(loanApplicationService.updateLoanApplicationMasterPaymentStatus(paymentRequest,
					paymentRequest.getUserId()));
			logger.info("end updatePaymentStatus from SIDBI()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while updating Payment Status from SIDBI==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}*/

	/*@RequestMapping(value = "/get_payment_status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPaymentStatus(@RequestBody PaymentRequest paymentRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			logger.info("start getPaymentStatus()");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			GatewayRequest gatewayRequest = new GatewayRequest();
			gatewayRequest.setUserId(userId);

			BeanUtils.copyProperties(paymentRequest, gatewayRequest);
			GatewayRequest paymentStatus = gatewayClient.getPaymentStatus(gatewayRequest);
			logger.info(RESPONSE_MSG, paymentStatus);
			LoansResponse response = new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value());
			response.setData(paymentStatus);
			logger.info("end getPaymentStatus()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_UPDATING_PAYMENT_STATUS_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}*/

	@RequestMapping(value = "/update_ddr_status/{applicationId}/{statusId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateDDRStatus(@PathVariable("applicationId") Long applicationId,
			@PathVariable("statusId") Long statusId, @RequestParam(value = "clientId", required = false) Long clientId,
			HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "updateDDRStatus");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(statusId)) {
				logger.warn("statusId(Action Id in Workflow) Must not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn(APPLICATION_ID_MUST_NOT_BE_NULL);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			loanApplicationService.updateDDRStatus(applicationId, userId, clientId, statusId);
			CommonDocumentUtils.endHook(logger, "updateDDRStatus");
			return new ResponseEntity<LoansResponse>(new LoansResponse(SUCCESSFULLY_UPDATED, HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Locking final information==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/update_ddr_status/{applicationId}/{proposalId}/{statusId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateDDRStatusNew(@PathVariable("applicationId") Long applicationId,@PathVariable("proposalId") Long proposalId,
			@PathVariable("statusId") Long statusId,  @RequestParam(value = "clientId", required = false) Long clientId,
			HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "updateDDRStatus by proposal id");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(statusId)) {
				logger.warn("statusId(Action Id in Workflow) Must not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn("applicationId Must not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			loanApplicationService.updateDDRStatusByProposalId(applicationId, userId, proposalId, statusId);
			CommonDocumentUtils.endHook(logger, "updateDDRStatusNew");
			return new ResponseEntity<LoansResponse>(new LoansResponse(SUCCESSFULLY_UPDATED, HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Locking final information==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/copy_loan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> copyOrImportLoan(
			@RequestBody AutoFillOneFormDetailRequest autoFillOneFormDetailRequest,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		logger.info("=========== Enter in copyOrImportLoan() ==============");
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectListNull(autoFillOneFormDetailRequest.getFromApplicationId(),
					autoFillOneFormDetailRequest.getToApplicationId(), autoFillOneFormDetailRequest.getFromProductId(),
					autoFillOneFormDetailRequest.getToProductId())) {
				logger.warn(ALL_PARAMETER_MUST_NOT_BE_NULL_MSG);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			autoFillOneFormDetailService.getAndSaveCorporateAutoFillOneFormDateils(userId,
					autoFillOneFormDetailRequest);
			logger.info("==============Exit from copyOrImportLoan()===============");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Successfully loan copied", HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Copyig Loan Information==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}
/**
 * used the proposal id instead of applicationId
 * */
	@RequestMapping(value = "/get_client/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoanApplicationRequest> getClient(@PathVariable("proposalId") Long proposalId) {
		// request must not be null
		try {
			if (proposalId == null) {
				logger.warn("ID Require to get Loan Application Details. ID==>" + proposalId);
				return null;
			}
			CommonDocumentUtils.endHook(logger, "get");
			return new ResponseEntity<LoanApplicationRequest>(loanApplicationService.getFromClient(proposalId), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details from Client==>", e);
			return null;
		}
	}
	
	@RequestMapping(value = "/getBasicInformation/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoanApplicationRequest> getBasicInformation(@PathVariable("applicationId") Long applicationId) {
		// request must not be null
		try {
			if (applicationId == null) {
				logger.warn("ID Require to get Loan Application Details. ID==>{}" , applicationId);
				return null;
			}
			CommonDocumentUtils.endHook(logger, "get");
			return new ResponseEntity<LoanApplicationRequest>(loanApplicationService.getBasicInformation(applicationId), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details from Client==>", e);
			return null;
		}
	}

	@RequestMapping(value = "/getDisbursementDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getDisbursementDetails(@RequestBody DisbursementRequest disbursementRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			logger.info("start getDisbursementDetails()");
			/*
			 * Long userId = null; if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)
			 * request.getAttribute(CommonUtils.USER_TYPE)) .intValue() ||
			 * CommonUtils.UserType.NETWORK_PARTNER == ((Integer)
			 * request.getAttribute(CommonUtils.USER_TYPE)) .intValue()) { userId =
			 * clientId; } else { userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			 * }
			 */
			if(disbursementRequest.getIsIneligibleProposal() == null || disbursementRequest.getIsIneligibleProposal() == false) {
				if (CommonUtils.isObjectListNull(disbursementRequest.getApplicationId(),disbursementRequest.getProductMappingId())) {
					logger.warn(ALL_PARAMETER_MUST_NOT_BE_NULL_MSG);
					return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
				}
			}else if(disbursementRequest.getIsIneligibleProposal() != null && disbursementRequest.getIsIneligibleProposal() == true && CommonUtils.isObjectNullOrEmpty(disbursementRequest.getApplicationId()) ) {
					logger.warn("Application Id must not be null");
					return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse response = new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value());
			response.setData(loanApplicationService.getDisbursementDetails(disbursementRequest));
			logger.info("end getDisbursementDetails()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getDisbursementDetails", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getDetailsForSanctionPopup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getDetailsForSanctionPopup(@RequestBody DisbursementRequest disbursementRequest, HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			logger.info("start getDetailsForApproval()");
			if(disbursementRequest.getIsIneligibleProposal() == null || disbursementRequest.getIsIneligibleProposal() == false) {
				if (CommonUtils.isObjectListNull(disbursementRequest.getApplicationId(),disbursementRequest.getProductMappingId())) {
					logger.warn(ALL_PARAMETER_MUST_NOT_BE_NULL_MSG);
					return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
				}
			}else if(disbursementRequest.getIsIneligibleProposal() != null && disbursementRequest.getIsIneligibleProposal() == true && CommonUtils.isObjectNullOrEmpty(disbursementRequest.getApplicationId()) ) {
					logger.warn("Application Id must not be null");
					return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse response = new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value());
			response.setData(loanApplicationService.getDetailsForSanction(disbursementRequest));
			logger.info("end getDetailsForApproval()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getDetailsForApproval", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/saveDetailsForSanctionPopup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveDetailsForSanctionPopup(@RequestBody LoanSanctionRequest loanSanctionRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			logger.info("start getDetailsForApproval()");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userType = Long.valueOf(request.getAttribute(CommonUtils.USER_TYPE).toString());
			if (userId == null) {
				logger.warn(USER_ID_MUST_NOT_BE_NULL_MSG);
				return new ResponseEntity<LoansResponse>(new LoansResponse(
						INVALID_USER_PLEASE_RELOGIN_AND_TRY_AGAIN_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (userType == null) {
				logger.warn("userType must not be null==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(
						INVALID_USER_PLEASE_RELOGIN_AND_TRY_AGAIN_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			loanSanctionRequest.setActionBy(userId.toString());

			if (CommonUtils.isObjectListNull(loanSanctionRequest.getApplicationId(), loanSanctionRequest.getBranch(),
					loanSanctionRequest.getOrgId(), loanSanctionRequest.getRoi(),
					loanSanctionRequest.getSanctionAmount(), loanSanctionRequest.getTenure(),
					loanSanctionRequest.getProcessingFee(), loanSanctionRequest.getBusinessTypeId())) {
				logger.warn(ALL_PARAMETER_MUST_NOT_BE_NULL_MSG);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse response = new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value());

			Integer result = loanSanctionService.saveSanctionDetailFromPopup(loanSanctionRequest);
			logger.info("result of save sanction detail ---------------------{}", result);

			if (result == 4) {
				response.setData(true);
				response.setMessage("Updated Successfully");
				response.setStatus(HttpStatus.OK.value());
				if(loanSanctionRequest.getIsSanctionedFrom() != 2){
					ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
					proposalMappingRequest.setId(loanSanctionRequest.getProposalId());
					proposalMappingRequest.setProposalStatusId(loanSanctionRequest.getProposalStatusId());
					proposalMappingRequest.setLastActionPerformedBy(userType);
					proposalMappingRequest.setUserId(userId);
					proposalDetailsClient.changeStatus(proposalMappingRequest);
				}
			} else if (result == 1) {
				response.setData(false);
				response.setMessage("The said borrower has already receive in-principle approval through online journey, hence the same proposal can not be sanctioned through offline mode");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			} else if (result == 2) {
				response.setData(false);
				response.setMessage("The said proposal has already been sanctioned by one of our bank partners, hence the same proposal can not be sanctioned again");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			} else {
				response.setData(false);
				response.setMessage("The application has encountered an error, please try again after sometime!!!");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			}
			logger.info("end getDetailsForApproval()---------------->" + result);
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getDetailsForApproval", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/updateProductDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateProductDetails(@RequestBody LoanApplicationRequest loanRequest) {
		try {
			logger.info("Enter in update product details service--------------------------------->");
			if (CommonUtils.isObjectListNull(loanRequest.getId(), loanRequest.getProductId())) {
				logger.warn(ALL_PARAMETER_MUST_NOT_BE_NULL_MSG);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.updateProductDetails(loanRequest));
			CommonDocumentUtils.endHook(logger, "updateProductDetails");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while updateProductDetails==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/inactive_application/{id}/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> inActiveApplication(@PathVariable("id") Long id,
			@PathVariable("userId") Long userId) {
		// request must not be null
		try {
			logger.info("Entry inActiveApplication");
			logger.info("Application Require to Inactive Loan Application Details ==>" + id);
			LoansResponse loansResponse = new LoansResponse(INACTIVATED, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.inActiveApplication(id, userId));
			logger.info("Exit inActiveApplication");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Inactivating Loan Application Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getFpDetailsByFpProductId/{fpProductId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFpDetailsByFpProductId(@PathVariable("fpProductId") Long fpProductId) {
		try {
			logger.info("Entry getFpDetailsByFpProductId");
			LoansResponse loansResponse = new LoansResponse(INACTIVATED, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.getFpDetailsByFpProductId(fpProductId));
			logger.info("Exit getFpDetailsByFpProductId");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getFpDetailsByFpProductId==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/getFpDetailsByFpProductMappindId/{fpProductMappingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFpDetailsByFpProductMappingId(@PathVariable("fpProductMappingId") Long fpProductMappingId) {
		
		try {
			logger.info("ENTER  IN GET FP DETAILS BY PRODUCT MAPPING ID -------------FP PRODUCT MAPPING ID >>>>>>>>>>>"+fpProductMappingId);
			LoansResponse loansResponse = new LoansResponse("DATA FOUND", HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.getFpDetailsByFpProductMappingId(fpProductMappingId));
			logger.info("Exit getFpDetailsByFpProductMappingId");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

			
		} catch (Exception e) {
			logger.error("Error while getFpDetailsByFpProductMappingId==========>", e.getMessage());
			
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}


	/**@RequestMapping(value = "/update_skip_payment_status/{appId}/{orgId}/{fpProductId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateSkipPaymentStatus(@PathVariable("appId") Long appId,
			@PathVariable("orgId") Long orgId, @PathVariable("fpProductId") Long fprProductId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			logger.info("start updateSkipPaymentStatus()");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			LoansResponse response = new LoansResponse(SUCCESSFULLY_UPDATED, HttpStatus.OK.value());
			loanApplicationService.updateSkipPayment(userId, appId, orgId, fprProductId);
			logger.info("end updateSkipPaymentStatus()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_UPDATING_PAYMENT_STATUS_MSG, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/update_skip_payment_whiteLabel/{appId}/{businessTypeId}/{orgId}/{fpProductId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateSkipPayment(@PathVariable("appId") Long appId, @PathVariable("businessTypeId") Integer businessTypeId,
			@PathVariable("orgId") Long orgId, @PathVariable("fpProductId") Long fprProductId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			logger.info("start updateSkipPaymentForWhiteLabel()");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			LoansResponse response = new LoansResponse("Successfully send In-Principle for WhiteLabel", HttpStatus.OK.value());
			loanApplicationService.updateSkipPaymentWhiteLabel(userId, appId, businessTypeId, orgId, fprProductId);
			logger.info("end updateSkipPaymentStatus()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while updating Payment Status for WhiteLabel==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/send_inPrinciple_personalLoan/{appId}/{businessTypeId}/{orgId}/{fpProductId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> sendInPrincipleForPersonalLoan(@PathVariable("appId") Long appId, @PathVariable("businessTypeId") Integer businessTypeId,
			@PathVariable("orgId") Long orgId, @PathVariable("fpProductId") Long fprProductId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			logger.info("start inPrincipleForPersonalLoan()");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			LoansResponse response = new LoansResponse("Successfully send In-Principle for Personal Loan", HttpStatus.OK.value());
			loanApplicationService.sendInPrincipleForPersonalLoan(userId, appId, businessTypeId, orgId, fprProductId);
			logger.info("end inPrincipleForPersonalLoan()");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while sending In-Principle for Personal Loan==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
*/
	@RequestMapping(value = "/getProposalDataFromAppId/{appId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getProposalDataFromAppId(@PathVariable("appId") Long appId) {
		try {
			logger.info("start getProposalDataFromAppId()");
			LoanApplicationRequest loanResponse = loanApplicationService.getProposalDataFromApplicationId(appId);
			if (!CommonUtils.isObjectNullOrEmpty(loanResponse)) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully get result", HttpStatus.OK.value(), loanResponse),
						HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(new LoansResponse("Data not found !!", HttpStatus.OK.value()),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getProposalDataFromAppId ==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}

	// For Payment Gateway response through Mobile API
	// ===========================================================================================================================

	/**@RequestMapping(value = "mobile/successUrl", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void payUMoneyResponse(@RequestBody String response) {

		try {
			logger.info("Payment Gateway redirect response for Mobile" + response);
			Map<String, Object> map = new HashMap<String, Object>();
			String responseMessageSequence = "MerchantID|txnid|Filler1|Filler2|TxnAmount|BankID|Filler3|Filler4|CurrencyType|ItemCode|Filler5|Filler6|Filler7|txnDate|statusCode|Filler7|Phone|EmailId|firstname|applicationId|productinfo|AdditionalInfo2|AdditionalInfo3|AdditionalInfo4|status|checkSum";
			String responseType = null;
			PaymentRequest paymentRequest = null;
			int flag = 0;
			String decodedresponse = null;
			String responseParams = null;
			try {
				decodedresponse = URLDecoder.decode(response, StandardCharsets.UTF_8.toString());
				responseParams = decodedresponse;
				logger.info("Decoded value=======++++++>>>>" + decodedresponse);
			} catch (UnsupportedEncodingException e) {
				logger.error("Something went wrong while decoding the response!!!",e);
			}

			StringTokenizer a = new StringTokenizer(decodedresponse, "&");

			while (a.hasMoreElements()) {
				String z = a.nextToken();
				StringTokenizer m = new StringTokenizer(z, "=");

				logger.info("Checking whether the Response is from BillDesk or PayUMoney");
				String key;
//				String value;
				try {
					key = m.nextToken();
					if ("msg".equals(key)) {
						responseType = "BillDesk";
						logger.info("Response is from BillDesk" + key);
						break;
					}
				} catch (Exception e) {
					logger.error(TOKEN_NULL_MSG + e.getMessage());
//					key = null;
				}
				try {
//					value = m.nextToken();
				} catch (Exception e) {
					logger.error(TOKEN_NULL_MSG + e.getMessage());
//					value = null;
				}
				// map.put(key, value);
			}

			if ("BillDesk".equals(responseType)) {

				StringTokenizer x = new StringTokenizer(decodedresponse, "&");

				while (x.hasMoreElements()) {
					String z = x.nextToken();
					StringTokenizer p = new StringTokenizer(z, "=");
					while (p.hasMoreElements()) {
						String msg = p.nextToken();

						if (!"msg".equals(msg)) {
							// String pipedMessage = msg.replaceAll("%7C", "|");
							logger.info("Piped Message================>" + msg);
							StringTokenizer b = new StringTokenizer(msg, "|");
							StringTokenizer c = new StringTokenizer(responseMessageSequence, "|");
							while (b.hasMoreElements()) {
								while (c.hasMoreElements()) {
									String key = c.nextToken();
									String value = b.nextToken();
									logger.info(key + "================>" + value);

									map.put(key, value);

								}
							}
							flag = 1;
						}
						if (flag == 1)
							break;
					}
					if (flag == 1)
						break;
				}
				responseParams = responseParams.replaceAll("&", " ");
				responseParams = responseParams.replace("|", " ");
				logger.info("Response Params================>" + responseParams);
				paymentRequest = new PaymentRequest();
				paymentRequest.setApplicationId(Long.valueOf(String.valueOf(map.get("applicationId"))));
				paymentRequest.setUserId(Long.valueOf(String.valueOf(map.get("AdditionalInfo2"))));
				paymentRequest.setPurposeCode(map.get(PRODUCT_INFO).toString());
				paymentRequest.setResponseParams(responseParams);
				paymentRequest.setNameOfEntity(map.get("firstname").toString());
				
				if ("0300".equals(map.get("statusCode"))) {
					paymentRequest.setStatus(CommonUtils.SUCCESS);
				}
				else {
					paymentRequest.setStatus(FAILED_LITERAL);
				}
			}

			else {

				StringTokenizer x = new StringTokenizer(response, "&");
				while (x.hasMoreElements()) {
					String z = x.nextToken();
					StringTokenizer p = new StringTokenizer(z, "=");
					logger.info("Success URL ----------------------------> " + p);
					String key;
					String value;
					try {
						key = p.nextToken();
					} catch (Exception e) {
						logger.error(TOKEN_NULL_MSG + e.getMessage());
						key = null;
					}
					try {
						value = p.nextToken();
					} catch (Exception e) {
						logger.error(TOKEN_NULL_MSG + e.getMessage());
						value = null;
					}
					map.put(key, value);

				}
				responseParams = responseParams.replaceAll("&", " ");
				logger.info("Response Params================>" + responseParams);
				paymentRequest = new PaymentRequest();
				paymentRequest.setApplicationId(Long.valueOf(String.valueOf(map.get("udf1"))));
				paymentRequest.setUserId(Long.valueOf(String.valueOf(map.get("udf2"))));
				paymentRequest.setPurposeCode(map.get(PRODUCT_INFO).toString());
				paymentRequest.setResponseParams(responseParams);
				paymentRequest.setNameOfEntity(map.get("firstname").toString());
				
				if ("success".equals(map.get("status").toString())) {
					paymentRequest.setStatus(CommonUtils.SUCCESS);
				} else {
					paymentRequest.setStatus(FAILED_LITERAL);
				}

			}

			logger.info("AppId==>" + paymentRequest.getApplicationId() + " UserId==>" + paymentRequest.getUserId()
					+ " PuposeCode==>" + paymentRequest.getPurposeCode());

			
			paymentRequest.setTrxnId(map.get("txnid").toString());

			LoansResponse loansResponse = new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.updateLoanApplicationMasterPaymentStatus(paymentRequest,
					paymentRequest.getUserId()));
			logger.info("end updatePaymentForMobileStatus()");
		} catch (Exception e) {
			logger.error("Error while updating Payment Status for mobile==>{}", e);
		}

	}

	@RequestMapping(value = "mobile/billDesk", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void billDeskResponse(@RequestBody String response) {

		try {
			logger.info("BillDesk redirect response for Mobile" + response);

			Map<String, Object> map = new HashMap<String, Object>();
			String responseMessageSequence = "MerchantID|txnid|Filler1|Filler2|TxnAmount|BankID|Filler3|Filler4|CurrencyType|ItemCode|Filler5|Filler6|Filler7|txnDate|statusCode|Filler7|Phone|EmailId|firstname|applicationId|productinfo|AdditionalInfo2|AdditionalInfo3|AdditionalInfo4|status|checkSum";
			int flag = 0;

			StringTokenizer x = new StringTokenizer(response, "&");

			while (x.hasMoreElements()) {
				String z = x.nextToken();
				StringTokenizer p = new StringTokenizer(z, "=");
				while (p.hasMoreElements()) {
					String msg = p.nextToken();

					if (!"msg".equals(msg)) {
						String pipedMessage = msg.replaceAll("%7C", "|");
						logger.info("Piped Message================>" + pipedMessage);
						StringTokenizer b = new StringTokenizer(pipedMessage, "|");
						StringTokenizer c = new StringTokenizer(responseMessageSequence, "|");
						while (b.hasMoreElements()) {
							while (c.hasMoreElements()) {
								String key = c.nextToken();
								String value = b.nextToken();
								logger.info(key + "================>" + value);

								map.put(key, value);

							}
						}
						flag = 1;
					}
					if (flag == 1)
						break;
				}
				if (flag == 1)
					break;
			}

			PaymentRequest paymentRequest = new PaymentRequest();

			paymentRequest.setApplicationId(Long.valueOf(String.valueOf(map.get("udf1"))));
			paymentRequest.setUserId(Long.valueOf(String.valueOf(map.get("udf2"))));
			paymentRequest.setPurposeCode(map.get(PRODUCT_INFO).toString());
			if ("success".equals(map.get("status").toString())) {
				paymentRequest.setStatus(CommonUtils.SUCCESS);
			} else {
				paymentRequest.setStatus(FAILED_LITERAL);
			}
			paymentRequest.setTrxnId(map.get("txnid").toString());

			LoansResponse loansResponse = new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.updateLoanApplicationMasterPaymentStatus(paymentRequest,
					paymentRequest.getUserId()));
			logger.info("end updatePaymentForMobileStatus()");
		} catch (Exception e) {
			logger.error("Error while updating Payment Status for mobile==>{}", e);
		}

	}
*/

	
//	loanSanctionService.saveSanctionAndDisbursementDetailsFromBank();

	/*@RequestMapping(value = "/saveLoanSanctionDisbursementDetailFromBank", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<LoansResponse> saveLoanSanctionDisbursementDetailFromBank(@RequestBody String encryptedString) {
		LoansResponse loansResponse = null;
		String sanctionReason = null;
		String disbursementReason = null;
		Long orgId = null;
		String decrypt = null;
		LoanSanctionAndDisbursedRequest loanSanctionAndDisbursedRequest = null;
		GenerateTokenRequest generateTokenRequest = null;
		String tokenString = null;
		try {
			logger.info(
					"=============================Entry saveLoanSanctionDisbursementDetailFromBank(){} ============================= ");
			
			
			 * if(CommonUtils.isObjectNullOrEmpty(tokenString)) { reason = TOKEN_IS_NULL;
			 * loansResponse = new LoansResponse(reason, HttpStatus.UNAUTHORIZED .value());
			 * return new ResponseEntity<LoansResponse>(loansResponse,
			 * HttpStatus.UNAUTHORIZED); }else {
			 * if(CommonUtils.isObjectNullOrEmpty((tokenString =
			 * tokenService.checkTokenExpiration(tokenString)))) { reason =
			 * TOKEN_IS_EXPIRED; loansResponse = new LoansResponse(reason,
			 * HttpStatus.UNAUTHORIZED .value()); return new
			 * ResponseEntity<LoansResponse>(loansResponse, HttpStatus.UNAUTHORIZED); } }
			 
			// logger.info("-----------------------------Entry
			// saveLoanSanctionDisbursementDetailFromBank(){} --------------------");
			if (encryptedString != null) {
				
				try {
					decrypt = AESEncryptionUtility.decrypt(encryptedString);
					
					loanSanctionAndDisbursedRequest = MultipleJSONObjectHelper.getObjectFromString(decrypt,
							LoanSanctionAndDisbursedRequest.class);
					
				} catch (Exception e) {
					logger.info(
							"Error while Converting Encrypted Object to LoanSanctionAndDisbursedRequest  saveLoanSanctionDisbursementDetailFromBank(){} -------------------------> ",
							e.getMessage());
					loansResponse = new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value(),
							HttpStatus.OK);
					loansResponse.setData(false);
					if (CommonUtils.isObjectNullOrEmpty(decrypt)) {
						sanctionReason = ERROR_WHILE_DECRYPT_ENCRYPTED_OBJECT_MSG;
					} else {
						sanctionReason = "error while converting decrypt string to profileReqRes ====> Msg ===> ";
					}
					sanctionReason += e.getMessage();
					return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
				}
			
				if (!CommonUtils.isObjectListNull(loanSanctionAndDisbursedRequest,
					loanSanctionAndDisbursedRequest.getApplicationId(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getAccountNo(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getApplicationId(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getBranch(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getRoi(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getSanctionAmount(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getSanctionDate(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getTenure(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getUserName(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getPassword(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getReferenceNo(),
					loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getActionBy())) {
					orgId = auditComponentBankToCW.getOrgIdByCredential(loanSanctionAndDisbursedRequest.getUserName(),
							loanSanctionAndDisbursedRequest.getPassword());
					if (!CommonUtils.isObjectNullOrEmpty(orgId)) {
						sanctionReason = loanSanctionService.requestValidation(loanSanctionAndDisbursedRequest.getApplicationId(), orgId);
						if (SUCCESS_LITERAL.equalsIgnoreCase(sanctionReason) && loanSanctionService
								.saveLoanSanctionDetail(loanSanctionAndDisbursedRequest.getLoanSanctionRequest())) {
							if(! CommonUtils.isListNullOrEmpty(loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList())) {
								disbursementReason = loanDisbursementService.bankRequestValidationAndSave(
									loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList(), orgId , CommonUtility.ApiType.SANCTION_AND_DISBURSEMENT);
							}
						}

						if (SUCCESS_LITERAL.equalsIgnoreCase(sanctionReason) || "First Disbursement".equalsIgnoreCase(sanctionReason)) {
							logger.info(
									"Success msg while saveLoanSanctionDisbursementDetailFromBank() ----------------> msg "
											+ sanctionReason);
							sanctionReason = null;
							loansResponse = new LoansResponse(INFORMATION_SUCCESSFULLY_STORED_MSG,
									HttpStatus.OK.value());
							if(SUCCESS_LITERAL.equalsIgnoreCase(disbursementReason) || "First Disbursement".equalsIgnoreCase(disbursementReason)){
								loansResponse.setData(CommonUtility.ApiType.SANCTION_AND_DISBURSEMENT);
							}else {
								loansResponse.setData(CommonUtility.ApiType.SANCTION);
							}
							logger.info("Exit saveLoanSanctionDisbursementDetailFromBank() ---------------->  msg ==>"
									+ INFORMATION_SUCCESSFULLY_STORED_MSG);
							return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
						} else {
							logger.info(
									"Failure msg while save LoanSanctionAndDisbursedRequest in saveLoanSanctionDisbursementDetailFromBank() to CW ----------------> msg "
											+ sanctionReason);
							loansResponse = new LoansResponse(sanctionReason.split("[\\{}]")[0],
									HttpStatus.BAD_REQUEST.value());
							loansResponse.setData(false);
							logger.info("Exit saveLoanDisbursementDetail() ----------------> msg ==>" + sanctionReason);
							return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
						}
					} else {
						sanctionReason = INVALID_CREDENTIALS;
						logger.info(
								"Invalid Credentials while saveLoanSanctionDisbursementDetailFromBank() ----------------> orgId "
										+ orgId + REASON_MSG + sanctionReason);
						loansResponse = new LoansResponse(sanctionReason, HttpStatus.UNAUTHORIZED.value());
						loansResponse.setData(false);
						logger.info("================== Exit saveLoanDisbursementDetail() =================");
						return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
					}
				} else {
					logger.info(
							"Null in LoanSanctionAndDisbursedRequest while saveLoanSanctionDisbursementDetailFromBank() ----------------> LoanDisbursementRequest"
									+ loanSanctionAndDisbursedRequest);
					loansResponse = new LoansResponse(CommonUtils.MANDATORY_FIELDS_MUST_NOT_BE_NULL,
							HttpStatus.BAD_REQUEST.value(), HttpStatus.OK);
					loansResponse.setData(false);
					sanctionReason = "Mandatory Fields Must Not be Null while saveLoanSanctionDisbursementDetailFromBank() ===> LoanDisbursementRequest ====> "
							+ loanSanctionAndDisbursedRequest;
					return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
				}
			} else {
				logger.info(
						"Null in encryptedString while saveLoanSanctionDisbursementDetailFromBank() ----------------> encryptedString "
								+ encryptedString);
				loansResponse = new LoansResponse(CommonUtils.MANDATORY_FIELDS_MUST_NOT_BE_NULL, HttpStatus.BAD_REQUEST.value(),
						HttpStatus.OK);
				loansResponse.setData(false);
				sanctionReason = "Null in encryptedString while saveLoanSanctionDisbursementDetailFromBank() encryptedString ====>"
						+ encryptedString;
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Error while saveLoanSanctionDisbursementDetailFromBank()----------------------> ", e);
			loansResponse = new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG,
					HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK);
			loansResponse.setData(false);
			sanctionReason = "Error while save LoanSanctionAndDisbursedRequest in  saveLoanSanctionDisbursementDetailFromBank() ===> Msg "
					+ e.getMessage();
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} finally {
			logger.info(SAVING_REQUEST_TO_DB_MSG);
			generateTokenRequest = new GenerateTokenRequest();
			generateTokenRequest.setToken(tokenString);
			auditComponentBankToCW.saveBankToCWReqRes(decrypt != null ? decrypt : encryptedString,
					loanSanctionAndDisbursedRequest != null ? loanSanctionAndDisbursedRequest.getApplicationId() : null,
					CommonUtility.ApiType.SANCTION_AND_DISBURSEMENT, loansResponse, sanctionReason, orgId);
		}
	}*/


	
	@RequestMapping(value = "/ddr_status/{applicationId}", method = RequestMethod.GET)
	public ResponseEntity<LoansResponse> ddrStatus(@PathVariable("applicationId") Long applicationId) {
		try {
			logger.info("ENTER IN GET DDR STATUS ID---------------->" + applicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(), loanApplicationService.getDDRStatusId(applicationId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while get ddr status==>",e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	

	@RequestMapping(value = "/saveLoanWCRenewalType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveLoanWCRenewalType(@RequestBody LoanApplicationRequest loanRequest) {
		if(CommonUtils.isObjectNullOrEmpty(loanRequest.getId()) || CommonUtils.isObjectNullOrEmpty(loanRequest.getWcRenewalStatus())) {
			return new ResponseEntity<LoansResponse>(new LoansResponse("Request Parameter Null Or Empty !!", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			logger.info("ENTER IN saveLoanWCRenewalType---------------->" + loanRequest.getId());
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully save data", HttpStatus.OK.value(), 
					loanApplicationService.saveLoanWCRenewalType(loanRequest.getId(), loanRequest.getWcRenewalStatus())), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saveLoanWCRenewalType==>",e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/getLoanWCRenewalType/{applicationId}", method = RequestMethod.GET)
	public ResponseEntity<LoansResponse> getLoanWCRenewalType(@PathVariable("applicationId") Long applicationId) {
		try {
			logger.info("ENTER IN getLoanWCRenewalType---------------->" + applicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(),
					loanApplicationService.getLoanWCRenewalType(applicationId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getLoanWCRenewalType==>",e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/saveLoanDisbursementDetailForIneligibleApp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveLoanDisbursementDetailForInEligibleApp(@RequestBody LoanDisbursementRequest loanDisbursementRequest, HttpServletRequest httpServletRequest) {
		try {
			Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			Long userType = Long.valueOf(httpServletRequest.getAttribute(CommonUtils.USER_TYPE).toString());
			if (userId == null) {
				logger.warn(USER_ID_MUST_NOT_BE_NULL_MSG);
				return new ResponseEntity<LoansResponse>(new LoansResponse(
						INVALID_USER_PLEASE_RELOGIN_AND_TRY_AGAIN_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (userType == null) {
				logger.warn("userType must not be null==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(
						INVALID_USER_PLEASE_RELOGIN_AND_TRY_AGAIN_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			loanDisbursementRequest.setActionBy(userId.toString());
			LoansResponse loansResponse =null;
			logger.info("-----------------------------Enter in saveLoanDisbursementDetail(){} --------------------");
			if (!CommonUtils.isObjectListNull(loanDisbursementRequest.getApplicationId(),loanDisbursementRequest.getDisbursedAmount(), loanDisbursementRequest.getDisbursementDate(),loanDisbursementRequest.getPaymentMode(), loanDisbursementRequest.getTransactionNo(),loanDisbursementRequest.getOrgId())) {
					Long orgId = loanDisbursementRequest.getOrgId();
					if (!CommonUtils.isObjectNullOrEmpty(orgId)) {
						loanDisbursementRequest = loanDisbursementService.disbursementRequestValidation(null , loanDisbursementRequest, orgId , CommonUtility.ApiType.DISBURSEMENT);
						if (SUCCESS_LITERAL.equalsIgnoreCase(loanDisbursementRequest.getReason()) || "First Disbursement".equalsIgnoreCase(loanDisbursementRequest.getReason())) {
							logger.info("Success msg while saveLoanDisbursementDetail()");
							loansResponse = new LoansResponse(INFORMATION_SUCCESSFULLY_STORED_MSG,HttpStatus.OK.value());
							loansResponse.setData(loanDisbursementService.saveLoanDisbursementDetail(loanDisbursementRequest));
							logger.info("Exit saveLoanDisbursementDetail() ---------------->  msg ==>"+ INFORMATION_SUCCESSFULLY_STORED_MSG);
							return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
						} else {
							logger.info("Failure msg while saveLoanDisbursementDetail in saveLoanDisbursementDetail() ----------------> msg ");
							loansResponse = new LoansResponse(loanDisbursementRequest.getReason().split("[\\{}]")[0],HttpStatus.BAD_REQUEST.value());
							loansResponse.setData(false);
							logger.info("Exit saveLoanDisbursementDetail() ----------------> msg ==>");
							return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
						}
					} 
				} else {
					logger.info("Null in LoanDisbursementRequest while saveLoanDisbursementDetail() ----------------> LoanDisbursementRequest"+ loanDisbursementRequest);
					loansResponse = new LoansResponse(CommonUtils.MANDATORY_FIELDS_MUST_NOT_BE_NULL,HttpStatus.BAD_REQUEST.value(), HttpStatus.OK);
					loansResponse.setData(false);
					return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
				}
			} catch (Exception e) {
			logger.error("Error while saveLoanDisbursementDetail()----------------------> ", e);
			LoansResponse loansResponse =null;
			loansResponse = new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK);
			loansResponse.setData(false);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} 
		return null;
	}
	
	@RequestMapping(value = "/getCommonPropValue/{keyName}", method = RequestMethod.GET)
	public ResponseEntity<LoansResponse> getCommonPropValue(@PathVariable("keyName") String keyName) {
		try {
			logger.info("ENTER IN getCommonPropValue---------------->" + keyName);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(),
					loanApplicationService.getCommonPropertiesValue(keyName)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getCommonPropValue==>",e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getProposalId/{applicationId}", method = RequestMethod.GET)
	public ResponseEntity<LoansResponse> getProposalId(@PathVariable("applicationId") Long applicationId, HttpServletRequest request) {
		try {

			Long userOrdId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			if(userOrdId == null){
				logger.error("User organization id can not be null");
				return new ResponseEntity<>(new LoansResponse(
						"User organization id can not be null", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			Long proposalId = loanApplicationService.getProposalId(applicationId,userOrdId);
			if(proposalId==null){
				return new ResponseEntity<>(new LoansResponse(
						"Proposal id not found", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse loansResponse  = new LoansResponse();
			loansResponse.setMessage("Successful");
			loansResponse.setData(proposalId);
			loansResponse.setStatus(200);
			return new ResponseEntity<>(loansResponse,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getProposalId==>{}",e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/corporate_mcq/{applicationId}/{proposalId}/{tabType}/{coAppllicantOrGuarantorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isAllowToMoveAheadMultiProposal(@PathVariable("applicationId") Long applicationId,
																		 @PathVariable("proposalId") Long proposalId,
																		 @PathVariable("tabType") Integer tabType,
																		 @PathVariable("coAppllicantOrGuarantorId") Long coAppllicantOrGuarantorId, HttpServletRequest request,
																		 @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "isAllowToMoveAhead");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (applicationId == null || userId == null) {
				logger.warn("ID And UserId Require to get Primary Working Details ==>" + applicationId
						+ " and UserId ==>" + userId);
				CommonDocumentUtils.endHook(logger, "isAllowToMoveAhead");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isAllowToMoveAheadForMultiProposal(applicationId, proposalId,userId, tabType,
					coAppllicantOrGuarantorId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Primary Working Details==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getLoanApplicationById/{proposalId}", method = RequestMethod.GET)
	public ResponseEntity<LoansResponse> getApplicationProposalByProposalId(@PathVariable("proposalId") Long proposalId, HttpServletRequest request) {
		try {
			logger.info("Inside getLoanApplicationById start");
			ApplicationProposalMapping applicationMaster = appPropMappService.getApplicationProposalMappingByProposalId(proposalId);
			if (applicationMaster == null) {
				logger.info("may be Loan Application is inActive");
				throw new NullPointerException("Invalid Loan Application ID==>" + proposalId);
			}
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			BeanUtils.copyProperties(applicationMaster, applicationRequest);
			if(applicationMaster.getApplicationId()==null){
				return new ResponseEntity<>(new LoansResponse(
						"Application not found", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse loansResponse  = new LoansResponse();
			loansResponse.setMessage("Loan application got successful");
			loansResponse.setData(applicationRequest);
			loansResponse.setStatus(200);
			logger.info("Inside getLoanApplicationById - end");
			return new ResponseEntity<>(loansResponse,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while /getLoanApplicationById/{applicationId}==>{}",e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}


	/**
	 * 
	 * @param request
	 * @param clientId
	 * @return loans response, that set with  json data of userApplicationList
	 * @author nilay.darji
	 */
	@RequestMapping(value = "/getUserApplicationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUserAppplicationList(HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getUserApplicationList from Procedure");
			
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (userId == null) {
				logger.warn("UserId Require to get Loan Applications Details ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			String response = loanApplicationService.getUserApplicationList(userId);
			if(response != null) {
				LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
				loansResponse.setData(response);
				CommonDocumentUtils.endHook(logger, "getList");
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
			else {
				CommonDocumentUtils.endHook(logger, "Response Null::--> "+userId);
				return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_LOAN_APPLICATION_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = "/savePaymentGatewayAuditLogs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GatewayResponse> savePaymentGatewayAuditMaster(@RequestBody PaymentRequest paymentRequest,
			HttpServletRequest request) {
		try {
			logger.info("start savePaymentGatewayAuditLogs()");
				
			if (CommonUtils.isObjectNullOrEmpty(paymentRequest.getApplicationId())) {
				logger.error("AppId is null or Empty");
				return new ResponseEntity<GatewayResponse>(
						new GatewayResponse("Invalid Request, Application Id Null Or Empty",HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			Boolean status = loanApplicationService.savePaymentGatewayAudit(paymentRequest);
			logger.info("Response========>{}", status);

			GatewayResponse response = new GatewayResponse(CommonUtils.PaymentStatus.SUCCESS, HttpStatus.OK.value());
			response.setData(paymentRequest);
			logger.info("end savePaymentGatewayAuditLogs");
			return new ResponseEntity<GatewayResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Saving Payment info==>{}", e);
			return new ResponseEntity<GatewayResponse>(
					new GatewayResponse( CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}


	@RequestMapping(value = "/updateLoanType/{applicationId}/{loanTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateLoanType(@PathVariable("applicationId") Long applicationId, @PathVariable("loanTypeId") Long loanTypeId,
														HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (applicationId == null || loanTypeId == null) {
				logger.warn("Application ID Require to updateLoanType==>"
						+ applicationId+" loanTypeId ==> "+loanTypeId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			Boolean status = loanApplicationService.updateLoanType(userId,applicationId,loanTypeId);
			if(status)
			{
				LoansResponse loansResponse = new LoansResponse("LoanType Updated", HttpStatus.OK.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
			else
			{
				LoansResponse loansResponse = new LoansResponse("Error while LoanType Update", HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Error while loan Type update==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
