package com.opl.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundprovider.FpRatingValueMapping;

public interface FpRatingAgencyRepository extends JpaRepository<FpRatingValueMapping, Long>{
	@Modifying
	@Query("update FpRatingValueMapping gc set gc.isActive = false where gc.fpProductId =:fpProductId and gc.isActive = true")
	public int inActiveEmpWithByFpProductId(@Param("fpProductId") Long fpProductMaster);
	
	@Query("from FpRatingValueMapping o where o.fpProductId = :fpProductId and o.isActive = true")
	public List<FpRatingValueMapping> getEmpWithByFpProductId(@Param("fpProductId")Long fpProductMaster);
}
