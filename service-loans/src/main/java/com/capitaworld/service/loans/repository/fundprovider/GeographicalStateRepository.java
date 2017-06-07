package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetail;

public interface GeographicalStateRepository extends JpaRepository<GeographicalStateDetail, Long>{
	@Modifying
	@Query("update GeographicalStateDetail gc set gc.isActive = false where gc.fpProductMaster =:fpProductMaster and gc.isActive = true")
	public int inActiveMappingByFpProductId(@Param("fpProductMaster") Long fpProductMaster);
	
	@Query("select o.stateId from GeographicalStateDetail o where o.fpProductMaster = :fpProductMaster and o.isActive = true")
	public List<Long> getStateByFpProductId(@Param("fpProductMaster")Long fpProductMaster);
}
