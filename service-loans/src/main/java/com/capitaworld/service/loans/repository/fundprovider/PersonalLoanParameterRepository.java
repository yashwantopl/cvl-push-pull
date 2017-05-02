package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.PersonalLoanParameter;

public interface PersonalLoanParameterRepository extends JpaRepository<PersonalLoanParameter, Long>{
	@Query("from PersonalLoanParameter pp where pp.fpProductId.id =:id and isActive=true")
	public PersonalLoanParameter getByID(@Param("id") Long id);
}
