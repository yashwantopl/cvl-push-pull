package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

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

	public RetailProfileViewResponse getProfileViewPLResponse(Long applicantId, Long userId) throws Exception;
}
