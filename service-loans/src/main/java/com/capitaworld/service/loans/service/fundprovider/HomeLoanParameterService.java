package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.retail.HomeLoanParameterRequest;

public interface HomeLoanParameterService {
	public boolean saveOrUpdate(HomeLoanParameterRequest homeLoanParameterRequest);
	
	public HomeLoanParameterRequest getHomeLoanParameterRequest(Long id);
}
