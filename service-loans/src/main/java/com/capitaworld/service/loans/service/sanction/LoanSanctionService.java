package com.capitaworld.service.loans.service.sanction;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.sidbi.integration.model.GenerateTokenRequest;

public interface LoanSanctionService {
	
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws LoansException;
	
	public String sanctionRequestValidation( Long applicationId,Long orgId)  throws LoansException ;
	

	/*public void saveBankReqRes(LoanSanctionRequest loanSanctionRequest, Integer statementType , LoansResponse loansResponse, String msg, Long orgId) ;*/
	
	/*public Long getOrgIdByCredential(String userName, String pwd) throws Exception ;*/

	public Integer saveSanctionDetailFromPopup(LoanSanctionRequest loanSanctionRequest) throws LoansException;
	
	public Boolean saveSanctionAndDisbursementDetailsFromBank() throws LoansException ;
	
	public Boolean saveLoanSanctionDetailById(Long orgId ,LoanSanctionRequest loanSanctionRequest) throws LoansException ;

	public String getToken(String url , GenerateTokenRequest generateTokenRequest , Integer langCode  )throws Exception ;
}
