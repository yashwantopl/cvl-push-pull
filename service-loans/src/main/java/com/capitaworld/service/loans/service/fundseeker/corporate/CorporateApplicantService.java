package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;

public interface CorporateApplicantService {
	public boolean save(CorporateApplicantRequest applicantRequest,Long userId) throws Exception;

	public CorporateApplicantRequest getCorporateApplicant(Long userId, Long applicationId) throws Exception;
}
