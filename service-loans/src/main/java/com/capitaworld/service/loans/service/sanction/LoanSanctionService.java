package com.capitaworld.service.loans.service.sanction;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoanSanctionRequest;

public interface LoanSanctionService {
	
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws LoansException;
	
	public String sanctionRequestValidation( Long applicationId,Long orgId)  throws LoansException ;
	

	/*public void saveBankReqRes(LoanSanctionRequest loanSanctionRequest, Integer statementType , LoansResponse loansResponse, String msg, Long orgId) ;*/
	
	/*public Long getOrgIdByCredential(String userName, String pwd) throws Exception ;*/

	public Integer saveSanctionDetailFromPopup(LoanSanctionRequest loanSanctionRequest) throws LoansException;

}
