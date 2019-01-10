package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantIncomeRequest;

public interface RetailApplicantIncomeService {
	
	public boolean save(RetailApplicantIncomeRequest appIncomeReq) throws LoansException;
	
	public boolean saveAll(List<RetailApplicantIncomeRequest> appIncomeReqList) throws LoansException;
	
	public List<RetailApplicantIncomeRequest> getAll(Long applicationId);

	public Boolean saveOrUpdateIncomeDetailForGrossIncome(FrameRequest frameRequest) throws LoansException;
}
