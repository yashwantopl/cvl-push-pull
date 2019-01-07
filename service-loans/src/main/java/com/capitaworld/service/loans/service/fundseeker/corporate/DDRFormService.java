package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import org.json.simple.JSONObject;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRFormDetails;
import com.capitaworld.service.loans.model.common.DocumentUploadFlagRequest;
import com.capitaworld.service.loans.model.ddr.DDRCustomerRequest;
import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDROneFormResponse;
import com.capitaworld.service.loans.model.ddr.DDRRequest;
import com.capitaworld.service.loans.model.ddr.DDRUploadRequest;

public interface DDRFormService {

	public void saveDDRForm(DDRFormDetailsRequest ddrFormDetailsRequest) throws LoansException;
	
	public DDRRequest getMergeDDR(Long appId,Long userId) ;
	
	public void saveMergeDDR(DDRRequest dDRRequest) throws LoansException;
	
	public DDRFormDetailsRequest get(Long id,Long userId);
	
	public List<JSONObject> getFinancialSummaryFieldsList();
	
	public List<JSONObject> getFinancialSummaryToBeFieldsList();
	
	public DDROneFormResponse getOneFormDetails(Long userId, Long applicationId,boolean setExistingData);
	
	public Long saveDocumentFLag(DocumentUploadFlagRequest documentUploadFlagRequest) throws LoansException;
	
	public Boolean isDDRApproved(Long userId, Long applicationId) throws LoansException;
	
	public com.capitaworld.sidbi.integration.model.ddr.DDRFormDetailsRequest getSIDBIDetails(Long appId,Long userId);
	
	public boolean deleteDocument(DDRUploadRequest ddrUploadRequest);
	
	public DDRCustomerRequest checkCustomerDetailFilled(Long applicationId);
	
	public Boolean saveCustomerDetailFilled(DDRCustomerRequest customerRequest);
	
	public DDRCustomerRequest getCustomerNameById(DDRCustomerRequest customerRequest);
	
	public DDRFormDetails getDDRDetailByApplicationId(Long applicationId) ;
}
