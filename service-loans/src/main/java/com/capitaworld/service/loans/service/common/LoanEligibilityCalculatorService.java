package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.exceptions.LoansException;
import org.json.simple.JSONObject;

import com.capitaworld.service.loans.model.CMADetailResponse;
import com.capitaworld.service.loans.model.common.HomeLoanEligibilityRequest;
import com.capitaworld.service.loans.model.common.LAPEligibilityRequest;
import com.capitaworld.service.loans.model.common.LoanEligibilility;
import com.capitaworld.service.loans.model.common.PersonalLoanEligibilityRequest;

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
	
}
