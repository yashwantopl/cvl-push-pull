package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;

public interface CoApplicantService {
	public boolean save(CoApplicantRequest applicantRequest, Long applicationId);

	public CoApplicantRequest get(Long id, Long applicationId);

	public List<CoApplicantRequest> getList(Long applicationId);
	
	public boolean saveFinal(FinalCommonRetailRequest applicantRequest);

	public FinalCommonRetailRequest getFinal(Long id, Long applicationId);
}
