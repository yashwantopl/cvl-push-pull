package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.SubsectorDetail;

public interface SubSectorRepository extends JpaRepository<SubsectorDetail, Long>{
	
	@Modifying
	@Query("update SubsectorDetail sd set sd.isActive = false where sd.applicationId =:applicationId and sd.isActive = true")
	public int inActiveMappingByApplicationId(@Param("applicationId") Long applicationId);
	
	public List<Long> findBySectorSubsectorTransactionIdAndApplicationIdAndIsActive(Long sectorSubsectorTransactionId,Long applicationId, Boolean isActive);
	
	@Query("select sd.sectorSubsectorTransactionId from SubsectorDetail sd where sd.applicationId =:applicationId and sd.isActive = true")
	public List<Long> getSubSectorByApplicationId(@Param("applicationId") Long applicationId);

}
