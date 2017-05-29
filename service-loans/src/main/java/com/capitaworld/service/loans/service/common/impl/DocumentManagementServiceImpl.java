package com.capitaworld.service.loans.service.common.impl;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sanket
 *
 */
@Service
public class DocumentManagementServiceImpl implements DocumentManagementService{
	
	 @Autowired
	    private Environment environment;

	@Autowired
	private DMSClient dmsClient;

	 protected static final String DMS_URL = "dmsURL";
	
	 public List<Object> getDocumentDetails(Long id,String userType,Long documentMappingId) throws DocumentException{
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
	        return documentResponse.getDataList();
	    } catch (DocumentException e) {
	        e.printStackTrace();
	        throw new DocumentException(e.getMessage());
	    	}
		}

}
