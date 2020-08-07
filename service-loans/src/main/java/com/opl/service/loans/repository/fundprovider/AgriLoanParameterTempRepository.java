package com.opl.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.fundprovider.AgriLoanParameterTemp;

public interface AgriLoanParameterTempRepository extends JpaRepository<AgriLoanParameterTemp, Long> {
	
	public AgriLoanParameterTemp findById(Long id);
	
}
