package com.capitaworld.service.loans.service.common.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;

/**
 * @author Sanket
 *
 */
@Service
public class DocumentManagementServiceImpl implements DocumentManagementService{
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentManagementServiceImpl.class);
	
	@Autowired
	private DMSClient dmsClient;

	 protected static final String DMS_URL = "dmsURL";
	
	 public List<Object> getDocumentDetails(Long id,String userType,Long documentMappingId) throws DocumentException{
		CommonDocumentUtils.startHook(logger, "getDocumentDetails");
	    DocumentRequest documentRequest = new DocumentRequest();
	    switch (userType) {
			case DocumentAlias.UERT_TYPE_APPLICANT:
			 documentRequest.setApplicationId(id);
			break;
			case DocumentAlias.UERT_TYPE_CO_APPLICANT:
				documentRequest.setCoApplicantId(id);
				break;
			case DocumentAlias.UERT_TYPE_GUARANTOR:
				documentRequest.setGuarantorId(id);
				break;
			default:
			break;
		}
	   
	    documentRequest.setUserType(userType);
	    documentRequest.setProductDocumentMappingId(documentMappingId);
	    try {
	        DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
	        CommonDocumentUtils.endHook(logger, "getDocumentDetails");
	        return documentResponse.getDataList();
	    } catch (DocumentException e) {
	        e.printStackTrace();
	        throw new DocumentException(e.getMessage());
	    	}
		}

}
