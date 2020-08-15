package com.opl.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.fundprovider.AgriLoanParameter;

public interface AgriLoanParameterRepository extends JpaRepository<AgriLoanParameter, Long> {
	
	public AgriLoanParameter findById(Long id);
	
}
