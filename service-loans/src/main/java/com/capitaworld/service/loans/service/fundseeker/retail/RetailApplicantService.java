package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import org.json.simple.JSONObject;

import com.capitaworld.service.loans.model.common.CibilFullFillOfferRequest;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;

public interface RetailApplicantService {
	public boolean save(RetailApplicantRequest applicantRequest, Long userId) throws Exception;

	public RetailApplicantRequest get(Long userId, Long applicationId) throws Exception;

	public boolean saveFinal(FinalCommonRetailRequest applicantRequest, Long userId) throws Exception;

	public FinalCommonRetailRequest getFinal(Long userId, Long applicationId) throws Exception;
	
	public List<CoApplicantRequest> getCoApplicants(Long userId, Long applicationId) throws Exception;
	
	public List<GuarantorRequest> getGuarantors(Long userId, Long applicationId) throws Exception;
	
	public Integer getCurrency(Long applicationId,Long userId) throws Exception;
	
	public JSONObject getCoapAndGuarIds(Long userId, Long applicationId) throws Exception;
	
	public CibilFullFillOfferRequest getProfile(Long userId, Long applicationId) throws Exception;
}
