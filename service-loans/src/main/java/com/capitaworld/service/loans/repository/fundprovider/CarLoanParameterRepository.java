package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.CarLoanParameter;

public interface CarLoanParameterRepository extends JpaRepository<CarLoanParameter, Long>{
	@Query("from CarLoanParameter crp where crp.fpProductId.id =:id and isActive=true")
	public CarLoanParameter getByID(@Param("id") Long id);
}
