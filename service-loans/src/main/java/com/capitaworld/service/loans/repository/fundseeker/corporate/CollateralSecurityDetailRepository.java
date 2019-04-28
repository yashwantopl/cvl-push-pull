package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CollateralSecurityDetail;

@Repository
public interface CollateralSecurityDetailRepository extends JpaRepository<CollateralSecurityDetail, Long>{

	List<CollateralSecurityDetail> findByApplicationIdAndIsActive(Long applicationId, Boolean isActive);
	
	@Modifying
	@Query("update CollateralSecurityDetail pm set pm.isActive = false, pm.modifiedDate = NOW(), pm.modifiedBy =:userId where pm.applicationId =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);
}
