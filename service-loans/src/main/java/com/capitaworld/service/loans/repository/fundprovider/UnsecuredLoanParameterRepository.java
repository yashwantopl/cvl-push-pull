package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.UnsecureLoanParameter;

public interface UnsecuredLoanParameterRepository extends JpaRepository<UnsecureLoanParameter, Long>{
	@Query("from UnsecureLoanParameter usp where usp.fpProductId.id =:id ")
	public UnsecureLoanParameter getById(@Param("id") Long id);
	
	@Query("from UnsecureLoanParameter usp where usp.userId =:userId and isActive=true")
	public List<UnsecureLoanParameter> getByActiveUserId(@Param("userId") Long userId);
}
