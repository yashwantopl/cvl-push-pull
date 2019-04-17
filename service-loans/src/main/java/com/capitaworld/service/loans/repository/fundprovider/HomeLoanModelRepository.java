package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundprovider.HomeLoanModel;

public interface HomeLoanModelRepository extends JpaRepository<HomeLoanModel, Long> {
	
	public HomeLoanModel findById(Long id);
	
}
