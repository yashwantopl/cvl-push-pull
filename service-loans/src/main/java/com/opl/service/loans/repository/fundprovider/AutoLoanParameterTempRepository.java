package com.opl.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.fundprovider.AutoLoanParameterTemp;

public interface AutoLoanParameterTempRepository extends JpaRepository<AutoLoanParameterTemp , Long> {
	
	public AutoLoanParameterTemp findById(Long id);
	
}
