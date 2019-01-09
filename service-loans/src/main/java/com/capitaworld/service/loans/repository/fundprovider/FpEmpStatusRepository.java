package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.EmpStatusMappingDetail;

public interface FpEmpStatusRepository extends JpaRepository<EmpStatusMappingDetail, Long>{
	@Modifying
	@Query("update EmpStatusMappingDetail gc set gc.isActive = false where gc.fpProductId =:fpProductId and gc.isActive = true")
	public int inActiveEmpStatusByFpProductId(@Param("fpProductId") Long fpProductMaster);
	
	@Query("select o.empStatusId from EmpStatusMappingDetail o where o.fpProductId = :fpProductId and o.isActive = true")
	public List<Integer> getEmpStatusByFpProductId(@Param("fpProductId")Long fpProductMaster);
}
