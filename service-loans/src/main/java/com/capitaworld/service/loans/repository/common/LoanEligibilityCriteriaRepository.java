package com.capitaworld.service.loans.repository.common;

import com.capitaworld.service.loans.domain.common.HomeLoanEligibilityCriteria;

public interface LoanEligibilityCriteriaRepository {

	public HomeLoanEligibilityCriteria getHomeLoanBySalarySlab(Long income, Integer type, Integer bankId);

	public HomeLoanEligibilityCriteria getHomeLoanBySVMV(Long sv, Long mv, Integer bankId);

	public Object[] getMinMaxRoiForHomeLoan();
}
