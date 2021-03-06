package com.opl.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundprovider.EmpStatusMappingDetailTemp;

public interface FpEmpStatusTempRepository extends JpaRepository<EmpStatusMappingDetailTemp, Long>{
	@Modifying
	@Query("update EmpStatusMappingDetailTemp gc set gc.isActive = false where gc.fpProductId =:fpProductId and gc.isActive = true")
	public int inActiveEmpStatusTempByFpProductId(@Param("fpProductId") Long fpProductMaster);
	
	@Query("select o.empStatusId from EmpStatusMappingDetailTemp o where o.fpProductId = :fpProductId and o.isActive = true")
	public List<Integer> getEmpStatusTempByFpProductId(@Param("fpProductId")Long fpProductMaster);
}
