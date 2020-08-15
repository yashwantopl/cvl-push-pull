package com.opl.service.loans.service.common;

import org.json.simple.JSONObject;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.CMADetailResponse;
import com.opl.mudra.api.loans.model.common.HomeLoanEligibilityRequest;
import com.opl.mudra.api.loans.model.common.LAPEligibilityRequest;
import com.opl.mudra.api.loans.model.common.LoanEligibilility;
import com.opl.mudra.api.loans.model.common.PersonalLoanEligibilityRequest;

public interface LoanEligibilityCalculatorService {

	// For Home Loan
	public JSONObject getMinMaxBySalarySlab(HomeLoanEligibilityRequest homeLoanRequest) throws LoansException;

	public Integer calculateTenure(LoanEligibilility eligibilility, Integer productId) throws LoansException;

	public JSONObject calcHomeLoanAmount(HomeLoanEligibilityRequest homeLoanRequest) throws LoansException;

	// For Personal Loan
	public JSONObject calcMinMaxForPersonalLoan(PersonalLoanEligibilityRequest eligibilityRequest) throws LoansException;
	
	//For LAP
	
	public JSONObject calcMinMaxForLAP(LAPEligibilityRequest eligibilityRequest) throws LoansException;
	
	public JSONObject calcLAPAmount(LAPEligibilityRequest homeLoanRequest) throws LoansException;
	
	public CMADetailResponse getCMADetail(Long applicationId );
	
	public CMADetailResponse getCMADetailApi(Long applicationId);
	
}
