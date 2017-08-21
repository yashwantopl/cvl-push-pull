package com.capitaworld.service.loans.service.common;

import java.util.Map;

import org.json.simple.JSONObject;

import com.capitaworld.service.loans.model.common.HomeLoanEligibilityRequest;

public interface LoanEligibilityCalculatorService {

   public JSONObject getMinMaxBySalarySlab(HomeLoanEligibilityRequest homeLoanRequest) throws Exception;

	public Integer calculateTenure(HomeLoanEligibilityRequest homeLoanRequest) throws Exception;

	public JSONObject calcHomeLoanAmount(HomeLoanEligibilityRequest homeLoanRequest) throws Exception;
	
//	public Map<Integer,JSONObject> calculateMinMaxForHomeLoan(HomeLoanEligibilityRequest homeLoanRequest) throws Exception;
}
