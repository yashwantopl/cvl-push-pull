
package com.opl.service.loans.service.fundseeker.corporate;

import com.opl.mudra.api.loans.model.api_model.FinancialRequest;
import com.opl.mudra.api.loans.model.corporate.CMARequest;

public interface CMAService {

	public void saveCMA(CMARequest cmaRequest);
	
	public CMARequest getCMA(Long applicationId);
	
	public FinancialRequest getFinancialDetailsForBankIntegration(Long applicationId, Long proposalId);
	
}
