package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.corporate.CMARequest;
import com.capitaworld.sidbi.integration.model.financial.FinancialRequest;

public interface CMAService {

	public void saveCMA(CMARequest cmaRequest);
	
	public CMARequest getCMA(Long applicationId);
	
	public FinancialRequest getFinancialDetailsForBankIntegration(Long applicationId);
	
}
