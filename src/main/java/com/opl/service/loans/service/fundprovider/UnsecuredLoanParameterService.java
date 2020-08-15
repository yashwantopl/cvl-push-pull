package com.opl.service.loans.service.fundprovider;

import com.opl.mudra.api.loans.model.corporate.UnsecuredLoanParameterRequest;

public interface UnsecuredLoanParameterService {
	public boolean saveOrUpdate(UnsecuredLoanParameterRequest unsecuredLoanParameterRequest);

	public UnsecuredLoanParameterRequest getUnsecuredLoanParameterRequest(Long id);
}
