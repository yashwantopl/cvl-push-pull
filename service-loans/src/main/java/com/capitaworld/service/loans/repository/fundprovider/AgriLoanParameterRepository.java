package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundprovider.AgriLoanParameter;

public interface AgriLoanParameterRepository extends JpaRepository<AgriLoanParameter, Long> {
	
	public AgriLoanParameter findById(Long id);
	
}
