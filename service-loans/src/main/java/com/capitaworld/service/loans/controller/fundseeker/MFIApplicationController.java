package com.capitaworld.service.loans.controller.fundseeker;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.mfi.MFIFinancialArrangementRequest;
import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.FlagCheckMFI;
import com.capitaworld.service.loans.model.micro_finance.MFIConversationReq;
import com.capitaworld.service.loans.model.micro_finance.MfiApplicantDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiAssetsDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiBankDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeAndExpenditureReq;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanRecomandationReq;
import com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.ProjectDetailsReq;
import com.capitaworld.service.loans.service.fundseeker.microfinance.MfiApplicationService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mfi")
public class MFIApplicationController {
	private static final Logger logger = LoggerFactory.getLogger(MFIApplicationController.class);

	@Autowired
	private MfiApplicationService mfiApplicationService;

	/**
	 * save Aadhar detail For create new Application in MFI Application
	 *
	 */
	@PostMapping(value = "/saveAdharDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveAdharDetails(@RequestPart("file") MultipartFile uploadingFile, @RequestPart("requestData") String requestData, HttpServletRequest request) {
		try {
			// request must not be null
			CommonDocumentUtils.startHook(logger, "save aadhar details");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
			}
			if(requestData == null){
				logger.warn("Data Should not be null ==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
			}
			AadharDetailsReq aadharDetailsReq = MultipleJSONObjectHelper.getObjectFromString(requestData, AadharDetailsReq.class);
			aadharDetailsReq.setUserId(userId);
			aadharDetailsReq.setOrgId(userOrgId);
			AadharDetailsReq aadharDetails = mfiApplicationService.saveOrUpdateAadharDetails(uploadingFile, aadharDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse(aadharDetails.getMessage(), HttpStatus.OK.value(), aadharDetails), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Adhar Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	@PostMapping(value = "/saveConsentFormImage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveConsentFormImage(@RequestPart("file") MultipartFile uploadingFile, @RequestPart("requestData") String requestData) {
		try {
			// request must not be null
			CommonDocumentUtils.startHook(logger, "save aadhar details");
			if(requestData == null){
				logger.warn("Data Should not be null ==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
			}
			AadharDetailsReq aadharDetailsReq = MultipleJSONObjectHelper.getObjectFromString(requestData, AadharDetailsReq.class);
            boolean consentFormImage = mfiApplicationService.saveConsentFormImage(uploadingFile, aadharDetailsReq);
            CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("successfull upload images", HttpStatus.OK.value(), consentFormImage), HttpStatus.OK);

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
					HttpStatus.INTERNAL_SERVER_ERROR.value(), aadharDetailsByAppId), HttpStatus.OK);

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
					return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
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
	public ResponseEntity<LoansResponse> saveProjectDetails(@RequestBody ProjectDetailsReq projectDetailsReq) {
		try {
			CommonDocumentUtils.startHook(logger, "save project details");
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
					return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(projectDetails.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			logger.error("Error while saving save Project Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/saveBankDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveBankDetails(@RequestPart("file") MultipartFile uploadingFile, @RequestPart("requestData") String requestData,HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "save bank details");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if(CommonUtils.isObjectNullOrEmpty(requestData)){
				logger.warn("Request data  can not be empty ==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			MfiBankDetailsReq mfiBankDetailsReq = MultipleJSONObjectHelper.getObjectFromString(requestData, MfiBankDetailsReq.class);
			if(mfiBankDetailsReq.getApplicationId() == null){
                logger.warn("applicationId  can not be empty ==>");
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            mfiBankDetailsReq.setUserId(userId);
			Object bankDetails = mfiApplicationService.saveOrUpdateBankDetails(uploadingFile,mfiBankDetailsReq);
			CommonDocumentUtils.endHook(logger, "save bank details");
			if(bankDetails instanceof Boolean){
				boolean bankDetail = (Boolean) bankDetails;
				if(bankDetail) {
					return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
							HttpStatus.OK);
				} else {
					return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
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

	@GetMapping(value = "/getApplicantDetails/{applicationId}/{type}")
	public ResponseEntity<LoansResponse> getApplicantDetails(@PathVariable("applicationId") Long applicationId,@PathVariable("type") Integer type,
			HttpServletRequest request) {
		try {

			CommonDocumentUtils.startHook(logger, "getApplicantDetails");
			MfiApplicantDetailsReq applicantDetails = mfiApplicationService.getApplicantDetails(applicationId,type);
			CommonDocumentUtils.endHook(logger, "getApplicantDetails");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Applicant or coApplicant details.",HttpStatus.OK.value(), applicantDetails), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get All applicant or coApplicant Details ==>", e);
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
					return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
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
            mfiAssetsDetailsReq.setUserId(userId);
			LoansResponse assetsLiabilityDetails = mfiApplicationService.saveOrUpdateAssetsLiabilityDetails(mfiAssetsDetailsReq);
            CommonDocumentUtils.endHook(logger, "save");
            if(assetsLiabilityDetails == null){
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<LoansResponse>(assetsLiabilityDetails,HttpStatus.OK);
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
                    return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
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


	@PostMapping(value = "/saveLoanRecomandationDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveLoanRecomandationDetails(@RequestBody MfiLoanRecomandationReq mfiLoanRecomandationReq, HttpServletRequest request) {
		try {
			logger.info("service call saveLoanAssessmentDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if(mfiLoanRecomandationReq.getId() == null){
				logger.warn("Id can not be empty ==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			mfiLoanRecomandationReq.setUserId(userId);

			Object loanAssessmentDetails = mfiApplicationService.saveOrUpdateLoanRecommandationDetails(mfiLoanRecomandationReq);
			CommonDocumentUtils.endHook(logger, "save");
			if(loanAssessmentDetails instanceof Boolean) {
					return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
							HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>((LoansResponse) loanAssessmentDetails,HttpStatus.OK);
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

	@GetMapping(value = "/getApplicationsByStatus/{status}")
	public ResponseEntity<LoansResponse> getPendingApplications(@PathVariable("status") Integer status,HttpServletRequest request) {
		try {

			CommonDocumentUtils.startHook(logger, "getApplicationsByStatus");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (userOrgId == null) {
				logger.warn("user Org Id  can not be empty ==>" + userOrgId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			AadharDetailsReq pendingApplications = mfiApplicationService.getApplicationsByStatus(userOrgId, userId,status);
			CommonDocumentUtils.endHook(logger, "getApplicationsByStatus");
			if(pendingApplications != null){
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Loan Application status details.",HttpStatus.OK.value(), pendingApplications), HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse("No data available.",HttpStatus.INTERNAL_SERVER_ERROR.value(), pendingApplications), HttpStatus.OK);
			}


		} catch (Exception e) {
			logger.error("Error while get Application by status By AppId ==>", e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}


	@PostMapping(value = "/saveApplicantDetails",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveApplicantDetails(
			@RequestBody MfiApplicantDetailsReq mfiApplicantDetailsReq, HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "saveApplicantDetails");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if(mfiApplicantDetailsReq.getApplicationId() == null){
				logger.warn("Application Id  can not be empty ==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			Boolean result = mfiApplicationService.saveOrUpdateApplicantDetail(mfiApplicantDetailsReq);
			CommonDocumentUtils.endHook(logger, "saveApplicantDetails");
			if(result) {
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse("fail to save data.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Error while saving save Income Expenditure Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "/getWorkflowData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getWorkflowData(@RequestBody WorkflowRequest workflowRequest,
														 HttpServletRequest request) {
		try {
			logger.info("service call getWorkflowData----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			workflowRequest.setUserId(userId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Job created successfully",
					HttpStatus.OK.value(), mfiApplicationService.getActiveButtons(workflowRequest)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/updateStatus/{applicationId}/{status}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateStatus(@PathVariable("applicationId") Long applicationId, @PathVariable("status") Long status,
													  HttpServletRequest request) {
		try {
			logger.info("service call updateStatus----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			boolean updated = mfiApplicationService.updateStaus(applicationId, status);
			if (updated) {
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Loan Assessment details.",
						HttpStatus.OK.value(), updated), HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	@GetMapping(value = "/callBureauGetFinancialDetails/{applicationId}/{userId}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> callBueroGetFinancialDetails(@PathVariable("applicationId") Long applicationId, @PathVariable("userId") Long userId) {
		try {
			logger.info("service call callBueroGetFinancialDetails----------->");
			CommonDocumentUtils.startHook(logger, "fetch");
			List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequests = mfiApplicationService.callBureauGetFinancialDetails(applicationId, userId);
			if (!CommonUtils.isListNullOrEmpty(financialArrangementsDetailRequests)) {
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Existing Loan details.",
						HttpStatus.OK.value(), financialArrangementsDetailRequests), HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}

	@PostMapping(value = "/getMfiConversation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getMfiConversation(@RequestBody MFIConversationReq mfiConversationReq,
															HttpServletRequest request) {
		try {
			logger.info("service call getMfiConversation()");
			CommonDocumentUtils.startHook(logger, "save");

			if (CommonUtils.isObjectNullOrEmpty(mfiConversationReq) || CommonUtils.isObjectNullOrEmpty(mfiConversationReq.getFromId()) || CommonUtils.isObjectNullOrEmpty(mfiConversationReq.getToId()) || CommonUtils.isObjectNullOrEmpty(mfiConversationReq.getApplicationId())) {
				logger.warn("toId, fromId, applicationId can not be empty ==>" + mfiConversationReq.toString());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(new LoansResponse("Conversation fetch successfully",
					HttpStatus.OK.value(), mfiApplicationService.getMfiConversation(mfiConversationReq)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "/saveMfiConversation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveMfiConversation(@RequestBody MFIConversationReq mfiConversationReq,
															 HttpServletRequest request) {
		try {
			logger.info("service call saveMfiConversation()");
			CommonDocumentUtils.startHook(logger, "save");

			if (CommonUtils.isObjectNullOrEmpty(mfiConversationReq) || CommonUtils.isObjectNullOrEmpty(mfiConversationReq.getFromId()) || CommonUtils.isObjectNullOrEmpty(mfiConversationReq.getToId()) || CommonUtils.isObjectNullOrEmpty(mfiConversationReq.getApplicationId())) {
				logger.warn("toId, fromId, applicationId can not be empty ==>" + mfiConversationReq.toString());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(new LoansResponse("Conversation saved successfully",
					HttpStatus.OK.value(), mfiApplicationService.saveOrUpdateMfiConversation(mfiConversationReq)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}
	
	@PostMapping(value = "/saveFinancialDetails/{applicationId}/{userId}/{applicantId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFinancialDetails(@RequestBody List<MFIFinancialArrangementRequest> financialDataList, @PathVariable("applicationId") Long applicationId, 
			@PathVariable("userId") Long createdBy, @PathVariable("applicantId") Long applicantId) {
		try {
			if (financialDataList == null) {
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			return new ResponseEntity<>(new LoansResponse("Saved successfully", HttpStatus.OK.value(), mfiApplicationService.saveFinancialDetails(financialDataList, applicationId, createdBy, applicantId)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}

}
