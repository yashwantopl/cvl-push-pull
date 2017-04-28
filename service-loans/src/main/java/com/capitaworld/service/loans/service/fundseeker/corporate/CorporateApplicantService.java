package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.CorporateApplicantRequest;

public interface CorporateApplicantService {
	public boolean save(CorporateApplicantRequest applicantRequest);

	public CorporateApplicantRequest getCorporateApplicant(Long corporateApplicantDetail);
}
