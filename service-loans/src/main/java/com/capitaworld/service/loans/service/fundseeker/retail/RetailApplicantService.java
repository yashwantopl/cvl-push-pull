package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequestOld;
import org.json.simple.JSONObject;

import com.capitaworld.service.loans.model.common.CibilFullFillOfferRequest;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.capitaworld.service.loans.model.retail.RetailITRManualResponse;

public interface RetailApplicantService {
	public boolean save(RetailApplicantRequest applicantRequest, Long userId) throws LoansException;

	public RetailApplicantRequest get(Long applicationId) throws LoansException;

	public boolean saveFinal(FinalCommonRetailRequestOld applicantRequest, Long userId) throws LoansException;

	public FinalCommonRetailRequestOld getFinal(Long userId, Long applicationId) throws LoansException;
	
	public List<CoApplicantRequest> getCoApplicants(Long userId, Long applicationId) throws LoansException;
	
	public List<GuarantorRequest> getGuarantors(Long userId, Long applicationId) throws LoansException;
	
	public Integer getCurrency(Long applicationId,Long userId) throws LoansException;
	
	public boolean saveITRResponse(RetailApplicantRequest applicantRequest) throws LoansException;
	
	public JSONObject getCoapAndGuarIds(Long userId, Long applicationId) throws LoansException;
	
	public CibilFullFillOfferRequest getProfile(Long userId, Long applicationId) throws LoansException;
	
	public JSONObject getNameAndPanByAppId(Long applicationId);
	
	public RetailITRManualResponse getITRManualFormData(Long applicationId,Long coAppId,Long userId);
}
