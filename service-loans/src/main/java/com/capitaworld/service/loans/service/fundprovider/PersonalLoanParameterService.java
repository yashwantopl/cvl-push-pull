package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.PersonalLoanParameterRequest;

public interface PersonalLoanParameterService {
	public boolean saveOrUpdate(PersonalLoanParameterRequest personalLoanParameterRequest,Long id);
	
	public PersonalLoanParameterRequest getPersonalLoanParameterRequest(Long id);

	public PersonalLoanParameterRequest getPersonalLoanParameterRequestTemp(Long id, Long role, Long userId);

	public Boolean saveOrUpdateTemp(PersonalLoanParameterRequest personalLoanParameterRequest);
	
	public Boolean saveMasterFromTempPl(Long mappingId) throws LoansException;
	
	public void saveEmiNmiTemp(PersonalLoanParameterRequest personalLoanParameterRequest) ;
	
	public void saveEmpStatus(PersonalLoanParameterRequest personalLoanParameterRequest);
	
	public void saveSalaryMode(PersonalLoanParameterRequest personalLoanParameterRequest);
	
	public void saveEmpWithMaster(PersonalLoanParameterRequest personalLoanParameterRequest) ;
	
	public void saveEmpWith(PersonalLoanParameterRequest personalLoanParameterRequest);
}
