package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;

public interface CorporateDirectorIncomeService {
	

	public Boolean saveOrUpdateIncomeDetails(List<CorporateDirectorIncomeRequest> request) throws Exception;

    public List<Map<String, Object>> getDirectorBackGroundDetails(Long applicationId) throws Exception;

	public List<CorporateDirectorIncomeRequest> getDirectorIncomeDetails(Long applicationId) throws Exception; 
	
}
