package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import org.json.simple.JSONObject;

import com.capitaworld.service.loans.model.common.DocumentUploadFlagRequest;
import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDROneFormResponse;

public interface DDRFormService {

	public void saveDDRForm(DDRFormDetailsRequest ddrFormDetailsRequest) throws Exception;
	
	public DDRFormDetailsRequest get(Long id,Long userId);
	
	public List<JSONObject> getFinancialSummaryFieldsList();
	
	public List<JSONObject> getFinancialSummaryToBeFieldsList();
	
	public DDROneFormResponse getOneFormDetails(Long userId, Long applicationId);
	
	public Long saveDocumentFLag(DocumentUploadFlagRequest documentUploadFlagRequest) throws Exception;
	
	public Boolean isDDRApproved(Long userId, Long applicationId) throws Exception;
	
}
