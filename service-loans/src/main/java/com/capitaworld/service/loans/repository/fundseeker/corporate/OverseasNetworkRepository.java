package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OverseasNetworkMappingDetail;

public interface OverseasNetworkRepository extends JpaRepository<OverseasNetworkMappingDetail, Long>{
	
	@Modifying
	@Query("update OverseasNetworkMappingDetail sd set sd.isActive = false where sd.applicationId =:applicationId and sd.isActive = true")
	public int inActiveMappingByApplicationId(@Param("applicationId") Long applicationId);

}
