package com.capitaworld.service.loans.service.sanction;

import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.model.LoansResponse;

public interface LoanSanctionService {
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest);
	
	public String requestValidation(String userName, String password, Long applicationId);
	
	public void saveBankReqRes(Object sanctionAndDisbursement, LoansResponse loansResponse, String msg);
	
}
