package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.model.teaser.finalview.RequirementsAndAvailabilityRawMaterialsDetailResponse;
import com.opl.service.loans.domain.fundseeker.corporate.RequirementsAndAvailabilityRawMaterialsDetail;

/**
 * @author Sanket
 *
 */
public interface RequirementsAndAvailabilityRawMaterialsDetailRepository extends JpaRepository<RequirementsAndAvailabilityRawMaterialsDetail, Long>{

	@Modifying
	@Transactional
	@Query("update RequirementsAndAvailabilityRawMaterialsDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveRequirementsAndAvailabilityRawMaterialsDetails(@Param("sId")Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update RequirementsAndAvailabilityRawMaterialsDetail a set a.isActive = false where a.applicationId.id= :applicationId and a.isActive=true")
	public void inActiveRequirementsAndAvailabilityRawMaterialsDetailsByAppId(@Param("applicationId")Long applicationId);
	
	@Query("select new com.opl.mudra.api.loans.model.teaser.finalview.RequirementsAndAvailabilityRawMaterialsDetailResponse(a.availability, a.leadTime, a.measurementUnitQuantity, a.name, a.quality, a.sources) from RequirementsAndAvailabilityRawMaterialsDetail a where a.applicationId.id= :applicationId and isActive=true")
	 public List<RequirementsAndAvailabilityRawMaterialsDetailResponse> listByApplicationId(@Param("applicationId")Long applicationId);
	
	public List<RequirementsAndAvailabilityRawMaterialsDetail> findByApplicationIdIdAndIsActive(Long applicationId, Boolean isActive);
	
	

}
