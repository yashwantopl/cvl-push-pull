package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundprovider.AutoLoanParameter;

public interface AutoLoanParameterRepository extends JpaRepository<AutoLoanParameter, Long> {
	public AutoLoanParameter findById(Long id);
}
