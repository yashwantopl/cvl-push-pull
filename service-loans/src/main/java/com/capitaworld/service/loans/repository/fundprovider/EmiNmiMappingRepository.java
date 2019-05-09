package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.EmiNmiMapping;

public interface EmiNmiMappingRepository extends JpaRepository<EmiNmiMapping, Long>{

	@Modifying
	@Query("update EmiNmiMapping enm set enm.isActive = false where enm.fpProductId =:fpProductId and enm.isActive = true")
	int inActiveByFpProductId(@Param("fpProductId") Long fpProductMaster);
	
	@Query("from EmiNmiMapping enm where enm.fpProductId =:fpProductId and enm.isActive = true")
	List<EmiNmiMapping> getByFpProductId(@Param("fpProductId") Long fpProductMaster);
	
}
