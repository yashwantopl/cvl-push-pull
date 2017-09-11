package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;

public interface TermLoanParameterRepository extends JpaRepository<TermLoanParameter, Long>{
	@Query("from TermLoanParameter tp where tp.fpProductId.id =:id ")
	public TermLoanParameter getById(@Param("id") Long id);
	
	@Query("from TermLoanParameter tp where tp.userId =:userId and isActive=true")
	public List<TermLoanParameter> getByActiveUserId(@Param("userId") Long userId);
}
