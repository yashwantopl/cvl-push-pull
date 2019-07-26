package com.capitaworld.service.loans.controller.fundseeker;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.capitaworld.service.loans.model.micro_finance.*;
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
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.fundseeker.microfinance.MfiApplicationService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/mfi")
public class MFIApplicationController {
	private static final Logger logger = LoggerFactory.getLogger(MFIApplicationController.class);

	@Autowired
	private MfiApplicationService mfiApplicationService;

    /**
     * save Aadhar detail For create new Application in MFI Application
     * @param aadharDetailsReq
     * @param request
     * @return
     */
    @PostMapping(value = "/saveAdharDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveAdharDetails(@RequestBody AadharDetailsReq aadharDetailsReq, HttpServletRequest request) {
        try {
            // request must not be null
            CommonDocumentUtils.startHook(logger, "save aadhar details");
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
            if (userId == null) {
                logger.warn("userId  can not be empty ==>" + userId);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            aadharDetailsReq.setUserId(userId);
            aadharDetailsReq.setOrgId(userOrgId);
            AadharDetailsReq aadharDetails = mfiApplicationService.saveOrUpdateAadharDetails(aadharDetailsReq);
            CommonDocumentUtils.endHook(logger, "save");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value(),aadharDetails),HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Adhar Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getAadharDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getAadharDetails(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		try {
			AadharDetailsReq aadharDetailsByAppId = mfiApplicationService.getAadharDetailsByAppId(applicationId);
			CommonDocumentUtils.endHook(logger, "Get Aadhar");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Aadhar details.",
					HttpStatus.OK.value(), aadharDetailsByAppId), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get Adhar Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "/savePersonalDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> savePersonalDetails(@RequestBody PersonalDetailsReq personalDetailsReq,
			HttpServletRequest request) {
		try {
			logger.info("service call savepersonalDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			mfiApplicationService.saveOrUpdatePersonalDetails(personalDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Personal Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getPersonalDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getPersonalDetails(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		try {
			PersonalDetailsReq personalrDetailsByAppId = mfiApplicationService.getPersonalDetailsAppId(applicationId);
			CommonDocumentUtils.endHook(logger, "Get Personal Details");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Personal details.",
					HttpStatus.OK.value(), personalrDetailsByAppId), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get Adhar Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "/saveProjectDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveProjectDetails(@RequestBody ProjectDetailsReq projectDetailsReq,
			HttpServletRequest request) {
		try {
			logger.info("service call saveProjectDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			mfiApplicationService.saveOrUpdateProjectDetails(projectDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Project Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getMfiDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getMfiApplicantIncomeDetails(
			@PathVariable("applicationId") Long applicationId) {
		logger.info("ENTER HERE MFI GET APPLIANT DETAILS====={}======{}======>" + applicationId);
		try {
			List<MfiReqResponse> list = mfiApplicationService.getMfiApplicantDetails(applicationId);
			logger.info("GET DETAIILS FOR MFI APPLICANTS DETAILS PURPOSE----->" + list.size());
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("SUCCESSFULLY RETRIVE MFI RESPONSE", HttpStatus.OK.value(), list), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("THROW EXCEPTION WHILE GETTING MFI DETAILS ====={}======{}====>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/saveBankDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveBankDetails(@RequestBody MfiBankDetailsReq mfiBankDetailsReq,
			HttpServletRequest request) {
		try {
			logger.info("service call saveProjectDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			mfiApplicationService.saveOrUpdateBankDetails(mfiBankDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Project Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getBankDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getBankDetails(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Bank Details");

			MfiBankDetailsReq mfiBankDetailsReq = mfiApplicationService.fetchBankDetail(applicationId);
			CommonDocumentUtils.endHook(logger, "Get Bank Details");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Successfully Fetch Bank details.", HttpStatus.OK.value(), mfiBankDetailsReq),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get Bank Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getAllApplicantDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getAllApplicantDetails(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Bank Details");

			List<MfiApplicantDetailsReq> allApplicantDetails = mfiApplicationService
					.getAllApplicantDetails(applicationId);
			CommonDocumentUtils.endHook(logger, "Get Bank Details");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch All Applicant details.",
					HttpStatus.OK.value(), allApplicantDetails), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get All applicant Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getProjectDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getProjectDetails(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Project Details");

			ProjectDetailsReq projectDetailsByAppId = mfiApplicationService.getProjectDetailsAppId(applicationId);
			CommonDocumentUtils.endHook(logger, "Get Project Details");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Project details.",
					HttpStatus.OK.value(), projectDetailsByAppId), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get Project Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "/saveIncomeExpenditureDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveIncomeExpenditureDetails(
			@RequestBody MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq, HttpServletRequest request) {
		try {
			logger.info("service call saveIncomeExpenditureDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			mfiApplicationService.saveOrUpdateIncomeExpenditureDetails(mfiIncomeAndExpenditureReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Income Expenditure Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getIncomeExpenditureDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getIncomeExpenditureDetails(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Income Expenditure Details");

			MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq = mfiApplicationService
					.getIncomeExpenditureDetailsAppId(applicationId);
			CommonDocumentUtils.endHook(logger, "Get Income Expenditure Details");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Income Expenditure details.",
					HttpStatus.OK.value(), mfiIncomeAndExpenditureReq), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get Income Expenditure Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "/saveAssetsLiabilityDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveAssetsLiabilityDetails(
			@RequestBody MfiAssetsDetailsReq mfiAssetsDetailsReq, HttpServletRequest request) {
		try {
			logger.info("service call saveAssetsLiabilityDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			mfiApplicationService.saveOrUpdateAssetsLiabilityDetails(mfiAssetsDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Income Expenditure Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getAssetsLiabilityDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getAssetsLiabilityDetails(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Assets Liability Details");

			MfiAssetsDetailsReq mfiAssetsDetailsReq = mfiApplicationService
					.getAssetsLiabilityDetailsAppId(applicationId);
			CommonDocumentUtils.endHook(logger, "Get Assets Liability Details");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Assets Liability details.",
					HttpStatus.OK.value(), mfiAssetsDetailsReq), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get Assets liability Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	@GetMapping(value = "/findAllFlag/{applicationId}/{type}")
	public ResponseEntity<LoansResponse> findAllFlag(@PathVariable("applicationId") Long applicationId,@PathVariable("type") Integer type,
			HttpServletRequest request) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Assets Liability Details");

			FlagCheckMFI allFlag = mfiApplicationService.findAllFlag(applicationId, type);
			CommonDocumentUtils.endHook(logger, "Get Flag Details");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Flag details.",
					HttpStatus.OK.value(), allFlag), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get Flag Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

}
