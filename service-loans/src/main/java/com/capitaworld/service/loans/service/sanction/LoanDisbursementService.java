package com.capitaworld.service.loans.service.sanction;

import java.io.IOException;

import com.capitaworld.service.loans.model.LoanDisbursementRequest;
import com.capitaworld.service.loans.model.LoansResponse;

public interface LoanDisbursementService {
	public Boolean saveLoanDisbursementDetail(LoanDisbursementRequest loanDisbursementRequest)throws IOException ;
	
	public String requestValidation(LoanDisbursementRequest loanDisbursementRequest, Long orgId)throws IOException ;
	
	public void saveBankReqRes(LoanDisbursementRequest loanDisbursementRequest, LoansResponse loansResponse, String msg, Long orgId) throws IOException  ;
	
	public Long getOrgIdByCredential(String userName, String pwd) ;
}
