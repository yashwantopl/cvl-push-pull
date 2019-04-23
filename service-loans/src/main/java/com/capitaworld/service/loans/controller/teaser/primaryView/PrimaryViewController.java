package com.capitaworld.service.loans.controller.teaser.primaryView;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import com.capitaworld.service.loans.exceptions.LoansException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.CarLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.CorporatePrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.HomeLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.LapPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.NtbPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.PlTeaserViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.TermLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.UnsecuredLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.WorkingCapitalPrimaryViewResponse;
import com.capitaworld.service.loans.service.common.NotificationService;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.teaser.primaryview.CarLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.CorporatePrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.HomeLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.LapPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.NtbTeaserViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.PersonalLoansViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.PlTeaserViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.TermLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.UnsecuredLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.WorkingCapitalPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonNotificationUtils.NotificationTemplate;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UserTypeRequest;
import com.capitaworld.service.users.model.UsersRequest;

@RestController
@RequestMapping("/PrimaryView")
public class PrimaryViewController {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryViewController.class);
	private static final String USER_ID_MSG = "userId : ";
	private static final String USER_TYPE_MSG = " userType : ";

	@Autowired
	private HomeLoanPrimaryViewService homeLoanPrimaryViewService;

	@Autowired
	private PersonalLoansViewService personalLoansViewService;

	@Autowired
	private CarLoanPrimaryViewService carLoanPrimaryViewService;

	@Autowired
	private LapPrimaryViewService lapPrimaryService;

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
	private LoanApplicationService loanApplicationService;

	@Autowired
	private ProductMasterService productMasterService;

	@Autowired
	private UsersClient usersClient;

	@Autowired
	private NtbTeaserViewService ntbTeaserViewService;

	@Autowired
	private PlTeaserViewService plTeaserViewService;

	private static final String WARN_MSG_USER_VERIFICATION_INVALID_REQUEST_CLIENT_ID_IS_NOT_VALID = "user_verification, Invalid Request... Client Id is not valid : ";
	private static final String ERROR_MSG_USER_VERIFICATION_INVALID_REQUEST_SOMETHING_WENT_WRONG = "user_verification, Invalid Request... Something went wrong : ";
	private static final String WORKING_CAPITAL_PRIMARY_DETAILS = "Working Capital Primary Details";
	private static final String MSG_USER_ID = "userId : ";
	private static final String MSG_USER_TYPE = " userType : ";

	@GetMapping(value = "/HomeLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewHomeLoan(
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

			logger.debug(MSG_USER_ID + userId + MSG_USER_TYPE + userType);

		if (CommonUtils.isObjectNullOrEmpty(toApplicationId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		} else {
			HomeLoanPrimaryViewResponse homeLoanPrimaryViewResponse = null;
			try {
				homeLoanPrimaryViewResponse = homeLoanPrimaryViewService.getHomeLoanPrimaryViewDetails(toApplicationId);
				if (!CommonUtils.isObjectNullOrEmpty(homeLoanPrimaryViewResponse)) {
					loansResponse.setData(homeLoanPrimaryViewResponse);
					loansResponse.setMessage("Home Loan Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Home Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setMessage(CommonUtils.SOMETHING_WENT_WRONG + e.getMessage());
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

	@GetMapping(value = "/PersonalLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfPersonalLoans(
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
					logger.warn(ERROR_MSG_USER_VERIFICATION_INVALID_REQUEST_SOMETHING_WENT_WRONG,e);
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

		logger.info(USER_ID_MSG + userId + USER_TYPE_MSG + userType);

		if (CommonUtils.isObjectNullOrEmpty(toApplicationId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		} else {
			RetailPrimaryViewResponse personalLoansPrimaryViewResponse = null;
			try {
				personalLoansPrimaryViewResponse = personalLoansViewService
						.getPersonalLoansPrimaryViewDetails(toApplicationId);
				if (!CommonUtils.isObjectNullOrEmpty(personalLoansPrimaryViewResponse)) {
					loansResponse.setData(personalLoansPrimaryViewResponse);
					loansResponse.setMessage("Personal Loans Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Personal Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(personalLoansPrimaryViewResponse);
				loansResponse.setMessage(e.getMessage());
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

	@GetMapping(value = "/CarLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfCarLoan(
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

		logger.info(USER_ID_MSG + userId + USER_TYPE_MSG + userType);

		if (CommonUtils.isObjectNullOrEmpty(toApplicationId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		} else {
			CarLoanPrimaryViewResponse carLoanPrimaryViewResponse = null;
			try {
				carLoanPrimaryViewResponse = carLoanPrimaryViewService.getCarLoanPrimaryViewDetails(toApplicationId);
				if (!CommonUtils.isObjectNullOrEmpty(carLoanPrimaryViewResponse)) {
					loansResponse.setData(carLoanPrimaryViewResponse);
					loansResponse.setMessage("Car Loan Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Car Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(carLoanPrimaryViewResponse);
				loansResponse.setMessage(e.getMessage());
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

	@GetMapping(value = "/LapLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfLap(
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

		logger.info(USER_ID_MSG + userId + USER_TYPE_MSG + userType);

		if (CommonUtils.isObjectNullOrEmpty(toApplicationId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		} else {
			LapPrimaryViewResponse lapPrimaryViewResponse = null;
			try {
				lapPrimaryViewResponse = lapPrimaryService.getLapPrimaryViewDetails(toApplicationId);
				if (!CommonUtils.isObjectNullOrEmpty(lapPrimaryViewResponse)) {
					loansResponse.setData(lapPrimaryViewResponse);
					loansResponse.setMessage("LAP Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for LAP Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(lapPrimaryViewResponse);
				loansResponse.setMessage(CommonUtils.SOMETHING_WENT_WRONG);
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

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

	// NTB PRIMARY VIEW
	@GetMapping(value = "/NtbTeaserView/{toApplicationId}/{productMappingId}/{isFinalView}")
	public @ResponseBody ResponseEntity<LoansResponse> ntbViewOfCorporateCommon(
			@PathVariable(value = "toApplicationId") Long toApplicationId,
			@PathVariable(value = "productMappingId") Long productMappingId,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request,
			@PathVariable(value = "isFinalView") Boolean isFinalView) {

		logger.info("In NTB View Ctrl of applicationId" + toApplicationId + "productMappingId" + productMappingId);

		// GET USER ID AND USER TYPE
		Long userId = null;
		Integer userType = null;
		if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
			userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
		}
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if (!CommonUtils.isObjectNullOrEmpty(clientId) && userType != CommonUtils.UserType.FUND_PROVIDER) {
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
			}

		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
		}
		if (CommonUtils.isObjectNullOrEmpty(toApplicationId) || CommonUtils.isObjectNullOrEmpty(productMappingId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND + toApplicationId + productMappingId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		} else {
			LoansResponse loansResponse = new LoansResponse();
			NtbPrimaryViewResponse ntbPrimaryViewResponse = null;
			try {
				logger.info("GET NTB PRIMARY TEASER VIEW OF USER OF APPLICATION ID" + toApplicationId
						+ "PRODUCT MAPPING ID" + productMappingId + "USER TYPE" + userType + "USER ID" + userId);
				ntbPrimaryViewResponse = ntbTeaserViewService.getNtbTeaserViewDetails(toApplicationId, userType, userId,
						productMappingId, isFinalView);
				if (!CommonUtils.isObjectNullOrEmpty(ntbPrimaryViewResponse)) {
					logger.info("Response of Teaser View" + ntbPrimaryViewResponse.toString());
					loansResponse.setData(ntbPrimaryViewResponse);
					loansResponse.setMessage("Ntb Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Ntb final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(ntbPrimaryViewResponse);
				loansResponse.setMessage(CommonUtils.SOMETHING_WENT_WRONG);
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

	/* CURRENTLY COMMENTED THE CODDE FOR MULTIPLE BANKS
	 *
	 * @RequestMapping(value = "/sendPrimaryTeaserViewNotification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void primaryTeaserViewNotification(@RequestBody ProposalMappingRequest request,
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
		Long proposalId  =null;

		String toUserId = null;
		Long notificationId;

		if (CommonUtils.UserType.FUND_SEEKER == fromUserTypeId) {
			notificationId = NotificationAlias.SYS_FS_VIEW;
			Object[] o = productMasterService.getUserDetailsByPrductId(fpProductId);
			toUserId = o[0].toString();
		} else {
			Object[] o = loanApplicationService.getApplicationDetailsById(4479l); // PREVIOUS
			logger.info("THIS IS THE APPLICATION iD =====>"+applicationId);
			//Object[] o = loanApplicationService.getApplicationDetailsByProposalId(applicationId,proposalId); // NEW BASED ON PROPOSAL MAPPING ID =={} PENDING
			toUserId = o[0].toString();
			logger.info("=============>"+toUserId);
			notificationId = NotificationAlias.SYS_FP_VIEW;
		}

		try {

			notificationService.sendViewNotification(toUserId, fromUserId, fromUserTypeId, notificationId,
				applicationId, fpProductId, NotificationTemplate.PRIMARY_VIEW, loginUserType);

		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
	}*/

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

	// PL PRIMARY VIEW
	@GetMapping(value = "/PlTeaserView/{toApplicationId}/{productMappingId}/{isFinalView}")
	public @ResponseBody ResponseEntity<LoansResponse> plViewOfCorporateCommon(
			@PathVariable(value = "toApplicationId") Long toApplicationId,
			@PathVariable(value = "productMappingId") Long productMappingId,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request,
			@PathVariable(value = "isFinalView") Boolean isFinalView) {

		logger.info("In PL View Ctrl of applicationId" + toApplicationId + "productMappingId" + productMappingId);

		// GET USER ID AND USER TYPE
		Long userId = null;
		Integer userType = null;
		if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
			userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
		}
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if (!CommonUtils.isObjectNullOrEmpty(clientId) && userType != CommonUtils.UserType.FUND_PROVIDER) {
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
			}

		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = 1;// ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}
		if (CommonUtils.isObjectNullOrEmpty(toApplicationId) || CommonUtils.isObjectNullOrEmpty(productMappingId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND + toApplicationId + productMappingId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		} else {
			LoansResponse loansResponse = new LoansResponse();
			PlTeaserViewResponse plPrimaryViewResponse = null;
			try {
				logger.info("GET PL PRIMARY TEASER VIEW OF USER OF APPLICATION ID" + toApplicationId
						+ "PRODUCT MAPPING ID" + productMappingId + "USER TYPE" + userType + "USER ID" + userId);
				plPrimaryViewResponse = plTeaserViewService.getPlPrimaryViewDetails(toApplicationId, userType, userId,
						productMappingId, isFinalView);
				if (!CommonUtils.isObjectNullOrEmpty(plPrimaryViewResponse)) {
					logger.info("Response of Teaser View" + plPrimaryViewResponse.toString());
					loansResponse.setData(plPrimaryViewResponse);
					loansResponse.setMessage("PL Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Pl final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(plPrimaryViewResponse);
				loansResponse.setMessage(CommonUtils.SOMETHING_WENT_WRONG);
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}
	
	@GetMapping(value = "/PlTeaserView/{toApplicationId}/{productMappingId}/{isFinalView}/{proposalId}")
	public @ResponseBody ResponseEntity<LoansResponse> plViewOfCorporateCommonByProposalId(
			@PathVariable(value = "toApplicationId") Long toApplicationId,
			@PathVariable(value = "productMappingId") Long productMappingId,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request,
			@PathVariable(value = "isFinalView") Boolean isFinalView,
			@PathVariable("proposalId") Long proposalId) {

		logger.info("In PL View Ctrl of applicationId" + toApplicationId + "productMappingId" + productMappingId);

		// GET USER ID AND USER TYPE
		Long userId = null;
		Integer userType = null;
		if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE))) {
			userType = (Integer) request.getAttribute(CommonUtils.USER_TYPE);
		}
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if (!CommonUtils.isObjectNullOrEmpty(clientId) && userType != CommonUtils.UserType.FUND_PROVIDER) {
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
			}

		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = 1;// ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}
		if (CommonUtils.isObjectNullOrEmpty(toApplicationId) || CommonUtils.isObjectNullOrEmpty(productMappingId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND + toApplicationId + productMappingId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		} else {
			LoansResponse loansResponse = new LoansResponse();
			PlTeaserViewResponse plPrimaryViewResponse = null;
			try {
				logger.info("GET PL PRIMARY TEASER VIEW OF USER OF APPLICATION ID" + toApplicationId
						+ "PRODUCT MAPPING ID" + productMappingId + "USER TYPE" + userType + "USER ID" + userId);
				plPrimaryViewResponse = plTeaserViewService.getPlPrimaryViewDetailsByProposalId(toApplicationId, userType, userId,
						productMappingId, isFinalView, proposalId);
				if (!CommonUtils.isObjectNullOrEmpty(plPrimaryViewResponse)) {
					logger.info("Response of Teaser View" + plPrimaryViewResponse.toString());
					loansResponse.setData(plPrimaryViewResponse);
					loansResponse.setMessage("PL Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Pl final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(plPrimaryViewResponse);
				loansResponse.setMessage(CommonUtils.SOMETHING_WENT_WRONG);
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

}
