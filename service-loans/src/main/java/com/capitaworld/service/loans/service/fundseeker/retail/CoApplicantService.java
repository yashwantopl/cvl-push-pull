package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.CoApplicantRequest;

public interface CoApplicantService {
	public boolean save(CoApplicantRequest applicantRequest, Long applicationId);

	public CoApplicantRequest get(Long id, Long applicationId);

	public List<CoApplicantRequest> getList(Long applicationId);
}
