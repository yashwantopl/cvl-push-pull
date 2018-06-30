package com.capitaworld.service.loans.service.sanction;

import com.capitaworld.service.loans.model.LoanSanctionRequest;

public interface LoanSanctionService {
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws Exception ;
	
	public String requestValidation( Long applicationId,Long orgId)  throws Exception ;
	
}
