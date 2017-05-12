package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;

public interface TermLoanParameterRepository extends JpaRepository<TermLoanParameter, Long>{
	@Query("from TermLoanParameter tp where tp.fpProductId.id =:id and isActive=true")
	public TermLoanParameter getByID(@Param("id") Long id);
}
