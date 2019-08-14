package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.AgriLoanParameterRequest;

public interface AgriLoanParameterService {
	public boolean saveOrUpdate(AgriLoanParameterRequest agriLoanParameterRequest);
	
	public AgriLoanParameterRequest get(Long id);
	
	public AgriLoanParameterRequest getTemp(Long id, Long role, Long userId);

	public Boolean saveOrUpdateTemp(AgriLoanParameterRequest homeLoanParameterRequest);
	
	public Boolean saveMasterFromTemp(Long mappingId) throws LoansException;
}
