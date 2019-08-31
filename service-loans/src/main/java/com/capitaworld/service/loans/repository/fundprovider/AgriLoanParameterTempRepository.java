package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundprovider.AgriLoanParameterTemp;

public interface AgriLoanParameterTempRepository extends JpaRepository<AgriLoanParameterTemp, Long> {
	
	public AgriLoanParameterTemp findById(Long id);
	
}
