package com.capitaworld.service.loans.controller.fundseeker;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.users.model.UserResponse;

@RestController
@RequestMapping("/loan_application")
public class LoanApplicationController {

	private static final Logger logger = LoggerFactory.getLogger(LoanApplicationController.class);

	@Autowired
	private LoanApplicationService loanApplicationService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody FrameRequest commonRequest, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			// request must not be null
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			commonRequest.setUserId(userId);
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))) {
				commonRequest.setClientId(clientId);
			}
			loanApplicationService.saveOrUpdate(commonRequest, userId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving applicationRequest Details==>", e);
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
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))) {
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
			LoanApplicationRequest response = loanApplicationService.get(id, userId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
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
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))) {
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
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
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
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))) {
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
			loanApplicationService.inActive(id, userId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Inactivated", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getListByUserIdList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getListByUseIdList(@RequestBody Long userId, HttpServletRequest request) {
		// request must not be null
		try {

			if (userId == null) {
				logger.warn("UserId Require to get Loan Applications Details ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			List<LoanApplicationDetailsForSp> response = loanApplicationService.getLoanDetailsByUserIdList(userId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/set_last_application_access/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> setLastApplicationAccess(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		// request must not be null
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (applicationId == null) {
				logger.warn("applicationId Require to Set last Access Profile ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			UserResponse userResponse = loanApplicationService.setLastAccessApplication(applicationId, userId);
			if (userResponse != null && userResponse.getStatus() == 200) {
				LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
				loansResponse.setData(userResponse);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
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
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (applicationId == null) {
				logger.warn("applicationId Require to get Product Id ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			Integer productId = loanApplicationService.getProductIdByApplicationId(applicationId, userId);
			if (!CommonUtils.isObjectNullOrEmpty(productId)) {
				LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
				loansResponse.setId(productId.longValue());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while Getting Product Id by Application Id==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUserIdByApplicationId", method = RequestMethod.POST)
	public ResponseEntity<LoansResponse> getUserIdByApplicationId(HttpServletRequest request,
			@RequestBody Long applicationId) {

		try {

			if (applicationId == null) {
				logger.warn("applicationId Require to get Loan Applications Details ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse();

			Object[] response = loanApplicationService.getApplicationDetailsById(applicationId);
			if (!CommonUtils.isObjectListNull(response)) {
				loansResponse.setData(response[0]);
			}
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error while Getting Product Id by Application Id==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUserNameByApplicationId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUserNameByApplicationId(HttpServletRequest request,
			@RequestBody Long applicationId) {

		try {

			if (applicationId == null) {
				logger.warn("applicationId Require to get Loan Applications Details ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse();

			Object[] response = loanApplicationService.getApplicationDetailsById(applicationId);
			loansResponse.setData(response[1]);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error while Getting Product Id by Application Id==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/lock_primary/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> lockPrimary(@PathVariable("applicationId") Long applicationId,
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		try {
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn("applicationId Must not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			loanApplicationService.lockPrimary(applicationId, userId,true);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully updated", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
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
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn("applicationId Must not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			loanApplicationService.lockFinal(applicationId, userId, true);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully updated", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Locking final information==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/update_final_information_flag/{applicationId}/{flag}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateFinalInformationFlag(@PathVariable("applicationId") Long applicationId,
			@PathVariable("flag") Boolean flag, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error("Application id must not be null.");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			loanApplicationService.updateFinalCommonInformation(applicationId, userId, flag);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
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
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error("Application id must not be null.");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse("Success Result", HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isProfileAndPrimaryDetailFilled(applicationId, userId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/primary_locked/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isPrimaryLocked(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error("Application id must not be null.");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse("Success Result", HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isPrimaryLocked(applicationId, userId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
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
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error("Application id must not be null.");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse("Success Result", HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isFinalDetailFilled(applicationId, userId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/final_locked/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isFinalLocked(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error("Application id must not be null.");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse("Success Result", HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isFinalLocked(applicationId, userId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
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
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error("Application id must not be null.");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			JSONObject json = new JSONObject();
			json.put("isPrimaryLock", loanApplicationService.isPrimaryLocked(applicationId, userId));
			json.put("isFinalLock", loanApplicationService.isFinalLocked(applicationId, userId));
			LoansResponse loansResponse = new LoansResponse("Success Result", HttpStatus.OK.value());
			loansResponse.setData(json);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get_selfview_primary_locked/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getSelfViewAndPrimaryLocked(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		try {
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error("Application id must not be null.");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse("Success Result", HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.getSelfViewAndPrimaryLocked(applicationId, userId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
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
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (applicationId == null || userId == null) {
				logger.warn("ID And UserId Require to get Primary Working Details ==>" + applicationId
						+ " and UserId ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.getCurrencyAndDenomination(applicationId, userId));
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
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (applicationId == null || userId == null) {
				logger.warn("ID And UserId Require to get Primary Working Details ==>" + applicationId
						+ " and UserId ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(loanApplicationService.isAllowToMoveAhead(applicationId, userId, tabType,
					coAppllicantOrGuarantorId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Primary Working Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
