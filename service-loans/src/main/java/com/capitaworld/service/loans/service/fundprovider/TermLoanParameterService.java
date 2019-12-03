package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.corporate.TermLoanParameterRequest;

public interface TermLoanParameterService {
	public boolean saveOrUpdate(TermLoanParameterRequest termLoanParameterRequest , Long mappingId,Integer roleId);
	
	public boolean saveOrUpdateNtb(TermLoanParameterRequest termLoanParameterRequest , Long mappingId);
	
	public TermLoanParameterRequest getTermLoanParameterRequest(Long id,Long role);
	
	public TermLoanParameterRequest getNtbTermLoanParameterRequest(Long id,Long role);

	/**
	 * @param mappingId
	 * @param roleId 
	 * @return
	 */
	public Boolean saveMasterFromTempTl(Long mappingId, Integer roleId) throws LoansException;
	
	public Boolean saveMasterFromNtbTempTl(Long mappingId) throws LoansException;

	/**
	 * @param loanParameterRequest
	 * @return
	 */
	public Boolean saveOrUpdateTemp(TermLoanParameterRequest loanParameterRequest);
	
	public Boolean saveOrUpdateNtbTemp(TermLoanParameterRequest loanParameterRequest);

	TermLoanParameterRequest getTermLoanParameterRequestTemp(Long id,Long role,Long userId);
	
	TermLoanParameterRequest getNtbTermLoanParameterRequestTemp(Long id,Long role,Long userId);
}
