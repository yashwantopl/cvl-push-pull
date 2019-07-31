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
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiApplicantDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiAssetsDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiBankDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeAndExpenditureReq;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiReqResponse;
import com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.ProjectDetailsReq;
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
	 * 
	 * @param aadharDetailsReq
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/saveAdharDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveAdharDetails(@RequestBody AadharDetailsReq aadharDetailsReq,
			HttpServletRequest request) {
		try {
			// request must not be null
			CommonDocumentUtils.startHook(logger, "save aadhar details");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			aadharDetailsReq.setUserId(userId);
			aadharDetailsReq.setOrgId(userOrgId);
			AadharDetailsReq aadharDetails = mfiApplicationService.saveOrUpdateAadharDetails(aadharDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse(aadharDetails.getMessage(), HttpStatus.OK.value(), aadharDetails), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Adhar Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getAadharDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getAadharDetails(@PathVariable("applicationId") Long applicationId) {
		try {
			CommonDocumentUtils.startHook(logger, "get aadhar details");
			if(applicationId == null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
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
	public ResponseEntity<LoansResponse> savePersonalDetails(@RequestBody PersonalDetailsReq personalDetailsReq,HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "save personal details");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
            if(personalDetailsReq.getId() == null){
                logger.warn("Id  can not be empty ==>");
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
			Object personalDetails = mfiApplicationService.saveOrUpdatePersonalDetails(personalDetailsReq);
			if(personalDetails instanceof Boolean) {
				boolean personalInfo = (Boolean) personalDetails;
				CommonDocumentUtils.endHook(logger, "save personal details");
				if(personalInfo) {
					return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
							HttpStatus.OK);
				} else {
					return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.OK.value()),
							HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(personalDetails.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Error while saving save Personal Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getPersonalDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getPersonalDetails(@PathVariable("applicationId") Long applicationId) {
		try {
			CommonDocumentUtils.startHook(logger, "get personal details");
			if(applicationId == null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
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
	public ResponseEntity<LoansResponse> saveProjectDetails(@RequestBody ProjectDetailsReq projectDetailsReq,HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "save project details");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
            if(projectDetailsReq.getId() == null){
                logger.warn("Id  can not be empty ==>");
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
			Object projectDetails = mfiApplicationService.saveOrUpdateProjectDetails(projectDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			if(projectDetails instanceof Boolean) {
				boolean projectInfo = (Boolean) projectDetails;
				if(projectInfo) {
					return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
							HttpStatus.OK);
				} else {
					return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.OK.value()),
							HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(projectDetails.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Error while saving save Project Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getMfiDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getMfiApplicantIncomeDetails(@PathVariable("applicationId") Long applicationId) {
		logger.info("ENTER HERE MFI GET APPLIANT DETAILS====={}======{}======>" + applicationId);
		try {
			if(applicationId == null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
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
			CommonDocumentUtils.startHook(logger, "save bank details");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
            if(mfiBankDetailsReq.getApplicationId() == null){
                logger.warn("applicationId  can not be empty ==>");
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
			Object bankDetails = mfiApplicationService.saveOrUpdateBankDetails(mfiBankDetailsReq);
			CommonDocumentUtils.endHook(logger, "save bank details");
			if(bankDetails instanceof Boolean){
				boolean bankDetail = (Boolean) bankDetails;
				if(bankDetail) {
					return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
							HttpStatus.OK);
				} else {
					return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.OK.value()),
							HttpStatus.OK);
				}
			} else{
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(bankDetails.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}


		} catch (Exception e) {
			logger.error("Error while saving save bank Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getBankDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getBankDetails(@PathVariable("applicationId") Long applicationId) {
		try {
			CommonDocumentUtils.startHook(logger, "Get Bank Details");
			if(applicationId == null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			MfiBankDetailsReq mfiBankDetailsReq = mfiApplicationService.fetchBankDetail(applicationId);
			CommonDocumentUtils.endHook(logger, "Get Bank Details");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Bank details.", HttpStatus.OK.value(), mfiBankDetailsReq),
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
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch All Applicant details.",HttpStatus.OK.value(), allApplicantDetails), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get All applicant Details ==>", e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getProjectDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getProjectDetails(@PathVariable("applicationId") Long applicationId) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Project Details");
			if(applicationId == null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
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
			CommonDocumentUtils.startHook(logger, "save Income expenditure");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
            if(mfiIncomeAndExpenditureReq.getId() == null){
                logger.warn("Id  can not be empty ==>");
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
			Object expenditureDetails = mfiApplicationService.saveOrUpdateIncomeExpenditureDetails(mfiIncomeAndExpenditureReq);
			CommonDocumentUtils.endHook(logger, "save");
			if(expenditureDetails instanceof Boolean) {
				boolean expenditureDetail = (Boolean) expenditureDetails;
				if(expenditureDetail) {
					return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
							HttpStatus.OK);
				} else {
					return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.OK.value()),
							HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(expenditureDetails.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Error while saving save Income Expenditure Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getIncomeExpenditureDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getIncomeExpenditureDetails(@PathVariable("applicationId") Long applicationId) {
		try {
			CommonDocumentUtils.startHook(logger, "Get Income Expenditure Details");
			if(applicationId == null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq = mfiApplicationService.getIncomeExpenditureDetailsAppId(applicationId);
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
	public ResponseEntity<LoansResponse> saveAssetsLiabilityDetails(@RequestBody MfiAssetsDetailsReq mfiAssetsDetailsReq, HttpServletRequest request) {
		try {
			logger.info("service call saveAssetsLiabilityDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
            if(mfiAssetsDetailsReq.getId() == null || mfiAssetsDetailsReq.getApplicationId() == null){
                logger.warn("Id / application id  can not be empty ==>");
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
			boolean assetsLiabilityDetails = mfiApplicationService.saveOrUpdateAssetsLiabilityDetails(mfiAssetsDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			if(assetsLiabilityDetails){
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}


		} catch (Exception e) {
			logger.error("Error while saving save Assets and Liability Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getAssetsLiabilityDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getAssetsLiabilityDetails(@PathVariable("applicationId") Long applicationId) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Assets Liability Details");
			if(applicationId == null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
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
	public ResponseEntity<LoansResponse> findAllFlag(@PathVariable("applicationId") Long applicationId,@PathVariable("type") Integer type) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Assets Liability Details");
			if(applicationId == null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
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

	@PostMapping(value = "/saveLoanAssessmentDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveLoanAssessmentDetails(
			@RequestBody MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq, HttpServletRequest request) {
		try {
			logger.info("service call saveLoanAssessmentDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
            if(mfiLoanAssessmentDetailsReq.getId() == null){
                logger.warn("Id can not be empty ==>");
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
			Object loanAssessmentDetails = mfiApplicationService.saveOrUpdateLoanAssessmentDetails(mfiLoanAssessmentDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			if(loanAssessmentDetails instanceof Boolean) {
                boolean loanAssessmentDetail = (Boolean) loanAssessmentDetails;
                if(loanAssessmentDetail) {
                    return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.OK.value()),
                            HttpStatus.OK);
                }
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(loanAssessmentDetails.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Error while saving save Loan Assessment Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getLoanAssessmentDetails/{applicationId}")
	public ResponseEntity<LoansResponse> getLoanAssessmentDetails(@PathVariable("applicationId") Long applicationId) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Loan Assessment Details");
			if(applicationId == null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq = mfiApplicationService.getLoanAssessmentDetailsAppId(applicationId);
			CommonDocumentUtils.endHook(logger, "Get Loan Assessment Details");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Loan Assessment details.",
					HttpStatus.OK.value(), mfiLoanAssessmentDetailsReq), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get Loan Assessment Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	@GetMapping(value = "/getCashFlowAssesmentByAppId/{applicationId}/{type}")
	public ResponseEntity<LoansResponse> getCashFlowAssesmentByAppId(@PathVariable("applicationId") Long applicationId,@PathVariable("type") Integer type) {
		try {

			CommonDocumentUtils.startHook(logger, "getCashFlowAssesmentByAppId");
			if(applicationId == null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

		MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq = mfiApplicationService.getCashFlowAssesmentByAppId(applicationId,type);
			CommonDocumentUtils.endHook(logger, "getCashFlowAssesmentByAppId");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Loan Assessment details.",
					HttpStatus.OK.value(), mfiLoanAssessmentDetailsReq), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while get CashFlow Assesment By AppId ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

}
