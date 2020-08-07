package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import org.json.simple.JSONObject;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.common.DocumentUploadFlagRequest;
import com.opl.mudra.api.loans.model.ddr.DDRCustomerRequest;
import com.opl.mudra.api.loans.model.ddr.DDRFormDetailsRequest;
import com.opl.mudra.api.loans.model.ddr.DDROneFormResponse;
import com.opl.mudra.api.loans.model.ddr.DDRRequest;
import com.opl.mudra.api.loans.model.ddr.DDRUploadRequest;
import com.opl.service.loans.domain.fundseeker.ddr.DDRFormDetails;



public interface DDRFormService {

	public void saveDDRForm(com.opl.mudra.api.loans.model.ddr.DDRFormDetailsRequest ddrFormDetailsRequest) throws LoansException;
	
	public DDRRequest getMergeDDR(Long appId,Long userId) ;
	
	public DDRRequest getMergeDDRByProposalId(Long appId, Long userId, Long proposalId);

	public void saveMergeDDR(DDRRequest dDRRequest) throws LoansException;
	
	public void saveMergeDDRByProposalId(DDRRequest dDRRequest) throws Exception;

	public DDRFormDetailsRequest get(Long id,Long userId);
	public DDRFormDetailsRequest get(Long id,Long userId,Long proposalId);

	public List<JSONObject> getFinancialSummaryFieldsList();
	
	public List<JSONObject> getFinancialSummaryToBeFieldsList();
	
	public DDROneFormResponse getOneFormDetails(Long userId, Long applicationId, Long proposalId, boolean setExistingData);
	
	public Long saveDocumentFLag(DocumentUploadFlagRequest documentUploadFlagRequest) throws LoansException;
	
	public Boolean isDDRApproved(Long userId, Long applicationId) throws LoansException; // PREVIOUS
	public Boolean isDDRApprovedByProposaId(Long proposalId) throws Exception; // NEW BASED ON PROPOSAL ID
	
	public com.capitaworld.service.loans.model.api_model.DDRFormDetailsRequest getSIDBIDetails(Long appId,Long userId);
	
	public boolean deleteDocument(DDRUploadRequest ddrUploadRequest);

	public boolean deleteDocumentByProposalId(DDRUploadRequest ddrUploadRequest);

	public DDRCustomerRequest checkCustomerDetailFilled(Long applicationId);
	
	public Boolean saveCustomerDetailFilled(DDRCustomerRequest customerRequest);
	
	public DDRCustomerRequest getCustomerNameById(DDRCustomerRequest customerRequest);
	
	public DDRFormDetails getDDRDetailByApplicationId(Long applicationId) ;
}
