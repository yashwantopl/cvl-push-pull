package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateUploadService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class CorporateUploadServiceImpl implements CorporateUploadService {

	private static final Logger logger = LoggerFactory.getLogger(CorporateUploadServiceImpl.class);

	@Autowired
	private Environment environment;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public DocumentResponse uploadProfile(Long applicantId, Long mappingId, String fileName, String userType,
			MultipartFile multipartFile) throws Exception {
		try {
			DMSClient dmsClient = new DMSClient(environment.getRequiredProperty(CommonUtils.DMS_BASE_URL_KEY));
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
			DMSClient dmsClient = new DMSClient(environment.getRequiredProperty(CommonUtils.DMS_BASE_URL_KEY));
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
			DMSClient dmsClient = new DMSClient(environment.getRequiredProperty(CommonUtils.DMS_BASE_URL_KEY));
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
			DMSClient dmsClient = new DMSClient(environment.getRequiredProperty(CommonUtils.DMS_BASE_URL_KEY));
			return dmsClient.listProductDocument(documentRequest);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.error("Error while getting Corporate Other Documents");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public void updateLoanApplicationFlag(Long applicantId, Long userId, int tabType, Boolean isFilled,String filledCount)
			throws Exception {
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
