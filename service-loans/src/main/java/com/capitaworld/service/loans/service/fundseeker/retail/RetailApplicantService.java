package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.RetailApplicantRequest;

public interface RetailApplicantService {
	public boolean save(RetailApplicantRequest applicantRequest);

	public RetailApplicantRequest get(Long id);
}
