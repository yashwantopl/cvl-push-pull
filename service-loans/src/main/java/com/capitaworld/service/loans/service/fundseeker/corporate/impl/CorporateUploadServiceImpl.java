package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateUploadService;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.fundseeker.retail.impl.CoApplicantServiceImpl;
import com.capitaworld.service.loans.utils.CommonUtils;

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
	public DocumentResponse uploadOtherDoc(String documentRequestString, MultipartFile multipartFiles)
			throws Exception {
		try {
			return dmsClient.uploadFile(documentRequestString, multipartFiles);
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
			Long applicantArray[] = { 55L, 56L, 61L, 63L, 64L, 65L };
			Long co_appArray[] = { 57L, 58L, 69L, 71L, 72L, 73L };
			Long guarantorArray[] = { 59L, 60L, 77L, 79L, 80L, 81L };
			proIdList.addAll((List<Long>) Arrays.asList(applicantArray));
			co_app_proIdList.addAll((List<Long>) Arrays.asList(co_appArray));
			gua_proIdList.addAll((List<Long>) Arrays.asList(guarantorArray));
			Map<String, Map<String, Object>> maps = new HashMap<String, Map<String,Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
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
					else if(id.equals(63L)){
						map.put("itr", true);
					}
					else if(id.equals(64L)){
						map.put("audited_annual_report", true);
					}
					else if(id.equals(65L)){
						map.put("address_proof", true);
					}
				} else {
					if(id.equals(55L) || id.equals(56L)){
						map.put("identity_proof", false);
					}
					else if(id.equals(61L)){
						map.put("bank_statement", false);
					}
					else if(id.equals(63L)){
						map.put("itr", false);
					}
					else if(id.equals(64L)){
						map.put("audited_annual_report", false);
					}
					else if(id.equals(65L)){
						map.put("address_proof", false);
					}
				}
			}
			maps.put("app",map);
			List<Long> ids = coApplicantService.getCoAppIds(userId, applicationId);
			Map<String, Object> mapCoApp = null;
			int i =1;
			for (Long coappid : ids) {
				mapCoApp = new HashMap<String, Object>();
				for (Long id : co_app_proIdList) {
					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setCoApplicantId(coappid);
					documentRequest.setProductDocumentMappingId(id);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_CO_APPLICANT);
					dmsClient.listProductDocument(documentRequest);
					if (dmsClient.listProductDocument(documentRequest).getDataList().size() > 0) {
						if(id.equals(57L) || id.equals(58L)){
							map.put("identity_proof", true);
						}
						else if(id.equals(59L)){
							map.put("bank_statement", true);
						}
						else if(id.equals(71L)){
							map.put("itr", true);
						}
						else if(id.equals(72L)){
							map.put("audited_annual_report", true);
						}
						else if(id.equals(73L)){
							map.put("address_proof", true);
						}
					} else {
						if(id.equals(57L) || id.equals(58L)){
							map.put("identity_proof", false);
						}
						else if(id.equals(58L)){
							map.put("identity_proof", false);
						}
						else if(id.equals(59L)){
							map.put("bank_statement", false);
						}
						else if(id.equals(71L)){
							map.put("itr", false);
						}
						else if(id.equals(72L)){
							map.put("audited_annual_report", false);
						}
						else if(id.equals(73L)){
							map.put("address_proof", false);
						}
					}
				}
				maps.put("coApp "+i,mapCoApp);
				i++;
			}
			
			
			
			List<Long> gua_ids = guarantorService.getGuarantorIds(userId, applicationId);
			Map<String, Object> mapGua = null;
			int j = 1;
			for (Long guaId : gua_ids) {
				 mapGua = new HashMap<String, Object>();
			for (Long id : gua_proIdList) {
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setGuarantorId(guaId);
				documentRequest.setProductDocumentMappingId(id);
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_GUARANTOR);
				dmsClient.listProductDocument(documentRequest);
				if (dmsClient.listProductDocument(documentRequest).getDataList().size() > 0) {
					if(id.equals(59L) || id.equals(60L)){
						map.put("identity_proof", true);
					}
					else if(id.equals(77L)){
						map.put("bank_statement", true);
					}
					else if(id.equals(79L)){
						map.put("itr", true);
					}
					else if(id.equals(80L)){
						map.put("audited_annual_report", true);
					}
					else if(id.equals(81L)){
						map.put("address_proof", true);
					}
				} else {
					if(id.equals(59L) || id.equals(60L)){
						map.put("identity_proof", false);
					}
					else if(id.equals(77L)){
						map.put("bank_statement", false);
					}
					else if(id.equals(79L)){
						map.put("itr", false);
					}
					else if(id.equals(80L)){
						map.put("audited_annual_report", false);
					}
					else if(id.equals(81L)){
						map.put("address_proof", false);
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
		try {
			switch (tabType) {
			case CommonUtils.TabType.PRIMARY_UPLOAD:
				loanApplicationRepository.setIsPrimaryUploadMandatoryFilled(applicantId, userId, isFilled);
				loanApplicationRepository.setPrimaryFilledCount(applicantId, userId, filledCount);
				break;
			case CommonUtils.TabType.FINAL_UPLOAD:
				loanApplicationRepository.setIsFinalUploadMandatoryFilled(applicantId, userId, isFilled);
				loanApplicationRepository.setFinalFilledCount(applicantId, userId, filledCount);
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

}
