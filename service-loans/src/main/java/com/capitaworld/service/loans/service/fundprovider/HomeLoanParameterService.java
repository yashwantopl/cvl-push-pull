package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.HomeLoanParameterRequest;

public interface HomeLoanParameterService {
	public boolean saveOrUpdate(HomeLoanParameterRequest homeLoanParameterRequest);
	
	public HomeLoanParameterRequest getHomeLoanParameterRequest(Long id);
	
	public HomeLoanParameterRequest getTemp(Long id, Long role, Long userId);

	public Boolean saveOrUpdateTemp(HomeLoanParameterRequest homeLoanParameterRequest);
	
	public Boolean saveMasterFromTemp(Long mappingId) throws LoansException;
}
