package com.capitaworld.service.loans.repository.fundseeker.ddr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRFormDetails;

public interface DDRFormDetailsRepository extends JpaRepository<DDRFormDetails, Long>{

	@Query("select ddr from DDRFormDetails ddr where ddr.id =:id and ddr.isActive = true")
	public DDRFormDetails getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select ddr from DDRFormDetails ddr where ddr.id =:id and ddr.applicationId =:appId and ddr.isActive = true")
	public DDRFormDetails getByIdAndAppIdAndIsActive(@Param("id") Long id,@Param("appId") Long appId);
	
	@Query("select ddr from DDRFormDetails ddr where ddr.applicationId =:appId and ddr.isActive = true")
	public DDRFormDetails getByAppIdAndIsActive(@Param("appId") Long appId);
}
