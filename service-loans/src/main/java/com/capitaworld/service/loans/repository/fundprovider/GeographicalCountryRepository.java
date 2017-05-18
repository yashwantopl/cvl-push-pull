package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetail;

public interface GeographicalCountryRepository extends JpaRepository<GeographicalCountryDetail, Long>{
	@Modifying
	@Query("update GeographicalCountryDetail gc set gc.isActive = false where gc.fpProductMaster =:fpProductMaster and gc.isActive = true")
	public int inActiveMappingByFpProductId(@Param("fpProductMaster") Long fpProductMaster);
	
	@Query("select o.countryId from GeographicalCountryDetail o where o.fpProductMaster = :fpProductMaster and o.isActive = true")
	public List<Long> getCountryByFpProductId(@Param("fpProductMaster")Long fpProductMaster);
}
