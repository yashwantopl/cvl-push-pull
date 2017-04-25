package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.WorkingCapitalParameterRequest;

public interface WorkingCapitalParameterService {
	public boolean saveOrUpdate(WorkingCapitalParameterRequest workingCapitalParameterRequest);
	
	public WorkingCapitalParameterRequest getWorkingCapitalParameter(Long id);
}
