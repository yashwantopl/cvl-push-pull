package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.RequirementsAndAvailabilityRawMaterialsDetail;
import com.capitaworld.service.loans.model.teaser.finalview.RequirementsAndAvailabilityRawMaterialsDetailResponse;

/**
 * @author Sanket
 *
 */
public interface RequirementsAndAvailabilityRawMaterialsDetailRepository extends JpaRepository<RequirementsAndAvailabilityRawMaterialsDetail, Long>{

	@Modifying
	@Transactional
	@Query("update RequirementsAndAvailabilityRawMaterialsDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveRequirementsAndAvailabilityRawMaterialsDetails(@Param("sId")Long storageDetailsId);
	
	@Query("select new com.capitaworld.service.loans.model.teaser.finalview.RequirementsAndAvailabilityRawMaterialsDetailResponse(a.availability, a.leadTime, a.measurementUnitQuantity, a.name, a.quality, a.sources) from RequirementsAndAvailabilityRawMaterialsDetail a where a.applicationId.id= :applicationId and isActive=true")
	 public List<RequirementsAndAvailabilityRawMaterialsDetailResponse> listByApplicationId(@Param("applicationId")Long applicationId);

}
