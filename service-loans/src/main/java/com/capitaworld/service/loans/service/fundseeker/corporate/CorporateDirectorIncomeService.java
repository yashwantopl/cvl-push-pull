package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;

public interface CorporateDirectorIncomeService {
	

	public Boolean saveOrUpdateIncomeDetails(List<CorporateDirectorIncomeRequest> request) throws Exception;
	

}
