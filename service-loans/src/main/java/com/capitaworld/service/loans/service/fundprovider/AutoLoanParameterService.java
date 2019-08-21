package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.AutoLoanParameterRequest;

public interface AutoLoanParameterService {
public boolean saveOrUpdate(AutoLoanParameterRequest autoLoanParameterRequest);
	
	public AutoLoanParameterRequest getAutoLoanParameterRequest(Long id);
	
	public AutoLoanParameterRequest getTemp(Long id, Long role, Long userId);

	public Boolean saveOrUpdateTemp(AutoLoanParameterRequest autoLoanParameterRequest);
	
	public Boolean saveMasterFromTemp(Long mappingId) throws LoansException;
}
