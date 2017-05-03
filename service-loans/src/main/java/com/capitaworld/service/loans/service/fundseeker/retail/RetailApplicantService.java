package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;

public interface RetailApplicantService {
	public boolean save(RetailApplicantRequest applicantRequest) throws Exception;

	public RetailApplicantRequest get(Long id,Long applicationId) throws Exception;

	public boolean saveFinal(FinalCommonRetailRequest applicantRequest) throws Exception;

	public FinalCommonRetailRequest getFinal(Long id, Long applicationId) throws Exception;
}
