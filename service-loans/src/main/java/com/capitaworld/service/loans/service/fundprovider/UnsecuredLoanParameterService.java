package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.corporate.UnsecuredLoanParameterRequest;

public interface UnsecuredLoanParameterService {
	public boolean saveOrUpdate(UnsecuredLoanParameterRequest unsecuredLoanParameterRequest);

	public UnsecuredLoanParameterRequest getUnsecuredLoanParameterRequest(Long id);
}
