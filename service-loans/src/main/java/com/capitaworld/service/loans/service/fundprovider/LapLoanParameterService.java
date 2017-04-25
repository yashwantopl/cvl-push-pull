package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.LapParameterRequest;

public interface LapLoanParameterService {
	public boolean saveOrUpdate(LapParameterRequest lapParameterRequest);
	
	public LapParameterRequest getLapParameterRequest(Long id);
}
