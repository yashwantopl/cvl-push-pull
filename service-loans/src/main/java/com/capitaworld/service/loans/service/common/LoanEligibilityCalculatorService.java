package com.capitaworld.service.loans.service.common;

import java.util.Map;

import org.json.simple.JSONObject;

import com.capitaworld.service.loans.model.common.HomeLoanEligibilityRequest;
import com.capitaworld.service.loans.model.common.LoanEligibilility;
import com.capitaworld.service.loans.model.common.PersonalLoanEligibilityRequest;

public interface LoanEligibilityCalculatorService {

	// For Home Loan
	public JSONObject getMinMaxBySalarySlab(HomeLoanEligibilityRequest homeLoanRequest) throws Exception;

	public Integer calculateTenure(LoanEligibilility eligibilility, Integer productId) throws Exception;

	public JSONObject calcHomeLoanAmount(HomeLoanEligibilityRequest homeLoanRequest) throws Exception;

	// For Personal Loan
	public JSONObject calcMinMaxForPersonalLoan(PersonalLoanEligibilityRequest eligibilityRequest) throws Exception;
}
