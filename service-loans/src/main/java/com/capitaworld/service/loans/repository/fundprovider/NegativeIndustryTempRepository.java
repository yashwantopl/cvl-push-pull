package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.NegativeIndustry;
import com.capitaworld.service.loans.domain.fundprovider.NegativeIndustryTemp;

public interface NegativeIndustryTempRepository extends JpaRepository<NegativeIndustryTemp, Long>{
	
	@Modifying
	@Query("update NegativeIndustryTemp n set n.isActive = false where n.fpProductMasterId =:fpProductMasterId and n.isActive = true")
	public int inActiveMappingByFpProductMasterId(@Param("fpProductMasterId") Long fpProductMasterId);
	
	@Query("select n.industryId from NegativeIndustryTemp n where n.fpProductMasterId = :fpProductMasterId and n.isActive = true")
	public List<Long> getIndustryByFpProductMasterId(@Param("fpProductMasterId") Long fpProductMasterId);
}
