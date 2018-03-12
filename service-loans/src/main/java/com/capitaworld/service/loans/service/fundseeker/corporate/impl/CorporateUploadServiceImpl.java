package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRFormDetails;
import com.capitaworld.service.loans.model.common.DocumentUploadFlagRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRFormDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateUploadService;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.fundseeker.retail.impl.CoApplicantServiceImpl;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class CorporateUploadServiceImpl implements CorporateUploadService {

	private static final Logger logger = LoggerFactory.getLogger(CorporateUploadServiceImpl.class);

	// @Autowired
	// private Environment environment;

	@Autowired
	private DMSClient dmsClient;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	CoApplicantService coApplicantService;
	
	@Autowired
	GuarantorService guarantorService;
	
	@Autowired
	private DDRFormDetailsRepository ddrFormDetailsRepository;

	@SuppressWarnings("unchecked")
	@Override
	public DocumentResponse uploadProfile(Long applicantId, Long mappingId, String fileName, String userType,
			MultipartFile multipartFile) throws Exception {
		try {
			JSONObject jsonObj = new JSONObject();

			if (CommonUtils.UploadUserType.UERT_TYPE_APPLICANT.equalsIgnoreCase(userType)) {
				jsonObj.put("applicationId", applicantId);
			} else if (CommonUtils.UploadUserType.UERT_TYPE_CO_APPLICANT.equalsIgnoreCase(userType)) {
				// here we have set same applicant variable because when
				// requested user is co-applicant then it "coApplicantId" will
				// be considered and same as for "guarantors".
				jsonObj.put("coApplicantId", applicantId);
			} else if (CommonUtils.UploadUserType.UERT_TYPE_GUARANTOR.equalsIgnoreCase(userType)) {
				// here we have set same applicant variable because when
				// requested user is co-applicant then it "coApplicantId" will
				// be considered and same as for "guarantors".
				jsonObj.put("guarantorId", applicantId);
			}

			jsonObj.put("productDocumentMappingId", mappingId);
			jsonObj.put("userType", userType);
			jsonObj.put("originalFileName", fileName);
			DocumentResponse documentResponse = dmsClient.productImage(jsonObj.toString(), multipartFile);
			return documentResponse;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.error("Error while uploading Profile Document");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public DocumentResponse getProfilePic(Long applicantId, Long mappingId, String userType) throws Exception {
		try {
			DocumentRequest docRequest = new DocumentRequest();
			if (CommonUtils.UploadUserType.UERT_TYPE_APPLICANT.equalsIgnoreCase(userType)) {
				docRequest.setApplicationId(applicantId);
			} else if (CommonUtils.UploadUserType.UERT_TYPE_CO_APPLICANT.equalsIgnoreCase(userType)) {
				// here we have set same applicant variable because when
				// requested user is co-applicant then it "coApplicantId" will
				// be considered and same as for "guarantors".
				docRequest.setCoApplicantId(applicantId);
			} else if (CommonUtils.UploadUserType.UERT_TYPE_GUARANTOR.equalsIgnoreCase(userType)) {
				// here we have set same applicant variable because when
				// requested user is co-applicant then it "coApplicantId" will
				// be considered and same as for "guarantors".
				docRequest.setGuarantorId(applicantId);
			}
			docRequest.setProductDocumentMappingId(mappingId);
			docRequest.setUserType(userType);
			return dmsClient.listProductDocument(docRequest);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.error("Error while getting Profile Document");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public DocumentResponse uploadOtherDoc(String documentRequestString, MultipartFile multipartFiles, Long userId)
			throws Exception {
		DocumentResponse response = dmsClient.uploadFile(documentRequestString, multipartFiles);
		
		try {
			DocumentRequest request = MultipleJSONObjectHelper.getObjectFromString(documentRequestString, DocumentRequest.class);
			request.setUserId(userId);
			try{
				logger.error("saving Upload FLag");
			Long resp = saveDocumentFLag( request);
			if(resp == 0L){
				logger.error("Error while saving Upload FLag");
				throw new Exception("Error while saving Upload FLag");
			}
			
			}
			catch (Exception e) {
				e.printStackTrace();
				logger.error("Error while saving Upload FLag");
				throw new Exception("Error while saving Upload FLag");
			}
			return response;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.error("Error while uploading Corporate Other Documents");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public DocumentResponse getOtherDoc(DocumentRequest documentRequest) throws Exception {
		try {
			return dmsClient.listProductDocument(documentRequest);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.error("Error while getting Corporate Other Documents");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	// New UBI Requirement
		@Override
		public Map<String, Map<String, Object>> getOtherDocReport(Long applicationId) throws Exception {
			try {
				
				Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);
				List<Long> proIdList = new ArrayList<>();
				List<Long> co_app_proIdList = new ArrayList<>();
				List<Long> gua_proIdList = new ArrayList<>();
				Long applicantArray[] = { 55L, 56L, 61L, 63L, 64L, 65L, 243L, 248L };
				Long co_appArray[] = { 57L, 58L, 69L, 71L, 72L, 73L, 254L, 259L };
				Long guarantorArray[] = { 59L, 60L, 77L, 79L, 80L, 81L, 264L, 269L };
				proIdList.addAll((List<Long>) Arrays.asList(applicantArray));
				co_app_proIdList.addAll((List<Long>) Arrays.asList(co_appArray));
				gua_proIdList.addAll((List<Long>) Arrays.asList(guarantorArray));
				Map<String, Map<String, Object>> maps = new HashMap<String, Map<String,Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("identity_proof", false);
				map.put("bank_statement", false);
				map.put("itr", false);
				map.put("audited_annual_report", false);
				map.put("address_proof", false);
				for (Long id : proIdList) {
					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setApplicationId(applicationId);
					documentRequest.setProductDocumentMappingId(id);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
					dmsClient.listProductDocument(documentRequest);
								
					if (dmsClient.listProductDocument(documentRequest).getDataList().size() > 0) {
						if(id.equals(55L) || id.equals(56L)){
							map.put("identity_proof", true);
						}
						else if(id.equals(61L)){
							map.put("bank_statement", true);
						}
						else if(id.equals(63L) || id.equals(243L) || id.equals(248L)){
							map.put("itr", true);
						}
						else if(id.equals(64L)){
							map.put("audited_annual_report", true);
						}
						else if(id.equals(65L)){
							map.put("address_proof", true);
						}
					}
				}
				maps.put("app",map);
				List<Long> ids = coApplicantService.getCoAppIds(userId, applicationId);
				
				int i =1;
				
				for (Long coappid : ids) {
					Map<String, Object> mapCoApp = new HashMap<String, Object>();
					mapCoApp.put("identity_proof", false);
					mapCoApp.put("bank_statement", false);
					mapCoApp.put("itr", false);
					mapCoApp.put("audited_annual_report", false);
					mapCoApp.put("address_proof", false);
					for (Long id : co_app_proIdList) {
						DocumentRequest documentRequest = new DocumentRequest();
						documentRequest.setCoApplicantId(coappid);
						documentRequest.setProductDocumentMappingId(id);
						documentRequest.setUserType(DocumentAlias.UERT_TYPE_CO_APPLICANT);
						dmsClient.listProductDocument(documentRequest);
								
						if (dmsClient.listProductDocument(documentRequest).getDataList().size() > 0) {
							if(id.equals(57L) || id.equals(58L)){
								mapCoApp.put("identity_proof", true);
							}
							else if(id.equals(69L)){
								mapCoApp.put("bank_statement", true);
							}
							else if(id.equals(71L) || id.equals(254L) || id.equals(259L)){
								mapCoApp.put("itr", true);
							}
							else if(id.equals(72L)){
								mapCoApp.put("audited_annual_report", true);
							}
							else if(id.equals(73L)){
								mapCoApp.put("address_proof", true);
							}
						} 
					}
					maps.put("coApp "+i,mapCoApp);
					i++;
				}
				
				
				
				List<Long> gua_ids = guarantorService.getGuarantorIds(userId, applicationId);
				
				int j = 1;
				for (Long guaId : gua_ids) {
					Map<String, Object> mapGua = new HashMap<String, Object>();
						mapGua.put("identity_proof", false);
						mapGua.put("bank_statement", false);
						mapGua.put("itr", false);
						mapGua.put("audited_annual_report", false);
						mapGua.put("address_proof", false);
				for (Long id : gua_proIdList) {
					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setGuarantorId(guaId);
					documentRequest.setProductDocumentMappingId(id);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_GUARANTOR);
					dmsClient.listProductDocument(documentRequest);
						
					if (dmsClient.listProductDocument(documentRequest).getDataList().size() > 0) {
						if(id.equals(59L) || id.equals(60L)){
							mapGua.put("identity_proof", true);
						}
						else if(id.equals(77L)){
							mapGua.put("bank_statement", true);
						}
						else if(id.equals(79L) || id.equals(264L) || id.equals(269L)){
							mapGua.put("itr", true);
						}
						else if(id.equals(80L)){
							mapGua.put("audited_annual_report", true);
						}
						else if(id.equals(81L)){
							mapGua.put("address_proof", true);
						}
					} 
				}
				maps.put("Guar "+j, mapGua);
				j++;
				}
				
				return maps;
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				logger.error("Error while getting Corporate Other Documents");
				throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
			}

		}
		// end New UBI Requirement
		
	@Override
	public void updateLoanApplicationFlag(Long applicantId, Long userId, int tabType, Boolean isFilled,
			String filledCount) throws Exception {
		logger.info("In updateLoanApplicationFlag service method");
		logger.info("appId----------->" + applicantId + "------userId------->" + userId + 
				"---------tabtype------->"+tabType + "--------isFilled------->" + isFilled +
				"----------FileCount----------"+filledCount);
		try {
			switch (tabType) {
			case CommonUtils.TabType.PRIMARY_UPLOAD:
				loanApplicationRepository.setIsPrimaryUploadMandatoryFilled(applicantId, userId, isFilled);
				loanApplicationRepository.setPrimaryFilledCount(applicantId, userId, filledCount);
				break;
			case CommonUtils.TabType.FINAL_UPLOAD:
				logger.info("Before setIsFinalUploadMandatoryFilled");
				loanApplicationRepository.setIsFinalUploadMandatoryFilled(applicantId, userId, isFilled);
				logger.info("After setIsFinalUploadMandatoryFilled");
				logger.info("Before setFinalFilledCount");
				loanApplicationRepository.setFinalFilledCount(applicantId, userId, filledCount);
				logger.info("After setFinalFilledCount");
				break;
			case CommonUtils.TabType.FINAL_DPR_UPLOAD:
				loanApplicationRepository.setIsFinalDprMandatoryFilled(applicantId, userId, isFilled);
				loanApplicationRepository.setFinalFilledCount(applicantId, userId, filledCount);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error while updating Flag to loan_application_master for upload");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	public Long saveDocumentFLag(DocumentRequest documentUploadFlagRequest) throws Exception {
//		DDRFormDetailsRequest
		try{
		DDRFormDetails dDRFormDetails = ddrFormDetailsRepository.getByAppIdAndIsActive(documentUploadFlagRequest.getApplicationId());
		if(CommonUtils.isObjectNullOrEmpty(dDRFormDetails)){
			dDRFormDetails = new DDRFormDetails();
			dDRFormDetails.setApplicationId(documentUploadFlagRequest.getApplicationId());
			dDRFormDetails.setUserId(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setCreatedBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setCreatedDate(new Date());
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			dDRFormDetails.setIsActive(true);
		}
		
		final int switchCase = documentUploadFlagRequest.getProductDocumentMappingId().intValue();
		switch (switchCase) {
		// Working Capital 
		
		case 3:
			dDRFormDetails.setPanCardOfTheCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
		case 9:
			dDRFormDetails.setAuditedFinancialsForLast3years("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
		case 10:
			dDRFormDetails.setSanctionLetter("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 11:
			dDRFormDetails.setItrForLast3years("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 12:
			dDRFormDetails.setCaCertifiedNetworthStatement("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 13:
			dDRFormDetails.setProvisionalFinancialsForCurrentYear("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 14:
			dDRFormDetails.setPanCardAndResidenceAddProofOfDirectors("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 15:
			dDRFormDetails.setListOfShareholdersAndShareHoldingPatter("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 18:
			dDRFormDetails.setMoaOfTheCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 297:
			dDRFormDetails.setIrrOfAllDirectorsForLast2years("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;

			
		case 305:
			dDRFormDetails.setResolutionAndForm32forAdditionOfDirector("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 308:
			dDRFormDetails.setDebtorsList("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 315:
			dDRFormDetails.setLetterOfIntentFromFundProviders("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 330:
			dDRFormDetails.setFieldAuditReport("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 331:
			dDRFormDetails.setBankStatementOfLast12months("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 332:
			dDRFormDetails.setFinancialFigures("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 333:
			dDRFormDetails.setCentralSalesTaxRegistrationOfCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 334:
			dDRFormDetails.setCentralExciseRegistrationOfCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 335:
			dDRFormDetails.setVatRegistrationOfCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 336:
			dDRFormDetails.setListOfDirectors("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
			
			
			// term Loan
			
		case 30:
			dDRFormDetails.setPanCardOfTheCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 36:
			dDRFormDetails.setAuditedFinancialsForLast3years("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;

		case 37:
			dDRFormDetails.setSanctionLetter("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 39:
			dDRFormDetails.setCaCertifiedNetworthStatement("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 40:
			dDRFormDetails.setProvisionalFinancialsForCurrentYear("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 41:
			dDRFormDetails.setPanCardAndResidenceAddProofOfDirectors("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 42:
			dDRFormDetails.setListOfShareholdersAndShareHoldingPatter("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 45:
			dDRFormDetails.setMoaOfTheCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 298:
			dDRFormDetails.setIrrOfAllDirectorsForLast2years("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;

		case 306:
			dDRFormDetails.setResolutionAndForm32forAdditionOfDirector("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;

		case 309:
			dDRFormDetails.setDebtorsList("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 316:
			dDRFormDetails.setLetterOfIntentFromFundProviders("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		
			
		case 38:
			dDRFormDetails.setItrForLast3years("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
			
			
		case 337:
			dDRFormDetails.setFieldAuditReport("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 338:
			dDRFormDetails.setBankStatementOfLast12months("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 339:
			dDRFormDetails.setFinancialFigures("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 340:
			dDRFormDetails.setCentralSalesTaxRegistrationOfCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 341:
			dDRFormDetails.setCentralExciseRegistrationOfCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 342:
			dDRFormDetails.setVatRegistrationOfCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 343:
			dDRFormDetails.setListOfDirectors("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
			
			
		
			
			
			
			// Unsecured loan
			
			
		case 276:
			dDRFormDetails.setAuditedFinancialsForLast3years("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
			
		case 277:
			dDRFormDetails.setSanctionLetter("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 278:
			dDRFormDetails.setItrForLast3years("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 279:
			dDRFormDetails.setCaCertifiedNetworthStatement("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 280:
			dDRFormDetails.setProvisionalFinancialsForCurrentYear("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 283:
			dDRFormDetails.setPanCardOfTheCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 284:
			dDRFormDetails.setPanCardAndResidenceAddProofOfDirectors("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 285:
			dDRFormDetails.setListOfShareholdersAndShareHoldingPatter("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 299:
			dDRFormDetails.setIrrOfAllDirectorsForLast2years("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
		
		case 307:
			dDRFormDetails.setResolutionAndForm32forAdditionOfDirector("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 310:
			dDRFormDetails.setDebtorsList("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 311:
			dDRFormDetails.setMoaOfTheCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 317:
			dDRFormDetails.setLetterOfIntentFromFundProviders("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
			
			
		case 344:
			dDRFormDetails.setFieldAuditReport("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 345:
			dDRFormDetails.setBankStatementOfLast12months("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 346:
			dDRFormDetails.setFinancialFigures("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 347:
			dDRFormDetails.setCentralSalesTaxRegistrationOfCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 348:
			dDRFormDetails.setCentralExciseRegistrationOfCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 349:
			dDRFormDetails.setVatRegistrationOfCompany("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
		case 350:
			dDRFormDetails.setListOfDirectors("Yes");
			dDRFormDetails.setModifyBy(documentUploadFlagRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
			break;
			
			
			

		default:
			break;
		}
		ddrFormDetailsRepository.save(dDRFormDetails);
		return 1L;
		}
		catch (Exception e) {
			return 0L;
		}
	}


}
