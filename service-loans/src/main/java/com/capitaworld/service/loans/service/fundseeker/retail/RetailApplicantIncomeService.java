package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.retail.RetailApplicantIncomeRequest;

public interface RetailApplicantIncomeService {
	
	public boolean save(RetailApplicantIncomeRequest appIncomeReq);
	
	public boolean saveAll(List<RetailApplicantIncomeRequest> appIncomeReqList);
	
	public List<RetailApplicantIncomeRequest> getAll(Long applicationId);

}
