package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;
import java.util.Map;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.CorporateDirectorIncomeRequest;

public interface CorporateDirectorIncomeService {
	

	public Boolean saveOrUpdateIncomeDetails(List<CorporateDirectorIncomeRequest> request) throws LoansException;

    public List<Map<String, Object>> getDirectorBackGroundDetails(Long applicationId) throws LoansException;

	public List<CorporateDirectorIncomeRequest> getDirectorIncomeDetails(Long applicationId) throws LoansException;
	
	public List<CorporateDirectorIncomeRequest> getDirectorIncomeLatestYearDetails(Long applicationId) throws LoansException;
	
}
