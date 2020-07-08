package com.capitaworld.service.loans.controller.teaser.primaryView;

import java.util.Arrays;
import java.util.LinkedHashMap;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.CommonRequest;
import com.capitaworld.service.loans.model.teaser.primaryview.CorporatePrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.TermLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.UnsecuredLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.WorkingCapitalPrimaryViewResponse;
import com.capitaworld.service.loans.repository.common.CommonRepository;
import com.capitaworld.service.loans.service.common.NotificationService;
import com.capitaworld.service.loans.service.teaser.primaryview.CorporatePrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.TermLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.UnsecuredLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.WorkingCapitalPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonNotificationUtils.NotificationTemplate;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.notification.utils.EmailSubjectAlias;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UserTypeRequest;
import com.capitaworld.service.users.model.UsersRequest;

@RestController
@RequestMapping("/PrimaryView")
public class PrimaryViewController {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryViewController.class);

	@Autowired
	private WorkingCapitalPrimaryViewService workingCapitalPrimaryViewService;

	@Autowired
	private TermLoanPrimaryViewService termLoanPrimaryViewService;

	@Autowired
	private UnsecuredLoanPrimaryViewService unsecuredLoanPrimaryViewService;

	@Autowired
	private CorporatePrimaryViewService corporatePrimaryViewService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UsersClient usersClient;

	@Autowired
	private CommonRepository commonRepository;

	private static final String WARN_MSG_USER_VERIFICATION_INVALID_REQUEST_CLIENT_ID_IS_NOT_VALID = "user_verification, Invalid Request... Client Id is not valid : ";
	private static final String ERROR_MSG_USER_VERIFICATION_INVALID_REQUEST_SOMETHING_WENT_WRONG = "user_verification, Invalid Request... Something went wrong : ";
	private static final String WORKING_CAPITAL_PRIMARY_DETAILS = "Working Capital Primary Details";

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/WorkingCapital/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfWorkingCapital(
			@PathVariable(value = "toApplicationId") Long toApplicationId,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		LoansResponse loansResponse = new LoansResponse();

		// get user id from http servlet request
		Long userId = null;
		Integer userType = null;

		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if (!CommonUtils.isObjectNullOrEmpty(clientId)) {
				// MEANS FS, FP VIEW
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if (response != null && response.getData() != null) {
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					} else {
						logger.warn(WARN_MSG_USER_VERIFICATION_INVALID_REQUEST_CLIENT_ID_IS_NOT_VALID);
						return new ResponseEntity<LoansResponse>(
								new LoansResponse(CommonUtils.CLIENT_ID_IS_NOT_VALID, HttpStatus.BAD_REQUEST.value()),
								HttpStatus.OK);
					}
				} catch (Exception e) {
					logger.error(ERROR_MSG_USER_VERIFICATION_INVALID_REQUEST_SOMETHING_WENT_WRONG,e);
					return new ResponseEntity<LoansResponse>(
							new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
							HttpStatus.OK);
				}
			} else {
				if (CommonUtils.UserType.SERVICE_PROVIDER == userType) {
					userType = CommonUtils.UserType.SERVICE_PROVIDER;
				} else if (CommonUtils.UserType.NETWORK_PARTNER == userType) {
					userType = CommonUtils.UserType.NETWORK_PARTNER;
				}
			}

		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
		}

		if (CommonUtils.isObjectNullOrEmpty(toApplicationId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		} else {
			WorkingCapitalPrimaryViewResponse workingCapitalPrimaryViewResponse = null;
			try {
				workingCapitalPrimaryViewResponse = workingCapitalPrimaryViewService
						.getWorkingCapitalPrimaryViewDetails(toApplicationId, userType, userId);
				if (!CommonUtils.isObjectNullOrEmpty(workingCapitalPrimaryViewResponse)) {
					loansResponse.setData(workingCapitalPrimaryViewResponse);
					loansResponse.setMessage(WORKING_CAPITAL_PRIMARY_DETAILS);
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Working Capital final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(workingCapitalPrimaryViewResponse);
				loansResponse.setMessage(CommonUtils.SOMETHING_WENT_WRONG);
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/TermLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfTermLoan(
			@PathVariable(value = "toApplicationId") Long toApplicationId,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		LoansResponse loansResponse = new LoansResponse();

		// get user id from http servlet request
		Long userId = null;
		Integer userType = null;

		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if (!CommonUtils.isObjectNullOrEmpty(clientId)) {
				// MEANS FS, FP VIEW
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if (response != null && response.getData() != null) {
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					} else {
						logger.warn(WARN_MSG_USER_VERIFICATION_INVALID_REQUEST_CLIENT_ID_IS_NOT_VALID);
						return new ResponseEntity<LoansResponse>(
								new LoansResponse(CommonUtils.CLIENT_ID_IS_NOT_VALID, HttpStatus.BAD_REQUEST.value()),
								HttpStatus.OK);
					}
				} catch (Exception e) {
					logger.error(ERROR_MSG_USER_VERIFICATION_INVALID_REQUEST_SOMETHING_WENT_WRONG,e);
					return new ResponseEntity<LoansResponse>(
							new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
							HttpStatus.OK);
				}
			} else {
				if (CommonUtils.UserType.SERVICE_PROVIDER == userType) {
					userType = CommonUtils.UserType.SERVICE_PROVIDER;
				} else if (CommonUtils.UserType.NETWORK_PARTNER == userType) {
					userType = CommonUtils.UserType.NETWORK_PARTNER;
				}
			}

		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
		}

		if (toApplicationId == null) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		} else {
			TermLoanPrimaryViewResponse termLoanPrimaryViewResponse = null;
			try {
				termLoanPrimaryViewResponse = termLoanPrimaryViewService.getTermLoanPrimaryViewDetails(toApplicationId,
						userType, userId);
				if (!CommonUtils.isObjectNullOrEmpty(termLoanPrimaryViewResponse)) {
					loansResponse.setData(termLoanPrimaryViewResponse);
					loansResponse.setMessage(WORKING_CAPITAL_PRIMARY_DETAILS);
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Term Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(termLoanPrimaryViewResponse);
				loansResponse.setMessage(CommonUtils.SOMETHING_WENT_WRONG);
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

	// UNSECURED LOAN

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/UnsecuredLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfUnsecuredLoan(
			@PathVariable(value = "toApplicationId") Long toApplicationId,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		LoansResponse loansResponse = new LoansResponse();

		// get user id from http servlet request
		Long userId = null;
		Integer userType = null;

		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if (!CommonUtils.isObjectNullOrEmpty(clientId)) {
				// MEANS FS, FP VIEW
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if (response != null && response.getData() != null) {
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					} else {
						logger.warn(WARN_MSG_USER_VERIFICATION_INVALID_REQUEST_CLIENT_ID_IS_NOT_VALID);
						return new ResponseEntity<LoansResponse>(
								new LoansResponse(CommonUtils.CLIENT_ID_IS_NOT_VALID, HttpStatus.BAD_REQUEST.value()),
								HttpStatus.OK);
					}
				} catch (Exception e) {
					logger.error(ERROR_MSG_USER_VERIFICATION_INVALID_REQUEST_SOMETHING_WENT_WRONG,e);
					return new ResponseEntity<LoansResponse>(
							new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
							HttpStatus.OK);
				}
			} else {
				if (CommonUtils.UserType.SERVICE_PROVIDER == userType) {
					userType = CommonUtils.UserType.SERVICE_PROVIDER;
				} else if (CommonUtils.UserType.NETWORK_PARTNER == userType) {
					userType = CommonUtils.UserType.NETWORK_PARTNER;
				}
			}

		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
		}

		if (toApplicationId == null) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		} else {
			UnsecuredLoanPrimaryViewResponse unsecuredLoanPrimaryViewResponse = null;
			try {
				unsecuredLoanPrimaryViewResponse = unsecuredLoanPrimaryViewService
						.getUnsecuredLoanPrimaryViewDetails(toApplicationId, userType, userId);
				if (!CommonUtils.isObjectNullOrEmpty(unsecuredLoanPrimaryViewResponse)) {
					loansResponse.setData(unsecuredLoanPrimaryViewResponse);
					loansResponse.setMessage(WORKING_CAPITAL_PRIMARY_DETAILS);
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Term Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(unsecuredLoanPrimaryViewResponse);
				loansResponse.setMessage(CommonUtils.SOMETHING_WENT_WRONG);
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

	// -----------corporate Common
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/Corporate/{applicationId}/{proposalId}")    // @GetMapping(value = "/Corporate/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfCorporateCommon(@PathVariable(value = "applicationId") Long applicationId,
			@PathVariable(value = "proposalId") Long proposalId,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		logger.info("Into /Corporate/{proposalId} and proposalId is==>{}" , proposalId);
		LoansResponse loansResponse = new LoansResponse();

		// get user id from http servlet request
		Long userId = null;
		Integer userType = null;
		if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
			userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
		}

		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if (!CommonUtils.isObjectNullOrEmpty(clientId) && userType != CommonUtils.UserType.FUND_PROVIDER) {
				// MEANS FS, FP VIEW
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if (response != null && response.getData() != null) {
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					} else {
						logger.warn(WARN_MSG_USER_VERIFICATION_INVALID_REQUEST_CLIENT_ID_IS_NOT_VALID);
						return new ResponseEntity<LoansResponse>(
								new LoansResponse(CommonUtils.CLIENT_ID_IS_NOT_VALID, HttpStatus.BAD_REQUEST.value()),
								HttpStatus.OK);
					}
				} catch (Exception e) {
					logger.error(ERROR_MSG_USER_VERIFICATION_INVALID_REQUEST_SOMETHING_WENT_WRONG,e);
					return new ResponseEntity<LoansResponse>(
							new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
							HttpStatus.OK);
				}
			} else {
				if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
					userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
				}
				if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
					userId = ((Long) request.getAttribute(CommonUtils.USER_ID));
				}
				/*
				 * if(CommonUtils.UserType.SERVICE_PROVIDER == userType){ userType =
				 * CommonUtils.UserType.SERVICE_PROVIDER; }else
				 * if(CommonUtils.UserType.NETWORK_PARTNER == userType){ userType =
				 * CommonUtils.UserType.NETWORK_PARTNER; }
				 */
			}

		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
		}

		if (CommonUtils.isObjectNullOrEmpty(proposalId)) {
            logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, proposalId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		} else {
			CorporatePrimaryViewResponse corporatePrimaryViewResponse = null;
			try {
				logger.info("proposalId==>{} ,userType==>{} ,userId==>{}" , proposalId , userType , userId);
				corporatePrimaryViewResponse = corporatePrimaryViewService.getCorporatePrimaryViewDetails(applicationId,proposalId,userType, userId);
				if (!CommonUtils.isObjectNullOrEmpty(corporatePrimaryViewResponse)) {
					logger.info("Response is==>{}" , corporatePrimaryViewResponse.toString());
					loansResponse.setData(corporatePrimaryViewResponse);
					loansResponse.setMessage("Corporate Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Corporate final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				logger.error("Error/Exception occurred Into /Corporate/{applicationId}/{proposalId}..Error==>{}",e);
				loansResponse.setData(corporatePrimaryViewResponse);
				loansResponse.setMessage(CommonUtils.SOMETHING_WENT_WRONG);
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

	//CURRENTLY COMMENTED THE CODDE FOR MULTIPLE BANKS
	 
	@RequestMapping(value = "/sendPrimaryTeaserViewNotification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void primaryTeaserViewNotification(@RequestBody ProposalMappingRequest request,
			HttpServletRequest httpRequest, @RequestParam(value = "clientId", required = false) Long clientId,
			@RequestParam(value = "clientUserType", required = false) Long clientUserType) throws Exception {

		Long fromUserId = null;
		Long fromUserTypeId = null;
		Long loginUserType = null;
		if(httpRequest.getAttribute(CommonUtils.USER_TYPE) != null) {
			loginUserType = Long.valueOf(httpRequest.getAttribute(CommonUtils.USER_TYPE).toString());
		}

		if (CommonDocumentUtils.isThisClientApplication(httpRequest) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
			fromUserId = clientId;
			fromUserTypeId = clientUserType;
		} else {
			fromUserId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
			fromUserTypeId = loginUserType;
		}
		if(request.getUserId() != null) {
			fromUserId = request.getUserId();
		}
		
		Long applicationId = request.getApplicationId();
		Long notificationId;

		notificationId = NotificationAlias.SYS_FP_VIEW;
		if(applicationId != null) {
		
			try {
				Object[] userDetails = commonRepository.getUserDetailsByApplicationId(applicationId);
				CommonRequest req = notificationService.extractArrayToCommonRequest(userDetails);
				logger.info(""+Arrays.toString(userDetails));
				Long loanType = null;
				if(req.getLoanTypeId() != null) {
					loanType = Long.valueOf(req.getLoanTypeId());
				}
				Object subject=EmailSubjectAlias.EMAIL_PRIMARY_VIEW_FS.getSubjectId();
		        if (!CommonUtils.isObjectNullOrEmpty(req.getEmailId())) {
		        	Integer viewedTeaser = commonRepository.getViewedTeaser(req.getEmailId());
					if(viewedTeaser != null && viewedTeaser > 0 && req.getUserId() != null) {
						notificationService.sendViewNotification(String.valueOf(req.getUserId()), fromUserId, fromUserTypeId, notificationId,
								applicationId, req.getFpProductId(), NotificationTemplate.PRIMARY_VIEW, loanType,subject,req.getLoanTypeId(),req.getUserOrgId());
					}
				}	
				
			}catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
		}
	}

	/*CURRENTLY COMMENTED THE CODDE FOR MULTIPLE BANKS
	 *
	 * @RequestMapping(value = "/sendFinalTeaserViewNotification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void finalTeaserViewNotification(@RequestBody ProposalMappingRequest request, HttpServletRequest httpRequest,
			@RequestParam(value = "clientId", required = false) Long clientId,
			@RequestParam(value = "clientUserType", required = false) Long clientUserType) throws LoansException {

		// request must not be null

		Long fromUserId = null;
		Long fromUserTypeId = null;
		Long loginUserType = Long.valueOf(httpRequest.getAttribute(CommonUtils.USER_TYPE).toString());

		if (CommonDocumentUtils.isThisClientApplication(httpRequest) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
			fromUserId = clientId;
			fromUserTypeId = clientUserType;
		} else {
			fromUserId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
			fromUserTypeId = loginUserType;
		}

		Long applicationId = request.getApplicationId();
		Long fpProductId = request.getFpProductId();
		String toUserId = null;
		Long notificationId = null;

		if (CommonUtils.UserType.FUND_PROVIDER == fromUserTypeId) {
			Object[] o = loanApplicationService.getApplicationDetailsById(applicationId);
			toUserId = o[0].toString();
			notificationId = NotificationAlias.SYS_FP_VIEWSEC;
		} else if (CommonUtils.UserType.FUND_SEEKER == fromUserTypeId) {
			Object[] o = loanApplicationService.getApplicationDetailsById(applicationId);
			toUserId = o[0].toString();
			notificationId = NotificationAlias.SYS_FS_VIEWSEC;
		}

		try {
			if (applicationId != null) {
				notificationService.sendViewNotification(toUserId, fromUserId, fromUserTypeId, notificationId,
						applicationId, fpProductId, NotificationTemplate.FINAL_VIEW, loginUserType);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
	}*/

	/*CURRENTLY COMMENTED THE CODDE FOR MULTIPLE BANKS
	 *
	 * @RequestMapping(value = "/finalTeaserReqViewNotification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void finalTeaserReqViewNotification(@RequestBody ProposalMappingRequest request,
			HttpServletRequest httpRequest, @RequestParam(value = "clientId", required = false) Long clientId,
			@RequestParam(value = "clientUserType", required = false) Long clientUserType) throws LoansException {

		// request must not be null

		Long fromUserId = null;
		Long fromUserTypeId = null;
		Long loginUserType = Long.valueOf(httpRequest.getAttribute(CommonUtils.USER_TYPE).toString());

		if (CommonDocumentUtils.isThisClientApplication(httpRequest) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
			fromUserId = clientId;
			fromUserTypeId = clientUserType;
		} else {
			fromUserId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
			fromUserTypeId = loginUserType;
		}

		Long applicationId = request.getApplicationId();
		Long fpProductId = request.getFpProductId();
		String toUserId = null;
		Long notificationId = null;

		if (CommonUtils.UserType.FUND_PROVIDER == fromUserTypeId) {
			Object[] o = loanApplicationService.getApplicationDetailsById(applicationId);
			toUserId = o[0].toString();
			notificationId = NotificationAlias.SYS_FP_REQ_VIEWSEC;
		} else if (CommonUtils.UserType.FUND_SEEKER == fromUserTypeId) {
			Object[] o = loanApplicationService.getApplicationDetailsById(applicationId);
			toUserId = o[0].toString();
			notificationId = NotificationAlias.SYS_FS_REQ_VIEWSEC;
		}

		try {

			notificationService.sendViewNotification(toUserId, fromUserId, fromUserTypeId, notificationId,
					applicationId, fpProductId, null, loginUserType);

		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
	}*/

	@PostMapping(value = "/getCubictreeReport")
	public @ResponseBody ResponseEntity<LoansResponse> getCubictreeReport(@RequestBody Long applicationId) {
		logger.info("In PL View Ctrl of applicationId==>{} " , applicationId);
		 LoansResponse cubictreeReport = new LoansResponse();
		try {
			 cubictreeReport = corporatePrimaryViewService.getCubictreeReport(applicationId);
			
		}catch (Exception e) {
			logger.error("Exception {}",e);
		}
		return new ResponseEntity<LoansResponse>(cubictreeReport, HttpStatus.OK);
			
	}
	
}
