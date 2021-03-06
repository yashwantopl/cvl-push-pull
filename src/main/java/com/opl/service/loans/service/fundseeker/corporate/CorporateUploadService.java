package com.opl.service.loans.service.fundseeker.corporate;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.opl.mudra.api.dms.exception.DocumentException;
import com.opl.mudra.api.dms.model.DocumentRequest;
import com.opl.mudra.api.dms.model.DocumentResponse;
import com.opl.mudra.api.loans.exception.LoansException;

public interface CorporateUploadService {
	public DocumentResponse uploadProfile(Long applicantId, Long mappingId, String fileName, String userType,
			MultipartFile multipartFile) throws LoansException;

	public DocumentResponse getProfilePic(Long applicantId, Long mappingId, String userType) throws LoansException;

	public DocumentResponse getProfilePicByProposalId(Long proposalId,Long applicantId, Long mappingId, String userType) throws Exception;

	public DocumentResponse uploadOtherDoc(String documentString, MultipartFile multipartFiles, Long userId) throws LoansException;

	public DocumentResponse uploadOtherDocByProposalId(String documentString, MultipartFile multipartFiles, Long userId) throws Exception;

	public DocumentResponse getOtherDoc(DocumentRequest documentRequest) throws Exception;

	public DocumentResponse getOtherDocByProposalId(DocumentRequest documentRequest) throws LoansException;

	public void updateLoanApplicationFlag(Long applicantId, Long userId, int tabType,Boolean isFilled,String filledCount) throws Exception;

	public void updateLoanApplicationFlagByProposalId(Long proposalId,Long applicantId, Long userId, int tabType,Boolean isFilled,String filledCount) throws LoansException;

	public Map<String, Map<String, Object>> getOtherDocReport(Long applicationId) throws LoansException ;
	
	public DocumentResponse listOfDocumentByMultiProDocMapId(DocumentRequest documentRequest) throws DocumentException;
}
