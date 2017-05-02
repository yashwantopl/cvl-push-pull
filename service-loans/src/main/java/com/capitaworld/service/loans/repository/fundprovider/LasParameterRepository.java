package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.LasParameter;

public interface LasParameterRepository extends JpaRepository<LasParameter, Long>{
	@Query("from LasParameter lsp where lsp.fpProductId.id =:id and isActive=true")
	public LasParameter getByID(@Param("id") Long id);
}
