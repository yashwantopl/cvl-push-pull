package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.HomeLoanParameter;

public interface HomeLoanParameterRepository extends JpaRepository<HomeLoanParameter, Long>{
	@Query("from HomeLoanParameter hlp where hlp.fpProductId.id =:id")
	public HomeLoanParameter getByID(@Param("id") Long id);
}
