package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;

public interface CorporateDirectorIncomeService {
	

	public Boolean saveOrUpdateIncomeDetails(List<CorporateDirectorIncomeRequest> request) throws LoansException;

    public List<Map<String, Object>> getDirectorBackGroundDetails(Long applicationId) throws LoansException;

	public List<CorporateDirectorIncomeRequest> getDirectorIncomeDetails(Long applicationId) throws LoansException;
	
	public List<CorporateDirectorIncomeRequest> getDirectorIncomeLatestYearDetails(Long applicationId) throws LoansException;
	
}
