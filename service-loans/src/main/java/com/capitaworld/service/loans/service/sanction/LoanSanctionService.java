package com.capitaworld.service.loans.service.sanction;

import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.sidbi.integration.model.GenerateTokenRequest;

public interface LoanSanctionService {
	
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws Exception ;
	
	public String sanctionRequestValidation( Long applicationId,Long orgId)  throws Exception ;
	

	/*public void saveBankReqRes(LoanSanctionRequest loanSanctionRequest, Integer statementType , LoansResponse loansResponse, String msg, Long orgId) ;*/
	
	/*public Long getOrgIdByCredential(String userName, String pwd) throws Exception ;*/

	public Boolean saveSanctionDetailFromPopup(LoanSanctionRequest loanSanctionRequest) throws Exception;
	
	public Boolean saveSanctionAndDisbursementDetailsFromBank() throws Exception ;
	
	public Boolean saveLoanSanctionDetailById(LoanSanctionRequest loanSanctionRequest) throws Exception ;

	public String getToken(String url , GenerateTokenRequest generateTokenRequest , Integer langCode  )throws Exception ;
}
