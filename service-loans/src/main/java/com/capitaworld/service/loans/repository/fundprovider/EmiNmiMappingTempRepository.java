package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.EmiNmiMappingTemp;

public interface EmiNmiMappingTempRepository extends JpaRepository<EmiNmiMappingTemp, Long>{

	@Modifying
	@Query("update EmiNmiMappingTemp enm set enm.isActive = false where enm.fpProductId =:fpProductId and enm.isActive = true")
	int inActiveByFpProductId(@Param("fpProductId") Long fpProductMaster);
	
	@Query("from EmiNmiMappingTemp enm where enm.fpProductId =:fpProductId and enm.isActive = true")
	List<EmiNmiMappingTemp> getByFpProductId(@Param("fpProductId") Long fpProductMaster);
	
}
