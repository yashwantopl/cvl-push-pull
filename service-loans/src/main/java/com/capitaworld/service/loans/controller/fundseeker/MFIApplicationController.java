package com.capitaworld.service.loans.controller.fundseeker;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.api.reports.ReportRequest;
import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.client.reports.ReportsClient;
import com.capitaworld.service.dms.client.DMSClient;
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
import com.capitaworld.service.loans.utils.EncryptionUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@RestController
@RequestMapping("/mfi")
public class MFIApplicationController {
	private static final Logger logger = LoggerFactory.getLogger(MFIApplicationController.class);

	@Autowired
	private MfiApplicationService mfiApplicationService;

	@Autowired
	private ReportsClient reportsClient;

	@Autowired
	private DMSClient dmsClient;

	/**
	 * save Aadhar detail For create new Application in MFI Application
	 *
	 */
	@PostMapping(value = "/saveAdharDetailsWithMultiImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveAdharDetailsWithMultiImage(@RequestPart("file") MultipartFile uploadingFile,@RequestPart("addressFile") MultipartFile[] addressProofFile, @RequestPart("requestData") String requestData, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted, HttpServletRequest request) {
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
			if(CommonUtils.isObjectNullOrEmpty(requestData) || CommonUtils.isObjectNullOrEmpty(uploadingFile) || CommonUtils.isObjectNullOrEmpty(addressProofFile)){
				logger.warn("Data Should not be null ==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
			}
			AadharDetailsReq aadharDetailsReq;
			if (isEncrypted  != null && isEncrypted == 0) {
				aadharDetailsReq = MultipleJSONObjectHelper.getObjectFromString(requestData, AadharDetailsReq.class);
			} else {
				aadharDetailsReq = MultipleJSONObjectHelper.getObjectFromString(new EncryptionUtils().decriptWithKey(requestData), AadharDetailsReq.class);
			}
			aadharDetailsReq.setUserId(userId);
			aadharDetailsReq.setOrgId(userOrgId);
			AadharDetailsReq aadharDetails = mfiApplicationService.saveOrUpdateAadharDetails(uploadingFile, addressProofFile,aadharDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse(aadharDetails.getMessage(), HttpStatus.OK.value(), aadharDetails), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Adhar Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "/saveAdharDetails", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveAdharDetails(@RequestPart("file") MultipartFile uploadingFile,@RequestPart("addressFile") MultipartFile addressProofFile,@RequestPart("addressFile1") MultipartFile addressProofFile1, @RequestPart("requestData") String requestData, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted, HttpServletRequest request) {
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
			if(CommonUtils.isObjectNullOrEmpty(requestData) || CommonUtils.isObjectNullOrEmpty(uploadingFile) || CommonUtils.isObjectNullOrEmpty(addressProofFile)){
				logger.warn("Data Should not be null ==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
			}
			AadharDetailsReq aadharDetailsReq;
			if(isEncrypted  != null && isEncrypted == 0) {
				aadharDetailsReq = MultipleJSONObjectHelper.getObjectFromString(requestData, AadharDetailsReq.class);
			} else {
				aadharDetailsReq = MultipleJSONObjectHelper.getObjectFromString(new EncryptionUtils().decriptWithKey(requestData), AadharDetailsReq.class);
			}
			aadharDetailsReq.setUserId(userId);
			aadharDetailsReq.setOrgId(userOrgId);
			MultipartFile[]  addressProofFiles = new MultipartFile[2];
			addressProofFiles[0] = addressProofFile;
			addressProofFiles[1] = addressProofFile1;
			AadharDetailsReq aadharDetails = mfiApplicationService.saveOrUpdateAadharDetails(uploadingFile, addressProofFiles,aadharDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse(aadharDetails.getMessage(), HttpStatus.OK.value(), aadharDetails), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Adhar Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	@PostMapping(value = "/saveConsentFormImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveConsentFormImages(@RequestPart("file") MultipartFile[] uploadingFile, @RequestPart("requestData") String requestData, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted, HttpServletRequest request) {
		try {
			// request must not be null
			CommonDocumentUtils.startHook(logger, "save Consent Form Images Multiple details");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
			}
			if(CommonUtils.isObjectNullOrEmpty(requestData) || CommonUtils.isObjectNullOrEmpty(uploadingFile)){
				logger.warn("Data Should not be null ==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
			}

			AadharDetailsReq aadharDetailsReq;
			if (isEncrypted  != null && isEncrypted == 0){
				aadharDetailsReq = MultipleJSONObjectHelper.getObjectFromString(requestData, AadharDetailsReq.class);
			}else {
				aadharDetailsReq = MultipleJSONObjectHelper.getObjectFromString(new EncryptionUtils().decriptWithKey(requestData), AadharDetailsReq.class);
			}


			aadharDetailsReq.setUserId(userId);
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

	@PostMapping(value = "/saveConsentFormImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveConsentFormImage(@RequestPart("file") MultipartFile uploadingFile,@RequestPart("file1") MultipartFile uploadingFile1, @RequestPart("requestData") String requestData, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted,  HttpServletRequest request) {
		try {
			// request must not be null
			CommonDocumentUtils.startHook(logger, "save Consent Form Images diff parameters details");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
			}
			if(CommonUtils.isObjectNullOrEmpty(requestData) || CommonUtils.isObjectNullOrEmpty(uploadingFile)|| CommonUtils.isObjectNullOrEmpty(uploadingFile1)){
				logger.warn("Data Should not be null ==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
			}

			AadharDetailsReq aadharDetailsReq;
			if (isEncrypted  != null && isEncrypted == 0){ //for encryption
				aadharDetailsReq = MultipleJSONObjectHelper.getObjectFromString(requestData, AadharDetailsReq.class);
			}else {//for skip encryption
				aadharDetailsReq = MultipleJSONObjectHelper.getObjectFromString(new EncryptionUtils().decriptWithKey(requestData), AadharDetailsReq.class);
			}

			aadharDetailsReq.setUserId(userId);
			MultipartFile[] multipartFiles = new MultipartFile[2];
			multipartFiles[0] = uploadingFile;
			multipartFiles[1] = uploadingFile1;
			boolean consentFormImage = mfiApplicationService.saveConsentFormImage(multipartFiles, aadharDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("successfull upload images", HttpStatus.OK.value(), consentFormImage), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Adhar Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getAadharDetails/{applicationId}/{type}")
	public ResponseEntity<LoansResponse> getAadharDetails(@PathVariable("applicationId") Long applicationId,@PathVariable("type") Integer type) {
		try {
			CommonDocumentUtils.startHook(logger, "get aadhar details");
			if(applicationId == null || type==null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				logger.warn("type  can not be empty ==>" + type);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			AadharDetailsReq aadharDetailsByAppId = mfiApplicationService.getAadharDetailsByAppId(applicationId,type);
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
	public ResponseEntity<LoansResponse> savePersonalDetails(@RequestBody String personalDataReq, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted, HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "save personal details");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			PersonalDetailsReq personalDetailsReq;
			if (isEncrypted  != null && isEncrypted == 0){
				personalDetailsReq = MultipleJSONObjectHelper.getObjectFromString(personalDataReq, PersonalDetailsReq.class);
			}else {
				personalDetailsReq = MultipleJSONObjectHelper.getObjectFromString(new EncryptionUtils().decriptWithKey(personalDataReq), PersonalDetailsReq.class);
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

	@GetMapping(value = "/getPersonalDetails/{applicationId}/{type}")
	public ResponseEntity<LoansResponse> getPersonalDetails(@PathVariable("applicationId") Long applicationId,@PathVariable("type") Integer type) {
		try {
			CommonDocumentUtils.startHook(logger, "get personal details");
			if(applicationId == null || type==null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				logger.warn("type  can not be empty ==>" + type);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			PersonalDetailsReq personalrDetailsByAppId = mfiApplicationService.getPersonalDetailsAppId(applicationId,type);
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
	public ResponseEntity<LoansResponse> saveProjectDetails(@RequestBody String projectDataReq, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted) {
		try {
			CommonDocumentUtils.startHook(logger, "save project details");
			ProjectDetailsReq projectDetailsReq;
			if(isEncrypted  != null && isEncrypted == 0) {
				projectDetailsReq = MultipleJSONObjectHelper.getObjectFromString(projectDataReq, ProjectDetailsReq.class);
			} else {
				projectDetailsReq = MultipleJSONObjectHelper.getObjectFromString(new EncryptionUtils().decriptWithKey(projectDataReq), ProjectDetailsReq.class);
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

	@PostMapping(value = "/saveBankDetails", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveBankDetails(@RequestPart("file") MultipartFile uploadingFile, @RequestPart("requestData") String requestData, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted ,HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "save bank details");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if(CommonUtils.isObjectNullOrEmpty(requestData) || CommonUtils.isObjectNullOrEmpty(uploadingFile)){
				logger.warn("Data  can not be empty ==>");
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			MfiBankDetailsReq mfiBankDetailsReq;
			if(isEncrypted  != null && isEncrypted == 0) {
				mfiBankDetailsReq = MultipleJSONObjectHelper.getObjectFromString(requestData, MfiBankDetailsReq.class);
			} else {
				mfiBankDetailsReq = MultipleJSONObjectHelper.getObjectFromString(new EncryptionUtils().decriptWithKey(requestData), MfiBankDetailsReq.class);
			}
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

	@GetMapping(value = "/getProjectDetails/{applicationId}/{type}")
	public ResponseEntity<LoansResponse> getProjectDetails(@PathVariable("applicationId") Long applicationId,@PathVariable("type") Integer type) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Project Details");
			if(applicationId == null | type==null){
				logger.warn("applicationId or type can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			ProjectDetailsReq projectDetailsByAppId = mfiApplicationService.getProjectDetailsAppId(applicationId,type);
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
	public ResponseEntity<LoansResponse> saveIncomeExpenditureDetails(@RequestBody String mfiIncomeAndExpenditureDataReq, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted, HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "save Income expenditure");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq;
			if(isEncrypted  != null && isEncrypted == 0) {
				mfiIncomeAndExpenditureReq = MultipleJSONObjectHelper.getObjectFromString(mfiIncomeAndExpenditureDataReq, MfiIncomeAndExpenditureReq.class);
			} else {
				mfiIncomeAndExpenditureReq = MultipleJSONObjectHelper.getObjectFromString(new EncryptionUtils().decriptWithKey(mfiIncomeAndExpenditureDataReq), MfiIncomeAndExpenditureReq.class);
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

	@PostMapping(value = "/saveAssetsLiabilityDetails", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveAssetsLiabilityDetails(@RequestBody String mfiAssetsDataReq , @RequestParam(value="isEncrypted",required = false) Integer isEncrypted, HttpServletRequest request) {
		try {
			logger.info("service call saveAssetsLiabilityDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			MfiAssetsDetailsReq mfiAssetsDetailsReq;
			if(isEncrypted  != null && isEncrypted == 0) {
				mfiAssetsDetailsReq = MultipleJSONObjectHelper.getObjectFromString(mfiAssetsDataReq, MfiAssetsDetailsReq.class);
			} else {
				mfiAssetsDetailsReq = MultipleJSONObjectHelper.getObjectFromString(new EncryptionUtils().decriptWithKey(mfiAssetsDataReq), MfiAssetsDetailsReq.class);
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

	@PostMapping(value = "/saveLoanAssessmentDetails", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveLoanAssessmentDetails(
			@RequestBody String mfiLoanAssessmentDataReq, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted, HttpServletRequest request) {
		try {
			logger.info("service call saveLoanAssessmentDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq;
			if(isEncrypted  != null && isEncrypted == 0) {
				mfiLoanAssessmentDetailsReq = MultipleJSONObjectHelper.getObjectFromString(mfiLoanAssessmentDataReq, MfiLoanAssessmentDetailsReq.class);
			} else {
				mfiLoanAssessmentDetailsReq = MultipleJSONObjectHelper.getObjectFromString(new EncryptionUtils().decriptWithKey(mfiLoanAssessmentDataReq), MfiLoanAssessmentDetailsReq.class);
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


	@PostMapping(value = "/saveLoanRecomandationDetails", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveLoanRecomandationDetails(@RequestBody String mfiLoanRecomandationDataReq, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted, HttpServletRequest request) {
		try {
			logger.info("service call saveLoanAssessmentDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			MfiLoanRecomandationReq mfiLoanRecomandationReq;
			if(isEncrypted  != null && isEncrypted == 0) {
				mfiLoanRecomandationReq = MultipleJSONObjectHelper.getObjectFromString(mfiLoanRecomandationDataReq, MfiLoanRecomandationReq.class);
			} else{
				mfiLoanRecomandationReq = MultipleJSONObjectHelper.getObjectFromString(new EncryptionUtils().decriptWithKey(mfiLoanRecomandationDataReq), MfiLoanRecomandationReq.class);
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

	@GetMapping(value = "/getLoanAssessmentDetails/{applicationId}/{type}")
	public ResponseEntity<LoansResponse> getLoanAssessmentDetails(@PathVariable("applicationId") Long applicationId,@PathVariable("type") Integer type) {
		try {

			CommonDocumentUtils.startHook(logger, "Get Loan Assessment Details");
			if(applicationId == null || type==null){
				logger.warn("applicationId or type can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq = mfiApplicationService.getLoanAssessmentDetailsAppId(applicationId,type);
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
	public ResponseEntity<LoansResponse> getCashFlowAssesmentByAppId(@PathVariable("applicationId") Long applicationId,@PathVariable("type") Integer type, @PathVariable(value="isEncrypted",required = false) Integer isEncrypted) {
		try {

			CommonDocumentUtils.startHook(logger, "getCashFlowAssesmentByAppId");
			if(applicationId == null){
				logger.warn("applicationId  can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq = mfiApplicationService.getCashFlowAssesmentByAppId(applicationId,type);
			CommonDocumentUtils.endHook(logger, "getCashFlowAssesmentByAppId");
			if(isEncrypted  != null && isEncrypted == 0) {
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Loan Assessment details.",
						HttpStatus.OK.value(), mfiLoanAssessmentDetailsReq), HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Loan Assessment details.",
						HttpStatus.OK.value(), new EncryptionUtils().encryptionWithKey(mfiLoanAssessmentDetailsReq.toString())), HttpStatus.OK);
			}

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

	@PostMapping(value = "/getWorkflowData", produces = MediaType.APPLICATION_JSON_VALUE)
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
	@GetMapping(value = "/callBureauGetFinancialDetails/{applicationId}/{applicantId}/{type}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> callBueroGetFinancialDetails(@PathVariable("applicationId") String applicationEncId, @PathVariable("applicantId") String applicantEncId, @PathVariable("type") Integer type, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted, HttpServletRequest request) {
		try {
			logger.info("service call callBueroGetFinancialDetails----------->");
			CommonDocumentUtils.startHook(logger, "fetch");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			Long applicationId,applicantId;
			if(isEncrypted  != null && isEncrypted == 0) {
				applicationId = Long.valueOf(applicationEncId);
				applicantId = Long.valueOf(applicantEncId);
			} else {
				applicationId = Long.valueOf(new EncryptionUtils().decriptWithKey(applicationEncId));
				applicantId = Long.valueOf(new EncryptionUtils().decriptWithKey(applicantEncId));
			}

			LoansResponse loansResponse = mfiApplicationService.callBureauGetFinancialDetails(applicationId, applicantId, userId, type);
			if (!CommonUtils.isObjectNullOrEmpty(loansResponse)) {
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}

	@PostMapping(value = "/getMfiConversation", produces = MediaType.APPLICATION_JSON_VALUE)
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

	@PostMapping(value = "/saveFinancialData", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveFinancialData(@RequestBody MFIFinancialArrangementRequest financialData,HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (financialData == null) {
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			return new ResponseEntity<>(new LoansResponse("Saved successfully", HttpStatus.OK.value(), mfiApplicationService.saveFinancialData(financialData, userId)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	@GetMapping(value = "/proceedFinancialDetails/{applicationId}/{creditWorthiness}")
	public ResponseEntity<LoansResponse> proceedFinancialDetails(@PathVariable("applicationId") String applicationIdEnc, @PathVariable("creditWorthiness") String creditWorthinessEnc, @RequestParam(value="isEncrypted",required = false) Integer isEncrypted , HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			Long applicationId;Integer creditWorthiness;
			if(isEncrypted  != null && isEncrypted == 0) {
				applicationId = Long.valueOf(applicationIdEnc);
				creditWorthiness = Integer.valueOf(creditWorthinessEnc);
			} else  {
				applicationId = Long.valueOf(new EncryptionUtils().decriptWithKey(applicationIdEnc));
				creditWorthiness = Integer.valueOf(new EncryptionUtils().decriptWithKey(creditWorthinessEnc));
			}
			return new ResponseEntity<>(new LoansResponse("Saved successfully", HttpStatus.OK.value(), mfiApplicationService.proceedFinancialFinalData(applicationId, userId, creditWorthiness)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getFinancialDetails/{applicationId}/{applicantId}")
	public ResponseEntity<LoansResponse> getFinancialDetails(@PathVariable("applicationId") Long applicationId,@PathVariable("applicantId") Long applicantId) {
		try {
			CommonDocumentUtils.startHook(logger, "get Financial details");
			if(applicationId == null || applicantId==null){
				logger.warn("applicationId or applicantiId can not be empty ==>" + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			List<MFIFinancialArrangementRequest> mfiFinancialArrangementRequest = mfiApplicationService.getFinancialDetailsAppId(applicationId,applicantId);

			CommonDocumentUtils.endHook(logger, "Get Financial Details");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Financial details.",
					HttpStatus.OK.value(), mfiFinancialArrangementRequest), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while Get Financial Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "/saveAllApplicantsDetails", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveAllApplicantsDetails(
			@RequestPart("profileimg") MultipartFile uploadingFile,
			@RequestPart("addressproof") MultipartFile addressProof,
			@RequestPart("consentformimg") MultipartFile consentformImg,
			@RequestPart("aadharimg") MultipartFile aadharImg, @RequestPart("requestData") String requestData,
			HttpServletRequest request) {
		try {

			MfiApplicantDetailsReq mfiApplicantDetailsReq = MultipleJSONObjectHelper.getObjectFromString(requestData,
					MfiApplicantDetailsReq.class);
			CommonDocumentUtils.startHook(logger, "save All Applicants details");
			mfiApplicantDetailsReq.setUserId((Long) request.getAttribute(CommonUtils.USER_ID));
			mfiApplicantDetailsReq.setOrgId((Long) request.getAttribute(CommonUtils.USER_ORG_ID));

			if (mfiApplicantDetailsReq.getUserId() == null) {
				logger.warn("userId  can not be empty ==>" + mfiApplicantDetailsReq.getUserId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			Object allApplicantDetails = mfiApplicationService.saveOrUpdateAllApplicantsDetails(uploadingFile,
					addressProof, consentformImg, aadharImg, mfiApplicantDetailsReq);
			if (allApplicantDetails instanceof Boolean) {
				boolean personalInfo = (Boolean) allApplicantDetails;
				CommonDocumentUtils.endHook(logger, "save All Applicant details");
				if (personalInfo) {
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
				} else {
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("fail to save data.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
							HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(allApplicantDetails.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Error while saving save All Applicants Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}


	@RequestMapping(value = "/documentUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.ALL_VALUE)
	public ResponseEntity<LoansResponse> documentUpload(@RequestPart("uploadRequest") String uploadRequestString,
														@RequestPart("file") MultipartFile[] multipartFiles, HttpServletRequest request) {
		try {
			Long userId = (Long)request.getAttribute(CommonUtils.USER_ID);
			CommonDocumentUtils.startHook(logger, "uploadDoc");
			if (CommonUtils.isObjectNullOrEmpty(uploadRequestString)) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}


			for (MultipartFile multipartFile : multipartFiles) {

				String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

				if(!"pdf".equalsIgnoreCase(extension)) {
					logger.warn("File format is not PDF...!");
					return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.WRONG_FILE_FORMAT, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
				}
			}
			MfiApplicantDetailsReq mfiApplicantDetailsReq = MultipleJSONObjectHelper.getObjectFromString(uploadRequestString, MfiApplicantDetailsReq.class);

			if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq)||CommonUtils.isObjectNullOrEmpty(multipartFiles)) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			boolean documentUpload = mfiApplicationService.uploadDocuments(multipartFiles, mfiApplicantDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("successfull upload images", HttpStatus.OK.value(), documentUpload), HttpStatus.OK);


		} catch (Exception e) {
			logger.error("Error while document upload "+e.getMessage());
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* TO Download Report */

	@GetMapping(value = "/getReportDetails/{applicationId}/{type}")
	public byte[] getReportDetails(@PathVariable("applicationId") Long applicationId,
								   @PathVariable("type") Integer type, HttpServletRequest request) {

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		/*
		 * if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
		 * logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND,
		 * applicationId); return new ResponseEntity<LoansResponse>( new
		 * LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND,
		 * HttpStatus.BAD_REQUEST.value()), HttpStatus.OK); }
		 */

		try {
			Map<String, Object> response = mfiApplicationService.getReportDetails(applicationId);
			Map<String, Object> applicantDetails = mfiApplicationService.getApplicantDetails1(applicationId, type);
			CommonDocumentUtils.startHook(logger, "getApplicantDetails");
			ReportRequest reportRequest = new ReportRequest();
			response.putAll(applicantDetails);
			reportRequest.setParams(response);
			//reportRequest.setParams(applicantDetails);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(baos);
//			reportRequest.setTemplate("MFICAMPRIMARY");
//			reportRequest.setType("MFICAMPRIMARY");

			List<String> tempaltes  =  new ArrayList<String>();  //["MFICAMPRIMARY" A "MFICAMDPN"	 + "MFICAMLOANDHO" + "MFICAMLOL"];
			tempaltes.add("MFICAMPRIMARY");
			tempaltes.add("MFICAMDPN");
			tempaltes.add("MFICAMLOANDHO");
			tempaltes.add("MFICAMLOL");


			for (int i = 0; i < tempaltes.size(); i++)
			{
				reportRequest.setTemplate(tempaltes.get(i));
				byte[] byteArr = reportsClient.generatePDFFile(reportRequest);

				if (byteArr != null) {
					ZipEntry entry = new ZipEntry(tempaltes.get(i) + ".pdf");
					entry.setSize(byteArr.length);
					zos.putNextEntry(entry);
					zos.write(byteArr);

				}

			}
			zos.closeEntry();
			zos.close();
			return baos.toByteArray();

		}
		catch (Exception e) {
			logger.error("Error while getting MAP Details==>", e);
		}

		return null;
	}





}
