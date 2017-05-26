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
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateUploadService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class CorporateUploadServiceImpl implements CorporateUploadService {

	private static final Logger logger = LoggerFactory.getLogger(CorporateUploadServiceImpl.class);

	@Autowired
	private Environment environment;

	@SuppressWarnings("unchecked")
	@Override
	public DocumentResponse uploadProfile(Long applicantId, Long mappingId, String fileName,String userType,
			MultipartFile multipartFile) throws Exception {
		try {
			DMSClient dmsClient = new DMSClient(environment.getRequiredProperty(CommonUtils.DMS_BASE_URL_KEY));
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("applicationId", applicantId);
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
	public DocumentResponse getProfilePic(Long applicantId, Long mappingId,String userType) throws Exception {
		try {
			DocumentRequest docRequest = new DocumentRequest();
			docRequest.setApplicationId(applicantId);
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

}
