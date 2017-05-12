package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameter;

public interface WorkingCapitalParameterRepository extends JpaRepository<WorkingCapitalParameter, Long>{
	@Query("from WorkingCapitalParameter wp where wp.fpProductId.id =:id and isActive=true")
	public WorkingCapitalParameter getByID(@Param("id") Long id);
}
