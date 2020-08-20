package com.opl.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.fundprovider.HomeLoanParameterTemp;

public interface HomeLoanParameterTempRepository extends JpaRepository<HomeLoanParameterTemp, Long> {
	
	public HomeLoanParameterTemp findById(Long id);
	
}
