package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.CarLoanParameterRequest;

public interface CarLoanParameterService {
	public boolean saveOrUpdate(CarLoanParameterRequest carLoanParameterRequest);
	
	public CarLoanParameterRequest getCarLoanParameterRequest(Long id);
}
