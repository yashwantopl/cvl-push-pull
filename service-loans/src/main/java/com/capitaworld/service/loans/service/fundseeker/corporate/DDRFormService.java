package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import org.json.simple.JSONObject;

import com.capitaworld.service.loans.model.common.DocumentUploadFlagRequest;
import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDROneFormResponse;
import com.capitaworld.service.loans.model.ddr.DDRRequest;
import com.capitaworld.service.loans.model.ddr.DDRUploadRequest;

public interface DDRFormService {

	public void saveDDRForm(DDRFormDetailsRequest ddrFormDetailsRequest) throws Exception;
	
	public DDRRequest getMergeDDR(Long appId,Long userId) ;
	
	public void saveMergeDDR(DDRRequest dDRRequest) throws Exception;
	
	public DDRFormDetailsRequest get(Long id,Long userId);
	
	public List<JSONObject> getFinancialSummaryFieldsList();
	
	public List<JSONObject> getFinancialSummaryToBeFieldsList();
	
	public DDROneFormResponse getOneFormDetails(Long userId, Long applicationId,boolean setExistingData);
	
	public Long saveDocumentFLag(DocumentUploadFlagRequest documentUploadFlagRequest) throws Exception;
	
	public Boolean isDDRApproved(Long userId, Long applicationId) throws Exception;
	
	public com.capitaworld.sidbi.integration.model.ddr.DDRFormDetailsRequest getSIDBIDetails(Long appId,Long userId);
	
	public boolean deleteDocument(DDRUploadRequest ddrUploadRequest);
	
}
