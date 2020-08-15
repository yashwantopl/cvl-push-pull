package com.opl.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.fundprovider.HomeLoanParameter;

public interface HomeLoanParameterRepository extends JpaRepository<HomeLoanParameter, Long> {
	
	public HomeLoanParameter findById(Long id);
	
}
