package com.capitaworld.service.loans.service.sanction;

import java.io.IOException;

import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.model.LoansResponse;

public interface LoanSanctionService {
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest);
	
	public String requestValidation(String userName, String password, Long applicationId);
	
	public void saveBankReqRes(LoanSanctionRequest loanSanctionRequest, LoansResponse loansResponse, String msg, Long orgId)throws IOException ;
	
	public Long getOrgIdByCredential(String userName, String pwd);
	
}
