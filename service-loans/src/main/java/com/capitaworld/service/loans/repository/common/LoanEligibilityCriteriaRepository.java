package com.capitaworld.service.loans.repository.common;

import com.capitaworld.service.loans.domain.common.HomeLoanEligibilityCriteria;
import com.capitaworld.service.loans.domain.common.PersonalLoanEligibilityCriteria;

public interface LoanEligibilityCriteriaRepository {

	// For Home Loan
	public HomeLoanEligibilityCriteria getHomeLoanBySalarySlab(Long income, Integer type, Integer bankId);

	public HomeLoanEligibilityCriteria getHomeLoanBySVMV(Long sv, Long mv, Integer bankId);

	public Object[] getMinMaxRoiForHomeLoan();

	// For Personal Loan
	public PersonalLoanEligibilityCriteria getPersonalLoanBySalarySlab(Long income, Integer type, Integer bankId);
	
	public Object[] getMinMaxRoiForPersonalLoan(Integer type);
}
