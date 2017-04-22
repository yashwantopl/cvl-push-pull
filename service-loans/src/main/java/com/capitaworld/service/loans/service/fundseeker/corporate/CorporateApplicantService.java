package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.model.CorporateApplicantRequest;

public interface CorporateApplicantService {
	public boolean saveOrUpdate(CorporateApplicantRequest applicantRequest);
	
	public CorporateApplicantDetail getCorporateApplicant(CorporateApplicantDetail corporateApplicantDetail);
}
