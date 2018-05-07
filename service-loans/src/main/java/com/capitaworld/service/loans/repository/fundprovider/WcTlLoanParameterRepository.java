package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.WcTlParameter;

public interface WcTlLoanParameterRepository extends JpaRepository<WcTlParameter, Long>{
	@Query("from WcTlParameter tp where tp.fpProductId.id =:id ")
	public WcTlParameter getById(@Param("id") Long id);
	
	@Query("from WcTlParameter tp where tp.userId =:userId and isActive=true")
	public List<WcTlParameter> getByActiveUserId(@Param("userId") Long userId);
}
