package com.opl.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundprovider.FSParameterMapping;

public interface FSParameterMappingRepository extends JpaRepository<FSParameterMapping, Long> {

	@Modifying
	@Query("update FSParameterMapping p set p.isActive = false where p.applicationId =:applicationId and p.type =:type and p.isActive = true")
	public int inactiveMapping(@Param("applicationId") Long fpMappingId, @Param("type") Integer type);
	
	@Query("select p.parameterId from FSParameterMapping p where p.applicationId =:applicationId  and p.type =:type and p.isActive = true")
	public List<Integer> getParametersByApplicationIdAndType(@Param("applicationId") Long fpMappingId,@Param("type") Integer type);
	
}
