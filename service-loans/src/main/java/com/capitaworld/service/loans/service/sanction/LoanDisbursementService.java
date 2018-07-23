package com.capitaworld.service.loans.service.sanction;

import java.io.IOException;
import java.util.List;

import com.capitaworld.service.loans.model.LoanDisbursementRequest;

public interface LoanDisbursementService {
	public Boolean saveLoanDisbursementDetail(LoanDisbursementRequest loanDisbursementRequest)throws IOException ;
	
	public String requestValidation(LoanDisbursementRequest loanDisbursementRequest, Long orgId)throws IOException ;

	public List<LoanDisbursementRequest> getDisbursedList(Long applicationId) throws Exception;
}
