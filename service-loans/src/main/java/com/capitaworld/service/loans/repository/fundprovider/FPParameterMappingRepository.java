package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.domain.fundprovider.FPParameterMapping;

@Repository
public interface FPParameterMappingRepository extends JpaRepository<FPParameterMapping, Long>{

	@Modifying
	@Query("update FPParameterMapping fpm set fpm.isActive = false where fpm.fpProductMappingId = :fpMappingId and fpm.type =:type and fpm.isActive = true")
	public int inactiveMapping(@Param("fpMappingId") Long fpMappingId,@Param("type") Integer type);
	
	@Query("select fpm.parameterId from FPParameterMapping fpm where fpm.fpProductMappingId = :fpMappingId and fpm.type =:type and fpm.isActive = true")
	public List<Integer> getParametersByFpProductIdAndType(@Param("fpMappingId") Long fpMappingId,@Param("type") Integer type);
}
