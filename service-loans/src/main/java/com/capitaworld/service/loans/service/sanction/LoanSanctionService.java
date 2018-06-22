package com.capitaworld.service.loans.service.sanction;

import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.model.LoansResponse;

public interface LoanSanctionService {
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws Exception ;
	
	public String requestValidation( Long applicationId,Long orgId)  throws Exception ;
	
	public void saveBankReqRes(LoanSanctionRequest loanSanctionRequest, Integer statementType , LoansResponse loansResponse, String msg, Long orgId) ;
	
	public Long getOrgIdByCredential(String userName, String pwd) throws Exception ;
	
}
