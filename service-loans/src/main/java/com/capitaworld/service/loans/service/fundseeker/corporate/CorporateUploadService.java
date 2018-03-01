package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;

public interface CorporateUploadService {
	public DocumentResponse uploadProfile(Long applicantId, Long mappingId, String fileName, String userType,
			MultipartFile multipartFile) throws Exception;

	public DocumentResponse getProfilePic(Long applicantId, Long mappingId, String userType) throws Exception;

	public DocumentResponse uploadOtherDoc(String documentString, MultipartFile multipartFiles, Long userId) throws Exception;

	public DocumentResponse getOtherDoc(DocumentRequest documentRequest) throws Exception;
	
	public void updateLoanApplicationFlag(Long applicantId, Long userId, int tabType,Boolean isFilled,String filledCount) throws Exception;
	
	public Map<String, Map<String, Object>> getOtherDocReport(Long applicationId) throws Exception ;
}
