package com.opl.service.loans.service.sanction;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.LoanSanctionRequest;

public interface LoanSanctionService {
	
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws LoansException;

	public LoanSanctionRequest getSanctionDetail(Long applicationId) throws LoansException;

	public String sanctionRequestValidation( Long applicationId,Long orgId)  throws LoansException ;
	

	/*public void saveBankReqRes(LoanSanctionRequest loanSanctionRequest, Integer statementType , LoansResponse loansResponse, String msg, Long orgId) ;*/
	
	/*public Long getOrgIdByCredential(String userName, String pwd) throws Exception ;*/

	public Integer saveSanctionDetailFromPopup(LoanSanctionRequest loanSanctionRequest) throws LoansException;

	/*public Boolean saveSanctionAndDisbursementDetailsFromBank() throws Exception ;

	public Boolean saveLoanSanctionDetailById(Long orgId ,LoanSanctionRequest loanSanctionRequest) throws Exception ;*/

	public Boolean  sendMailToHOBOCheckerMakerForMultipleBanks(Long applicationId) throws IndexOutOfBoundsException;
}
