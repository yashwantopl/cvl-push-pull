package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewCommonResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;

public interface CoApplicantService {
	public boolean save(CoApplicantRequest applicantRequest, Long applicationId, Long userId) throws Exception;

	public CoApplicantRequest get(Long userId, Long applicationId, Long id) throws Exception;

	public List<CoApplicantRequest> getList(Long applicationId, Long userId) throws Exception;

	public boolean saveFinal(FinalCommonRetailRequest applicantRequest, Long userId) throws Exception;

	public FinalCommonRetailRequest getFinal(Long userId, Long applicationId,Long id) throws Exception;

	public List<RetailProfileViewResponse> getCoApplicantPLResponse(Long applicantId, Long userId) throws Exception;
	
	public List<RetailFinalViewCommonResponse> getCoApplicantFinalResponse(Long applicantId, Long userId) throws Exception;
}
