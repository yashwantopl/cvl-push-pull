package com.capitaworld.service.loans.service.sanction;

import java.io.IOException;
import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoanDisbursementRequest;

public interface LoanDisbursementService {
	
	public Boolean saveLoanDisbursementDetail(LoanDisbursementRequest loanDisbursementRequest)throws IOException ;
	
	public LoanDisbursementRequest disbursementRequestValidation(Long sanctionPrimaryId , LoanDisbursementRequest loanDisbursementRequest, Long orgId , Integer apiType)throws IOException ;

	public List<LoanDisbursementRequest> getDisbursedList(Long applicationId) throws LoansException;
	
	public Boolean saveLoanDisbursementDetailbyId(Long orgId, LoanDisbursementRequest loanDisbursementRequest) throws IOException ;
	
}
