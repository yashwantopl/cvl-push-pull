package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.EmpWithMappingDetail;
import com.capitaworld.service.loans.domain.fundprovider.SalaryModeDetail;

public interface FpSalaryModeRepository extends JpaRepository<SalaryModeDetail, Long>{
	@Modifying
	@Query("update SalaryModeDetail gc set gc.isActive = false where gc.fpProductId =:fpProductId and gc.isActive = true")
	public int inActiveSalaryModeByFpProductId(@Param("fpProductId") Long fpProductMaster);
	
	@Query("select o.salaryModeId from SalaryModeDetail o where o.fpProductId = :fpProductId and o.isActive = true")
	public List<Integer> getSalaryModeByFpProductId(@Param("fpProductId")Long fpProductMaster);
}
