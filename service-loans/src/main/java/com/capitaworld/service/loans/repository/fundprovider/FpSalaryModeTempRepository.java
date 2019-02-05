package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.EmpWithMappingDetail;
import com.capitaworld.service.loans.domain.fundprovider.SalaryModeDetail;
import com.capitaworld.service.loans.domain.fundprovider.SalaryModeDetailTemp;

public interface FpSalaryModeTempRepository extends JpaRepository<SalaryModeDetailTemp, Long>{
	@Modifying
	@Query("update SalaryModeDetailTemp gc set gc.isActive = false where gc.fpProductId =:fpProductId and gc.isActive = true")
	public int inActiveSalaryModeByFpProductId(@Param("fpProductId") Long fpProductMaster);
	
	@Query("select o.salaryModeId from SalaryModeDetailTemp o where o.fpProductId = :fpProductId and o.isActive = true")
	public List<Integer> getSalaryModeByFpProductId(@Param("fpProductId")Long fpProductMaster);
}
