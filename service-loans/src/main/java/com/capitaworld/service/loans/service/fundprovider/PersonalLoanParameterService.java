package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.PersonalLoanParameterRequest;

public interface PersonalLoanParameterService {
	public boolean saveOrUpdate(PersonalLoanParameterRequest personalLoanParameterRequest);
	
	public PersonalLoanParameterRequest getPersonalLoanParameterRequest(Long id);
}
